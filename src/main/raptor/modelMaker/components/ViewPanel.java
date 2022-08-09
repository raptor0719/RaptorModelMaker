package raptor.modelMaker.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;
import raptor.modelMaker.model.ViewDirection;
import raptor.modelMaker.render.RenderUtility;
import raptor.modelMaker.spriteLibrary.DirectionalSprite;
import raptor.modelMaker.spriteLibrary.Sprite;
import raptor.modelMaker.spriteLibrary.SpriteCollection;
import raptor.modelMaker.spriteLibrary.SpriteLibrary;

public class ViewPanel extends JPanel {
	private static final Point2D ORIGIN;
	static {
		ORIGIN = new Point2D(0, 0);
	}

	private Model model;
	private SpriteLibrary spriteLibrary;
	private int pointDrawDiameter;

	private final JButton[] directionalButtons;
	private int directionIndex;

	private Hardpoint selected;

	private boolean renderPoints;
	private boolean renderImages;
	private boolean renderDimensions;

	public ViewPanel() {
		super(null);

		this.model = null;
		this.spriteLibrary = null;

		this.pointDrawDiameter = 10;

		this.addMouseListener(new ViewPanelFocus(this));

		final Font directionalButtonFont = new Font("Arial", Font.BOLD, 10);
		final Insets directionalButtonInsets = new Insets(0, 0, 0, 0);
		final Point2D[] directionalButtonLocations = new Point2D[] {
			new Point2D(25, 25),
			new Point2D(50, 25)
		};
		directionalButtons = new JButton[ViewDirection.values().length];

		for (int i = 0; i < directionalButtons.length; i++) {
			final JButton button = new SetViewDirectionButton(ViewDirection.values()[i], this, directionalButtons);
			button.setBackground(Color.WHITE);
			button.setFont(directionalButtonFont);
			button.setMargin(directionalButtonInsets);
			button.setRolloverEnabled(false);

			final Point2D location = directionalButtonLocations[i];
			button.setBounds(location.getX(), location.getY(), 25, 25);

			directionalButtons[i] = button;
			this.add(button);
		}

		this.directionIndex = 1;
		directionalButtons[directionIndex].doClick();

		this.selected = null;

		this.renderPoints = true;
		this.renderImages = true;
		this.renderDimensions = false;
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;

		final int panelWidth = this.getWidth();
		final int panelHeight = this.getHeight();

		g2.clearRect(0, 0, panelWidth, panelHeight);

		if (model == null)
			return;

		// Calculate plane origin on the viewport
		final int planeOriginXOnViewport = panelWidth / 2;
		final int planeOriginYOnViewport = panelHeight / 2;

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));

		for (final Hardpoint hardpoint : orderHardpointsToDisplayOrder(model.getHardpoints())) {
			final Point2D translated = toDrawPoint(hardpoint.getPoint(), planeOriginXOnViewport, planeOriginYOnViewport, ViewDirection.values()[directionIndex]);

			if (renderImages && spriteLibrary != null) {
				final SpriteCollection attachedSpriteCollection = spriteLibrary.getSpriteCollection(hardpoint.getSpriteCollectionName());

				if (attachedSpriteCollection != null) {
					final DirectionalSprite directionalSprite = attachedSpriteCollection.getSprite(hardpoint.getSpritePhase());

					if (directionalSprite != null) {
						if (directionalSprite.getSprite(getCurrentViewDirection()).getImage() != null) {
							final int rotation = (directionIndex == ViewDirection.LEFT.ordinal()) ? -hardpoint.getRotation() : hardpoint.getRotation();
							final Sprite translatedSprite = RenderUtility.translateSprite(directionalSprite.getSprite(getCurrentViewDirection()), rotation);

							final BufferedImage image = translatedSprite.getImage();
							final Point2D attachmentPoint = translatedSprite.getAttachmentPoint();

							g2.drawImage(image, translated.getX() - attachmentPoint.getX(), translated.getY() - attachmentPoint.getY(), image.getWidth(), image.getHeight(), null);
						}
					}
				}
			}

			if (renderPoints) {
				final int centerTransform = pointDrawDiameter/2;

				if (hardpoint.equals(selected)) {
					g2.setColor(Color.GREEN);
					g2.fillOval(translated.getX() - centerTransform, translated.getY() - centerTransform, pointDrawDiameter, pointDrawDiameter);
					g2.setColor(Color.BLACK);
				} else {
					g2.fillOval(translated.getX() - centerTransform, translated.getY() - centerTransform, pointDrawDiameter, pointDrawDiameter);
				}
			}
		}

		if (renderDimensions) {
			final int xStart = planeOriginXOnViewport - model.getWidth()/2 + model.getCenterOffsetX();
			final int yStart = planeOriginYOnViewport - model.getCenterOffsetY();

			g2.drawRect(xStart, yStart, model.getWidth(), model.getHeight());
		}
	}

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
		this.repaint();
	}

	public void setSpriteLibrary(final SpriteLibrary spriteLibrary) {
		this.spriteLibrary = spriteLibrary;
		this.repaint();
	}

	public void rotateX(final boolean otherWay) {
		final int delta = 1;

		directionIndex = ((otherWay) ? directionIndex - delta : directionIndex + delta) % ViewDirection.values().length;

		if (directionIndex < 0)
			directionIndex = ViewDirection.values().length + directionIndex;

		directionalButtons[directionIndex].doClick();
	}

	public void setDirection(final ViewDirection direction) {
		directionIndex = direction.ordinal();
		repaint();
	}

	public int select(final int mouseX, final int mouseY) {
		if (model == null)
			return -1;

		final int panelWidth = this.getWidth();
		final int panelHeight = this.getHeight();
		final int planeOriginXOnViewport = panelWidth / 2;
		final int planeOriginYOnViewport = panelHeight / 2;

		final int centerTransform = pointDrawDiameter/2;

		int index = 0;
		for (final Hardpoint h : model.getHardpoints()) {
			final Point2D hitbox = toDrawPoint(h.getPoint(), planeOriginXOnViewport, planeOriginYOnViewport, ViewDirection.values()[directionIndex]);

			if (mouseX >= hitbox.getX() - centerTransform && mouseX <= hitbox.getX() - centerTransform + pointDrawDiameter &&
					mouseY >= hitbox.getY() - centerTransform && mouseY <= hitbox.getY() + pointDrawDiameter - centerTransform) {
				selected = h;
				return index;
			}

			index++;
		}

		return -1;
	}

	public void unselect() {
		selected = null;
	}

	public void setSelected(final Hardpoint hardpoint) {
		this.selected = hardpoint;
	}

	public Hardpoint getSelected() {
		return selected;
	}

	public boolean renderPoints() {
		return renderPoints;
	}

	public void setRenderPoints(final boolean render) {
		this.renderPoints = render;
		repaint();
	}

	public boolean renderImages() {
		return renderImages;
	}

	public void setRenderImages(final boolean render) {
		this.renderImages = render;
		repaint();
	}

	public boolean renderDimensions() {
		return renderDimensions;
	}

	public void setRenderDimensions(final boolean render) {
		this.renderDimensions = render;
		repaint();
	}

	private Point2D toDrawPoint(final Point2D point, final int originX, final int originY, final ViewDirection viewDirection) {
		final boolean mirrored = viewDirection == ViewDirection.LEFT;
		return new Point2D(originX + ((mirrored) ? -point.getX() : point.getX()), originY - point.getY());
	}

	private List<Hardpoint> orderHardpointsToDisplayOrder(final List<Hardpoint> hardpoints) {
		final List<Hardpoint> sorted = new ArrayList<Hardpoint>();

		for (final Hardpoint h : hardpoints)
			sorted.add(h);

		sorted.sort(new HardpointDrawDepthComparator());

		return sorted;
	}

	private ViewDirection getCurrentViewDirection() {
		return ViewDirection.values()[directionIndex];
	}

	private static class HardpointDrawDepthComparator implements Comparator<Hardpoint> {
		@Override
		public int compare(final Hardpoint a, final Hardpoint b) {
			final int dA = a.getDrawDepth();
			final int dB = b.getDrawDepth();

			if (dA < dB)
				return -1;
			else if (dA == dB)
				return 0;
			else
				return 1;
		}
	}

	private static class SetViewDirectionButton extends JButton {
		public SetViewDirectionButton(final ViewDirection direction, final ViewPanel viewPanel, final JButton[] clearHighlight) {
			super(direction.getAbbreviation());

			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					for (final JButton button : clearHighlight)
						button.setBackground(Color.WHITE);

					setBackground(Color.GREEN);

					viewPanel.setDirection(direction);
					viewPanel.requestFocus();
				}
			});
		}
	}

	private static class ViewPanelFocus implements MouseListener {
		final JComponent component;

		public ViewPanelFocus(final JComponent component) {
			this.component = component;
		}

		@Override
		public void mouseClicked(final MouseEvent e) {
			component.requestFocus();
		}

		@Override
		public void mouseEntered(final MouseEvent e) {
			// No-op
		}

		@Override
		public void mouseExited(final MouseEvent e) {
			// No-op
		}

		@Override
		public void mousePressed(final MouseEvent e) {
			// No-op
		}

		@Override
		public void mouseReleased(final MouseEvent e) {
			// No-op
		}
	}
}

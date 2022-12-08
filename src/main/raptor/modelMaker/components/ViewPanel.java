package raptor.modelMaker.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;
import raptor.modelMaker.render.RenderUtility;
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

	private int rotation;

	private Hardpoint selected;

	private boolean renderPoints;
	private boolean renderImages;
	private boolean renderDimensions;

	public ViewPanel() {
		super(null);

		this.model = null;
		this.spriteLibrary = null;

		this.pointDrawDiameter = 10;

		this.rotation = 0;

		this.addMouseListener(new ViewPanelFocus(this));

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
			final Point2D translated = toDrawPoint(hardpoint.getPoint(), planeOriginXOnViewport, planeOriginYOnViewport, rotation);

			if (renderImages && spriteLibrary != null) {
				final SpriteCollection attachedSpriteCollection = spriteLibrary.getSpriteCollection(hardpoint.getSpriteCollectionName());

				if (attachedSpriteCollection != null) {
					final Sprite sprite = attachedSpriteCollection.getSprite(hardpoint.getSpritePhase());

					if (sprite != null) {
						if (sprite.getImage() != null) {
							final Sprite translatedSprite = RenderUtility.translateSprite(sprite, rotation + hardpoint.getRotation());

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

	public void rotateX(final int degrees, final boolean otherWay) {
		final int degreesWithSign = (otherWay) ? -degrees : degrees;

		this.rotation += degreesWithSign;
		this.repaint();
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
			final Point2D hitbox = toDrawPoint(h.getPoint(), planeOriginXOnViewport, planeOriginYOnViewport, rotation);

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

	public void setRotationToFacePoint(final int x, final int y) {
		final int panelWidth = this.getWidth();
		final int panelHeight = this.getHeight();
		final int planeOriginXOnViewport = panelWidth / 2;
		final int planeOriginYOnViewport = panelHeight / 2;

		final int normalX = 0;
		final int normalY = 1;

		final int mouseX = x - planeOriginXOnViewport;
		final int mouseY = y - planeOriginYOnViewport;

		final double radians = Point2D.getAngleBetweenAsVectors(new Point2D(normalX, normalY), new Point2D(mouseX, mouseY));

		this.rotation = (int)Math.toDegrees(radians) * ((mouseX < 0) ? 1 : -1);

		this.repaint();
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

	private Point2D toDrawPoint(final Point2D point, final int originX, final int originY, final int rotation) {
		final int translatedX = point.getX() + originX;
		final int translatedY = point.getY() + originY;
		return RenderUtility.rotatePoint(new Point2D(translatedX, translatedY), new Point2D(originX, originY), rotation);
	}

	private List<Hardpoint> orderHardpointsToDisplayOrder(final List<Hardpoint> hardpoints) {
		final List<Hardpoint> sorted = new ArrayList<Hardpoint>();

		for (final Hardpoint h : hardpoints)
			sorted.add(h);

		sorted.sort(new HardpointDrawDepthComparator());

		return sorted;
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

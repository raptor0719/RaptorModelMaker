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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.math.Vector;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;
import raptor.modelMaker.model.ViewDirection;
import raptor.modelMaker.render.RenderUtility;
import raptor.modelMaker.spriteLibrary.Sprite;
import raptor.modelMaker.spriteLibrary.SpriteCollection;
import raptor.modelMaker.spriteLibrary.SpriteLibrary;

public class ViewPanel extends JPanel {
	private static final Point ORIGIN;
	private static final int AXIS_MARKER_ENDPOINT_LENGTH;
	private static final Point[] AXIS_MARKER_ENDPOINTS;
	private static final Color[] AXIS_MARKER_COLORS;
	static {
		ORIGIN = new Point(0, 0, 0);
		AXIS_MARKER_ENDPOINT_LENGTH = 20;
		AXIS_MARKER_ENDPOINTS = new Point[] {
			new Point(AXIS_MARKER_ENDPOINT_LENGTH, 0, 0),
			new Point(0, AXIS_MARKER_ENDPOINT_LENGTH, 0),
			new Point(0, 0, AXIS_MARKER_ENDPOINT_LENGTH)
		};
		AXIS_MARKER_COLORS = new Color[] {
			Color.BLUE,
			Color.GREEN,
			Color.RED
		};
	}

	private Model model;
	private SpriteLibrary spriteLibrary;

	private Plane viewPlane;
	private int pointDrawDiameter;

	private final JButton[] directionalButtons;
	private int directionIndex;

	private Hardpoint selected;

	public ViewPanel(final Model startModel, final SpriteLibrary spriteLibrary) {
		super(null);

		this.model = startModel;
		this.spriteLibrary = spriteLibrary;

		this.viewPlane = new Plane();
		this.pointDrawDiameter = 10;

		this.addMouseListener(new ViewPanelFocus(this));

		final Font directionalButtonFont = new Font("Arial", Font.BOLD, 10);
		final Insets directionalButtonInsets = new Insets(0, 0, 0, 0);
		final Point2D[] directionalButtonLocations = new Point2D[] {
			new Point2D(50, 0),
			new Point2D(75, 25),
			new Point2D(100, 50),
			new Point2D(75, 75),
			new Point2D(50, 100),
			new Point2D(25, 75),
			new Point2D(0, 50),
			new Point2D(25, 25)
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

		this.directionIndex = 0;
		directionalButtons[directionIndex].doClick();

		this.selected = null;
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;

		// Calculate plane origin on the viewport
		final int panelWidth = this.getWidth();
		final int panelHeight = this.getHeight();
		final double planeOriginXOnViewport = panelWidth / 2;
		final double planeOriginYOnViewport = panelHeight / 2;

		g2.clearRect(0, 0, panelWidth, panelHeight);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));

		for (final Hardpoint hardpoint : model.getHardpoints()) {
			final Point2D translated = toDrawPoint(hardpoint.getPoint(), viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);

			if (spriteLibrary != null) {
				final SpriteCollection attachedSpriteCollection = spriteLibrary.getSpriteCollection(hardpoint.getSpriteCollectionName());

				if (attachedSpriteCollection != null) {
					final Sprite translatedSprite = RenderUtility.translateSprite(attachedSpriteCollection.getSprite(getCurrentViewDirection()), hardpoint.getRotation());

					final BufferedImage image = translatedSprite.getImage();
					final Point2D attachmentPoint = translatedSprite.getAttachmentPoint();

					g2.drawImage(image, translated.getX() - attachmentPoint.getX(), translated.getY() - attachmentPoint.getY(), image.getWidth(), image.getHeight(), null);
				}
			}

			final int centerTransform = pointDrawDiameter/2;

			if (hardpoint.equals(selected)) {
				g2.setColor(Color.GREEN);
				g2.fillOval(translated.getX() - centerTransform, translated.getY() - centerTransform, pointDrawDiameter, pointDrawDiameter);
				g2.setColor(Color.BLACK);
			} else {
				g2.fillOval(translated.getX() - centerTransform, translated.getY() - centerTransform, pointDrawDiameter, pointDrawDiameter);
			}
		}

		final Point2D origin = toDrawPoint(ORIGIN, viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);
		g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < AXIS_MARKER_ENDPOINTS.length; i++) {
			final Point2D endpoint = toDrawPoint(AXIS_MARKER_ENDPOINTS[i], viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);
			g2.setColor(AXIS_MARKER_COLORS[i]);
			g2.drawLine(origin.getX() + panelWidth/2 - AXIS_MARKER_ENDPOINT_LENGTH - 5, origin.getY() - panelHeight/2 + AXIS_MARKER_ENDPOINT_LENGTH + 5,
					endpoint.getX() + panelWidth/2 - AXIS_MARKER_ENDPOINT_LENGTH - 5, endpoint.getY() - panelHeight/2 + AXIS_MARKER_ENDPOINT_LENGTH + 5);
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
		directionIndex = ((otherWay) ? directionIndex - 1 : directionIndex + 1) % ViewDirection.values().length;

		if (directionIndex < 0)
			directionIndex = ViewDirection.values().length + directionIndex;

		directionalButtons[directionIndex].doClick();
	}

	public void setDirection(final ViewDirection direction) {
		directionIndex = direction.ordinal();
		viewPlane.set(direction.getParameters());
		repaint();
	}

	public Plane getViewPlane() {
		return viewPlane;
	}

	public int select(final int mouseX, final int mouseY) {
		final int panelWidth = this.getWidth();
		final int panelHeight = this.getHeight();
		final double planeOriginXOnViewport = panelWidth / 2;
		final double planeOriginYOnViewport = panelHeight / 2;

		int index = 0;
		for (final Hardpoint h : model.getHardpoints()) {
			final Point2D hitbox = toDrawPoint(h.getPoint(), viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);

			if (mouseX >= hitbox.getX() && mouseX <= hitbox.getX() + pointDrawDiameter && mouseY >= hitbox.getY() && mouseY <= hitbox.getY() + pointDrawDiameter) {
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

	private Point2D toDrawPoint(final Point point, final Plane viewPlane, final double planeOriginXOnViewport, final double planeOriginYOnViewport) {
		// Project onto the plane
		//  1) Create a vector V from Po to A
		//	2) Project V onto Pn to get Vp
		//	3) Subtract Vp from A to get Q
		//	4) Q is the result
		final Vector planeOriginToPoint = viewPlane.getOrigin().vectorTo(point);
		final Vector projectionOntoPlaneNormal = planeOriginToPoint.project(viewPlane.getNormal());
		final Point pointOnPlane = point.subtract(projectionOntoPlaneNormal);

		// Convert Point to Plane Coordinates
		//	1) Create a vector K from Po to Q
		//	2) Calculate dot(K, Py) = Dy
		//	3) Calculate dot(K, Px) = Dx
		//	4) Create 2D point T with coordinates x = Dx, y = Dy
		//	5) T is the result
		final Vector planeOriginToPlanePoint = viewPlane.getOrigin().vectorTo(pointOnPlane);
		final double xCoordinate = planeOriginToPlanePoint.dot(viewPlane.getxAxisNormal());
		final double yCoordinate = planeOriginToPlanePoint.dot(viewPlane.getyAxisNormal());

		// Translate to viewport coordinates
		//	1) Add the x coordinate of T to the x coordinate of the plane origin on the viewport
		//	2) Subtract the y coordinate of T to the y coordinate of the plane origin on the viewport
		// NOTE: We subtract the y coordinate because the direction of the y coordinate is reversed for the viewport
		final double xCoordinateOnViewport = planeOriginXOnViewport + xCoordinate;
		final double yCoordinateOnViewport = planeOriginYOnViewport - yCoordinate;

		return new Point2D((int)Math.round(xCoordinateOnViewport), (int)Math.round(yCoordinateOnViewport));
	}

	private ViewDirection getCurrentViewDirection() {
		return ViewDirection.values()[directionIndex];
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

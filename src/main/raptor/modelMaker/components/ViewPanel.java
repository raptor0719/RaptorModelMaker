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

public class ViewPanel extends JPanel {
	private static final Point ORIGIN;
	private static final Point[] AXIS_MARKER_ENDPOINTS;
	private static final Color[] AXIS_MARKER_COLORS;
	static {
		ORIGIN = new Point(0, 0, 0);
		AXIS_MARKER_ENDPOINTS = new Point[] {
			new Point(20, 0, 0),
			new Point(0, 20, 0),
			new Point(0, 0, 20)
		};
		AXIS_MARKER_COLORS = new Color[] {
			Color.BLUE,
			Color.GREEN,
			Color.RED
		};
	}

	private Model model;
	private Plane viewPlane;
	private int pointDrawDiameter;

	private final JButton[] directionalButtons;
	private int directionIndex;

	public ViewPanel(final Model startModel) {
		super(null);

		this.model = startModel;
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
			g2.fillOval(translated.getX(), translated.getY(), pointDrawDiameter, pointDrawDiameter);
		}

		final Point2D origin = toDrawPoint(ORIGIN, viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);
		g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < AXIS_MARKER_ENDPOINTS.length; i++) {
			final Point2D endpoint = toDrawPoint(AXIS_MARKER_ENDPOINTS[i], viewPlane, planeOriginXOnViewport, planeOriginYOnViewport);
			g2.setColor(AXIS_MARKER_COLORS[i]);
			g2.drawLine(origin.getX(), origin.getY(), endpoint.getX(), endpoint.getY());
		}
	}

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
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

		final double centerTransform = pointDrawDiameter / 2;

		return new Point2D((int)Math.round(xCoordinateOnViewport - centerTransform), (int)Math.round(yCoordinateOnViewport - centerTransform));
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

package raptor.modelMaker.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import raptor.modelMaker.math.Matrix;
import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.math.Vector;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

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

	private static final Plane NORTH_PARAMETERS;
	private static final Plane NORTHEAST_PARAMETERS;
	private static final Plane EAST_PARAMETERS;
	private static final Plane SOUTHEAST_PARAMETERS;
	private static final Plane SOUTH_PARAMETERS;
	private static final Plane SOUTHWEST_PARAMETERS;
	private static final Plane WEST_PARAMETERS;
	private static final Plane NORTHWEST_PARAMETERS;
	private static final Plane[] VIEW_PLANE_DIRECTION_PARAMETERS;
	static {
		final Plane viewPlaneStart = new Plane(new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));

		NORTH_PARAMETERS = buildCameraPlane(viewPlaneStart, 0, 45);
		NORTHEAST_PARAMETERS = buildCameraPlane(viewPlaneStart, 45, 45);
		EAST_PARAMETERS = buildCameraPlane(viewPlaneStart, 90, 45);
		SOUTHEAST_PARAMETERS = buildCameraPlane(viewPlaneStart, 135, 45);
		SOUTH_PARAMETERS = buildCameraPlane(viewPlaneStart, 180, 45);
		SOUTHWEST_PARAMETERS = buildCameraPlane(viewPlaneStart, 225, 45);
		WEST_PARAMETERS = buildCameraPlane(viewPlaneStart, 270, 45);
		NORTHWEST_PARAMETERS = buildCameraPlane(viewPlaneStart, 315, 45);

		VIEW_PLANE_DIRECTION_PARAMETERS = new Plane[] {
			NORTH_PARAMETERS,
			NORTHEAST_PARAMETERS,
			EAST_PARAMETERS,
			SOUTHEAST_PARAMETERS,
			SOUTH_PARAMETERS,
			SOUTHWEST_PARAMETERS,
			WEST_PARAMETERS,
			NORTHWEST_PARAMETERS
		};
	}

	private Model model;
	private Plane viewPlane;
	private int pointDrawDiameter;

	private int directionIndex;

	public ViewPanel(final Model startModel) {
		this.model = startModel;
		this.viewPlane = new Plane();
		this.pointDrawDiameter = 10;
		this.directionIndex = 0;

		this.addMouseListener(new ViewPanelFocus(this));

		viewPlane.set(VIEW_PLANE_DIRECTION_PARAMETERS[directionIndex]);

//		final JButton
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
		directionIndex = ((otherWay) ? directionIndex - 1 : directionIndex + 1) % VIEW_PLANE_DIRECTION_PARAMETERS.length;

		if (directionIndex < 0)
			directionIndex = VIEW_PLANE_DIRECTION_PARAMETERS.length + directionIndex;

		viewPlane.set(VIEW_PLANE_DIRECTION_PARAMETERS[directionIndex]);

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

	private static Plane buildCameraPlane(final Plane start, final int xDegrees, final int yDegrees) {
		final Plane newPlane = new Plane();
		newPlane.set(start);

		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getyAxisNormal(), xDegrees));
		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getxAxisNormal(), yDegrees));

		return newPlane;
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

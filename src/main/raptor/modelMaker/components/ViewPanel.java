package raptor.modelMaker.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import raptor.modelMaker.math.Matrix;
import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Vector;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class ViewPanel extends JPanel {
	private Model model;
	private Plane viewPlane;
	private int pointDrawDiameter;

	public ViewPanel(final Model startModel) {
		this.model = startModel;
		this.viewPlane = new Plane(new Point(50, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
		this.pointDrawDiameter = 10;

		this.addMouseListener(new ViewPanelFocus(this));
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

		for (final Hardpoint hardpoint : model.getHardpoints()) {
			// Project onto the plane
			//  1) Create a vector V from Po to A
			//	2) Project V onto Pn to get Vp
			//	3) Subtract Vp from A to get Q
			//	4) Q is the result
			final Point point = hardpoint.getPoint();
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

			g2.fillOval((int)Math.round(xCoordinateOnViewport - centerTransform), (int)Math.round(yCoordinateOnViewport - centerTransform), pointDrawDiameter, pointDrawDiameter);
		}
	}

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
	}

	public void rotateY(final double degrees) {
		viewPlane.transform(Matrix.getRotationYMatrix(degrees));
	}

	public void rotateZ(final double degrees) {
		viewPlane.transform(Matrix.getRotationZMatrix(degrees));
	}

	public Plane getViewPlane() {
		return viewPlane;
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

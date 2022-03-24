package raptor.modelMaker.model;

import raptor.modelMaker.math.Matrix;
import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Vector;

public enum ViewDirection {
	NORTH(buildCameraPlane(180, 45), "N", buildCameraPoint(-1, 0)),
	NORTHEAST(buildCameraPlane(135, 45), "NE", buildCameraPoint(-1, -1)),
	EAST(buildCameraPlane(90, 45), "E", buildCameraPoint(0, -1)),
	SOUTHEAST(buildCameraPlane(45, 45), "SE", buildCameraPoint(1, -1)),
	SOUTH(buildCameraPlane(0, 45), "S", buildCameraPoint(1, 0)),
	SOUTHWEST(buildCameraPlane(315, 45), "SW", buildCameraPoint(1, 1)),
	WEST(buildCameraPlane(270, 45), "W", buildCameraPoint(0, 1)),
	NORTHWEST(buildCameraPlane(225, 45), "NW", buildCameraPoint(-1, 1));

	private final Plane parameters;
	private final String abbreviation;
	private final Point cameraPoint;

	private ViewDirection(final Plane parameters, final String abbreviation, final Point cameraPoint) {
		this.parameters = parameters;
		this.abbreviation = abbreviation;
		this.cameraPoint = cameraPoint;
	}

	public Plane getParameters() {
		return parameters;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public Point getCameraPoint() {
		return cameraPoint;
	}

	@Override
	public String toString() {
		return this.name();
	}

	private static Plane buildCameraPlane(final int xDegrees, final int yDegrees) {
		final Plane newPlane = new Plane(new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));

		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getyAxisNormal(), xDegrees));
		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getxAxisNormal(), yDegrees));

		return newPlane;
	}

	private static Point buildCameraPoint(final int x, final int y) {
		final int xVal = getCameraPointValue(x);
		final int yVal = getCameraPointValue(y);

		return new Point(xVal, yVal, Integer.MAX_VALUE/2);
	}

	private static int getCameraPointValue(final int val) {
		if (val < 0)
			return Integer.MIN_VALUE/2;
		else if (val == 0)
			return 0;
		else
			return Integer.MAX_VALUE/2;
	}
}

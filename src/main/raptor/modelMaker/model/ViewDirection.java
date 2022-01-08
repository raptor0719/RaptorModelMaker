package raptor.modelMaker.model;

import raptor.modelMaker.math.Matrix;
import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Vector;

public enum ViewDirection {
	NORTH(buildCameraPlane(0, 45)),
	NORTHEAST(buildCameraPlane(45, 45)),
	EAST(buildCameraPlane(90, 45)),
	SOUTHEAST(buildCameraPlane(135, 45)),
	SOUTH(buildCameraPlane(180, 45)),
	SOUTHWEST(buildCameraPlane(225, 45)),
	WEST(buildCameraPlane(270, 45)),
	NORTHWEST(buildCameraPlane(315, 45));

	private final Plane parameters;

	private ViewDirection(final Plane parameters) {
		this.parameters = parameters;
	}

	public Plane getParameters() {
		return parameters;
	}

	private static Plane buildCameraPlane(final int xDegrees, final int yDegrees) {
		final Plane newPlane = new Plane(new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));

		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getyAxisNormal(), xDegrees));
		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getxAxisNormal(), yDegrees));

		return newPlane;
	}
}

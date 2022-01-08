package raptor.modelMaker.model;

import raptor.modelMaker.math.Matrix;
import raptor.modelMaker.math.Plane;
import raptor.modelMaker.math.Point;
import raptor.modelMaker.math.Vector;

public enum ViewDirection {
	NORTH(buildCameraPlane(180, 45), "N"),
	NORTHEAST(buildCameraPlane(135, 45), "NE"),
	EAST(buildCameraPlane(90, 45), "E"),
	SOUTHEAST(buildCameraPlane(45, 45), "SE"),
	SOUTH(buildCameraPlane(0, 45), "S"),
	SOUTHWEST(buildCameraPlane(315, 45), "SW"),
	WEST(buildCameraPlane(270, 45), "W"),
	NORTHWEST(buildCameraPlane(225, 45), "NW");

	private final Plane parameters;
	private final String abbreviation;

	private ViewDirection(final Plane parameters, final String abbreviation) {
		this.parameters = parameters;
		this.abbreviation = abbreviation;
	}

	public Plane getParameters() {
		return parameters;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	private static Plane buildCameraPlane(final int xDegrees, final int yDegrees) {
		final Plane newPlane = new Plane(new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));

		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getyAxisNormal(), xDegrees));
		newPlane.transform(Matrix.getRotationMatrixAroundAxis(newPlane.getxAxisNormal(), yDegrees));

		return newPlane;
	}
}

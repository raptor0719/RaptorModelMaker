package raptor.modelMaker.math;

public class Vector {
	private final double[] vector;

	public Vector() {
		this(0, 0, 0, 0);
	}

	public Vector(final double x, final double y, final double z, final double w) {
		this.vector = new double[] {x, y, z, w};
	}

	public double[] getRaw() {
		return vector;
	}

	public int size() {
		return vector.length;
	}

	public double get(final int index) {
		return vector[index];
	}

	public void set(final int index, final double val) {
		vector[index] = val;
	}

	public Vector transform(final Matrix transformMatrix) {
		final double[] resultRaw = this.getRaw();
		final double[][] transformRaw = transformMatrix.getRaw();

		resultRaw[0] = resultRaw[0]*transformRaw[0][0] +
				resultRaw[1]*transformRaw[1][0] +
				resultRaw[2]*transformRaw[2][0] +
				resultRaw[3]*transformRaw[3][0];
		resultRaw[1] = resultRaw[0]*transformRaw[0][1] +
				resultRaw[1]*transformRaw[1][1] +
				resultRaw[2]*transformRaw[2][1] +
				resultRaw[3]*transformRaw[3][1];
		resultRaw[2] = resultRaw[0]*transformRaw[0][2] +
				resultRaw[1]*transformRaw[1][2] +
				resultRaw[2]*transformRaw[2][2] +
				resultRaw[3]*transformRaw[3][2];
		resultRaw[3] = resultRaw[0]*transformRaw[0][3] +
				resultRaw[1]*transformRaw[1][3] +
				resultRaw[2]*transformRaw[2][3] +
				resultRaw[3]*transformRaw[3][3];

		return this;
	}

	public Vector unitVector() {
		return getUnitVector(this);
	}

	public Vector scale(final double scale) {
		return new Vector(get(0) * scale, get(1) * scale, get(2) * scale, get(3) * scale);
	}

	public double dot(final Vector v) {
		return get(0)*v.get(0) + get(1)*v.get(1) + get(2)*v.get(2) + get(3)*v.get(3);
	}

	public double magnitude() {
		return calculateMagnitude(this);
	}

	public Vector project(final Vector v) {
		final double scalarProjection = this.dot(v);
		final double magnitudeV = v.magnitude();

		return v.scale(scalarProjection / magnitudeV * magnitudeV);
	}

	/* INTERNAL */

	private static Vector getUnitVector(final Vector v) {
		final double magnitude = calculateMagnitude(v);

		if (magnitude == 0.0)
			return new Vector(0, 0, 0, 0);

		final double[] raw = v.getRaw();

		final double unitX = raw[0] / magnitude;
		final double unitY = raw[1] / magnitude;
		final double unitZ = raw[2] / magnitude;
		final double unitW = raw[3] / magnitude;

		return new Vector(unitX, unitY, unitZ, unitW);
	}

	private static double calculateMagnitude(final Vector v) {
		final double[] raw = v.getRaw();

		final double xSquared = raw[0] * raw[0];
		final double ySquared = raw[1] * raw[1];
		final double zSquared = raw[2] * raw[2];
		final double wSquared = raw[3] * raw[3];

		return Math.sqrt(xSquared + ySquared + zSquared + wSquared);
	}
}

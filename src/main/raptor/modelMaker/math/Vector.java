package raptor.modelMaker.math;

import java.util.Arrays;

public class Vector {
	private final double[] vector;

	public Vector() {
		this(0, 0, 0);
	}

	public Vector(final double x, final double y, final double z) {
		this.vector = new double[] {x, y, z};
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
				resultRaw[2]*transformRaw[2][0];
		resultRaw[1] = resultRaw[0]*transformRaw[0][1] +
				resultRaw[1]*transformRaw[1][1] +
				resultRaw[2]*transformRaw[2][1];
		resultRaw[2] = resultRaw[0]*transformRaw[0][2] +
				resultRaw[1]*transformRaw[1][2] +
				resultRaw[2]*transformRaw[2][2];

		return this;
	}

	public Vector unitVector() {
		return getUnitVector(this);
	}

	public Vector scale(final double scale) {
		return new Vector(get(0) * scale, get(1) * scale, get(2) * scale);
	}

	public double dot(final Vector v) {
		return get(0)*v.get(0) + get(1)*v.get(1) + get(2)*v.get(2);
	}

	public double magnitude() {
		return calculateMagnitude(this);
	}

	public Vector project(final Vector v) {
		final double scalarProjection = this.dot(v);
		final double magnitudeV = v.magnitude();

		return v.scale(scalarProjection / magnitudeV * magnitudeV);
	}

	@Override
	public String toString() {
		return String.format("Vector[x=%s, y=%s, z=%s]", vector[0], vector[1], vector[2]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(vector);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	/* INTERNAL */

	private static Vector getUnitVector(final Vector v) {
		final double magnitude = calculateMagnitude(v);

		if (magnitude == 0.0)
			return new Vector(0, 0, 0);

		final double[] raw = v.getRaw();

		final double unitX = raw[0] / magnitude;
		final double unitY = raw[1] / magnitude;
		final double unitZ = raw[2] / magnitude;

		return new Vector(unitX, unitY, unitZ);
	}

	private static double calculateMagnitude(final Vector v) {
		final double[] raw = v.getRaw();

		final double xSquared = raw[0] * raw[0];
		final double ySquared = raw[1] * raw[1];
		final double zSquared = raw[2] * raw[2];

		return Math.sqrt(xSquared + ySquared + zSquared);
	}
}

package raptor.modelMaker.math;

import java.util.Arrays;

public class Point {
	private double[] point;

	public Point() {
		this(0, 0, 0);
	}

	public Point(final double x, final double y, final double z) {
		this.point = new double[] {x, y, z};
	}

	public double[] getRaw() {
		return point;
	}

	public double get(final int index) {
		return point[index];
	}

	public void set(final int index, final double val) {
		point[index] = val;
	}

	public void set(final Point p) {
		for (int i = 0; i < point.length; i++)
			point[i] = p.get(i);
	}

	public Point subtract(final Point p) {
		return new Point(get(0) - p.get(0), get(1) - p.get(1), get(2) - p.get(2));
	}

	public Point subtract(final Vector v) {
		return new Point(get(0) - v.get(0), get(1) - v.get(1), get(2) - v.get(2));
	}

	public Point transform(final Matrix transformMatrix) {
		final double[] raw = this.getRaw();
		final double[][] transformRaw = transformMatrix.getRaw();

		final double[] result = new double[3];

		result[0] = raw[0]*transformRaw[0][0] +
				raw[1]*transformRaw[1][0] +
				raw[2]*transformRaw[2][0] +
				transformRaw[3][0];
		result[1] = raw[0]*transformRaw[0][1] +
				raw[1]*transformRaw[1][1] +
				raw[2]*transformRaw[2][1] +
				transformRaw[3][1];
		result[2] = raw[0]*transformRaw[0][2] +
				raw[1]*transformRaw[1][2] +
				raw[2]*transformRaw[2][2] +
				transformRaw[3][2];

		point = result;

		return this;
	}

	public Vector vectorTo(final Point point) {
		return new Vector(point.get(0) - get(0), point.get(1) - get(1), point.get(2) - get(2));
	}

	@Override
	public String toString() {
		return String.format("Point[x=%s, y=%s, z=%s]", point[0], point[1], point[2]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(point);
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
		Point other = (Point) obj;
		if (!Arrays.equals(point, other.point))
			return false;
		return true;
	}
}

package raptor.modelMaker.math;

import java.util.Arrays;

public class Point {
	private final double[] point;

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

	public Point subtract(final Point p) {
		return new Point(get(0) - p.get(0), get(1) - p.get(1), get(2) - p.get(2));
	}

	public Point subtract(final Vector v) {
		return new Point(get(0) - v.get(0), get(1) - v.get(1), get(2) - v.get(2));
	}

	public Point transform(final Matrix transformMatrix) {
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
		resultRaw[3] = resultRaw[0]*transformRaw[0][3] +
				resultRaw[1]*transformRaw[1][3] +
				resultRaw[2]*transformRaw[2][3];

		return this;
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

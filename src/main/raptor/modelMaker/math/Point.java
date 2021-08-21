package raptor.modelMaker.math;

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
}

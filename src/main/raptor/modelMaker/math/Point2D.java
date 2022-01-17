package raptor.modelMaker.math;

public class Point2D {
	private final int x;
	private final int y;

	private final double doubleX;
	private final double doubleY;

	public Point2D(final int x, final int y) {
		this.x = x;
		this.y = y;
		this.doubleX = x;
		this.doubleY = y;
	}

	public Point2D(final double x, final double y) {
		this.x = (int)x;
		this.y = (int)y;
		this.doubleX = x;
		this.doubleY = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getDoubleX() {
		return doubleX;
	}

	public double getDoubleY() {
		return doubleY;
	}
}

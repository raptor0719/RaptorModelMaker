package raptor.modelMaker.math;

public class Point2D {
	private int x;
	private int y;

	private double doubleX;
	private double doubleY;

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

	public void setX(final int x) {
		this.x = x;
		this.doubleX = x;
	}

	public void setY(final int y) {
		this.y = y;
		this.doubleY = y;
	}

	public void setX(final double x) {
		this.x = (int)x;
		this.doubleX = x;
	}

	public void setY(final double y) {
		this.y = (int)y;
		this.doubleY = y;
	}
}

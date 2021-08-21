package raptor.modelMaker.math;

import org.junit.Test;

public class PlaneTest {
	private static final Point START_ORIGIN = new Point(5, 0, 0);
	private static final Vector START_NORMAL = new Vector(-1, 0, 0);
	private static final Vector START_X = new Vector(0, 1, 0);
	private static final Vector START_Y = new Vector(0, 0, 1);

	private static final Point Y45_ORIGIN = new Point(3.5355339059, 0, -3.5355339059);
	private static final Vector Y45_NORMAL = new Vector(-0.7071067812, 0, 0.7071067812);
	private static final Vector Y45_X = new Vector(0, 1, 0);
	private static final Vector Y45_Y = new Vector(0.7071067812, 0, 0.7071067812);

	private static final Point Y90_ORIGIN = new Point(0, 0, -5);
	private static final Vector Y90_NORMAL = new Vector(0, 0, 1);
	private static final Vector Y90_X = new Vector(0, 1, 0);
	private static final Vector Y90_Y = new Vector(1, 0, 0);

	private static final Point Z45_ORIGIN = new Point(3.535533906, 3.535533906, 0);
	private static final Vector Z45_NORMAL = new Vector(-0.7071067812, -0.7071067812, 0);
	private static final Vector Z45_X = new Vector(-0.7071067812, 0.7071067812, 0);
	private static final Vector Z45_Y = new Vector(0, 0, 1);

	@Test
	public void rotation_YAxis45Degree() {
		final Plane start = new Plane(START_ORIGIN, START_NORMAL, START_X, START_Y);

		start.transform(Matrix.getRotationYMatrix(45));

		final Plane expected = new Plane(Y45_ORIGIN, Y45_NORMAL, Y45_X, Y45_Y);

		System.out.println(start);
		System.out.println(expected);
	}

	@Test
	public void rotation_YAxis90Degree() {
		final Plane start = new Plane(Y45_ORIGIN, Y45_NORMAL, Y45_X, Y45_Y);

		start.transform(Matrix.getRotationYMatrix(45));

		final Plane expected = new Plane(Y90_ORIGIN, Y90_NORMAL, Y90_X, Y90_Y);

		System.out.println(start);
		System.out.println(expected);
	}

	@Test
	public void rotation_ZAxis45Degree() {
		final Plane start = new Plane(START_ORIGIN, START_NORMAL, START_X, START_Y);

		start.transform(Matrix.getRotationZMatrix(45));

		final Plane expected = new Plane(Z45_ORIGIN, Z45_NORMAL, Z45_X, Z45_Y);

		System.out.println(start);
		System.out.println(expected);
	}
}

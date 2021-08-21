package raptor.modelMaker.math;

import java.util.Arrays;

public class Matrix {
	private final double[][] matrix;

	public Matrix() {
		this.matrix = new double[4][4];
		for (final double[] arr : matrix)
			Arrays.fill(arr, 0.0);
	}

	public double[][] getRaw() {
		return matrix;
	}

	public int width() {
		return matrix.length;
	}

	public int height() {
		return matrix[0].length;
	}

	public double get(final int x, final int y) {
		return matrix[x][y];
	}

	public void set(final int x, final int y, final double value) {
		matrix[x][y] = value;
	}

	public static Matrix getTranslationMatrix(final double tX, final double tY, final double tZ) {
		final Matrix translation = new Matrix();
		final double[][] translationRaw = translation.getRaw();

		translationRaw[0][0] = 1;
		translationRaw[0][1] = 0;
		translationRaw[0][2] = 0;
		translationRaw[0][3] = 0;

		translationRaw[1][0] = 0;
		translationRaw[1][1] = 1;
		translationRaw[1][2] = 0;
		translationRaw[1][3] = 0;

		translationRaw[2][0] = 0;
		translationRaw[2][1] = 0;
		translationRaw[2][2] = 1;
		translationRaw[2][3] = 0;

		translationRaw[3][0] = tX;
		translationRaw[3][1] = tY;
		translationRaw[3][2] = tZ;
		translationRaw[3][3] = 1;

		return translation;
	}

	public static Matrix getScalingMatrix(final double sX, final double sY, final double sZ) {
		final Matrix translation = new Matrix();
		final double[][] translationRaw = translation.getRaw();

		translationRaw[0][0] = sX;
		translationRaw[0][1] = 0;
		translationRaw[0][2] = 0;
		translationRaw[0][3] = 0;

		translationRaw[1][0] = 0;
		translationRaw[1][1] = sY;
		translationRaw[1][2] = 0;
		translationRaw[1][3] = 0;

		translationRaw[2][0] = 0;
		translationRaw[2][1] = 0;
		translationRaw[2][2] = sZ;
		translationRaw[2][3] = 0;

		translationRaw[3][0] = 0;
		translationRaw[3][1] = 0;
		translationRaw[3][2] = 0;
		translationRaw[3][3] = 1;

		return translation;
	}

	public static Matrix getRotationXMatrix(final double angle) {
		final Matrix translation = new Matrix();
		final double[][] translationRaw = translation.getRaw();

		final double radians = Math.toRadians(angle);

		translationRaw[0][0] = 1;
		translationRaw[0][1] = 0;
		translationRaw[0][2] = 0;
		translationRaw[0][3] = 0;

		translationRaw[1][0] = 0;
		translationRaw[1][1] = Math.cos(radians);
		translationRaw[1][2] = Math.sin(radians);
		translationRaw[1][3] = 0;

		translationRaw[2][0] = 0;
		translationRaw[2][1] = -Math.sin(radians);
		translationRaw[2][2] = Math.cos(radians);
		translationRaw[2][3] = 0;

		translationRaw[3][0] = 0;
		translationRaw[3][1] = 0;
		translationRaw[3][2] = 0;
		translationRaw[3][3] = 1;

		return translation;
	}

	public static Matrix getRotationYMatrix(final double angle) {
		final Matrix translation = new Matrix();
		final double[][] translationRaw = translation.getRaw();

		final double radians = Math.toRadians(angle);

		translationRaw[0][0] = Math.cos(radians);
		translationRaw[0][1] = 0;
		translationRaw[0][2] = -Math.sin(radians);
		translationRaw[0][3] = 0;

		translationRaw[1][0] = 0;
		translationRaw[1][1] = 1;
		translationRaw[1][2] = 0;
		translationRaw[1][3] = 0;

		translationRaw[2][0] = Math.sin(radians);
		translationRaw[2][1] = 0;
		translationRaw[2][2] = Math.cos(radians);
		translationRaw[2][3] = 0;

		translationRaw[3][0] = 0;
		translationRaw[3][1] = 0;
		translationRaw[3][2] = 0;
		translationRaw[3][3] = 1;

		return translation;
	}

	public static Matrix getRotationZMatrix(final double angle) {
		final Matrix translation = new Matrix();
		final double[][] translationRaw = translation.getRaw();

		final double radians = Math.toRadians(angle);

		translationRaw[0][0] = Math.cos(radians);
		translationRaw[0][1] = Math.sin(radians);
		translationRaw[0][2] = 0;
		translationRaw[0][3] = 0;

		translationRaw[1][0] = -Math.sin(radians);
		translationRaw[1][1] = Math.cos(radians);
		translationRaw[1][2] = 0;
		translationRaw[1][3] = 0;

		translationRaw[2][0] = 0;
		translationRaw[2][1] = 0;
		translationRaw[2][2] = 1;
		translationRaw[2][3] = 0;

		translationRaw[3][0] = 0;
		translationRaw[3][1] = 0;
		translationRaw[3][2] = 0;
		translationRaw[3][3] = 1;

		return translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matrix);
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
		Matrix other = (Matrix) obj;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		return true;
	}
}

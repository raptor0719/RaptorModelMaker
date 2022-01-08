package raptor.modelMaker.math;

public class Plane {
	private final Point origin;
	private final Vector normal;
	private final Vector xAxisNormal;
	private final Vector yAxisNormal;

	public Plane(final Point origin, final Vector direction, final Vector xAxis, final Vector yAxis) {
		this.origin = origin;
		this.normal = direction.unitVector();
		this.xAxisNormal = xAxis.unitVector();
		this.yAxisNormal = yAxis.unitVector();
	}

	public Plane() {
		this(new Point(), new Vector(), new Vector(), new Vector());
	}

	public Point getOrigin() {
		return origin;
	}

	public Vector getNormal() {
		return normal;
	}

	public Vector getxAxisNormal() {
		return xAxisNormal;
	}

	public Vector getyAxisNormal() {
		return yAxisNormal;
	}

	public Plane transform(final Matrix transform) {
		origin.transform(transform);
		normal.transform(transform);
		xAxisNormal.transform(transform);
		yAxisNormal.transform(transform);

		return this;
	}

	public void set(final Plane plane) {
		origin.set(plane.getOrigin());
		normal.set(plane.getNormal());
		xAxisNormal.set(plane.getxAxisNormal());
		yAxisNormal.set(plane.getyAxisNormal());
	}

	@Override
	public String toString() {
		return String.format("Plane[origin=%s, normal=%s, xAxisNormal=%s, yAxisNormal=%s]", origin.toString(), normal.toString(), xAxisNormal.toString(), yAxisNormal.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((xAxisNormal == null) ? 0 : xAxisNormal.hashCode());
		result = prime * result + ((yAxisNormal == null) ? 0 : yAxisNormal.hashCode());
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
		Plane other = (Plane) obj;
		if (normal == null) {
			if (other.normal != null)
				return false;
		} else if (!normal.equals(other.normal))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (xAxisNormal == null) {
			if (other.xAxisNormal != null)
				return false;
		} else if (!xAxisNormal.equals(other.xAxisNormal))
			return false;
		if (yAxisNormal == null) {
			if (other.yAxisNormal != null)
				return false;
		} else if (!yAxisNormal.equals(other.yAxisNormal))
			return false;
		return true;
	}
}

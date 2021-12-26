package raptor.modelMaker.model;

import raptor.modelMaker.math.Point;

public class Hardpoint {
	private final Point p;
	private int rotation;
	private String name;

	public Hardpoint(final String name, final int rotation) {
		this.p = new Point();
		this.rotation = normalizeRotation(rotation);
		this.name = name;
	}

	public Point getPoint() {
		return p;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(final int rotation) {
		this.rotation = normalizeRotation(rotation);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	private int normalizeRotation(final int rotation) {
		return (rotation >= 0) ? rotation % 360 : 360 - ((rotation * -1) % 360);
	}
}

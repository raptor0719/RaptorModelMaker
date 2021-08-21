package raptor.modelMaker.model;

import raptor.modelMaker.math.Point;

public class Hardpoint {
	private final Point p;
	private String name;

	public Hardpoint(final String name) {
		this.p = new Point();
		this.name = name;
	}

	public Point getPoint() {
		return p;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}

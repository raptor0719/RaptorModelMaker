package raptor.modelMaker.model;

import raptor.modelMaker.math.Point;

public class Hardpoint {
	private final Point p;
	private int northSouthRotation;
	private int eastWestRotation;
	private String name;
	private String spriteCollectionName;
	private String spritePhase;

	public Hardpoint(final String name, final int northSouthRotation, final int eastWestRotation, final String spriteCollectionName, final String spritePhase) {
		this.p = new Point();
		this.northSouthRotation = normalizeRotation(northSouthRotation);
		this.eastWestRotation = normalizeRotation(eastWestRotation);
		this.name = name;
		this.spriteCollectionName = spriteCollectionName;
		this.spritePhase = spritePhase;
	}

	public Point getPoint() {
		return p;
	}

	public int getNorthSouthRotation() {
		return northSouthRotation;
	}

	public void setNorthSouthRotation(final int rotation) {
		this.northSouthRotation = normalizeRotation(rotation);
	}

	public int getEastWestRotation() {
		return eastWestRotation;
	}

	public void setEastWestRotation(final int eastWestRotation) {
		this.eastWestRotation = eastWestRotation;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSpriteCollectionName() {
		return spriteCollectionName;
	}

	public void setSpriteCollectionName(final String spriteCollectionName) {
		this.spriteCollectionName = spriteCollectionName;
	}

	public String getSpritePhase() {
		return spritePhase;
	}

	public void setSpritePhase(final String spritePhase) {
		this.spritePhase = spritePhase;
	}

	private int normalizeRotation(final int rotation) {
		return (rotation >= 0) ? rotation % 360 : 360 - ((rotation * -1) % 360);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Hardpoint other = (Hardpoint) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}

		return true;
	}
}

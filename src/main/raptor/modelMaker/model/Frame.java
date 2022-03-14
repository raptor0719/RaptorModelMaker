package raptor.modelMaker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import raptor.modelMaker.math.Point;

public class Frame {
	private final HashMap<String, SavedHardpointPosition> savedPositions;
	private String name;

	public Frame(final String name) {
		this.savedPositions = new HashMap<String, SavedHardpointPosition>();
		this.name = name;
	}

	public Frame(final String name, final List<Hardpoint> hardpoints) {
		this.savedPositions = new HashMap<String, SavedHardpointPosition>();
		this.name = name;

		for (final Hardpoint h : hardpoints)
			saveHardpoint(h);
	}

	public void saveHardpoint(final Hardpoint hardpoint) {
		final Point position = hardpoint.getPoint();
		final SavedHardpointPosition saved = new SavedHardpointPosition(position.get(0), position.get(1), position.get(2), hardpoint.getRotation());
		savedPositions.put(hardpoint.getName(), saved);
	}

	public void removeHardpoint(final String hardpointName) {
		savedPositions.remove(hardpointName);
	}

	public SavedHardpointPosition getSavedPosition(final String hardpointName) {
		return savedPositions.get(hardpointName);
	}

	public Map<String, SavedHardpointPosition> getSavedPositions() {
		return savedPositions;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Frame other = (Frame) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}

	public static class SavedHardpointPosition {
		private final double x;
		private final double y;
		private final double z;
		private final int rot;

		public SavedHardpointPosition(final double x, final double y, final double z, final int rot) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.rot = rot;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getZ() {
			return z;
		}

		public int getRot() {
			return rot;
		}
	}
}

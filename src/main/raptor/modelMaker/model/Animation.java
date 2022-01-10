package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

public class Animation {
	private final List<String> frameNames;
	private final List<Integer> holds;

	private String name;

	public Animation(final String name) {
		this.frameNames = new ArrayList<>();
		this.holds = new ArrayList<>();

		this.name = name;
	}

	public void addFrame(final String frameName, final int holdCount) {
		frameNames.add(frameName);
		holds.add(holdCount);
	}

	public void setFrame(final int index, final String newFrameName) {
		frameNames.set(index, newFrameName);
	}

	public void setHolds(final int index, final int newHolds) {
		holds.set(index, newHolds);
	}

	public void removeFrame(final int index) {
		frameNames.remove(index);
		holds.remove(index);
	}

	public String getFrame(final int index) {
		return frameNames.get(index);
	}

	public int getHolds(final int index) {
		return holds.get(index);
	}

	public int size() {
		return frameNames.size();
	}

	public String getName() {
		return name;
	}

	public void setName(final String newName) {
		this.name = newName;
	}

	@Override
	public String toString() {
		return name;
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
		Animation other = (Animation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

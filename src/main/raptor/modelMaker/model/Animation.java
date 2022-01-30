package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

public class Animation {
	private final List<String> frameNames;
	private final List<Integer> holds;

	private String name;
	private int totalFrameCount;

	public Animation(final String name) {
		this.frameNames = new ArrayList<>();
		this.holds = new ArrayList<>();

		this.name = name;
		this.totalFrameCount = 0;
	}

	public void addFrame(final String frameName, final int holdCount) {
		frameNames.add(frameName);
		holds.add(holdCount);
		totalFrameCount += holdCount;
	}

	public void setFrame(final int index, final String newFrameName) {
		frameNames.set(index, newFrameName);
	}

	public void setHolds(final int index, final int newHolds) {
		final int oldHolds = holds.get(index);
		holds.set(index, newHolds);
		totalFrameCount += (newHolds - oldHolds);
	}

	public int shiftFrameUp(final int index) {
		if (index <= 0)
			return index;

		final String frameName = frameNames.remove(index);
		final Integer hold = holds.remove(index);

		final int targetIndex = index - 1;

		frameNames.add(targetIndex, frameName);
		holds.add(targetIndex, hold);

		return targetIndex;
	}

	public int shiftFrameDown(final int index) {
		if (index >= frameNames.size() - 1)
			return index;

		final String frameName = frameNames.remove(index);
		final Integer hold = holds.remove(index);

		final int targetIndex = index + 1;

		frameNames.add(targetIndex, frameName);
		holds.add(targetIndex, hold);

		return targetIndex;
	}

	public void removeFrame(final int index) {
		final int frameHolds = holds.get(index);
		frameNames.remove(index);
		holds.remove(index);
		totalFrameCount -= frameHolds;
	}

	public void removeFrame(final String frameName) {
		final int maxIndex = frameNames.size();

		for (int i = 0; i < maxIndex; i++) {
			if (i >= frameNames.size())
				break;

			if (frameNames.get(i).equals(frameName)) {
				removeFrame(i);
				i--;
			}
		}
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

	public int totalFramesCount() {
		return totalFrameCount;
	}

	public List<String> getTotalFrames() {
		final List<String> frames = new ArrayList<String>();

		for (int i = 0; i < frameNames.size(); i++)
			for (int j = 0; j < holds.get(i); j++)
				frames.add(frameNames.get(i));

		return frames;
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

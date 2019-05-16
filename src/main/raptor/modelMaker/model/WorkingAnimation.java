package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkingAnimation {
	private final List<WorkingLogicalFrame> frames;
	private final int pointCount;
	private final int directionCount;

	private int id;
	private String name;

	public WorkingAnimation(final int id, final String name, final int pointCount, final int directionCount) {
		frames = new ArrayList<>();
		this.pointCount = pointCount;
		this.directionCount = directionCount;

		this.id = id;
		this.name = name;
	}

	public void addFrame(final int portion) {
		frames.add(new WorkingLogicalFrame(pointCount, directionCount, portion));
	}

	public List<WorkingLogicalFrame> getFrames() {
		return frames;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
}

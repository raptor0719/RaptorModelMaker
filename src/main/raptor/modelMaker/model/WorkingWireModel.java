package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkingWireModel {
	private final List<WorkingAnimation> animations;
	private final int pointCount;
	private final int directionCount;

	private int id;
	private String name;

	public WorkingWireModel(final int id, final String name, final int pointCount, final int directionCount) {
		animations = new ArrayList<>();
		this.pointCount = pointCount;
		this.directionCount = directionCount;

		this.id = id;
		this.name = name;
	}

	public void addAnimation(final int id, final String name) {
		animations.add(new WorkingAnimation(id, name, pointCount, directionCount));
	}

	public List<WorkingAnimation> getAnimations() {
		return animations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointCount() {
		return pointCount;
	}

	public int getDirectionCount() {
		return directionCount;
	}
}

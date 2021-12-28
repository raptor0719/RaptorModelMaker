package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private final List<Hardpoint> hardpoints;
	private final List<Frame> frames;

	private String name;

	public Model(final String name) {
		this.hardpoints = new ArrayList<>();
		this.frames = new ArrayList<>();
		this.name = name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addHardpoint(final String name, final int rotation) {
		if (getHardpointByName(name) != null)
			throw new IllegalArgumentException("Hardpoint with that name already exists");
		hardpoints.add(new Hardpoint(name, rotation));
	}

	public Hardpoint getHardpoint(final String name) {
		return getHardpointByName(name);
	}

	public List<Hardpoint> getHardpoints() {
		return hardpoints;
	}

	public void removeHardpoint(final String name) {
		final Hardpoint h = getHardpointByName(name);

		if (h == null)
			return;

		hardpoints.remove(h);
	}

	public boolean isHardpointWithName(final String name) {
		return getHardpointByName(name) != null;
	}

	public void addFrame(final String name) {
		if (getFrameByName(name) != null)
			throw new IllegalArgumentException("Frame with that name already exists");
		frames.add(new Frame(name, getHardpoints()));
	}

	public void removeFrame(final String name) {
		final Frame f = getFrameByName(name);

		if (f == null)
			return;

		frames.remove(f);
	}

	public List<Frame> getFrames() {
		return frames;
	}

	/* INTERNAL */

	private Hardpoint getHardpointByName(final String name) {
		for (final Hardpoint h : hardpoints)
			if (h.getName().equals(name))
				return h;
		return null;
	}

	private Frame getFrameByName(final String name) {
		for (final Frame f : frames)
			if (f.getName().equals(name))
				return f;
		return null;
	}
}

package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.List;

import raptor.modelMaker.math.Point2D;

public class Model {
	private final List<Hardpoint> hardpoints;
	private final List<Frame> frames;
	private final List<Animation> animations;

	private String name;

	private int width;
	private int height;
	private int centerOffsetX;
	private int centerOffsetY;

	public Model(final String name) {
		this.hardpoints = new ArrayList<>();
		this.frames = new ArrayList<>();
		this.animations = new ArrayList<>();
		this.name = name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setCenterOffsetX(final int centerOffsetX) {
		this.centerOffsetX = centerOffsetX;
	}

	public int getCenterOffsetX() {
		return centerOffsetX;
	}

	public void setCenterOffsetY(final int centerOffsetY) {
		this.centerOffsetY = centerOffsetY;
	}

	public int getCenterOffsetY() {
		return centerOffsetY;
	}

	public void addHardpoint(final String name) {
		if (getHardpointByName(name) != null)
			throw new IllegalArgumentException("Hardpoint with that name already exists");

		final Hardpoint newHardpoint = new Hardpoint(name, 0, null, null);

		hardpoints.add(newHardpoint);

		for (final Frame f : frames)
			f.saveHardpoint(newHardpoint);
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

		for (final Frame f : frames)
			f.removeHardpoint(h.getName());
	}

	public boolean isHardpointWithName(final String name) {
		return getHardpointByName(name) != null;
	}

	public void addFrame(final String name) {
		if (getFrameByName(name) != null)
			throw new IllegalArgumentException("Frame with that name already exists");
		frames.add(new Frame(name, getHardpoints()));
	}

	public Frame getFrame(final String name) {
		return getFrameByName(name);
	}

	public void removeFrame(final String name) {
		final Frame f = getFrameByName(name);

		if (f == null)
			return;

		frames.remove(f);

		for (final Animation a : animations)
			a.removeFrame(name);
	}

	public void saveHardpointsToFrame(final String name) {
		final Frame frame = getFrameByName(name);

		if (frame == null)
			return;

		for (final Hardpoint h : hardpoints)
			frame.saveHardpoint(h);
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public boolean isFrameWithName(final String name) {
		return getFrameByName(name) != null;
	}

	public void loadFrame(final String name) {
		final Frame frame = getFrameByName(name);

		if (frame == null)
			return;

		for (final Hardpoint h : hardpoints) {
			final Frame.SavedHardpointPosition saved = frame.getSavedPosition(h.getName());

			final Point2D p = h.getPoint();
			p.setX(saved.getX());
			p.setY(saved.getY());

			h.setDrawDepth(saved.getDepth());

			h.setRotation(saved.getRot());

			h.setSpritePhase(saved.getSpritePhase());
		}
	}

	public void addAnimation(final String name) {
		if (getAnimationByName(name) != null)
			throw new IllegalArgumentException("Animation with that name already exists");
		animations.add(new Animation(name));
	}

	public Animation getAnimation(final String name) {
		return getAnimationByName(name);
	}

	public List<Animation> getAnimations() {
		return animations;
	}

	public void removeAnimation(final String name) {
		final Animation a = getAnimationByName(name);

		if (a == null)
			return;

		animations.remove(a);
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

	private Animation getAnimationByName(final String name) {
		for (final Animation a : animations)
			if (a.getName().equals(name))
				return a;
		return null;
	}
}

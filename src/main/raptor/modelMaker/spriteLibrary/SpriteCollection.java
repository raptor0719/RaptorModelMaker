package raptor.modelMaker.spriteLibrary;

import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.model.ViewDirection;

public class SpriteCollection {
	private final Map<ViewDirection, Point2D> attachmentPoints;

	private String name;

	public SpriteCollection(final String name) {
		this.attachmentPoints = new HashMap<ViewDirection, Point2D>();
		this.name = name;

		initializeAttachmentPoints();
	}

	public SpriteCollection(final String name, final Map<ViewDirection, Point2D> attachmentPoints) {
		this.attachmentPoints = attachmentPoints;
		this.name = name;

		initializeAttachmentPoints();
	}

	public Point2D getAttachmentPoint(final ViewDirection direction) {
		return attachmentPoints.get(direction);
	}

	public void setAttachmentPoint(final ViewDirection direction, final Point2D point) {
		attachmentPoints.put(direction, point);
	}

	public void unsetAttachmentPoint(final ViewDirection direction) {
		initializeAttachmentPoint(direction);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	private void initializeAttachmentPoint(final ViewDirection direction) {
		setAttachmentPoint(direction, new Point2D(0, 0));
	}

	private void initializeAttachmentPoints() {
		for (final ViewDirection direction : ViewDirection.values())
			if (!attachmentPoints.containsKey(direction))
				initializeAttachmentPoint(direction);
	}
}

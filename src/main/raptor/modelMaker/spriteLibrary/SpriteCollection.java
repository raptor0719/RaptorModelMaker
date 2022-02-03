package raptor.modelMaker.spriteLibrary;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.model.ViewDirection;

public class SpriteCollection {
	private final Map<ViewDirection, Sprite> sprites;

	private String name;

	public SpriteCollection(final String name) {
		this.sprites = new HashMap<ViewDirection, Sprite>();
		this.name = name;

		initializeAttachmentPoints();
	}

	public SpriteCollection(final String name, final Map<ViewDirection, Sprite> sprites) {
		this.sprites = sprites;
		this.name = name;

		initializeAttachmentPoints();
	}

	public Image getImage(final ViewDirection direction) {
		return sprites.get(direction).getImage();
	}

	public Point2D getAttachmentPoint(final ViewDirection direction) {
		return sprites.get(direction).getAttachmentPoint();
	}

	public void setAttachmentPoint(final ViewDirection direction, final Point2D point) {
		sprites.get(direction).setAttachmentPoint(point.getX(), point.getY());
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
			if (!sprites.containsKey(direction))
				initializeAttachmentPoint(direction);
	}
}

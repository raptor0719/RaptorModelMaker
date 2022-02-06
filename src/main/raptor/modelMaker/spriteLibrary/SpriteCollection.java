package raptor.modelMaker.spriteLibrary;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.model.ViewDirection;

public class SpriteCollection {
	private final Map<ViewDirection, Sprite> sprites;

	private String name;

	public SpriteCollection(final String name) {
		this.sprites = new HashMap<ViewDirection, Sprite>();
		for (final ViewDirection viewDirection : ViewDirection.values())
			sprites.put(viewDirection, new Sprite());
		this.name = name;
	}

	public SpriteCollection(final String name, final Map<ViewDirection, Sprite> sprites) {
		this.sprites = sprites;
		this.name = name;
	}

	public Sprite getSprite(final ViewDirection direction) {
		return sprites.get(direction);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSpriteImages(final Map<ViewDirection, BufferedImage> newImages) {
		for (final ViewDirection viewDirection : ViewDirection.values()) {
			if (!newImages.containsKey(viewDirection))
				throw new IllegalArgumentException(String.format("The image for the '%s' direction was not found in the given map.", viewDirection.name()));
			sprites.get(viewDirection).setImage(newImages.get(viewDirection));
		}
	}

	@Override
	public String toString() {
		return name;
	}
}

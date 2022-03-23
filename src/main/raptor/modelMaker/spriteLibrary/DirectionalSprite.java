package raptor.modelMaker.spriteLibrary;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.model.ViewDirection;

public class DirectionalSprite {
	private final Map<ViewDirection, Sprite> sprites;

	public DirectionalSprite() {
		this.sprites = new HashMap<ViewDirection, Sprite>();
		for (final ViewDirection viewDirection : ViewDirection.values())
			sprites.put(viewDirection, new Sprite());
	}

	public DirectionalSprite(final Map<ViewDirection, Sprite> sprites) {
		this.sprites = sprites;
	}

	public Sprite getSprite(final ViewDirection direction) {
		return sprites.get(direction);
	}

	public void setSpriteImages(final Map<ViewDirection, BufferedImage> newImages) {
		for (final ViewDirection viewDirection : ViewDirection.values()) {
			if (!newImages.containsKey(viewDirection))
				throw new IllegalArgumentException(String.format("The image for the '%s' direction was not found in the given map.", viewDirection.name()));
			sprites.get(viewDirection).setImage(newImages.get(viewDirection));
		}
	}
}

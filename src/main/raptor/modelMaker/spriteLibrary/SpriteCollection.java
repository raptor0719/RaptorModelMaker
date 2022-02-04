package raptor.modelMaker.spriteLibrary;

import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.model.ViewDirection;

public class SpriteCollection {
	private final Map<ViewDirection, Sprite> sprites;

	private String name;

	public SpriteCollection(final String name) {
		this.sprites = new HashMap<ViewDirection, Sprite>();
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
}

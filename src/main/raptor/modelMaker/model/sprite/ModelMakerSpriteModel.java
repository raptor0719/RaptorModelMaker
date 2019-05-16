package raptor.modelMaker.model.sprite;

import java.util.List;

public class ModelMakerSpriteModel {
	private final List<ModelMakerSprite> sprites;

	public ModelMakerSpriteModel(final List<ModelMakerSprite> sprites) {
		this.sprites = sprites;
	}

	public ModelMakerSprite getSprite(final int direction) {
		return sprites.get(direction);
	}
}

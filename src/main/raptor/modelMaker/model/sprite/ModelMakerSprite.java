package raptor.modelMaker.model.sprite;

import java.awt.Image;

public class ModelMakerSprite {
	private final Image image;
	private final int originX;
	private final int originY;

	public ModelMakerSprite(final Image image, final int originX, final int originY) {
		this.image = image;
		this.originX = originX;
		this.originY = originY;
	}

	public Image getImage() {
		return image;
	}

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}
}

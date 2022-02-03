package raptor.modelMaker.spriteLibrary;

import java.awt.Image;

import raptor.modelMaker.math.Point2D;

public class Sprite {
	private final Image image;

	private Point2D attachmentPoint;

	public Sprite(final Image image) {
		this(image, 0, 0);
	}

	public Sprite(final Image image, final int attachX, final int attachY) {
		this.image = image;
		this.attachmentPoint = new Point2D(attachX, attachY);
	}

	public Image getImage() {
		return image;
	}

	public Point2D getAttachmentPoint() {
		return attachmentPoint;
	}

	public void setAttachmentPoint(final int attachX, final int attachY) {
		attachmentPoint = new Point2D(attachX, attachY);
	}
}

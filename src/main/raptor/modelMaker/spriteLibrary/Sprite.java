package raptor.modelMaker.spriteLibrary;

import java.awt.image.BufferedImage;

import raptor.modelMaker.math.Point2D;

public class Sprite {
	private BufferedImage image;
	private Point2D attachmentPoint;

	public Sprite(final BufferedImage image) {
		this(image, 0, 0);
	}

	public Sprite(final BufferedImage image, final int attachX, final int attachY) {
		this.image = image;
		this.attachmentPoint = new Point2D(attachX, attachY);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(final BufferedImage image) {
		this.image = image;
	}

	public Point2D getAttachmentPoint() {
		return attachmentPoint;
	}

	public void setAttachmentPoint(final int attachX, final int attachY) {
		attachmentPoint = new Point2D(attachX, attachY);
	}

	public void unsetAttachmentPoint() {
		setAttachmentPoint(0, 0);
	}
}

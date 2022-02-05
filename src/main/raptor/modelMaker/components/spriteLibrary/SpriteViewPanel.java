package raptor.modelMaker.components.spriteLibrary;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.spriteLibrary.Sprite;

public class SpriteViewPanel extends JPanel {
	private static final int POINT_DRAW_DIAMETER = 10;

	private Sprite sprite;

	public SpriteViewPanel() {
		super();
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;

		final int viewportWidth = this.getWidth();
		final int viewportHeight = this.getHeight();
		final int centerX = viewportWidth / 2;
		final int centerY = viewportHeight / 2;

		g2.clearRect(0, 0, viewportWidth, viewportHeight);

		if (sprite == null)
			return;

		final BufferedImage spriteImage = sprite.getImage();

		final int offsetX = spriteImage.getWidth() / 2;
		final int offsetY = spriteImage.getHeight() / 2;

		final int imageDrawOriginX = centerX - offsetX;
		final int imageDrawOriginY = centerY - offsetY;

		g2.drawImage(spriteImage, imageDrawOriginX, imageDrawOriginY, null);

		final Point2D attachmentPoint = sprite.getAttachmentPoint();

		final int pointOffsetX = POINT_DRAW_DIAMETER / 2;
		final int pointOffsetY = POINT_DRAW_DIAMETER / 2;

		final int pointDrawX = imageDrawOriginX + attachmentPoint.getX() - pointOffsetX;
		final int pointDrawY = imageDrawOriginY + attachmentPoint.getY() - pointOffsetY;

		g2.fillOval(pointDrawX, pointDrawY, POINT_DRAW_DIAMETER, POINT_DRAW_DIAMETER);

		final String currentAttachmentPointDisplayString = String.format("Current Attachment: (%s, %s)", attachmentPoint.getX(), attachmentPoint.getY());
		g2.drawChars(currentAttachmentPointDisplayString.toCharArray(), 0, currentAttachmentPointDisplayString.length(), 20, 20);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(final Sprite sprite) {
		this.sprite = sprite;
		this.repaint();
	}
}

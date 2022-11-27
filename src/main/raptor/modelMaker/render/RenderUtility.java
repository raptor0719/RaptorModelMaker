package raptor.modelMaker.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.spriteLibrary.Sprite;

public class RenderUtility {
	public static Sprite translateSprite(final Sprite sprite, final int rotation) {
		final BufferedImage originalImage = sprite.getImage();
		final Point2D originalAttachmentPoint = sprite.getAttachmentPoint();

		final BufferedImage imageWithRotationBuffer = addRotationBuffer(originalImage);
		final Point2D attachmentPointWithRotationBuffer = accountForRotationBuffer(originalAttachmentPoint, originalImage, imageWithRotationBuffer);

		final BufferedImage rotatedImage = rotateImage(imageWithRotationBuffer, rotation);
		final Point2D rotatedAttachemntPoint = rotatePoint(attachmentPointWithRotationBuffer, new Point2D(rotatedImage.getWidth()/2, rotatedImage.getHeight()/2), rotation);

		return new Sprite(rotatedImage, rotatedAttachemntPoint.getX(), rotatedAttachemntPoint.getY());
	}

	private static BufferedImage addRotationBuffer(final BufferedImage image) {
		final double coefficient = 1.2;

		final int largest = (image.getWidth() > image.getHeight()) ? image.getWidth() : image.getHeight();

		final int newWidth = (int)(largest * coefficient);
		final int newHeight = (int)(largest * coefficient);

		final BufferedImage enlarged = new BufferedImage(newWidth, newHeight, image.getType());

		final Graphics2D g = enlarged.createGraphics();
		g.translate((newWidth - image.getWidth())/2, (newHeight - image.getHeight())/2);

		g.drawImage(image, null, 0, 0);

		g.dispose();

		return enlarged;
	}

	private static Point2D accountForRotationBuffer(final Point2D point, final BufferedImage startImage, final BufferedImage enlargedImage) {
		final int widthCoefficient = (enlargedImage.getWidth() - startImage.getWidth())/2;
		final int heightCoefficient = (enlargedImage.getHeight() - startImage.getHeight())/2;

		return new Point2D(point.getX() + widthCoefficient, point.getY() + heightCoefficient);
	}

	private static BufferedImage rotateImage(final BufferedImage image, final int degrees) {
		final int width = image.getWidth();
		final int height = image.getHeight();

		final double radians = Math.toRadians(degrees);

		final BufferedImage rotated = new BufferedImage(width, height, image.getType());

		final Graphics2D g = rotated.createGraphics();

		g.rotate(radians, width/2, height/2);
		g.drawImage(image, null, 0, 0);

		return rotated;
	}

	public static Point2D rotatePoint(final Point2D point, final Point2D pivot, final int degrees) {
		final double sin = Math.sin(Math.toRadians(degrees));
		final double cos = Math.cos(Math.toRadians(degrees));

		final int tX = point.getX() - pivot.getX();
		final int tY = point.getY() - pivot.getY();

		final double newX = tX * cos - tY * sin;
		final double newY = tX * sin + tY * cos;

		final double x = newX + pivot.getX();
		final double y = newY + pivot.getY();

		return new Point2D(x, y);
	}
}

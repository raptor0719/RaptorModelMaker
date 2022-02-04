package raptor.modelMaker.spriteLibrary;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import raptor.modelMaker.math.Point2D;
import raptor.modelMaker.model.ViewDirection;

public class SpriteLibraryWriter {
	protected static final byte[] MAGIC_NUMBER = new byte[] {'s', 'p', 'r', 'i', 't', 'e'};
	protected static final String FILE_EXTENSION = "sl";

	public static void write(final SpriteLibrary spriteLibrary, final String locationPath) {
		try {
			final File directoryToSaveTo = new File(locationPath);

			if (!directoryToSaveTo.isDirectory())
				throw new IllegalArgumentException(String.format("The save location '%s' is not a directory.", locationPath));

			final File spriteLibraryFile = new File(directoryToSaveTo.getPath() + File.separator + spriteLibrary.getName() + "." + FILE_EXTENSION);

			spriteLibraryFile.createNewFile();

			OutputStream ostream = null;
			try {
				ostream = new BufferedOutputStream(new FileOutputStream(spriteLibraryFile));
				write(spriteLibrary, ostream);
			} finally {
				if (ostream != null)
					ostream.close();
			}
		} catch (final IOException e) {
			throw new RuntimeException("Failed to save sprite library.", e);
		}
	}

	private static void write(final SpriteLibrary spriteLibrary, final OutputStream outputStream) throws IOException {
		final DataOutputStream dos = new DataOutputStream(outputStream);

		dos.write(MAGIC_NUMBER);

		dos.write(serializeString(spriteLibrary.getName()));

		final List<SpriteCollection> spriteCollections = spriteLibrary.getSpriteCollections();

		dos.writeInt(spriteCollections.size());

		for (int i = 0; i < spriteCollections.size(); i++) {
			final SpriteCollection spriteCollection = spriteCollections.get(i);

			dos.write(serializeString(spriteCollection.getName()));

			for (final ViewDirection viewDirection : ViewDirection.values()) {
				final Point2D attachmentPoint = spriteCollection.getAttachmentPoint(viewDirection);

				dos.writeInt(attachmentPoint.getX());
				dos.writeInt(attachmentPoint.getY());
			}
		}
	}

	private static byte[] serializeString(final String str) throws IOException {
		final ByteArrayOutputStream os = new ByteArrayOutputStream(str.length()*2 + 4);
		final DataOutputStream dos = new DataOutputStream(os);

		try {
			dos.writeInt(str.length());
			dos.writeChars(str);

			return os.toByteArray();
		} catch (final IOException e) {
			throw e;
		} finally {
			try {
				os.close();
				dos.close();
			} catch (Throwable t) {
				/* SWALLOW */
			}
		}
	}
}

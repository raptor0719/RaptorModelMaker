package raptor.modelMaker.spriteLibrary;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteLibraryReader {
	public static SpriteLibrary read(final String filePath) {
		try {
			final File file = new File(filePath);

			if (!file.exists() || file.isDirectory())
				throw new IllegalArgumentException(String.format("The file '%s' does not exist.", filePath));

			return read(file.getParentFile(), new FileInputStream(file));
		} catch (final IOException e) {
			throw new RuntimeException(String.format("Failed to read sprite library '%s'", filePath), e);
		}
	}

	private static SpriteLibrary read(final File spriteLibraryDirectory, final InputStream istream) throws IOException {
		final DataInputStream dis = new DataInputStream(istream);

		final byte[] magicNumber = new byte[SpriteLibraryWriter.MAGIC_NUMBER.length];
		dis.readFully(magicNumber);

		if (!Arrays.equals(magicNumber, SpriteLibraryWriter.MAGIC_NUMBER))
			throw new IOException("Given file was not a sprite library file.");

		final String spriteLibraryName = deserializeString(dis);

		final List<SpriteCollection> spriteCollections = new ArrayList<SpriteCollection>();

		final int spriteCollectionCount = dis.readInt();

		for (int i = 0; i < spriteCollectionCount; i++) {
			final String spriteCollectionName = deserializeString(dis);

			final Map<String, Sprite> phases = new HashMap<String, Sprite>();

			final int phaseCount = dis.readInt();

			for (int p = 0; p < phaseCount; p++) {
				final String phaseName = deserializeString(dis);

				final int attachX = dis.readInt();
				final int attachY = dis.readInt();

				final BufferedImage image = readImage(spriteLibraryDirectory, getSpriteImageFileName(spriteCollectionName, phaseName));

				phases.put(phaseName, new Sprite(image, attachX, attachY));
			}

			spriteCollections.add(new SpriteCollection(spriteCollectionName, phases));
		}

		return new SpriteLibrary(spriteLibraryName, spriteLibraryDirectory.getAbsolutePath(), spriteCollections);
	}

	public static void loadImages(final SpriteCollection spriteCollection, final String location) {
		final File directory = new File(location);

		if (!directory.isDirectory() || !directory.exists())
			throw new IllegalArgumentException(String.format("Given location '%s' was not a directory or did not exist.", location));

		try {
			for (final String phase : spriteCollection.getPhases()) {
				final BufferedImage image = readImage(directory, getSpriteImageFileName(spriteCollection.getName(), phase));

				spriteCollection.getSprite(phase).setImage(image);
			}
		} catch (final IOException e) {
			throw new RuntimeException("Error reading images.", e);
		}
	}

	private static BufferedImage readImage(final File spriteLibraryDirectory, final String fileNameWithoutExtension) throws IOException {
		final File[] files = spriteLibraryDirectory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String fileName) {
				return fileNameWithoutExtension.equals(stripExtension(fileName));
			}
		});

		if (files.length < 1)
			return null;

		return ImageIO.read(files[0]);
	}

	private static String getSpriteImageFileName(final String spriteCollectionName, final String spritePhase) {
		return String.format("%s_%s", spriteCollectionName, spritePhase);
	}

	private static String stripExtension(final String fileName) {
		final int dotLocation = fileName.lastIndexOf(".");
		return (dotLocation < 1) ? fileName : fileName.substring(0, dotLocation);
	}

	private static String deserializeString(final DataInputStream dis) throws IOException {
		final int length = dis.readInt();
		final char[] buffer = new char[length];
		for (int i = 0; i < length; i++)
			buffer[i] = dis.readChar();

		return new String(buffer);
	}
}

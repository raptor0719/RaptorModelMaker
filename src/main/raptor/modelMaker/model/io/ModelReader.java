package raptor.modelMaker.model.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import raptor.modelMaker.math.Point;
import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Frame;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class ModelReader {
	public static Model read(final InputStream istream) throws IOException {
		final DataInputStream dis = new DataInputStream(istream);

		final byte[] magicNumber = new byte[ModelWriter.MAGIC_NUMBER.length];
		dis.readFully(magicNumber);

		if (!Arrays.equals(magicNumber, ModelWriter.MAGIC_NUMBER))
			throw new IllegalArgumentException("Was not a model format.");

		final String name = deserializeString(dis);

		final Model model = new Model(name);
		readHardpoints(model, dis);
		readFrames(model, dis);
		readAnimations(model, dis);

		return model;
	}

	private static void readHardpoints(final Model model, final DataInputStream dis) throws IOException {
		final int count = dis.readInt();

		for (int i = 0; i < count; i++) {
			final String name = deserializeString(dis);
			final double x = dis.readDouble();
			final double y = dis.readDouble();
			final double z = dis.readDouble();
			final int nsrot = dis.readInt();
			final int ewrot = dis.readInt();
			final String spritePhase = deserializeString(dis);
			final String spriteCollection = deserializeString(dis);

			model.addHardpoint(name, nsrot, ewrot);

			final Hardpoint hardpoint = model.getHardpoint(name);

			final Point coordinates = hardpoint.getPoint();
			coordinates.set(0, x);
			coordinates.set(1, y);
			coordinates.set(2, z);

			hardpoint.setSpritePhase(spritePhase);
			hardpoint.setSpriteCollectionName(spriteCollection);
		}
	}

	private static void readFrames(final Model model, final DataInputStream dis) throws IOException {
		final int count = dis.readInt();

		for (int i = 0; i < count; i++) {
			final String name = deserializeString(dis);

			model.addFrame(name);

			final Frame frame = model.getFrame(name);

			final int savedPositionCount = dis.readInt();

			for (int j = 0; j < savedPositionCount; j++) {
				final String hardpointName = deserializeString(dis);
				final double x = dis.readDouble();
				final double y = dis.readDouble();
				final double z = dis.readDouble();
				final int nsrot = dis.readInt();
				final int ewrot = dis.readInt();
				final String spritePhase = deserializeString(dis);

				final Hardpoint savedPosition = new Hardpoint(hardpointName, nsrot, ewrot, null, spritePhase);

				final Point coordinates = savedPosition.getPoint();
				coordinates.set(0, x);
				coordinates.set(1, y);
				coordinates.set(2, z);

				frame.saveHardpoint(savedPosition);
			}
		}
	}

	private static void readAnimations(final Model model, final DataInputStream dis) throws IOException {
		final int count = dis.readInt();

		for (int i = 0; i < count; i++) {
			final String name = deserializeString(dis);

			model.addAnimation(name);

			final Animation animation = model.getAnimation(name);

			final int frameCount = dis.readInt();

			for (int j = 0; j < frameCount; j++) {
				final String frameName = deserializeString(dis);
				final int holds = dis.readInt();
				final boolean isActivation = dis.readBoolean();

				animation.addFrame(frameName, holds, isActivation);
			}
		}
	}

	private static String deserializeString(final DataInputStream dis) throws IOException {
		final int length = dis.readInt();
		final char[] buffer = new char[length];
		for (int i = 0; i < length; i++)
			buffer[i] = dis.readChar();

		return new String(buffer);
	}
}

package raptor.modelMaker.model.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import raptor.modelMaker.math.Point2D;
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
			final int x = dis.readInt();
			final int y = dis.readInt();
			final int depth = dis.readInt();
			final int rot = dis.readInt();
			final String spritePhase = deserializeString(dis);
			final String spriteCollection = deserializeString(dis);

			model.addHardpoint(name);

			final Hardpoint hardpoint = model.getHardpoint(name);

			final Point2D coordinates = hardpoint.getPoint();
			coordinates.setX(x);
			coordinates.setY(y);

			hardpoint.setDrawDepth(depth);

			hardpoint.setRotation(rot);

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
				final int x = dis.readInt();
				final int y = dis.readInt();
				final int depth = dis.readInt();
				final int rot = dis.readInt();
				final String spritePhase = deserializeString(dis);

				final Hardpoint savedPosition = new Hardpoint(hardpointName, rot, null, spritePhase);

				final Point2D coordinates = savedPosition.getPoint();
				coordinates.setX(x);
				coordinates.setY(y);

				savedPosition.setDrawDepth(depth);

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

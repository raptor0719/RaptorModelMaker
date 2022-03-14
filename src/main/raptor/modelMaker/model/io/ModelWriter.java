package raptor.modelMaker.model.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import raptor.modelMaker.math.Point;
import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Frame;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class ModelWriter {
	public static final byte[] MAGIC_NUMBER = new byte[] {'m', 'o', 'd', 'e', 'l'};

	public static void write(final Model model, final OutputStream ostream) throws IOException {
		final DataOutputStream dos = new DataOutputStream(ostream);

		dos.write(MAGIC_NUMBER);

		dos.write(serializeString(model.getName()));

		writeHardpoints(model.getHardpoints(), dos);

		writeFrames(model.getFrames(), dos);

		writeAnimations(model.getAnimations(), dos);
	}

	private static void writeHardpoints(final List<Hardpoint> hardpoints, final DataOutputStream dos) throws IOException {
		dos.writeInt(hardpoints.size());

		for (final Hardpoint hardpoint : hardpoints) {
			dos.write(serializeString(hardpoint.getName()));

			final Point coordinates = hardpoint.getPoint();
			dos.writeDouble(coordinates.get(0));
			dos.writeDouble(coordinates.get(1));
			dos.writeDouble(coordinates.get(2));

			dos.writeInt(hardpoint.getRotation());

			dos.write(serializeString(hardpoint.getSpriteCollectionName()));
		}
	}

	private static void writeFrames(final List<Frame> frames, final DataOutputStream dos) throws IOException {
		dos.writeInt(frames.size());

		for (final Frame frame : frames) {
			dos.write(serializeString(frame.getName()));

			final Set<Map.Entry<String, Frame.SavedHardpointPosition>> savedPositions = frame.getSavedPositions().entrySet();

			dos.writeInt(savedPositions.size());

			for (final Map.Entry<String, Frame.SavedHardpointPosition> entry : savedPositions) {
				dos.write(serializeString(entry.getKey()));

				final Frame.SavedHardpointPosition savedPosition = entry.getValue();
				dos.writeDouble(savedPosition.getX());
				dos.writeDouble(savedPosition.getY());
				dos.writeDouble(savedPosition.getZ());
				dos.writeDouble(savedPosition.getRot());
			}
		}
	}

	private static void writeAnimations(final List<Animation> animations, final DataOutputStream dos) throws IOException {
		dos.writeInt(animations.size());

		for (final Animation animation : animations) {
			dos.write(serializeString(animation.getName()));

			dos.writeInt(animation.size());

			for (int i = 0; i < animation.size(); i++) {
				dos.write(serializeString(animation.getFrame(i)));
				dos.writeInt(animation.getHolds(i));
				dos.writeBoolean(animation.isActivation(i));
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

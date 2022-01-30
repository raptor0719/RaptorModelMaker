package raptor.modelMaker.controller;

import java.util.List;

import javax.swing.JComponent;

import raptor.modelMaker.components.AnimationPanel;
import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Model;

public class AnimationPreviewController {
	private final AnimationPanel animationEditorPanel;
	private final JComponent redrawOnChange;

	private Model model;
	private AnimationPreviewThread animationPreviewThread;

	public AnimationPreviewController(final AnimationPanel animationEditorPanel, final JComponent redrawOnChange, final Model model) {
		this.animationEditorPanel = animationEditorPanel;
		this.redrawOnChange = redrawOnChange;

		this.model = model;
		this.animationPreviewThread = null;
	}

	public void play() {
		if (animationPreviewThread == null) {
			final Animation selectedAnimation = animationEditorPanel.getSelectedAnimation();

			if (selectedAnimation != null) {
				animationPreviewThread = new AnimationPreviewThread(redrawOnChange, model, selectedAnimation);
				animationPreviewThread.start();
				animationPreviewThread.playPreview();
			}
		} else {
			animationPreviewThread.playPreview();
		}
	}

	public void pause() {
		if (animationPreviewThread != null)
			animationPreviewThread.pausePreview();
	}

	public void stop() {
		if (animationPreviewThread != null) {
			animationPreviewThread.killPreview();
			animationPreviewThread = null;
		}
	}

	public void stepForward() {
		if (animationPreviewThread != null) {
			animationPreviewThread.pausePreview();
			animationPreviewThread.goToNextFrame();
		}
	}

	public void stepBackward() {
		if (animationPreviewThread != null) {
			animationPreviewThread.pausePreview();
			animationPreviewThread.goToPreviousFrame();
		}
	}

	public void goToStart() {
		if (animationPreviewThread != null) {
			animationPreviewThread.pausePreview();
			animationPreviewThread.goToStart();
		}
	}

	public void setModel(final Model model) {
		this.stop();
		this.model = model;
	}

	private static class AnimationPreviewThread extends Thread {
		private static final int PLAY_ACTION = 1;
		private static final int PAUSE_ACTION = 0;
		private static final int KILL_ACTION = -1;

		private final JComponent redrawOnChange;

		private int currentAction;

		private Model model;
		private List<String> frames;
		private int currentFrame;

		private long previousTime;

		public AnimationPreviewThread(final JComponent redrawOnChange, final Model model, final Animation animation) {
			this.redrawOnChange = redrawOnChange;
			this.currentAction = PLAY_ACTION;

			this.model = model;
			this.frames = animation.getTotalFrames();
			this.currentFrame = 0;

			previousTime = 0;
		}

		@Override
		public void run() {
			previousTime = System.currentTimeMillis();

			while (currentAction != KILL_ACTION) {
				final long currentTime = System.currentTimeMillis();
				final long timeSinceLastUpdate = currentTime - previousTime;

				if (timeSinceLastUpdate >= 17) {
					if (currentAction == PLAY_ACTION) {
						previousTime = currentTime;

						updateModelAndVisuals();

						currentFrame++;
						currentFrame = currentFrame % frames.size();
					} else if (currentAction == PAUSE_ACTION) {
						/* no-op */
					}
				}
			}
		}

		public void playPreview() {
			currentAction = PLAY_ACTION;
		}

		public void pausePreview() {
			currentAction = PAUSE_ACTION;
		}

		public void killPreview() {
			currentAction = KILL_ACTION;
		}

		public void goToPreviousFrame() {
			previousTime = System.currentTimeMillis();

			currentFrame = (currentFrame < 1) ? frames.size() - 1 : currentFrame - 1;

			updateModelAndVisuals();
		}

		public void goToNextFrame() {
			previousTime = System.currentTimeMillis();

			currentFrame++;
			currentFrame = currentFrame % frames.size();

			updateModelAndVisuals();
		}

		public void goToStart() {
			previousTime = System.currentTimeMillis();

			currentFrame = 0;

			updateModelAndVisuals();
		}

		private void updateModelAndVisuals() {
			model.loadFrame(frames.get(currentFrame));
			redrawOnChange.repaint();
		}
	}
}

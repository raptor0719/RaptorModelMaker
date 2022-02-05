package raptor.modelMaker.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import raptor.modelMaker.components.AnimationPanel;
import raptor.modelMaker.components.FrameEditorPanel;
import raptor.modelMaker.components.TopMenuBar;
import raptor.modelMaker.components.ViewPanel;
import raptor.modelMaker.components.spriteLibrary.SpriteLibraryPanel;
import raptor.modelMaker.controller.AnimationPreviewController;
import raptor.modelMaker.model.Model;

public class ModelMaker {
	private static JFrame PARENT_FRAME;

	public static JFrame getParentFrame() {
		return PARENT_FRAME;
	}

	private Model model;

	public ModelMaker() {
		this.model = new Model("default_name");
		model.addHardpoint("test1", 0);
		model.addHardpoint("test2", 0);
		model.addHardpoint("test3", 0);

		final double[] test1Raw = model.getHardpoint("test1").getPoint().getRaw();
		test1Raw[0] = 50.0;
		test1Raw[1] = -50.0;
		test1Raw[2] = 50.0;
		final double[] test2Raw = model.getHardpoint("test2").getPoint().getRaw();
		test2Raw[0] = 5.0;
		test2Raw[1] = -5.0;
		test2Raw[2] = 5.0;
		final double[] test3Raw = model.getHardpoint("test3").getPoint().getRaw();
		test3Raw[0] = -10;
		test3Raw[1] = 22.5;
		test3Raw[2] = 22.5;

		model.addFrame("testFrame1");
		test1Raw[0] = 40.0;
		test1Raw[1] = -40.0;
		test1Raw[2] = 40.0;
		model.addFrame("testFrame2");
		test1Raw[0] = 30.0;
		test1Raw[1] = -30.0;
		test1Raw[2] = 30.0;
		model.addFrame("testFrame3");

		// Setup
		final JFrame frame = new JFrame();
		PARENT_FRAME = frame;
		final JPanel panel = new JPanel();

		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(new GridBagLayout());

		// Top Menu Bar
		final JPanel topMenuBarPanel = new JPanel();
		final GridBagConstraints topMenuBarPanel_constraints = new GridBagConstraints();
		topMenuBarPanel_constraints.gridx = 0;
		topMenuBarPanel_constraints.gridy = 0;
		topMenuBarPanel_constraints.gridwidth = 3;
		topMenuBarPanel_constraints.gridheight = 1;
		topMenuBarPanel_constraints.weightx = 0.1;
		topMenuBarPanel_constraints.weighty = 0.0;
		topMenuBarPanel_constraints.fill = GridBagConstraints.BOTH;
		topMenuBarPanel_constraints.anchor = GridBagConstraints.FIRST_LINE_START;

		topMenuBarPanel.setVisible(true);

		panel.add(new TopMenuBar(), topMenuBarPanel_constraints);

		// Frame Modifier
		final ViewPanel viewPanel = new ViewPanel(model);
		final GridBagConstraints viewPanel_constraints = new GridBagConstraints();
		viewPanel_constraints.gridx = 0;
		viewPanel_constraints.gridy = 1;
		viewPanel_constraints.gridwidth = 1;
		viewPanel_constraints.gridheight = 3;
		viewPanel_constraints.weightx = 1.0;
		viewPanel_constraints.weighty = 1.0;
		viewPanel_constraints.fill = GridBagConstraints.BOTH;
		viewPanel_constraints.anchor = GridBagConstraints.CENTER;

		viewPanel.setBackground(Color.white);
		viewPanel.setVisible(true);

		viewPanel.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
					viewPanel.rotateX(true);
					viewPanel.repaint();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
					viewPanel.rotateX(false);
					viewPanel.repaint();
				}
			}
		});

		panel.add(viewPanel, viewPanel_constraints);

		// Editor Panels
		final FrameEditorPanel frameEditorPanel = new FrameEditorPanel(model, viewPanel);
		frameEditorPanel.setVisible(true);

		final AnimationPanel animationEditorPanel = new AnimationPanel(model);
		animationEditorPanel.setVisible(true);

		final SpriteLibraryPanel spriteLibraryPanel = new SpriteLibraryPanel(viewPanel);
		spriteLibraryPanel.setVisible(true);

		final JTabbedPane editorPanes = new JTabbedPane();
		final GridBagConstraints editorPanes_constraints = new GridBagConstraints();
		editorPanes_constraints.gridx = 1;
		editorPanes_constraints.gridy = 1;
		editorPanes_constraints.gridwidth = 1;
		editorPanes_constraints.gridheight = 3;
		editorPanes_constraints.weightx = 0.0;
		editorPanes_constraints.weighty = 1.0;
		editorPanes_constraints.fill = GridBagConstraints.BOTH;
		editorPanes_constraints.anchor = GridBagConstraints.CENTER;
		editorPanes.setVisible(true);

		editorPanes.addTab("Frames", frameEditorPanel);
		editorPanes.addTab("Animations", animationEditorPanel);
		editorPanes.addTab("Sprite", spriteLibraryPanel);

		editorPanes.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent event) {
				if (editorPanes.getSelectedIndex() == 1) {
					animationEditorPanel.refresh();
				}
			}
		});

		panel.add(editorPanes, editorPanes_constraints);

		// Animation Preview Control
		// TODO Create separate panel with appropriate buttons
		final AnimationPreviewController animationPreviewController = new AnimationPreviewController(animationEditorPanel, viewPanel, model);

		viewPanel.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_L) {
					animationPreviewController.play();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_P) {
					animationPreviewController.pause();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_O) {
					animationPreviewController.stop();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
					animationPreviewController.stepBackward();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
					animationPreviewController.stepForward();
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_B) {
					animationPreviewController.goToStart();
				}
			}

			@Override
			public void keyReleased(final KeyEvent e) {
				/* no-op */
			}

			@Override
			public void keyTyped(final KeyEvent e) {
				/* no-op */
			}
		});

		// Final
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);

		viewPanel.requestFocus();
	}
}

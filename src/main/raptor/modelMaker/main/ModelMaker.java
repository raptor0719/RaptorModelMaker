package raptor.modelMaker.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
import raptor.modelMaker.controller.HardpointSelectionEditingController;
import raptor.modelMaker.controller.HardpointSelectionEditingController.HardpointField;
import raptor.modelMaker.main.config.Configuration;
import raptor.modelMaker.model.Model;
import raptor.modelMaker.spriteLibrary.SpriteLibraryReader;

public class ModelMaker {
	private static JFrame PARENT_FRAME;
	private static Configuration CONFIGURATION;

	public static JFrame getParentFrame() {
		return PARENT_FRAME;
	}

	public static Configuration getConfiguration() {
		return CONFIGURATION;
	}

	private final JFrame frame;
	private final JPanel panel;
	private final ViewPanel viewPanel;

	private Model model;

	private FrameEditorPanel frameEditorPanel;
	private AnimationPanel animationEditorPanel;
	private AnimationPreviewController animationPreviewController;

	public ModelMaker(final Configuration configuration) {
		CONFIGURATION = configuration;

		// Setup
		this.frame = new JFrame();
		PARENT_FRAME = frame;

		this.panel = new JPanel();

		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(new GridBagLayout());

		// Frame Modifier
		this.viewPanel = new ViewPanel();
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

		panel.add(new TopMenuBar(this), topMenuBarPanel_constraints);

		// Final
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);

		viewPanel.requestFocus();
	}

	public void setup(final Model model) {
		frame.setTitle(model.getName());
		this.model = model;

		if (frameEditorPanel != null) {
			setModel(model);
			return;
		}

		viewPanel.setModel(model);

		// Editor Panels
		this.frameEditorPanel = new FrameEditorPanel(model, viewPanel);
		frameEditorPanel.setVisible(true);

		this.animationEditorPanel = new AnimationPanel(model);
		animationEditorPanel.setVisible(true);

		final SpriteLibraryPanel spriteLibraryPanel = new SpriteLibraryPanel(viewPanel);
		spriteLibraryPanel.setVisible(true);

		if (ModelMaker.getConfiguration().getDefaultSpriteLibraryPath() != null && !ModelMaker.getConfiguration().getDefaultSpriteLibraryPath().trim().isEmpty())
			spriteLibraryPanel.setSpriteLibrary(SpriteLibraryReader.read(ModelMaker.getConfiguration().getDefaultSpriteLibraryPath()));

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
		this.animationPreviewController = new AnimationPreviewController(animationEditorPanel, viewPanel, model);
		final HardpointSelectionEditingController hardpointSelectionEditingController = new HardpointSelectionEditingController(viewPanel, frameEditorPanel.getHardpointTable());

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
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_Q) {
					hardpointSelectionEditingController.setField(HardpointField.X);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_W) {
					hardpointSelectionEditingController.setField(HardpointField.Y);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_E) {
					hardpointSelectionEditingController.setField(HardpointField.Z);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_R) {
					hardpointSelectionEditingController.setField(HardpointField.ROT);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
					hardpointSelectionEditingController.addToField(1);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
					hardpointSelectionEditingController.addToField(-1);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_V) {
					viewPanel.setRenderPoints(!viewPanel.renderPoints());
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_C) {
					viewPanel.setRenderImages(!viewPanel.renderImages());
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

		viewPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(final MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(final MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(final MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(final MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					final int rowIndex = viewPanel.select(e.getX(), e.getY());

					if (rowIndex >= 0) {
						viewPanel.repaint();
						frameEditorPanel.getHardpointTable().changeSelection(rowIndex, 0, false, false);
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					viewPanel.unselect();
					frameEditorPanel.getHardpointTable().clearSelection();
					viewPanel.repaint();
				}
			}
		});

		viewPanel.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(final MouseWheelEvent e) {
				final int sign = (e.getPreciseWheelRotation() < 0) ? 1 : -1;

				hardpointSelectionEditingController.addToField(1 * sign);
			}
		});

		panel.validate();
		panel.repaint();
	}

	public Model getModel() {
		return model;
	}

	private void setModel(final Model model) {
		viewPanel.setModel(model);
		frameEditorPanel.setModel(model);
		animationEditorPanel.setModel(model);
		animationPreviewController.setModel(model);
	}
}

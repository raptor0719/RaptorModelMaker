package raptor.modelMaker.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import raptor.modelMaker.components.TopMenuBar;
import raptor.modelMaker.components.ViewPanel;
import raptor.modelMaker.model.Model;

public class ModelMaker {
	private Model model;

	public ModelMaker() {
		this.model = new Model("default_name");
		model.addHardpoint("test1");
		model.addHardpoint("test2");
		model.addHardpoint("test3");

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

		// Setup
		final JFrame frame = new JFrame();
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
		final ViewPanel frameModifierPanel = new ViewPanel(model);
		final GridBagConstraints frameModifierPanel_constraints = new GridBagConstraints();
		frameModifierPanel_constraints.gridx = 0;
		frameModifierPanel_constraints.gridy = 1;
		frameModifierPanel_constraints.gridwidth = 1;
		frameModifierPanel_constraints.gridheight = 3;
		frameModifierPanel_constraints.weightx = 1.0;
		frameModifierPanel_constraints.weighty = 1.0;
		frameModifierPanel_constraints.fill = GridBagConstraints.BOTH;
		frameModifierPanel_constraints.anchor = GridBagConstraints.CENTER;

		frameModifierPanel.setBackground(Color.white);
		frameModifierPanel.setVisible(true);

		frameModifierPanel.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
					frameModifierPanel.rotateY(15);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
					frameModifierPanel.rotateY(-15);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
					frameModifierPanel.rotateZ(-15);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
					frameModifierPanel.rotateZ(15);
				}
				frameModifierPanel.repaint();
				System.out.println(frameModifierPanel.getViewPlane());
			}
		});

		panel.add(frameModifierPanel, frameModifierPanel_constraints);

		// Final
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);

		frameModifierPanel.requestFocus();
	}
}

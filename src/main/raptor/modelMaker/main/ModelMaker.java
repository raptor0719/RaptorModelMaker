package raptor.modelMaker.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import raptor.modelMaker.main.components.TopMenuBar;

public class ModelMaker {
	public ModelMaker() {
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
		final JPanel frameModifierPanel = new JPanel();
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

		panel.add(frameModifierPanel, frameModifierPanel_constraints);

		// Final
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);
	}
}

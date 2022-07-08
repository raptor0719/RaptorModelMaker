package raptor.modelMaker.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import raptor.modelMaker.model.Frame;
import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class FrameEditorPanel extends JPanel {
	private final HardpointTable hardpointTable;
	private final FrameChooser frameChooser;

	private Model model;

	public FrameEditorPanel(final Model model, final ViewPanel viewPanel) {
		this.model = model;

		this.setLayout(new GridBagLayout());

		// Create components
		this.hardpointTable = new HardpointTable(model, viewPanel);
		hardpointTable.setVisible(true);

		final JScrollPane hardpointTableScroller = new JScrollPane(hardpointTable);
		final GridBagConstraints hardpointTableScroller_constraints = new GridBagConstraints();
		hardpointTableScroller_constraints.gridx = 0;
		hardpointTableScroller_constraints.gridy = 2;
		hardpointTableScroller_constraints.gridwidth = 10;
		hardpointTableScroller_constraints.gridheight = 10;
		hardpointTableScroller_constraints.weightx = 1.0;
		hardpointTableScroller_constraints.weighty = 0.5;
		hardpointTableScroller_constraints.fill = GridBagConstraints.BOTH;
		hardpointTableScroller_constraints.anchor = GridBagConstraints.CENTER;
		hardpointTableScroller.setPreferredSize(new Dimension(400, 100));
		hardpointTableScroller.setVisible(true);

		add(hardpointTableScroller, hardpointTableScroller_constraints);

		final JButton hardpointDeleteButton = new JButton("Delete");
		hardpointDeleteButton.addActionListener(new HardpointDeleteActionListener());
		final GridBagConstraints hardpointDeleteButton_constraints = new GridBagConstraints();
		hardpointDeleteButton_constraints.gridx = 0;
		hardpointDeleteButton_constraints.gridy = 0;
		hardpointDeleteButton_constraints.gridwidth = 2;
		hardpointDeleteButton_constraints.gridheight = 2;
		hardpointDeleteButton_constraints.weightx = 0.0;
		hardpointDeleteButton_constraints.weighty = 0.0;
		hardpointDeleteButton_constraints.fill = GridBagConstraints.NONE;
		hardpointDeleteButton_constraints.anchor = GridBagConstraints.CENTER;
		hardpointDeleteButton.setVisible(true);

		add(hardpointDeleteButton, hardpointDeleteButton_constraints);

		final JButton copyDepthToAllFramesButton = new JButton("Copy Depth");
		copyDepthToAllFramesButton.addActionListener(new CopyDepthToAllFramesActionListener());
		final GridBagConstraints copyDepthToAllFramesButton_constraints = new GridBagConstraints();
		copyDepthToAllFramesButton_constraints.gridx = 2;
		copyDepthToAllFramesButton_constraints.gridy = 0;
		copyDepthToAllFramesButton_constraints.gridwidth = 2;
		copyDepthToAllFramesButton_constraints.gridheight = 2;
		copyDepthToAllFramesButton_constraints.weightx = 0.0;
		copyDepthToAllFramesButton_constraints.weighty = 0.0;
		copyDepthToAllFramesButton_constraints.fill = GridBagConstraints.NONE;
		copyDepthToAllFramesButton_constraints.anchor = GridBagConstraints.CENTER;
		copyDepthToAllFramesButton.setVisible(true);

		add(copyDepthToAllFramesButton, copyDepthToAllFramesButton_constraints);

		final JTextField hardpointAddNameField = new JTextField();
		final GridBagConstraints hardpointAddNameField_constraints = new GridBagConstraints();
		hardpointAddNameField_constraints.gridx = 6;
		hardpointAddNameField_constraints.gridy = 0;
		hardpointAddNameField_constraints.gridwidth = 2;
		hardpointAddNameField_constraints.gridheight = 2;
		hardpointAddNameField_constraints.weightx = 0.0;
		hardpointAddNameField_constraints.weighty = 0.0;
		hardpointAddNameField_constraints.insets = new Insets(5, 100, 5, 10);
		hardpointAddNameField_constraints.fill = GridBagConstraints.HORIZONTAL;
		hardpointAddNameField_constraints.anchor = GridBagConstraints.CENTER;
		hardpointAddNameField.setVisible(true);

		add(hardpointAddNameField, hardpointAddNameField_constraints);

		final JButton hardpointAddButton = new JButton("Add");
		hardpointAddButton.addActionListener(new HardpointAddActionListener(hardpointAddNameField));
		final GridBagConstraints hardpointAddButton_constraints = new GridBagConstraints();
		hardpointAddButton_constraints.gridx = 8;
		hardpointAddButton_constraints.gridy = 0;
		hardpointAddButton_constraints.gridwidth = 2;
		hardpointAddButton_constraints.gridheight = 2;
		hardpointAddButton_constraints.weightx = 0.0;
		hardpointAddButton_constraints.weighty = 0.0;
		hardpointAddButton_constraints.fill = GridBagConstraints.NONE;
		hardpointAddButton_constraints.anchor = GridBagConstraints.CENTER;
		hardpointAddButton.setVisible(true);

		add(hardpointAddButton, hardpointAddButton_constraints);

		this.frameChooser = new FrameChooser(model);
		frameChooser.setVisible(true);

		final JScrollPane frameChooserScroller = new JScrollPane(frameChooser);
		final GridBagConstraints frameChooserScroller_constraints = new GridBagConstraints();
		frameChooserScroller_constraints.gridx = 0;
		frameChooserScroller_constraints.gridy = 13;
		frameChooserScroller_constraints.gridwidth = 7;
		frameChooserScroller_constraints.gridheight = 12;
		frameChooserScroller_constraints.weightx = 1.0;
		frameChooserScroller_constraints.weighty = 0.5;
		frameChooserScroller_constraints.fill = GridBagConstraints.BOTH;
		frameChooserScroller_constraints.anchor = GridBagConstraints.CENTER;
		frameChooserScroller.setVisible(true);

		add(frameChooserScroller, frameChooserScroller_constraints);

		final JTextField frameAddNameField = new JTextField();
		final GridBagConstraints frameAddNameField_constraints = new GridBagConstraints();
		frameAddNameField_constraints.gridx = 7;
		frameAddNameField_constraints.gridy = 13;
		frameAddNameField_constraints.gridwidth = 3;
		frameAddNameField_constraints.gridheight = 2;
		frameAddNameField_constraints.weightx = 0.0;
		frameAddNameField_constraints.weighty = 0.0;
		frameAddNameField_constraints.insets = new Insets(5, 10, 5, 10);
		frameAddNameField_constraints.fill = GridBagConstraints.HORIZONTAL;
		frameAddNameField_constraints.anchor = GridBagConstraints.CENTER;
		frameAddNameField.setVisible(true);

		add(frameAddNameField, frameAddNameField_constraints);

		final JButton frameAddButton = new JButton("Add");
		frameAddButton.addActionListener(new FrameAddActionListener(frameAddNameField));
		final GridBagConstraints frameAddButton_constraints = new GridBagConstraints();
		frameAddButton_constraints.gridx = 8;
		frameAddButton_constraints.gridy = 15;
		frameAddButton_constraints.gridwidth = 2;
		frameAddButton_constraints.gridheight = 2;
		frameAddButton_constraints.weightx = 0.0;
		frameAddButton_constraints.weighty = 0.0;
		frameAddButton_constraints.fill = GridBagConstraints.HORIZONTAL;
		frameAddButton_constraints.anchor = GridBagConstraints.CENTER;
		frameAddButton_constraints.insets = new Insets(0, 0, 0, 10);
		frameAddButton.setVisible(true);

		add(frameAddButton, frameAddButton_constraints);

		final JButton frameLoadButton = new JButton("Load");
		frameLoadButton.addActionListener(new FrameLoadActionListener());
		final GridBagConstraints frameLoadButton_constraints = new GridBagConstraints();
		frameLoadButton_constraints.gridx = 7;
		frameLoadButton_constraints.gridy = 17;
		frameLoadButton_constraints.gridwidth = 3;
		frameLoadButton_constraints.gridheight = 3;
		frameLoadButton_constraints.weightx = 0.0;
		frameLoadButton_constraints.weighty = 0.5;
		frameLoadButton_constraints.fill = GridBagConstraints.BOTH;
		frameLoadButton_constraints.anchor = GridBagConstraints.CENTER;
		frameLoadButton_constraints.insets = new Insets(20, 10, 20, 10);
		frameLoadButton.setVisible(true);

		add(frameLoadButton, frameLoadButton_constraints);

		final JButton frameSaveButton = new JButton("Save");
		frameSaveButton.addActionListener(new FrameSaveActionListener());
		final GridBagConstraints frameSaveButton_constraints = new GridBagConstraints();
		frameSaveButton_constraints.gridx = 7;
		frameSaveButton_constraints.gridy = 20;
		frameSaveButton_constraints.gridwidth = 3;
		frameSaveButton_constraints.gridheight = 3;
		frameSaveButton_constraints.weightx = 0.0;
		frameSaveButton_constraints.weighty = 0.5;
		frameSaveButton_constraints.fill = GridBagConstraints.BOTH;
		frameSaveButton_constraints.anchor = GridBagConstraints.CENTER;
		frameSaveButton_constraints.insets = new Insets(0, 10, 20, 10);
		frameSaveButton.setVisible(true);

		add(frameSaveButton, frameSaveButton_constraints);

		final JButton frameDeleteButton = new JButton("Delete");
		frameDeleteButton.addActionListener(new FrameDeleteActionListener());
		final GridBagConstraints frameDeleteButton_constraints = new GridBagConstraints();
		frameDeleteButton_constraints.gridx = 7;
		frameDeleteButton_constraints.gridy = 23;
		frameDeleteButton_constraints.gridwidth = 2;
		frameDeleteButton_constraints.gridheight = 2;
		frameDeleteButton_constraints.weightx = 0.0;
		frameDeleteButton_constraints.weighty = 0.1;
		frameDeleteButton_constraints.fill = GridBagConstraints.NONE;
		frameDeleteButton_constraints.anchor = GridBagConstraints.WEST;
		frameDeleteButton_constraints.insets = new Insets(0, 10, 0, 0);
		frameDeleteButton.setVisible(true);

		add(frameDeleteButton, frameDeleteButton_constraints);
	}

	public void setModel(final Model model) {
		hardpointTable.setModel(model);
		frameChooser.setModel(model);
		this.model = model;
	}

	public HardpointTable getHardpointTable() {
		return hardpointTable;
	}

	private class CopyDepthToAllFramesActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			for (final Frame frame : model.getFrames()) {
				for (final Hardpoint hardpoint : model.getHardpoints()) {
					frame.getSavedPosition(hardpoint.getName()).setDepth(hardpoint.getDrawDepth());
				}
			}
		}
	}

	private class HardpointDeleteActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			final int selectedRowIndex = hardpointTable.getSelectedRow();

			if (selectedRowIndex < 0)
				return;

			final String hardpointName = (String) hardpointTable.getModel().getValueAt(selectedRowIndex, 0);

			model.removeHardpoint(hardpointName);

			hardpointTable.modelChanged();
		}
	}

	private class HardpointAddActionListener implements ActionListener {
		private final JTextField nameField;

		public HardpointAddActionListener(final JTextField nameField) {
			this.nameField = nameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final String name = nameField.getText();

			if (name == null || name.isEmpty())
				return;

			if (model.isHardpointWithName(name))
				return;

			model.addHardpoint(name);

			hardpointTable.modelChanged();

			nameField.setText("");
		}
	}

	private class FrameAddActionListener implements ActionListener {
		private final JTextField nameField;

		public FrameAddActionListener(final JTextField nameField) {
			this.nameField = nameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final String name = nameField.getText();

			if (name == null || name.isEmpty())
				return;

			if (model.isFrameWithName(name))
				return;

			model.addFrame(name);

			frameChooser.modelChanged();

			nameField.setText("");
		}
	}

	private class FrameLoadActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			final Frame selected = frameChooser.getSelectedValue();

			if (selected == null)
				return;

			model.loadFrame(selected.getName());

			hardpointTable.modelChanged();
		}
	}

	private class FrameSaveActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			final Frame selected = frameChooser.getSelectedValue();

			if (selected == null)
				return;

			for (final Hardpoint h : model.getHardpoints())
				selected.saveHardpoint(h);
		}
	}

	private class FrameDeleteActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			final Frame selected = frameChooser.getSelectedValue();

			if (selected == null)
				return;

			model.removeFrame(selected.getName());

			frameChooser.modelChanged();
			frameChooser.clearSelection();
		}
	}
}

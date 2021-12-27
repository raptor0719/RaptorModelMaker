package raptor.modelMaker.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import raptor.modelMaker.model.Model;

public class FrameEditorPanel extends JPanel {
	private final HardpointTable hardpointTable;

	private Model model;

	public FrameEditorPanel(final Model model, final JComponent redrawOnChange) {
		this.model = model;

		this.setLayout(new GridBagLayout());

		// Create components
		this.hardpointTable = new HardpointTable(model, redrawOnChange);
		final GridBagConstraints hardpointTable_constraints = new GridBagConstraints();
		hardpointTable_constraints.gridx = 0;
		hardpointTable_constraints.gridy = 0;
		hardpointTable_constraints.gridwidth = 10;
		hardpointTable_constraints.gridheight = 10;
		hardpointTable_constraints.weightx = 1.0;
		hardpointTable_constraints.weighty = 1.0;
		hardpointTable_constraints.fill = GridBagConstraints.BOTH;
		hardpointTable_constraints.anchor = GridBagConstraints.CENTER;
		hardpointTable.setVisible(true);

		add(hardpointTable, hardpointTable_constraints);

		final JButton hardpointDeleteButton = new JButton("Delete");
		hardpointDeleteButton.addActionListener(new HardpointDeleteActionListener());

		final GridBagConstraints hardpointDeleteButton_constraints = new GridBagConstraints();
		hardpointDeleteButton_constraints.gridx = 0;
		hardpointDeleteButton_constraints.gridy = 11;
		hardpointDeleteButton_constraints.gridwidth = 2;
		hardpointDeleteButton_constraints.gridheight = 2;
		hardpointDeleteButton_constraints.weightx = 0.1;
		hardpointDeleteButton_constraints.weighty = 0.0;
		hardpointDeleteButton_constraints.fill = GridBagConstraints.BOTH;
		hardpointDeleteButton_constraints.anchor = GridBagConstraints.CENTER;
		hardpointDeleteButton.setVisible(true);

		add(hardpointDeleteButton, hardpointDeleteButton_constraints);

		final JTextField hardpointAddNameField = new JTextField();
		final GridBagConstraints hardpointAddNameField_constraints = new GridBagConstraints();
		hardpointAddNameField_constraints.gridx = 5;
		hardpointAddNameField_constraints.gridy = 11;
		hardpointAddNameField_constraints.gridwidth = 3;
		hardpointAddNameField_constraints.gridheight = 2;
		hardpointAddNameField_constraints.weightx = 0.5;
		hardpointAddNameField_constraints.weighty = 0.0;
		hardpointAddNameField_constraints.fill = GridBagConstraints.BOTH;
		hardpointAddNameField_constraints.anchor = GridBagConstraints.CENTER;
		hardpointAddNameField.setVisible(true);

		add(hardpointAddNameField, hardpointAddNameField_constraints);

		final JButton hardpointAddButton = new JButton("Add");
		hardpointAddButton.addActionListener(new HardpointAddActionListener(hardpointAddNameField));

		final GridBagConstraints hardpointAddButton_constraints = new GridBagConstraints();
		hardpointAddButton_constraints.gridx = 8;
		hardpointAddButton_constraints.gridy = 11;
		hardpointAddButton_constraints.gridwidth = 2;
		hardpointAddButton_constraints.gridheight = 2;
		hardpointAddButton_constraints.weightx = 0.1;
		hardpointAddButton_constraints.weighty = 0.0;
		hardpointAddButton_constraints.fill = GridBagConstraints.BOTH;
		hardpointAddButton_constraints.anchor = GridBagConstraints.CENTER;
		hardpointAddButton.setVisible(true);

		add(hardpointAddButton, hardpointAddButton_constraints);
	}

	public void setModel(final Model model) {
		hardpointTable.setModel(model);
		this.model = model;
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
		public void actionPerformed(final ActionEvent arg0) {
			final String name = nameField.getText();

			if (name == null || name.isEmpty())
				return;

			if (model.isHardpointWithName(name))
				return;

			model.addHardpoint(name, 0);

			hardpointTable.modelChanged();
		}
	}
}

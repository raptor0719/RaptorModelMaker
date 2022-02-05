package raptor.modelMaker.components.spriteLibrary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import raptor.modelMaker.main.ModelMaker;
import raptor.modelMaker.spriteLibrary.SpriteLibrary;
import raptor.modelMaker.spriteLibrary.SpriteLibraryReader;

public class SpriteLibraryPanel extends JPanel {
	private final SpriteLibraryEditorPanel spriteLibraryEditorPanel;

	private SpriteLibrary spriteLibrary;

	public SpriteLibraryPanel(final JComponent redrawOnChange) {
		super();

		this.setLayout(new GridBagLayout());

		final JTextField spriteLibraryNameField = new JTextField();
		final GridBagConstraints spriteLibraryNameField_constraints = new GridBagConstraints();
		spriteLibraryNameField_constraints.gridx = 0;
		spriteLibraryNameField_constraints.gridy = 0;
		spriteLibraryNameField_constraints.gridwidth = 3;
		spriteLibraryNameField_constraints.gridheight = 2;
		spriteLibraryNameField_constraints.weightx = 0.0;
		spriteLibraryNameField_constraints.weighty = 0.0;
		spriteLibraryNameField_constraints.fill = GridBagConstraints.BOTH;
		spriteLibraryNameField_constraints.anchor = GridBagConstraints.CENTER;
		spriteLibraryNameField_constraints.insets = new Insets(3, 3, 3, 3);
		spriteLibraryNameField.setVisible(true);

		add(spriteLibraryNameField, spriteLibraryNameField_constraints);

		final JButton createNewSpriteLibraryButton = new JButton("Create New");
		createNewSpriteLibraryButton.addActionListener(new CreateNewSpriteLibraryActionListener(spriteLibraryNameField));
		final GridBagConstraints createNewSpriteLibraryButton_constraints = new GridBagConstraints();
		createNewSpriteLibraryButton_constraints.gridx = 0;
		createNewSpriteLibraryButton_constraints.gridy = 0;
		createNewSpriteLibraryButton_constraints.gridwidth = 3;
		createNewSpriteLibraryButton_constraints.gridheight = 2;
		createNewSpriteLibraryButton_constraints.weightx = 0.0;
		createNewSpriteLibraryButton_constraints.weighty = 0.0;
		createNewSpriteLibraryButton_constraints.fill = GridBagConstraints.BOTH;
		createNewSpriteLibraryButton_constraints.anchor = GridBagConstraints.CENTER;
		createNewSpriteLibraryButton_constraints.insets = new Insets(3, 3, 3, 3);
		createNewSpriteLibraryButton.setVisible(true);

		add(createNewSpriteLibraryButton, createNewSpriteLibraryButton_constraints);

		final JButton renameSpriteLibraryButton = new JButton("Rename");
		renameSpriteLibraryButton.addActionListener(new RenameSpriteLibraryActionListener(spriteLibraryNameField));
		final GridBagConstraints renameSpriteLibraryButton_constraints = new GridBagConstraints();
		renameSpriteLibraryButton_constraints.gridx = 0;
		renameSpriteLibraryButton_constraints.gridy = 0;
		renameSpriteLibraryButton_constraints.gridwidth = 3;
		renameSpriteLibraryButton_constraints.gridheight = 2;
		renameSpriteLibraryButton_constraints.weightx = 0.0;
		renameSpriteLibraryButton_constraints.weighty = 0.0;
		renameSpriteLibraryButton_constraints.fill = GridBagConstraints.BOTH;
		renameSpriteLibraryButton_constraints.anchor = GridBagConstraints.CENTER;
		renameSpriteLibraryButton_constraints.insets = new Insets(3, 3, 3, 3);
		renameSpriteLibraryButton.setVisible(true);

		add(renameSpriteLibraryButton, renameSpriteLibraryButton_constraints);

		final JButton loadSpriteLibraryButton = new JButton("Load");
		loadSpriteLibraryButton.addActionListener(new LoadSpriteLibraryActionListener());
		final GridBagConstraints loadSpriteLibraryButton_constraints = new GridBagConstraints();
		loadSpriteLibraryButton_constraints.gridx = 0;
		loadSpriteLibraryButton_constraints.gridy = 0;
		loadSpriteLibraryButton_constraints.gridwidth = 3;
		loadSpriteLibraryButton_constraints.gridheight = 2;
		loadSpriteLibraryButton_constraints.weightx = 0.0;
		loadSpriteLibraryButton_constraints.weighty = 0.0;
		loadSpriteLibraryButton_constraints.fill = GridBagConstraints.BOTH;
		loadSpriteLibraryButton_constraints.anchor = GridBagConstraints.CENTER;
		loadSpriteLibraryButton_constraints.insets = new Insets(3, 3, 3, 3);
		loadSpriteLibraryButton.setVisible(true);

		add(loadSpriteLibraryButton, loadSpriteLibraryButton_constraints);

		// SpriteLibraryEditorPanel
		this.spriteLibraryEditorPanel = new SpriteLibraryEditorPanel(redrawOnChange);
		final GridBagConstraints spriteLibraryEditorPanel_constraints = new GridBagConstraints();
		spriteLibraryEditorPanel_constraints.gridx = 0;
		spriteLibraryEditorPanel_constraints.gridy = 0;
		spriteLibraryEditorPanel_constraints.gridwidth = 3;
		spriteLibraryEditorPanel_constraints.gridheight = 2;
		spriteLibraryEditorPanel_constraints.weightx = 0.0;
		spriteLibraryEditorPanel_constraints.weighty = 0.0;
		spriteLibraryEditorPanel_constraints.fill = GridBagConstraints.BOTH;
		spriteLibraryEditorPanel_constraints.anchor = GridBagConstraints.CENTER;
		spriteLibraryEditorPanel_constraints.insets = new Insets(3, 3, 3, 3);
		spriteLibraryEditorPanel.setVisible(true);

		add(spriteLibraryEditorPanel, spriteLibraryEditorPanel_constraints);
	}

	public void setSpriteLibrary(final SpriteLibrary spriteLibrary) {
		this.spriteLibrary = spriteLibrary;
		spriteLibraryEditorPanel.setSpriteLibrary(spriteLibrary);
	}

	public SpriteLibrary getSpriteLibrary() {
		return spriteLibrary;
	}

	private class CreateNewSpriteLibraryActionListener implements ActionListener {
		private final JFileChooser fileChooser;
		private final JTextField nameField;

		public CreateNewSpriteLibraryActionListener(final JTextField nameField) {
			this.fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			this.nameField = nameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			int fileChooserReturn = JFileChooser.ERROR_OPTION;

			while (fileChooserReturn != JFileChooser.APPROVE_OPTION) {
				fileChooserReturn = fileChooser.showDialog(ModelMaker.getParentFrame(), "Select");

				if (fileChooserReturn == JFileChooser.CANCEL_OPTION)
					return;
			}

			setSpriteLibrary(new SpriteLibrary(nameField.getName(), fileChooser.getSelectedFile().getAbsolutePath()));
		}
	}

	private class RenameSpriteLibraryActionListener implements ActionListener {
		private final JTextField nameField;

		public RenameSpriteLibraryActionListener(final JTextField nameField) {
			this.nameField = nameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final String newName = nameField.getText();

			if (newName == null || newName.isEmpty())
				return;

			final SpriteLibrary spriteLibrary = getSpriteLibrary();
			spriteLibrary.setName(newName);

			setSpriteLibrary(spriteLibrary);
		}
	}

	private class LoadSpriteLibraryActionListener implements ActionListener {
		private final JFileChooser fileChooser;

		public LoadSpriteLibraryActionListener() {
			this.fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			int fileChooserReturn = JFileChooser.ERROR_OPTION;

			while (fileChooserReturn != JFileChooser.APPROVE_OPTION) {
				fileChooserReturn = fileChooser.showDialog(ModelMaker.getParentFrame(), "Load");

				if (fileChooserReturn == JFileChooser.CANCEL_OPTION)
					return;
			}

			setSpriteLibrary(SpriteLibraryReader.read(fileChooser.getSelectedFile().getAbsolutePath()));
		}
	}
}

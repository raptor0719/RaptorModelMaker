package raptor.modelMaker.components.spriteLibrary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import raptor.modelMaker.spriteLibrary.SpriteLibrary;

public class SpriteLibraryPanel extends JPanel {
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

		final JLabel spriteLibraryNameTitle = new JLabel("");
		final GridBagConstraints spriteLibraryNameTitle_constraints = new GridBagConstraints();
		spriteLibraryNameTitle_constraints.gridx = 0;
		spriteLibraryNameTitle_constraints.gridy = 0;
		spriteLibraryNameTitle_constraints.gridwidth = 3;
		spriteLibraryNameTitle_constraints.gridheight = 2;
		spriteLibraryNameTitle_constraints.weightx = 0.0;
		spriteLibraryNameTitle_constraints.weighty = 0.0;
		spriteLibraryNameTitle_constraints.fill = GridBagConstraints.BOTH;
		spriteLibraryNameTitle_constraints.anchor = GridBagConstraints.CENTER;
		spriteLibraryNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		spriteLibraryNameTitle.setVisible(true);

		add(spriteLibraryNameTitle, spriteLibraryNameTitle_constraints);

		final JButton saveSpriteLibraryButton = new JButton("Save");
		final GridBagConstraints saveSpriteLibraryButton_constraints = new GridBagConstraints();
		saveSpriteLibraryButton_constraints.gridx = 0;
		saveSpriteLibraryButton_constraints.gridy = 0;
		saveSpriteLibraryButton_constraints.gridwidth = 3;
		saveSpriteLibraryButton_constraints.gridheight = 2;
		saveSpriteLibraryButton_constraints.weightx = 0.0;
		saveSpriteLibraryButton_constraints.weighty = 0.0;
		saveSpriteLibraryButton_constraints.fill = GridBagConstraints.BOTH;
		saveSpriteLibraryButton_constraints.anchor = GridBagConstraints.CENTER;
		saveSpriteLibraryButton_constraints.insets = new Insets(3, 3, 3, 3);
		saveSpriteLibraryButton.setVisible(true);

		add(saveSpriteLibraryButton, saveSpriteLibraryButton_constraints);

		// SpriteLibraryEditorPanel
	}

	public SpriteLibrary getSpriteLibrary() {
		return spriteLibrary;
	}
}

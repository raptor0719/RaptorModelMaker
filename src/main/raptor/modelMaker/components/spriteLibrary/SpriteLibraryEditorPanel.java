package raptor.modelMaker.components.spriteLibrary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import raptor.modelMaker.spriteLibrary.SpriteCollection;

public class SpriteLibraryEditorPanel extends JPanel {
	private final JComboBox<SpriteCollection> spriteCollectionChooser;
	private final SpriteCollectionEditorPanel spriteCollectionEditorPanel;

	public SpriteLibraryEditorPanel(final JComponent redrawOnChange) {
		super();

		this.setLayout(new GridBagLayout());

		this.spriteCollectionChooser = new JComboBox<SpriteCollection>();
		final GridBagConstraints spriteCollectionChooser_constraints = new GridBagConstraints();
		spriteCollectionChooser_constraints.gridx = 0;
		spriteCollectionChooser_constraints.gridy = 0;
		spriteCollectionChooser_constraints.gridwidth = 3;
		spriteCollectionChooser_constraints.gridheight = 2;
		spriteCollectionChooser_constraints.weightx = 0.0;
		spriteCollectionChooser_constraints.weighty = 0.0;
		spriteCollectionChooser_constraints.fill = GridBagConstraints.BOTH;
		spriteCollectionChooser_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionChooser_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionChooser.setVisible(true);

		add(spriteCollectionChooser, spriteCollectionChooser_constraints);

		final JTextField spriteCollectionNameField = new JTextField();
		final GridBagConstraints spriteCollectionNameField_constraints = new GridBagConstraints();
		spriteCollectionNameField_constraints.gridx = 0;
		spriteCollectionNameField_constraints.gridy = 0;
		spriteCollectionNameField_constraints.gridwidth = 3;
		spriteCollectionNameField_constraints.gridheight = 2;
		spriteCollectionNameField_constraints.weightx = 0.0;
		spriteCollectionNameField_constraints.weighty = 0.0;
		spriteCollectionNameField_constraints.fill = GridBagConstraints.BOTH;
		spriteCollectionNameField_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionNameField_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionNameField.setVisible(true);

		add(spriteCollectionNameField, spriteCollectionNameField_constraints);

		final JButton deleteSpriteCollectionButton = new JButton("Delete");
		final GridBagConstraints deleteSpriteCollectionButton_constraints = new GridBagConstraints();
		deleteSpriteCollectionButton_constraints.gridx = 0;
		deleteSpriteCollectionButton_constraints.gridy = 0;
		deleteSpriteCollectionButton_constraints.gridwidth = 3;
		deleteSpriteCollectionButton_constraints.gridheight = 2;
		deleteSpriteCollectionButton_constraints.weightx = 0.0;
		deleteSpriteCollectionButton_constraints.weighty = 0.0;
		deleteSpriteCollectionButton_constraints.fill = GridBagConstraints.BOTH;
		deleteSpriteCollectionButton_constraints.anchor = GridBagConstraints.CENTER;
		deleteSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		deleteSpriteCollectionButton.setVisible(true);

		add(deleteSpriteCollectionButton, deleteSpriteCollectionButton_constraints);

		final JButton selectSpriteCollectionButton = new JButton("Select");
		final GridBagConstraints selectSpriteCollectionButton_constraints = new GridBagConstraints();
		selectSpriteCollectionButton_constraints.gridx = 0;
		selectSpriteCollectionButton_constraints.gridy = 0;
		selectSpriteCollectionButton_constraints.gridwidth = 3;
		selectSpriteCollectionButton_constraints.gridheight = 2;
		selectSpriteCollectionButton_constraints.weightx = 0.0;
		selectSpriteCollectionButton_constraints.weighty = 0.0;
		selectSpriteCollectionButton_constraints.fill = GridBagConstraints.BOTH;
		selectSpriteCollectionButton_constraints.anchor = GridBagConstraints.CENTER;
		selectSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectSpriteCollectionButton.setVisible(true);

		add(selectSpriteCollectionButton, selectSpriteCollectionButton_constraints);

		final JButton addSpriteCollectionButton = new JButton("Add");
		final GridBagConstraints addSpriteCollectionButton_constraints = new GridBagConstraints();
		addSpriteCollectionButton_constraints.gridx = 0;
		addSpriteCollectionButton_constraints.gridy = 0;
		addSpriteCollectionButton_constraints.gridwidth = 3;
		addSpriteCollectionButton_constraints.gridheight = 2;
		addSpriteCollectionButton_constraints.weightx = 0.0;
		addSpriteCollectionButton_constraints.weighty = 0.0;
		addSpriteCollectionButton_constraints.fill = GridBagConstraints.BOTH;
		addSpriteCollectionButton_constraints.anchor = GridBagConstraints.CENTER;
		addSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		addSpriteCollectionButton.setVisible(true);

		add(addSpriteCollectionButton, addSpriteCollectionButton_constraints);

		final JLabel spriteCollectionNameTitle = new JLabel("");
		final GridBagConstraints spriteCollectionNameTitle_constraints = new GridBagConstraints();
		spriteCollectionNameTitle_constraints.gridx = 0;
		spriteCollectionNameTitle_constraints.gridy = 0;
		spriteCollectionNameTitle_constraints.gridwidth = 3;
		spriteCollectionNameTitle_constraints.gridheight = 2;
		spriteCollectionNameTitle_constraints.weightx = 0.0;
		spriteCollectionNameTitle_constraints.weighty = 0.0;
		spriteCollectionNameTitle_constraints.fill = GridBagConstraints.BOTH;
		spriteCollectionNameTitle_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionNameTitle.setVisible(true);

		add(spriteCollectionNameTitle, spriteCollectionNameTitle_constraints);

		final JButton reloadSpriteCollectionButton = new JButton("Reload");
		final GridBagConstraints reloadSpriteCollectionButton_constraints = new GridBagConstraints();
		reloadSpriteCollectionButton_constraints.gridx = 0;
		reloadSpriteCollectionButton_constraints.gridy = 0;
		reloadSpriteCollectionButton_constraints.gridwidth = 3;
		reloadSpriteCollectionButton_constraints.gridheight = 2;
		reloadSpriteCollectionButton_constraints.weightx = 0.0;
		reloadSpriteCollectionButton_constraints.weighty = 0.0;
		reloadSpriteCollectionButton_constraints.fill = GridBagConstraints.BOTH;
		reloadSpriteCollectionButton_constraints.anchor = GridBagConstraints.CENTER;
		reloadSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		reloadSpriteCollectionButton.setVisible(true);

		add(reloadSpriteCollectionButton, reloadSpriteCollectionButton_constraints);

		this.spriteCollectionEditorPanel = new SpriteCollectionEditorPanel(redrawOnChange);
		final GridBagConstraints spriteCollectionEditorPanel_constraints = new GridBagConstraints();
		spriteCollectionEditorPanel_constraints.gridx = 0;
		spriteCollectionEditorPanel_constraints.gridy = 0;
		spriteCollectionEditorPanel_constraints.gridwidth = 3;
		spriteCollectionEditorPanel_constraints.gridheight = 2;
		spriteCollectionEditorPanel_constraints.weightx = 0.0;
		spriteCollectionEditorPanel_constraints.weighty = 0.0;
		spriteCollectionEditorPanel_constraints.fill = GridBagConstraints.BOTH;
		spriteCollectionEditorPanel_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionEditorPanel_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionEditorPanel.setVisible(true);

		add(spriteCollectionEditorPanel, spriteCollectionEditorPanel_constraints);
	}
}

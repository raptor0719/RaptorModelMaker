package raptor.modelMaker.components.spriteLibrary;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;

import raptor.modelMaker.spriteLibrary.SpriteCollection;
import raptor.modelMaker.spriteLibrary.SpriteLibrary;
import raptor.modelMaker.spriteLibrary.SpriteLibraryReader;
import raptor.modelMaker.spriteLibrary.SpriteLibraryWriter;

public class SpriteLibraryEditorPanel extends JPanel {
	private final JComboBox<SpriteCollection> spriteCollectionChooser;
	private final SpriteCollectionEditorPanel spriteCollectionEditorPanel;
	private final JLabel spriteLibraryNameTitle;

	private SpriteLibrary spriteLibrary;

	public SpriteLibraryEditorPanel(final JComponent redrawOnChange) {
		super();

		this.setLayout(new GridBagLayout());

		this.spriteCollectionChooser = new JComboBox<SpriteCollection>();
		spriteCollectionChooser.setModel(new SpriteCollectionComboBoxModel(null));
		final GridBagConstraints spriteCollectionChooser_constraints = new GridBagConstraints();
		spriteCollectionChooser_constraints.gridx = 0;
		spriteCollectionChooser_constraints.gridy = 2;
		spriteCollectionChooser_constraints.gridwidth = 5;
		spriteCollectionChooser_constraints.gridheight = 2;
		spriteCollectionChooser_constraints.weightx = 1.0;
		spriteCollectionChooser_constraints.weighty = 0.0;
		spriteCollectionChooser_constraints.fill = GridBagConstraints.HORIZONTAL;
		spriteCollectionChooser_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionChooser_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionChooser.setVisible(true);

		add(spriteCollectionChooser, spriteCollectionChooser_constraints);

		final JTextField spriteCollectionNameField = new JTextField();
		final GridBagConstraints spriteCollectionNameField_constraints = new GridBagConstraints();
		spriteCollectionNameField_constraints.gridx = 5;
		spriteCollectionNameField_constraints.gridy = 2;
		spriteCollectionNameField_constraints.gridwidth = 5;
		spriteCollectionNameField_constraints.gridheight = 2;
		spriteCollectionNameField_constraints.weightx = 1.0;
		spriteCollectionNameField_constraints.weighty = 0.0;
		spriteCollectionNameField_constraints.fill = GridBagConstraints.HORIZONTAL;
		spriteCollectionNameField_constraints.anchor = GridBagConstraints.EAST;
		spriteCollectionNameField_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionNameField.setVisible(true);

		add(spriteCollectionNameField, spriteCollectionNameField_constraints);

		final JButton deleteSpriteCollectionButton = new JButton("Delete");
		deleteSpriteCollectionButton.addActionListener(new DeleteSpriteCollectionActionListener());
		final GridBagConstraints deleteSpriteCollectionButton_constraints = new GridBagConstraints();
		deleteSpriteCollectionButton_constraints.gridx = 0;
		deleteSpriteCollectionButton_constraints.gridy = 4;
		deleteSpriteCollectionButton_constraints.gridwidth = 1;
		deleteSpriteCollectionButton_constraints.gridheight = 2;
		deleteSpriteCollectionButton_constraints.weightx = 1.0;
		deleteSpriteCollectionButton_constraints.weighty = 0.0;
		deleteSpriteCollectionButton_constraints.fill = GridBagConstraints.NONE;
		deleteSpriteCollectionButton_constraints.anchor = GridBagConstraints.WEST;
		deleteSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		deleteSpriteCollectionButton.setVisible(true);

		add(deleteSpriteCollectionButton, deleteSpriteCollectionButton_constraints);

		final JButton selectSpriteCollectionButton = new JButton("Select");
		selectSpriteCollectionButton.addActionListener(new SelectSpriteCollectionActionListener());
		final GridBagConstraints selectSpriteCollectionButton_constraints = new GridBagConstraints();
		selectSpriteCollectionButton_constraints.gridx = 4;
		selectSpriteCollectionButton_constraints.gridy = 4;
		selectSpriteCollectionButton_constraints.gridwidth = 1;
		selectSpriteCollectionButton_constraints.gridheight = 2;
		selectSpriteCollectionButton_constraints.weightx = 1.0;
		selectSpriteCollectionButton_constraints.weighty = 0.0;
		selectSpriteCollectionButton_constraints.fill = GridBagConstraints.NONE;
		selectSpriteCollectionButton_constraints.anchor = GridBagConstraints.EAST;
		selectSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectSpriteCollectionButton.setVisible(true);

		add(selectSpriteCollectionButton, selectSpriteCollectionButton_constraints);

		final JButton addSpriteCollectionButton = new JButton("Add");
		addSpriteCollectionButton.addActionListener(new AddSpriteCollectionActionListener(spriteCollectionNameField));
		final GridBagConstraints addSpriteCollectionButton_constraints = new GridBagConstraints();
		addSpriteCollectionButton_constraints.gridx = 9;
		addSpriteCollectionButton_constraints.gridy = 4;
		addSpriteCollectionButton_constraints.gridwidth = 1;
		addSpriteCollectionButton_constraints.gridheight = 2;
		addSpriteCollectionButton_constraints.weightx = 1.0;
		addSpriteCollectionButton_constraints.weighty = 0.0;
		addSpriteCollectionButton_constraints.fill = GridBagConstraints.NONE;
		addSpriteCollectionButton_constraints.anchor = GridBagConstraints.EAST;
		addSpriteCollectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		addSpriteCollectionButton.setVisible(true);

		add(addSpriteCollectionButton, addSpriteCollectionButton_constraints);

		this.spriteLibraryNameTitle = new JLabel("");
		spriteLibraryNameTitle.setFont(new Font("Arial", Font.BOLD, 18));
		final GridBagConstraints spriteLibraryNameTitle_constraints = new GridBagConstraints();
		spriteLibraryNameTitle_constraints.gridx = 0;
		spriteLibraryNameTitle_constraints.gridy = 0;
		spriteLibraryNameTitle_constraints.gridwidth = 4;
		spriteLibraryNameTitle_constraints.gridheight = 2;
		spriteLibraryNameTitle_constraints.weightx = 1.0;
		spriteLibraryNameTitle_constraints.weighty = 0.0;
		spriteLibraryNameTitle_constraints.fill = GridBagConstraints.HORIZONTAL;
		spriteLibraryNameTitle_constraints.anchor = GridBagConstraints.WEST;
		spriteLibraryNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		spriteLibraryNameTitle.setVisible(true);

		add(spriteLibraryNameTitle, spriteLibraryNameTitle_constraints);

		final JButton saveSpriteLibraryButton = new JButton("Save");
		saveSpriteLibraryButton.addActionListener(new SaveSpriteLibraryActionListener());
		final GridBagConstraints saveSpriteLibraryButton_constraints = new GridBagConstraints();
		saveSpriteLibraryButton_constraints.gridx = 5;
		saveSpriteLibraryButton_constraints.gridy = 0;
		saveSpriteLibraryButton_constraints.gridwidth = 1;
		saveSpriteLibraryButton_constraints.gridheight = 2;
		saveSpriteLibraryButton_constraints.weightx = 0.0;
		saveSpriteLibraryButton_constraints.weighty = 0.0;
		saveSpriteLibraryButton_constraints.fill = GridBagConstraints.HORIZONTAL;
		saveSpriteLibraryButton_constraints.anchor = GridBagConstraints.CENTER;
		saveSpriteLibraryButton_constraints.insets = new Insets(3, 3, 3, 3);
		saveSpriteLibraryButton.setVisible(true);

		add(saveSpriteLibraryButton, saveSpriteLibraryButton_constraints);

		this.spriteCollectionEditorPanel = new SpriteCollectionEditorPanel(this, redrawOnChange);
		final GridBagConstraints spriteCollectionEditorPanel_constraints = new GridBagConstraints();
		spriteCollectionEditorPanel_constraints.gridx = 0;
		spriteCollectionEditorPanel_constraints.gridy = 6;
		spriteCollectionEditorPanel_constraints.gridwidth = 10;
		spriteCollectionEditorPanel_constraints.gridheight = 14;
		spriteCollectionEditorPanel_constraints.weightx = 1.0;
		spriteCollectionEditorPanel_constraints.weighty = 1.0;
		spriteCollectionEditorPanel_constraints.fill = GridBagConstraints.BOTH;
		spriteCollectionEditorPanel_constraints.anchor = GridBagConstraints.CENTER;
		spriteCollectionEditorPanel_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionEditorPanel.setVisible(true);

		add(spriteCollectionEditorPanel, spriteCollectionEditorPanel_constraints);
	}

	public void setSpriteLibrary(final SpriteLibrary spriteLibrary) {
		this.spriteLibrary = spriteLibrary;

		final List<SpriteCollection> spriteCollections = (spriteLibrary == null) ? new ArrayList<SpriteCollection>() : spriteLibrary.getSpriteCollections();
		final String name = (spriteLibrary == null) ? "" : spriteLibrary.getName();

		spriteCollectionChooser.setModel(new SpriteCollectionComboBoxModel(spriteCollections));
		spriteLibraryNameTitle.setText(name);

		spriteCollectionEditorPanel.setSpriteCollection((SpriteCollection) spriteCollectionChooser.getSelectedItem());
	}

	public SpriteLibrary getSpriteLibrary() {
		return spriteLibrary;
	}

	private void refresh() {
		spriteCollectionChooser.setModel(new SpriteCollectionComboBoxModel(spriteLibrary.getSpriteCollections()));
		spriteCollectionEditorPanel.setSpriteCollection((SpriteCollection) spriteCollectionChooser.getSelectedItem());
	}

	private static class SpriteCollectionComboBoxModel implements ComboBoxModel<SpriteCollection> {
		private final List<SpriteCollection> spriteCollections;

		private SpriteCollection selected;

		public SpriteCollectionComboBoxModel(final List<SpriteCollection> spriteCollections) {
			this.spriteCollections = spriteCollections;
		}

		@Override
		public SpriteCollection getElementAt(final int index) {
			if (spriteCollections == null)
				return null;

			return (index < 0 || index >= spriteCollections.size()) ? null : spriteCollections.get(index);
		}

		@Override
		public int getSize() {
			return (spriteCollections == null) ? 0 : spriteCollections.size();
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public void setSelectedItem(final Object item) {
			if (!(item instanceof SpriteCollection))
				return;
			this.selected = (SpriteCollection) item;
		}

		@Override
		public void addListDataListener(final ListDataListener listDataListener) {
			// no-op
		}

		@Override
		public void removeListDataListener(final ListDataListener listDataListener) {
			// no-op
		}
	}

	private class DeleteSpriteCollectionActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteLibrary == null)
				return;

			final SpriteCollection spriteCollectionToDelete = (SpriteCollection) spriteCollectionChooser.getSelectedItem();

			spriteLibrary.removeSpriteCollection(spriteCollectionToDelete.getName());

			refresh();
		}
	}

	private class SelectSpriteCollectionActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteLibrary == null)
				return;

			spriteCollectionEditorPanel.setSpriteCollection((SpriteCollection) spriteCollectionChooser.getSelectedItem());
		}
	}

	private class AddSpriteCollectionActionListener implements ActionListener {
		private final JTextField nameField;

		public AddSpriteCollectionActionListener(final JTextField nameField) {
			this.nameField = nameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteLibrary == null)
				return;

			final SpriteCollection newSpriteCollection = new SpriteCollection(nameField.getText());
			newSpriteCollection.setSpriteImages(SpriteLibraryReader.loadImages(newSpriteCollection.getName(), spriteLibrary.getLocation()));

			spriteLibrary.addSpriteCollection(newSpriteCollection);
			spriteCollectionChooser.setModel(new SpriteCollectionComboBoxModel(spriteLibrary.getSpriteCollections()));
			nameField.setText("");
		}
	}

	private class SaveSpriteLibraryActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteLibrary == null)
				return;

			SpriteLibraryWriter.write(spriteLibrary, spriteLibrary.getLocation());
		}
	}
}

package raptor.modelMaker.components.spriteLibrary;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;

import raptor.modelMaker.main.ModelMaker;
import raptor.modelMaker.model.ViewDirection;
import raptor.modelMaker.spriteLibrary.Sprite;
import raptor.modelMaker.spriteLibrary.SpriteCollection;
import raptor.modelMaker.spriteLibrary.SpriteLibraryReader;

public class SpriteCollectionEditorPanel extends JPanel {
	private final JLabel spriteCollectionNameTitle;
	private final JComboBox<String> phaseChooser;
	private final JComboBox<ViewDirection> viewDirectionChooser;
	private final SpriteViewPanel spriteViewPanel;

	private SpriteCollection spriteCollection;

	public SpriteCollectionEditorPanel(final SpriteLibraryEditorPanel spriteLibraryEditorPanel, final JComponent redrawOnChange) {
		super();

		spriteCollection = null;

		this.setLayout(new GridBagLayout());

		this.spriteCollectionNameTitle = new JLabel("");
		spriteCollectionNameTitle.setFont(new Font("Arial", Font.BOLD, 16));
		final GridBagConstraints spriteCollectionNameTitle_constraints = new GridBagConstraints();
		spriteCollectionNameTitle_constraints.gridx = 0;
		spriteCollectionNameTitle_constraints.gridy = 0;
		spriteCollectionNameTitle_constraints.gridwidth = 4;
		spriteCollectionNameTitle_constraints.gridheight = 2;
		spriteCollectionNameTitle_constraints.weightx = 1.0;
		spriteCollectionNameTitle_constraints.weighty = 0.0;
		spriteCollectionNameTitle_constraints.fill = GridBagConstraints.HORIZONTAL;
		spriteCollectionNameTitle_constraints.anchor = GridBagConstraints.EAST;
		spriteCollectionNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		spriteCollectionNameTitle.setVisible(true);

		add(spriteCollectionNameTitle, spriteCollectionNameTitle_constraints);

		final JTextField phaseNameField = new JTextField();
		final GridBagConstraints phaseNameField_constraints = new GridBagConstraints();
		phaseNameField_constraints.gridx = 6;
		phaseNameField_constraints.gridy = 2;
		phaseNameField_constraints.gridwidth = 2;
		phaseNameField_constraints.gridheight = 2;
		phaseNameField_constraints.weightx = 1.0;
		phaseNameField_constraints.weighty = 0.0;
		phaseNameField_constraints.fill = GridBagConstraints.HORIZONTAL;
		phaseNameField_constraints.anchor = GridBagConstraints.WEST;
		phaseNameField_constraints.insets = new Insets(3, 3, 3, 3);
		phaseNameField.setVisible(true);

		add(phaseNameField, phaseNameField_constraints);

		final JButton phaseAddButton = new JButton("Add Phase");
		phaseAddButton.addActionListener(new PhaseAddActionListener(phaseNameField));
		final GridBagConstraints phaseAddButton_constraints = new GridBagConstraints();
		phaseAddButton_constraints.gridx = 10;
		phaseAddButton_constraints.gridy = 2;
		phaseAddButton_constraints.gridwidth = 2;
		phaseAddButton_constraints.gridheight = 2;
		phaseAddButton_constraints.weightx = 0.0;
		phaseAddButton_constraints.weighty = 0.0;
		phaseAddButton_constraints.fill = GridBagConstraints.NONE;
		phaseAddButton_constraints.anchor = GridBagConstraints.EAST;
		phaseAddButton_constraints.insets = new Insets(3, 3, 3, 3);
		phaseAddButton.setVisible(true);

		add(phaseAddButton, phaseAddButton_constraints);

		this.phaseChooser = new JComboBox<String>();
		phaseChooser.setModel(new PhaseChooserComboBoxModel());
		final GridBagConstraints phaseChooser_constraints = new GridBagConstraints();
		phaseChooser_constraints.gridx = 0;
		phaseChooser_constraints.gridy = 4;
		phaseChooser_constraints.gridwidth = 4;
		phaseChooser_constraints.gridheight = 2;
		phaseChooser_constraints.weightx = 1.0;
		phaseChooser_constraints.weighty = 0.0;
		phaseChooser_constraints.fill = GridBagConstraints.HORIZONTAL;
		phaseChooser_constraints.anchor = GridBagConstraints.WEST;
		phaseChooser_constraints.insets = new Insets(3, 3, 3, 3);
		phaseChooser.setVisible(true);

		add(phaseChooser, phaseChooser_constraints);

		this.viewDirectionChooser = new JComboBox<ViewDirection>();
		viewDirectionChooser.setModel(new ViewDirectionComboBoxModel());
		final GridBagConstraints viewDirectionChooser_constraints = new GridBagConstraints();
		viewDirectionChooser_constraints.gridx = 4;
		viewDirectionChooser_constraints.gridy = 4;
		viewDirectionChooser_constraints.gridwidth = 4;
		viewDirectionChooser_constraints.gridheight = 2;
		viewDirectionChooser_constraints.weightx = 1.0;
		viewDirectionChooser_constraints.weighty = 0.0;
		viewDirectionChooser_constraints.fill = GridBagConstraints.HORIZONTAL;
		viewDirectionChooser_constraints.anchor = GridBagConstraints.WEST;
		viewDirectionChooser_constraints.insets = new Insets(3, 3, 3, 3);
		viewDirectionChooser.setVisible(true);

		add(viewDirectionChooser, viewDirectionChooser_constraints);

		final JLabel viewDirectionNameTitle = new JLabel(((ViewDirection)viewDirectionChooser.getSelectedItem()).name());
		viewDirectionNameTitle.setFont(new Font("Arial", Font.BOLD, 14));
		final GridBagConstraints viewDirectionNameTitle_constraints = new GridBagConstraints();
		viewDirectionNameTitle_constraints.gridx = 0;
		viewDirectionNameTitle_constraints.gridy = 6;
		viewDirectionNameTitle_constraints.gridwidth = 6;
		viewDirectionNameTitle_constraints.gridheight = 2;
		viewDirectionNameTitle_constraints.weightx = 1.0;
		viewDirectionNameTitle_constraints.weighty = 0.0;
		viewDirectionNameTitle_constraints.fill = GridBagConstraints.HORIZONTAL;
		viewDirectionNameTitle_constraints.anchor = GridBagConstraints.CENTER;
		viewDirectionNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		viewDirectionNameTitle.setVisible(true);

		add(viewDirectionNameTitle, viewDirectionNameTitle_constraints);

		final JButton selectViewDirectionButton = new JButton("Select");
		selectViewDirectionButton.addActionListener(new SelectViewDirectionActionListener(viewDirectionNameTitle));
		final GridBagConstraints selectViewDirectionButton_constraints = new GridBagConstraints();
		selectViewDirectionButton_constraints.gridx = 7;
		selectViewDirectionButton_constraints.gridy = 6;
		selectViewDirectionButton_constraints.gridwidth = 1;
		selectViewDirectionButton_constraints.gridheight = 2;
		selectViewDirectionButton_constraints.weightx = 0.0;
		selectViewDirectionButton_constraints.weighty = 0.0;
		selectViewDirectionButton_constraints.fill = GridBagConstraints.NONE;
		selectViewDirectionButton_constraints.anchor = GridBagConstraints.EAST;
		selectViewDirectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectViewDirectionButton.setVisible(true);

		add(selectViewDirectionButton, selectViewDirectionButton_constraints);

		final JButton phaseRemoveButton = new JButton("Remove Phase");
		phaseRemoveButton.addActionListener(new PhaseRemoveActionListener(selectViewDirectionButton, redrawOnChange));
		final GridBagConstraints phaseRemoveButton_constraints = new GridBagConstraints();
		phaseRemoveButton_constraints.gridx = 0;
		phaseRemoveButton_constraints.gridy = 2;
		phaseRemoveButton_constraints.gridwidth = 4;
		phaseRemoveButton_constraints.gridheight = 2;
		phaseRemoveButton_constraints.weightx = 1.0;
		phaseRemoveButton_constraints.weighty = 0.0;
		phaseRemoveButton_constraints.fill = GridBagConstraints.HORIZONTAL;
		phaseRemoveButton_constraints.anchor = GridBagConstraints.EAST;
		phaseRemoveButton_constraints.insets = new Insets(3, 3, 3, 3);
		phaseRemoveButton.setVisible(true);

		add(phaseRemoveButton, phaseRemoveButton_constraints);

		final JTextField xAttachmentPointField = new JTextField();
		xAttachmentPointField.setHorizontalAlignment(JTextField.RIGHT);
		final GridBagConstraints xAttachmentPointField_constraints = new GridBagConstraints();
		xAttachmentPointField_constraints.gridx = 10;
		xAttachmentPointField_constraints.gridy = 4;
		xAttachmentPointField_constraints.gridwidth = 1;
		xAttachmentPointField_constraints.gridheight = 2;
		xAttachmentPointField_constraints.weightx = 1.0;
		xAttachmentPointField_constraints.weighty = 0.0;
		xAttachmentPointField_constraints.fill = GridBagConstraints.HORIZONTAL;
		xAttachmentPointField_constraints.anchor = GridBagConstraints.EAST;
		xAttachmentPointField_constraints.insets = new Insets(3, 3, 3, 3);
		xAttachmentPointField.setVisible(true);

		add(xAttachmentPointField, xAttachmentPointField_constraints);

		final JTextField yAttachmentPointField = new JTextField();
		yAttachmentPointField.setHorizontalAlignment(JTextField.RIGHT);
		final GridBagConstraints yAttachmentPointField_constraints = new GridBagConstraints();
		yAttachmentPointField_constraints.gridx = 11;
		yAttachmentPointField_constraints.gridy = 4;
		yAttachmentPointField_constraints.gridwidth = 1;
		yAttachmentPointField_constraints.gridheight = 2;
		yAttachmentPointField_constraints.weightx = 1.0;
		yAttachmentPointField_constraints.weighty = 0.0;
		yAttachmentPointField_constraints.fill = GridBagConstraints.HORIZONTAL;
		yAttachmentPointField_constraints.anchor = GridBagConstraints.EAST;
		yAttachmentPointField_constraints.insets = new Insets(3, 3, 3, 3);
		yAttachmentPointField.setVisible(true);

		add(yAttachmentPointField, yAttachmentPointField_constraints);

		this.spriteViewPanel = new SpriteViewPanel();
		final GridBagConstraints spriteViewPanel_constraints = new GridBagConstraints();
		spriteViewPanel_constraints.gridx = 0;
		spriteViewPanel_constraints.gridy = 8;
		spriteViewPanel_constraints.gridwidth = 12;
		spriteViewPanel_constraints.gridheight = 14;
		spriteViewPanel_constraints.weightx = 1.0;
		spriteViewPanel_constraints.weighty = 1.0;
		spriteViewPanel_constraints.fill = GridBagConstraints.BOTH;
		spriteViewPanel_constraints.anchor = GridBagConstraints.CENTER;
		spriteViewPanel_constraints.insets = new Insets(3, 3, 3, 3);
		spriteViewPanel.setVisible(true);

		add(spriteViewPanel, spriteViewPanel_constraints);

		final JButton reloadViewDirectionButton = new JButton("Reload");
		reloadViewDirectionButton.addActionListener(new ReloadSpriteCollectionActionListener(spriteLibraryEditorPanel, spriteViewPanel, redrawOnChange));
		final GridBagConstraints reloadViewDirectionButton_constraints = new GridBagConstraints();
		reloadViewDirectionButton_constraints.gridx = 6;
		reloadViewDirectionButton_constraints.gridy = 0;
		reloadViewDirectionButton_constraints.gridwidth = 2;
		reloadViewDirectionButton_constraints.gridheight = 2;
		reloadViewDirectionButton_constraints.weightx = 1.0;
		reloadViewDirectionButton_constraints.weighty = 0.0;
		reloadViewDirectionButton_constraints.fill = GridBagConstraints.NONE;
		reloadViewDirectionButton_constraints.anchor = GridBagConstraints.CENTER;
		reloadViewDirectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		reloadViewDirectionButton.setVisible(true);

		add(reloadViewDirectionButton, reloadViewDirectionButton_constraints);

		final JButton setAttachmentPointButton = new JButton("Set");
		setAttachmentPointButton.addActionListener(new SetAttachmentPointActionListener(xAttachmentPointField, yAttachmentPointField, spriteViewPanel, redrawOnChange));
		final GridBagConstraints setAttachmentPointButton_constraints = new GridBagConstraints();
		setAttachmentPointButton_constraints.gridx = 10;
		setAttachmentPointButton_constraints.gridy = 6;
		setAttachmentPointButton_constraints.gridwidth = 2;
		setAttachmentPointButton_constraints.gridheight = 2;
		setAttachmentPointButton_constraints.weightx = 1.0;
		setAttachmentPointButton_constraints.weighty = 0.0;
		setAttachmentPointButton_constraints.fill = GridBagConstraints.NONE;
		setAttachmentPointButton_constraints.anchor = GridBagConstraints.EAST;
		setAttachmentPointButton_constraints.insets = new Insets(3, 3, 3, 3);
		setAttachmentPointButton.setVisible(true);

		add(setAttachmentPointButton, setAttachmentPointButton_constraints);
	}

	public void setSpriteCollection(final SpriteCollection spriteCollection) {
		this.spriteCollection = spriteCollection;

		if (spriteCollection == null) {
			spriteCollectionNameTitle.setText("");
			return;
		}

		spriteCollectionNameTitle.setText(spriteCollection.getName());

		phaseChooser.setModel(new PhaseChooserComboBoxModel(spriteCollection.getPhases()));

		final String phase = (String) phaseChooser.getSelectedItem();

		if (phase == null)
			return;

		spriteViewPanel.setSprite(spriteCollection.getSprite(phase).getSprite((ViewDirection)viewDirectionChooser.getSelectedItem()));
	}

	public SpriteCollection getSpriteCollection() {
		return spriteCollection;
	}

	private static class PhaseChooserComboBoxModel implements ComboBoxModel<String> {
		private final List<String> phases;

		private String selected;

		public PhaseChooserComboBoxModel() {
			this(Collections.emptySet());
		}

		public PhaseChooserComboBoxModel(final Set<String> phases) {
			this.phases = new ArrayList<String>();

			for (final String phase : phases)
				this.phases.add(phase);

			this.selected = (this.phases.size() > 0) ? this.phases.get(0) : null;
		}

		@Override
		public String getElementAt(final int index) {
			if (index < 0 || index >= phases.size())
				return null;
			return phases.get(index);
		}

		@Override
		public int getSize() {
			return phases.size();
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public void setSelectedItem(final Object item) {
			if (!(item instanceof String))
				return;

			this.selected = (String) item;
		}

		@Override
		public void addListDataListener(final ListDataListener dataListener) {
			// no-op
		}

		@Override
		public void removeListDataListener(final ListDataListener dataListener) {
			// no-op
		}
	}

	private static class ViewDirectionComboBoxModel implements ComboBoxModel<ViewDirection> {
		private final ViewDirection[] viewDirections;

		private ViewDirection selected;

		public ViewDirectionComboBoxModel() {
			this.viewDirections = ViewDirection.values();
			this.selected = ViewDirection.NORTH;
		}

		@Override
		public ViewDirection getElementAt(int index) {
			return (index < 0 || index >= viewDirections.length) ? null : viewDirections[index];
		}

		@Override
		public int getSize() {
			return viewDirections.length;
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public void setSelectedItem(final Object item) {
			if (!(item instanceof ViewDirection))
				return;
			selected = (ViewDirection) item;
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

	private class PhaseAddActionListener implements ActionListener {
		private final JTextField nameSourceField;

		public PhaseAddActionListener(final JTextField nameSourceField) {
			this.nameSourceField = nameSourceField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			final String name = nameSourceField.getText();

			spriteCollection.addPhase(name);
			phaseChooser.setModel(new PhaseChooserComboBoxModel(spriteCollection.getPhases()));

			nameSourceField.setText("");
		}
	}

	private class PhaseRemoveActionListener implements ActionListener {
		private final JButton selectButton;
		private final JComponent redrawOnChange;

		public PhaseRemoveActionListener(final JButton selectButton, final JComponent redrawOnChange) {
			this.selectButton = selectButton;
			this.redrawOnChange = redrawOnChange;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			final String toRemove = (String) phaseChooser.getSelectedItem();

			if (toRemove == null)
				return;

			spriteCollection.removePhase(toRemove);
			phaseChooser.setModel(new PhaseChooserComboBoxModel(spriteCollection.getPhases()));
			selectButton.doClick();
			redrawOnChange.repaint();
		}
	}

	private class ReloadSpriteCollectionActionListener implements ActionListener {
		private final SpriteLibraryEditorPanel spriteLibraryEditorPanel;
		private final SpriteViewPanel spriteViewPanel;
		private final JComponent redrawOnChange;

		public ReloadSpriteCollectionActionListener(final SpriteLibraryEditorPanel spriteLibraryEditorPanel, final SpriteViewPanel spriteViewPanel, final JComponent redrawOnChange) {
			this.spriteLibraryEditorPanel = spriteLibraryEditorPanel;
			this.spriteViewPanel = spriteViewPanel;
			this.redrawOnChange = redrawOnChange;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			SpriteLibraryReader.loadImages(spriteCollection, spriteLibraryEditorPanel.getSpriteLibrary().getLocation());
			spriteViewPanel.repaint();
			redrawOnChange.repaint();
		}
	}

	private class SelectViewDirectionActionListener implements ActionListener {
		private final JLabel viewDirectionNameTitle;

		public SelectViewDirectionActionListener(final JLabel viewDirectionNameTitle) {
			this.viewDirectionNameTitle = viewDirectionNameTitle;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			final String phase = (String) phaseChooser.getSelectedItem();

			if (phase == null)
				return;

			final ViewDirection viewDirection = (ViewDirection) viewDirectionChooser.getSelectedItem();

			viewDirectionNameTitle.setText(spriteCollection.getName() + " : " + phase + " : " + viewDirection.name());
			spriteViewPanel.setSprite(spriteCollection.getSprite(phase).getSprite(viewDirection));
		}
	}

	private class SetAttachmentPointActionListener implements ActionListener {
		private final JTextField xField;
		private final JTextField yField;
		private final SpriteViewPanel spriteViewPanel;
		private final JComponent redrawOnChange;

		public SetAttachmentPointActionListener(final JTextField xField, final JTextField yField, final SpriteViewPanel spriteViewPanel, final JComponent redrawOnChange) {
			this.xField = xField;
			this.yField = yField;
			this.spriteViewPanel = spriteViewPanel;
			this.redrawOnChange = redrawOnChange;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			final Sprite currentSprite = spriteViewPanel.getSprite();

			int attachX = 0;
			int attachY = 0;
			boolean succeeded = false;
			try {
				attachX = Integer.parseInt(xField.getText());
				attachY = Integer.parseInt(yField.getText());
				succeeded = true;
			} catch (final NumberFormatException e) {
				JOptionPane.showMessageDialog(ModelMaker.getParentFrame(), "A given coordinate was not a number.", "Not a Number", JOptionPane.ERROR_MESSAGE);
			}

			if (!succeeded)
				return;

			currentSprite.setAttachmentPoint(attachX, attachY);
			spriteViewPanel.repaint();
			redrawOnChange.repaint();

			xField.setText("");
			yField.setText("");
		}
	}
}

package raptor.modelMaker.components.spriteLibrary;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		this.viewDirectionChooser = new JComboBox<ViewDirection>();
		viewDirectionChooser.setModel(new ViewDirectionComboBoxModel());
		final GridBagConstraints viewDirectionChooser_constraints = new GridBagConstraints();
		viewDirectionChooser_constraints.gridx = 0;
		viewDirectionChooser_constraints.gridy = 2;
		viewDirectionChooser_constraints.gridwidth = 8;
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
		viewDirectionNameTitle_constraints.gridy = 4;
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
		selectViewDirectionButton_constraints.gridy = 4;
		selectViewDirectionButton_constraints.gridwidth = 1;
		selectViewDirectionButton_constraints.gridheight = 2;
		selectViewDirectionButton_constraints.weightx = 0.0;
		selectViewDirectionButton_constraints.weighty = 0.0;
		selectViewDirectionButton_constraints.fill = GridBagConstraints.NONE;
		selectViewDirectionButton_constraints.anchor = GridBagConstraints.EAST;
		selectViewDirectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectViewDirectionButton.setVisible(true);

		add(selectViewDirectionButton, selectViewDirectionButton_constraints);

		final JTextField xAttachmentPointField = new JTextField();
		xAttachmentPointField.setHorizontalAlignment(JTextField.RIGHT);
		final GridBagConstraints xAttachmentPointField_constraints = new GridBagConstraints();
		xAttachmentPointField_constraints.gridx = 10;
		xAttachmentPointField_constraints.gridy = 2;
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
		yAttachmentPointField_constraints.gridy = 2;
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
		spriteViewPanel_constraints.gridy = 6;
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
		setAttachmentPointButton_constraints.gridy = 4;
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

		spriteViewPanel.setSprite(spriteCollection.getSprite((ViewDirection)viewDirectionChooser.getSelectedItem()));
		spriteCollectionNameTitle.setText(spriteCollection.getName());
	}

	public SpriteCollection getSpriteCollection() {
		return spriteCollection;
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

			spriteCollection.setSpriteImages(SpriteLibraryReader.loadImages(spriteCollection.getName(), spriteLibraryEditorPanel.getSpriteLibrary().getLocation()));
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
			final ViewDirection viewDirection = (ViewDirection) viewDirectionChooser.getSelectedItem();
			viewDirectionNameTitle.setText(viewDirection.name());

			if (spriteCollection == null)
				return;

			spriteViewPanel.setSprite(spriteCollection.getSprite(viewDirection));
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

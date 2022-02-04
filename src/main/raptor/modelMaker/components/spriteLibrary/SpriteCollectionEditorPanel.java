package raptor.modelMaker.components.spriteLibrary;

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

import raptor.modelMaker.model.ViewDirection;
import raptor.modelMaker.spriteLibrary.Sprite;
import raptor.modelMaker.spriteLibrary.SpriteCollection;

public class SpriteCollectionEditorPanel extends JPanel {
	private final JComboBox<ViewDirection> viewDirectionChooser;
	private final SpriteViewPanel spriteViewPanel;

	private SpriteCollection spriteCollection;

	public SpriteCollectionEditorPanel(final JComponent redrawOnChange) {
		super();

		spriteCollection = null;

		this.setLayout(new GridBagLayout());

		this.viewDirectionChooser = new JComboBox<ViewDirection>();
		viewDirectionChooser.setModel(new ViewDirectionComboBoxModel());
		final GridBagConstraints viewDirectionChooser_constraints = new GridBagConstraints();
		viewDirectionChooser_constraints.gridx = 0;
		viewDirectionChooser_constraints.gridy = 0;
		viewDirectionChooser_constraints.gridwidth = 3;
		viewDirectionChooser_constraints.gridheight = 2;
		viewDirectionChooser_constraints.weightx = 0.0;
		viewDirectionChooser_constraints.weighty = 0.0;
		viewDirectionChooser_constraints.fill = GridBagConstraints.BOTH;
		viewDirectionChooser_constraints.anchor = GridBagConstraints.CENTER;
		viewDirectionChooser_constraints.insets = new Insets(3, 3, 3, 3);
		viewDirectionChooser.setVisible(true);

		add(viewDirectionChooser, viewDirectionChooser_constraints);

		final JLabel viewDirectionNameTitle = new JLabel(((ViewDirection)viewDirectionChooser.getSelectedItem()).name());
		final GridBagConstraints viewDirectionNameTitle_constraints = new GridBagConstraints();
		viewDirectionNameTitle_constraints.gridx = 0;
		viewDirectionNameTitle_constraints.gridy = 0;
		viewDirectionNameTitle_constraints.gridwidth = 3;
		viewDirectionNameTitle_constraints.gridheight = 2;
		viewDirectionNameTitle_constraints.weightx = 0.0;
		viewDirectionNameTitle_constraints.weighty = 0.0;
		viewDirectionNameTitle_constraints.fill = GridBagConstraints.BOTH;
		viewDirectionNameTitle_constraints.anchor = GridBagConstraints.CENTER;
		viewDirectionNameTitle_constraints.insets = new Insets(3, 3, 3, 3);
		viewDirectionNameTitle.setVisible(true);

		add(viewDirectionNameTitle, viewDirectionNameTitle_constraints);

		final JButton selectViewDirectionButton = new JButton("Select");
		selectViewDirectionButton.addActionListener(new SelectViewDirectionActionListener(viewDirectionChooser, viewDirectionNameTitle));
		final GridBagConstraints selectViewDirectionButton_constraints = new GridBagConstraints();
		selectViewDirectionButton_constraints.gridx = 0;
		selectViewDirectionButton_constraints.gridy = 0;
		selectViewDirectionButton_constraints.gridwidth = 3;
		selectViewDirectionButton_constraints.gridheight = 2;
		selectViewDirectionButton_constraints.weightx = 0.0;
		selectViewDirectionButton_constraints.weighty = 0.0;
		selectViewDirectionButton_constraints.fill = GridBagConstraints.BOTH;
		selectViewDirectionButton_constraints.anchor = GridBagConstraints.CENTER;
		selectViewDirectionButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectViewDirectionButton.setVisible(true);

		add(selectViewDirectionButton, selectViewDirectionButton_constraints);

		final JTextField xAttachmentPointField = new JTextField();
		final GridBagConstraints xAttachmentPointField_constraints = new GridBagConstraints();
		xAttachmentPointField_constraints.gridx = 0;
		xAttachmentPointField_constraints.gridy = 0;
		xAttachmentPointField_constraints.gridwidth = 3;
		xAttachmentPointField_constraints.gridheight = 2;
		xAttachmentPointField_constraints.weightx = 0.0;
		xAttachmentPointField_constraints.weighty = 0.0;
		xAttachmentPointField_constraints.fill = GridBagConstraints.BOTH;
		xAttachmentPointField_constraints.anchor = GridBagConstraints.CENTER;
		xAttachmentPointField_constraints.insets = new Insets(3, 3, 3, 3);
		xAttachmentPointField.setVisible(true);

		add(xAttachmentPointField, xAttachmentPointField_constraints);

		final JTextField yAttachmentPointField = new JTextField();
		final GridBagConstraints yAttachmentPointField_constraints = new GridBagConstraints();
		yAttachmentPointField_constraints.gridx = 0;
		yAttachmentPointField_constraints.gridy = 0;
		yAttachmentPointField_constraints.gridwidth = 3;
		yAttachmentPointField_constraints.gridheight = 2;
		yAttachmentPointField_constraints.weightx = 0.0;
		yAttachmentPointField_constraints.weighty = 0.0;
		yAttachmentPointField_constraints.fill = GridBagConstraints.BOTH;
		yAttachmentPointField_constraints.anchor = GridBagConstraints.CENTER;
		yAttachmentPointField_constraints.insets = new Insets(3, 3, 3, 3);
		yAttachmentPointField.setVisible(true);

		add(yAttachmentPointField, yAttachmentPointField_constraints);

		final JButton setAttachmentPointButton = new JButton("Set");
		setAttachmentPointButton.addActionListener(new SetAttachmentPointActionListener(xAttachmentPointField, yAttachmentPointField, this));
		final GridBagConstraints setAttachmentPointButton_constraints = new GridBagConstraints();
		setAttachmentPointButton_constraints.gridx = 0;
		setAttachmentPointButton_constraints.gridy = 0;
		setAttachmentPointButton_constraints.gridwidth = 3;
		setAttachmentPointButton_constraints.gridheight = 2;
		setAttachmentPointButton_constraints.weightx = 0.0;
		setAttachmentPointButton_constraints.weighty = 0.0;
		setAttachmentPointButton_constraints.fill = GridBagConstraints.BOTH;
		setAttachmentPointButton_constraints.anchor = GridBagConstraints.CENTER;
		setAttachmentPointButton_constraints.insets = new Insets(3, 3, 3, 3);
		setAttachmentPointButton.setVisible(true);

		add(setAttachmentPointButton, setAttachmentPointButton_constraints);

		this.spriteViewPanel = new SpriteViewPanel();
		final GridBagConstraints spriteViewPanel_constraints = new GridBagConstraints();
		spriteViewPanel_constraints.gridx = 0;
		spriteViewPanel_constraints.gridy = 0;
		spriteViewPanel_constraints.gridwidth = 3;
		spriteViewPanel_constraints.gridheight = 2;
		spriteViewPanel_constraints.weightx = 0.0;
		spriteViewPanel_constraints.weighty = 0.0;
		spriteViewPanel_constraints.fill = GridBagConstraints.BOTH;
		spriteViewPanel_constraints.anchor = GridBagConstraints.CENTER;
		spriteViewPanel_constraints.insets = new Insets(3, 3, 3, 3);
		spriteViewPanel.setVisible(true);

		add(spriteViewPanel, spriteViewPanel_constraints);
	}

	public void setSpriteCollection(final SpriteCollection spriteCollection) {
		this.spriteCollection = spriteCollection;
		spriteViewPanel.setSprite(spriteCollection.getSprite((ViewDirection)viewDirectionChooser.getSelectedItem()));
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

	private class SelectViewDirectionActionListener implements ActionListener {
		private final JComboBox<ViewDirection> viewDirectionChooser;
		private final JLabel viewDirectionNameTitle;

		public SelectViewDirectionActionListener(final JComboBox<ViewDirection> viewDirectionChooser, final JLabel viewDirectionNameTitle) {
			this.viewDirectionChooser = viewDirectionChooser;
			this.viewDirectionNameTitle = viewDirectionNameTitle;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (spriteCollection == null)
				return;

			final ViewDirection viewDirection = (ViewDirection) viewDirectionChooser.getSelectedItem();

			spriteViewPanel.setSprite(spriteCollection.getSprite(viewDirection));
			viewDirectionNameTitle.setText(viewDirection.name());
		}
	}

	private class SetAttachmentPointActionListener implements ActionListener {
		private final JTextField xField;
		private final JTextField yField;
		private final JComponent parent;

		public SetAttachmentPointActionListener(final JTextField xField, final JTextField yField, final JComponent parent) {
			this.xField = xField;
			this.yField = yField;
			this.parent = parent;
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
				JOptionPane.showMessageDialog(parent, "A given coordinate was not a number.", "Not a Number", JOptionPane.ERROR_MESSAGE);
			}

			if (!succeeded)
				return;

			currentSprite.setAttachmentPoint(attachX, attachY);
		}
	}
}

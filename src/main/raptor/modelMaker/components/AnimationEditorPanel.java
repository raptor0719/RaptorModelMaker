package raptor.modelMaker.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;

import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Model;

public class AnimationEditorPanel extends JPanel {
	private final JComboBox<Animation> animationChooser;
	private final AnimationEditor animationEditor;

	private Model model;

	public AnimationEditorPanel(final Model model) {
		super();

		this.model = model;

		this.setLayout(new GridBagLayout());

		this.animationChooser = new JComboBox<Animation>();
		animationChooser.setModel(new AnimationChooserComboBoxModel(model));
		final GridBagConstraints animationChooser_constraints = new GridBagConstraints();
		animationChooser_constraints.gridx = 0;
		animationChooser_constraints.gridy = 0;
		animationChooser_constraints.gridwidth = 3;
		animationChooser_constraints.gridheight = 2;
		animationChooser_constraints.weightx = 0.0;
		animationChooser_constraints.weighty = 0.0;
		animationChooser_constraints.fill = GridBagConstraints.BOTH;
		animationChooser_constraints.anchor = GridBagConstraints.CENTER;
		animationChooser_constraints.insets = new Insets(3, 3, 3, 3);
		animationChooser.setVisible(true);

		add(animationChooser, animationChooser_constraints);

		final JTextField addAnimationNameField = new JTextField();
		final GridBagConstraints addAnimationNameField_constraints = new GridBagConstraints();
		addAnimationNameField_constraints.gridx = 3;
		addAnimationNameField_constraints.gridy = 0;
		addAnimationNameField_constraints.gridwidth = 3;
		addAnimationNameField_constraints.gridheight = 2;
		addAnimationNameField_constraints.weightx = 0.5;
		addAnimationNameField_constraints.weighty = 0.0;
		addAnimationNameField_constraints.fill = GridBagConstraints.BOTH;
		addAnimationNameField_constraints.anchor = GridBagConstraints.CENTER;
		addAnimationNameField_constraints.insets = new Insets(3, 3, 3, 3);
		addAnimationNameField.setVisible(true);

		add(addAnimationNameField, addAnimationNameField_constraints);

		final JButton addAnimationButton = new JButton("Add");
		addAnimationButton.addActionListener(new AddAnimationActionListener(addAnimationNameField));
		final GridBagConstraints addAnimationButton_constraints = new GridBagConstraints();
		addAnimationButton_constraints.gridx = 5;
		addAnimationButton_constraints.gridy = 2;
		addAnimationButton_constraints.gridwidth = 1;
		addAnimationButton_constraints.gridheight = 2;
		addAnimationButton_constraints.weightx = 0.0;
		addAnimationButton_constraints.weighty = 0.0;
		addAnimationButton_constraints.fill = GridBagConstraints.NONE;
		addAnimationButton_constraints.anchor = GridBagConstraints.EAST;
		addAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		addAnimationButton.setVisible(true);

		add(addAnimationButton, addAnimationButton_constraints);

		final JButton deleteAnimationButton = new JButton("Delete");
		deleteAnimationButton.addActionListener(new DeleteAnimationActionListener());
		final GridBagConstraints deleteAnimationButton_constraints = new GridBagConstraints();
		deleteAnimationButton_constraints.gridx = 0;
		deleteAnimationButton_constraints.gridy = 2;
		deleteAnimationButton_constraints.gridwidth = 1;
		deleteAnimationButton_constraints.gridheight = 2;
		deleteAnimationButton_constraints.weightx = 0.0;
		deleteAnimationButton_constraints.weighty = 0.0;
		deleteAnimationButton_constraints.fill = GridBagConstraints.NONE;
		deleteAnimationButton_constraints.anchor = GridBagConstraints.CENTER;
		deleteAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		deleteAnimationButton.setVisible(true);

		add(deleteAnimationButton, deleteAnimationButton_constraints);

		final JButton renameAnimationButton = new JButton("Rename");
		renameAnimationButton.addActionListener(new RenameAnimationActionListener(addAnimationNameField));
		final GridBagConstraints renameAnimationButton_constraints = new GridBagConstraints();
		renameAnimationButton_constraints.gridx = 3;
		renameAnimationButton_constraints.gridy = 2;
		renameAnimationButton_constraints.gridwidth = 1;
		renameAnimationButton_constraints.gridheight = 2;
		renameAnimationButton_constraints.weightx = 0.0;
		renameAnimationButton_constraints.weighty = 0.0;
		renameAnimationButton_constraints.fill = GridBagConstraints.NONE;
		renameAnimationButton_constraints.anchor = GridBagConstraints.CENTER;
		renameAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		renameAnimationButton.setVisible(true);

		add(renameAnimationButton, renameAnimationButton_constraints);

		final JButton selectAnimationButton = new JButton("Select");
		selectAnimationButton.addActionListener(new SelectAnimationActionListener(animationChooser));
		final GridBagConstraints selectAnimationButton_constraints = new GridBagConstraints();
		selectAnimationButton_constraints.gridx = 2;
		selectAnimationButton_constraints.gridy = 2;
		selectAnimationButton_constraints.gridwidth = 1;
		selectAnimationButton_constraints.gridheight = 2;
		selectAnimationButton_constraints.weightx = 0.5;
		selectAnimationButton_constraints.weighty = 0.0;
		selectAnimationButton_constraints.fill = GridBagConstraints.NONE;
		selectAnimationButton_constraints.anchor = GridBagConstraints.EAST;
		selectAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		selectAnimationButton.setVisible(true);

		add(selectAnimationButton, selectAnimationButton_constraints);

		this.animationEditor = new AnimationEditor(model, (Animation)animationChooser.getSelectedItem());
		final GridBagConstraints animationEditor_constraints = new GridBagConstraints();
		animationEditor_constraints.gridx = 0;
		animationEditor_constraints.gridy = 4;
		animationEditor_constraints.gridwidth = 6;
		animationEditor_constraints.gridheight = 16;
		animationEditor_constraints.weightx = 1.0;
		animationEditor_constraints.weighty = 1.0;
		animationEditor_constraints.fill = GridBagConstraints.BOTH;
		animationEditor_constraints.anchor = GridBagConstraints.CENTER;
		animationEditor.setVisible(true);

		add(animationEditor, animationEditor_constraints);
	}

	public void setModel(final Model newModel) {
		this.model = newModel;
		refresh();
	}

	public void refresh() {
		animationChooser.setModel(new AnimationChooserComboBoxModel(model));
		animationEditor.setModel(model, (Animation)animationChooser.getSelectedItem());
	}

	private class AddAnimationActionListener implements ActionListener {
		private final JTextField addAnimationNameField;

		public AddAnimationActionListener(final JTextField addAnimationNameField) {
			this.addAnimationNameField = addAnimationNameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final String text = addAnimationNameField.getText();

			if (text == null || text.isEmpty())
				return;

			model.addAnimation(text);
			addAnimationNameField.setText("");
			animationEditor.setAnimation(model.getAnimation(text));
			animationChooser.setModel(new AnimationChooserComboBoxModel(model));
		}
	}

	private class DeleteAnimationActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			final Animation animation = (Animation)animationChooser.getSelectedItem();

			if (animation == null)
				return;

			model.removeAnimation(animation.getName());
			refresh();
		}
	}

	private class RenameAnimationActionListener implements ActionListener {
		private final JTextField renameAnimationNameField;

		public RenameAnimationActionListener(final JTextField renameAnimationNameField) {
			this.renameAnimationNameField = renameAnimationNameField;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final Animation animation = (Animation)animationChooser.getSelectedItem();
			final String newName = renameAnimationNameField.getText();

			if (animation == null || newName == null || newName.isEmpty())
				return;

			animation.setName(newName);
			renameAnimationNameField.setText("");
			refresh();
		}
	}

	private class SelectAnimationActionListener implements ActionListener {
		private final JComboBox<Animation> animationChooser;

		public SelectAnimationActionListener(final JComboBox<Animation> animationChooser) {
			this.animationChooser = animationChooser;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			final Animation selected = (Animation)animationChooser.getSelectedItem();

			if (selected == null)
				return;

			animationEditor.setAnimation(selected);
		}
	}

	private static class AnimationChooserComboBoxModel implements ComboBoxModel<Animation> {
		private Model model;
		private Animation selected;

		public AnimationChooserComboBoxModel(final Model model) {
			this.model = model;
			this.selected = (model.getAnimations().size() > 0) ? model.getAnimations().get(0) : null;
		}

		@Override
		public Animation getElementAt(final int index) {
			return model.getAnimations().get(index);
		}

		@Override
		public int getSize() {
			return model.getAnimations().size();
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public void setSelectedItem(final Object animation) {
			if (!(animation instanceof Animation))
				return;
			final Animation casted = (Animation)animation;
			selected = model.getAnimation(casted.getName());
		}

		@Override
		public void addListDataListener(final ListDataListener listDataListener) {
			return;
		}

		@Override
		public void removeListDataListener(final ListDataListener listDataListener) {
			return;
		}
	}
}

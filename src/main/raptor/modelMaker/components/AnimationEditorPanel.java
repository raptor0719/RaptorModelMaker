package raptor.modelMaker.components;

import java.awt.GridBagLayout;
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

	private Model model;

	public AnimationEditorPanel(final Model model) {
		super();

		this.model = model;

		this.setLayout(new GridBagLayout());

		this.animationChooser = new JComboBox<>();
		animationChooser.setModel(new AnimationChooserComboBoxModel(model));

		add(animationChooser);

		final JTextField addAnimationNameField = new JTextField();

		add(addAnimationNameField);

		final JButton addAnimationButton = new JButton("Add");
		addAnimationButton.addActionListener(new AddAnimationActionListener(addAnimationNameField));

		add(addAnimationButton);

		final JButton deleteAnimationButton = new JButton("Delete");
		deleteAnimationButton.addActionListener(new DeleteAnimationActionListener());

		add(deleteAnimationButton);

		final JButton renameAnimationButton = new JButton("Rename");
		renameAnimationButton.addActionListener(new RenameAnimationActionListener(addAnimationNameField));

		add(renameAnimationButton);

		final JButton selectAnimationButton = new JButton("Select");

		add(selectAnimationButton);
	}

	public void setModel(final Model newModel) {
		this.model = newModel;
		refresh();
	}

	private void refresh() {
		animationChooser.setModel(new AnimationChooserComboBoxModel(model));
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
			refresh();
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
			refresh();
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

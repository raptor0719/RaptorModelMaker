package raptor.modelMaker.components;

import java.awt.GridBagLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;

import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Model;

public class AnimationEditorPanel extends JPanel {
	private Model model;

	public AnimationEditorPanel(final Model model) {
		super();


		this.setLayout(new GridBagLayout());

		final JComboBox<Animation> animationChooser = new JComboBox<>();
		animationChooser.
	}

	private void setModel(final Model newModel) {
		this.model = newModel;
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
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeListDataListener(final ListDataListener listDataListener) {
			throw new UnsupportedOperationException();
		}
	}
}

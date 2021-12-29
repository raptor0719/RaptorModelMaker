package raptor.modelMaker.components;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import raptor.modelMaker.model.Frame;
import raptor.modelMaker.model.Model;

public class FrameChooser extends JList<Frame> {
	private final FrameChooserListModel listModel;

	public FrameChooser(final Model model) {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setLayoutOrientation(JList.VERTICAL);
		this.setVisibleRowCount(10);

		this.listModel = new FrameChooserListModel(model);

		this.setModel(listModel);
	}

	public void setModel(final Model model) {
		listModel.setModel(model);
	}

	public void modelChanged() {
		this.listModel.modelChanged();
	}

	private static class FrameChooserListModel extends AbstractListModel<Frame> {
		private Model model;

		public FrameChooserListModel(final Model model) {
			this.model = model;
		}

		@Override
		public Frame getElementAt(final int index) {
			return model.getFrames().get(index);
		}

		@Override
		public int getSize() {
			return model.getFrames().size();
		}

		public void setModel(final Model model) {
			this.model = model;
			fireContentsChanged(this, 0, getSize() - 1);
		}

		public void modelChanged() {
			fireContentsChanged(this, 0, getSize() - 1);
		}
	}
}

package raptor.modelMaker.components;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;

import raptor.modelMaker.model.Animation;

public class AnimationEditorTable extends JTable {
	private static final String[] COLUMN_NAMES = new String[] {"Frame", "Holds"};

	private AnimationTableModel animationTableModel;

	public AnimationEditorTable(final Animation animation) {
		this.animationTableModel = new AnimationTableModel(animation);
		setModel(animationTableModel);

		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public boolean editCellAt(final int row, final int col, final EventObject event) {
		final boolean result = super.editCellAt(row, col, event);

		final Component editor = getEditorComponent();

		if (editor != null)
			((JTextComponent)editor).selectAll();

		return result;
	}

	public void setAnimation(final Animation animation) {
		animationTableModel.setAnimation(animation);
	}

	public void animationChanged() {
		animationTableModel.fireTableDataChanged();
	}

	public void setSelectedIndex(final int index) {
		changeSelection(index, 0, false, false);
	}

	private static class AnimationTableModel extends AbstractTableModel {
		private Animation animation;

		public AnimationTableModel(final Animation animation) {
			this.animation = animation;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			return (animation == null) ? 0 : animation.size();
		}

		@Override
		public Object getValueAt(final int row, final int col) {
			switch (col) {
				case 1:
					return animation.getHolds(row);
				default:
					return animation.getFrame(row);
			}
		}

		@Override
		public boolean isCellEditable(final int row, final int col) {
			switch (col) {
				case 1:
					return true;
				default:
					return false;
			}
		}

		@Override
		public void setValueAt(final Object val, final int row, final int col) {
			final String stringVal = (String)val;

			if (stringVal == null || stringVal.isEmpty())
				return;

			switch (col) {
				case 1:
					animation.setHolds(row, Integer.parseInt(stringVal));
					break;
				default:
					break;
			}

			fireTableCellUpdated(row, col);
		}

		@Override
		public String getColumnName(final int col) {
			return COLUMN_NAMES[col];
		}

		public void setAnimation(final Animation animation) {
			this.animation = animation;

			fireTableDataChanged();
		}
	}
}

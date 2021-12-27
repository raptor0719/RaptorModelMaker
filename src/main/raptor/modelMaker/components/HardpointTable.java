package raptor.modelMaker.components;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class HardpointTable extends JTable {
	private static final String[] COLUMN_NAMES = new String[] {"Name", "x", "y", "z", "rot"};

	private final JComponent redrawOnChange;

	private ModelTableModel tableModel;

	public HardpointTable(final Model model, final JComponent redrawOnChange) {
		this.tableModel = new ModelTableModel(model);
		this.setModel(tableModel);

		this.redrawOnChange = redrawOnChange;

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void setModel(final Model model) {
		tableModel.setModel(model);
		this.setModel(tableModel);
	}

	@Override
	public void tableChanged(final TableModelEvent event) {
		super.tableChanged(event);

		if (redrawOnChange != null)
			redrawOnChange.repaint();
	}

	public void modelChanged() {
		tableModel.fireTableDataChanged();
	}

	private static class ModelTableModel extends AbstractTableModel {
		private Model model;

		public ModelTableModel(final Model model) {
			this.model = model;
		}

		@Override
		public int getColumnCount() {
			// name, x, y, z, rot
			return 5;
		}

		@Override
		public int getRowCount() {
			return model.getHardpoints().size();
		}

		@Override
		public Object getValueAt(final int row, final int col) {
			final Hardpoint h = model.getHardpoints().get(row);

			switch (col) {
				case 1:
					return h.getPoint().get(0);
				case 2:
					return h.getPoint().get(1);
				case 3:
					return h.getPoint().get(2);
				case 4:
					return h.getRotation();
				default:
					return h.getName();
			}
		}

		@Override
		public boolean isCellEditable(final int row, final int col) {
			return true;
		}

		@Override
		public void setValueAt(final Object val, final int row, final int col) {
			final Hardpoint h = model.getHardpoints().get(row);
			final String stringVal = (String)val;

			switch (col) {
				case 1:
					h.getPoint().getRaw()[0] = Double.parseDouble(stringVal);
					break;
				case 2:
					h.getPoint().getRaw()[1] = Double.parseDouble(stringVal);
					break;
				case 3:
					h.getPoint().getRaw()[2] = Double.parseDouble(stringVal);
					break;
				case 4:
					h.setRotation(Integer.parseInt(stringVal));
					break;
				default:
					h.setName(stringVal);
			}

			fireTableCellUpdated(row, col);
		}

		@Override
		public String getColumnName(final int col) {
			return COLUMN_NAMES[col];
		}

		public void setModel(final Model model) {
			this.model = model;

			fireTableDataChanged();
		}
	}
}

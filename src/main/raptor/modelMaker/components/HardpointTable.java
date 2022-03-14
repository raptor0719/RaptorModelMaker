package raptor.modelMaker.components;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;

import raptor.modelMaker.model.Hardpoint;
import raptor.modelMaker.model.Model;

public class HardpointTable extends JTable {
	private static final String[] COLUMN_NAMES = new String[] {"Name", "x", "y", "z", "rot", "Sprite Name"};

	private final JComponent redrawOnChange;

	private ModelTableModel tableModel;

	public HardpointTable(final Model model, final ViewPanel viewPanel) {
		this.tableModel = new ModelTableModel(model);
		this.setModel(tableModel);

		this.redrawOnChange = viewPanel;

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.selectionModel.addListSelectionListener(new SelectHardpointInViewPanelListener(this, viewPanel));
	}

	public void setModel(final Model model) {
		tableModel.setModel(model);
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

	@Override
	public boolean editCellAt(final int row, final int col, final EventObject event) {
		final boolean result = super.editCellAt(row, col, event);

		final Component editor = getEditorComponent();

		if (editor != null)
			((JTextComponent)editor).selectAll();

		return result;
	}

	private static class SelectHardpointInViewPanelListener implements ListSelectionListener {
		private final HardpointTable table;
		private final ViewPanel viewPanel;

		public SelectHardpointInViewPanelListener(final HardpointTable table, final ViewPanel viewPanel) {
			this.table = table;
			this.viewPanel = viewPanel;
		}

		@Override
		public void valueChanged(final ListSelectionEvent e) {

			final ModelTableModel tableModel = (ModelTableModel)table.getModel();
			final Model model = tableModel.getModel();
			final int rowIndex = table.getSelectedRow();

			if (rowIndex < 0 || rowIndex >= model.getHardpoints().size())
				return;

			final Hardpoint selected = model.getHardpoint((String)tableModel.getValueAt(rowIndex, 0));
			viewPanel.setSelected(selected);

			viewPanel.repaint();
		}
	}

	private static class ModelTableModel extends AbstractTableModel {
		private Model model;

		public ModelTableModel(final Model model) {
			this.model = model;
		}

		@Override
		public int getColumnCount() {
			// name, x, y, z, rot, sprite name
			return 6;
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
				case 5:
					return h.getSpriteCollectionName();
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
				case 5:
					h.setSpriteCollectionName(stringVal);
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

		public Model getModel() {
			return model;
		}
	}
}

package raptor.modelMaker.controller;

import raptor.modelMaker.components.HardpointTable;
import raptor.modelMaker.components.ViewPanel;
import raptor.modelMaker.model.Hardpoint;

public class HardpointSelectionEditingController {
	private final ViewPanel viewPanel;
	private final HardpointTable hardpointTable;

	public static enum HardpointField {
		X,
		Y,
		Z,
		ROT;
	}

	private HardpointField currentField;

	public HardpointSelectionEditingController(final ViewPanel viewPanel, final HardpointTable hardpointTable) {
		this.viewPanel = viewPanel;
		this.hardpointTable = hardpointTable;

		this.currentField = null;
	}

	public void setField(final HardpointField field) {
		this.currentField = field;
	}

	public void addToField(final double value) {
		if (currentField == null)
			return;

		final Hardpoint currentHardpoint = viewPanel.getSelected();

		if (currentHardpoint == null)
			return;

		if (HardpointField.ROT.equals(currentField)) {
			final int castValue = (int)value;
			currentHardpoint.setRotation(currentHardpoint.getRotation() + castValue);
		} else {
			final double[] rawVals = currentHardpoint.getPoint().getRaw();
			int index = -1;

			switch (currentField) {
				case X:
					index = 0;
					break;
				case Y:
					index = 1;
					break;
				case Z:
					index = 2;
					break;
				case ROT:
				default:
			}

			rawVals[index] += value;
		}

		viewPanel.repaint();
		hardpointTable.modelChanged();
	}
}

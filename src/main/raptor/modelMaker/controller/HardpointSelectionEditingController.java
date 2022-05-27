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
		DEPTH,
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

	public void addToField(final int value) {
		if (currentField == null)
			return;

		final Hardpoint currentHardpoint = viewPanel.getSelected();

		if (currentHardpoint == null)
			return;

		switch (currentField) {
			case X:
				currentHardpoint.getPoint().setX(currentHardpoint.getPoint().getX() + value);
				break;
			case Y:
				currentHardpoint.getPoint().setY(currentHardpoint.getPoint().getY() + value);
				break;
			case DEPTH:
				currentHardpoint.setDrawDepth(currentHardpoint.getDrawDepth() + value);
				break;
			case ROT:
				currentHardpoint.setRotation(currentHardpoint.getRotation() + value);
				break;
			default:
		}

		viewPanel.repaint();
		hardpointTable.modelChanged();
	}
}

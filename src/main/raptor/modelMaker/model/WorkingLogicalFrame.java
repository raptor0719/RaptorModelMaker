package raptor.modelMaker.model;

public class WorkingLogicalFrame {
	private final WorkingFrame[] directions;
	private int portion;

	public WorkingLogicalFrame(final int pointCount, final int directionCount, final int portion) {
		directions = new WorkingFrame[directionCount];

		for (int i = 0; i < directions.length; i++)
			directions[i] = new WorkingFrame(pointCount);

		this.portion = portion;
	}

	public WorkingFrame[] getDirections() {
		return directions;
	}

	public int getPortion() {
		return portion;
	}

	public void setPortion(int portion) {
		this.portion = portion;
	}
}

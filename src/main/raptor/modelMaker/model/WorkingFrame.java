package raptor.modelMaker.model;

public class WorkingFrame {
	private final WorkingHardpointPosition[] points;

	public WorkingFrame(final int pointCount) {
		points = new WorkingHardpointPosition[pointCount];

		for (int i = 0; i < points.length; i++)
			points[i] = new WorkingHardpointPosition();
	}

	public WorkingHardpointPosition[] getPoints() {
		return points;
	}
}

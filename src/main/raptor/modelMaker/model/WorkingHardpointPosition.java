package raptor.modelMaker.model;

public class WorkingHardpointPosition {
	private int x;
	private int y;
	private int z;
	private int rot;

	public WorkingHardpointPosition() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.rot = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getRot() {
		return rot;
	}

	public void setRot(int rot) {
		this.rot = rot;
	}
}

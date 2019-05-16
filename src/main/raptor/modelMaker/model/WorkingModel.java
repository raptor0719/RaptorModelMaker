package raptor.modelMaker.model;

public class WorkingModel {
	private WorkingWireModel wireModel;

	private int id;
	private String name;

	public WorkingModel(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public WorkingWireModel getWireModel() {
		return wireModel;
	}

	public void setWireModel(final WorkingWireModel wireModel) {
		this.wireModel = wireModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package raptor.modelMaker.model;

public enum ViewDirection {
	LEFT("L"),
	RIGHT("R");

	private final String abbreviation;

	private ViewDirection(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public String toString() {
		return this.name();
	}
}

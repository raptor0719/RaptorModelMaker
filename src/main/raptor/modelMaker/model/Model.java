package raptor.modelMaker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
	// TODO: Integrate pointframes and add getters, setters, and such

	private final List<Hardpoint> hardpoints;

	private String name;

	public Model(final String name) {
		this.hardpoints = new ArrayList<>();
		this.name = name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addHardpoint(final String name, final int rotation) {
		if (getHardpointByName(name) != null)
			throw new IllegalArgumentException("Hardpoint with that name already exists");
		hardpoints.add(new Hardpoint(name, rotation));
	}

	public Hardpoint getHardpoint(final String name) {
		return getHardpointByName(name);
	}

	public List<Hardpoint> getHardpoints() {
		return Collections.unmodifiableList(hardpoints);
	}

	public void removeHardpoint(final String name) {
		final Hardpoint h = getHardpointByName(name);

		if (h == null)
			return;

		hardpoints.remove(h);
	}

	/* INTERNAL */

	private Hardpoint getHardpointByName(final String name) {
		for (final Hardpoint h : hardpoints)
			if (h.getName().equals(name))
				return h;
		return null;
	}
}

package raptor.modelMaker.model;

import java.util.HashMap;
import java.util.Map;

import raptor.modelMaker.math.Point;

public class PointFrame {
	private final Map<String, Point> mapByName;

	public PointFrame() {
		this.mapByName = new HashMap<>();
	}

	public boolean contains(final String name) {
		return mapByName.containsKey(name);
	}

	public Point getPosition(final String name) {
		return mapByName.get(name);
	}

	public Point remove(final String name) {
		if (!mapByName.containsKey(name))
			return null;

		final Point p = mapByName.remove(name);

		return p;
	}
}

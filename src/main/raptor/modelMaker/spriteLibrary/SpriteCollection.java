package raptor.modelMaker.spriteLibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpriteCollection {
	private final Map<String, Sprite> phases;

	private String name;

	public SpriteCollection(final String name) {
		this.phases = new HashMap<String, Sprite>();
		this.name = name;
	}

	public SpriteCollection(final String name, final Map<String, Sprite> phases) {
		this.phases = phases;
		this.name = name;
	}

	public Sprite getSprite(final String phase) {
		return phases.get(phase);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void addPhase(final String phase) {
		if (phases.containsKey(phase))
			return;

		phases.put(phase, new Sprite());
	}

	public void removePhase(final String phase) {
		phases.remove(phase);
	}

	public Set<String> getPhases() {
		return phases.keySet();
	}

	@Override
	public String toString() {
		return name;
	}
}

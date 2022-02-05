package raptor.modelMaker.spriteLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpriteLibrary {
	private final List<SpriteCollection> spriteCollections;

	private String name;
	private String location;

	public SpriteLibrary(final String name, final String location) {
		this.spriteCollections = new ArrayList<SpriteCollection>();
		this.location = location;
		this.name = name;
	}

	public SpriteLibrary(final String name, final String location, final List<SpriteCollection> spriteCollections) {
		this.spriteCollections = spriteCollections;
		this.location = location;
		this.name = name;
	}

	public SpriteCollection getSpriteCollection(final String name) {
		return getSpriteCollectionByName(name);
	}

	public void addSpriteCollection(final SpriteCollection spriteCollection) {
		if (getSpriteCollectionByName(spriteCollection.getName()) != null)
			throw new IllegalArgumentException(String.format("Sprite Collection with name '%s' already exists.", spriteCollection.getName()));
		spriteCollections.add(spriteCollection);
	}

	public void removeSpriteCollection(final String name) {
		for (int i = 0; i < spriteCollections.size(); i++) {
			final SpriteCollection spriteCollection = spriteCollections.get(i);
			if (spriteCollection.getName().equals(name)) {
				spriteCollections.remove(i);
				return;
			}
		}
	}

	public List<SpriteCollection> getSpriteCollections() {
		return Collections.unmodifiableList(spriteCollections);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		if (this.location != null && location == null)
			throw new IllegalArgumentException("Cannot unset sprite library location.");
		this.location = location;
	}

	private SpriteCollection getSpriteCollectionByName(final String name) {
		for (final SpriteCollection spriteCollection : spriteCollections)
			if (spriteCollection.getName().equals(name))
				return spriteCollection;
		return null;
	}
}

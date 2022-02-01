package raptor.modelMaker.spriteLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpriteLibrary {
	private final List<SpriteCollection> spriteCollections;

	private String name;

	public SpriteLibrary(final String name) {
		this.spriteCollections = new ArrayList<SpriteCollection>();
		this.name = name;
	}

	public SpriteLibrary(final String name, final List<SpriteCollection> spriteCollections) {
		this.spriteCollections = spriteCollections;
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

	private SpriteCollection getSpriteCollectionByName(final String name) {
		for (final SpriteCollection spriteCollection : spriteCollections)
			if (spriteCollection.getName().equals(name))
				return spriteCollection;
		return null;
	}
}

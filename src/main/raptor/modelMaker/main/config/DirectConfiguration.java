package raptor.modelMaker.main.config;

public class DirectConfiguration implements Configuration {
	private final String defaultSpriteLibrary;
	private final String fileChooserHome;

	public DirectConfiguration(final String defaultSpriteLibrary, final String fileChooserHome) {
		this.defaultSpriteLibrary = defaultSpriteLibrary;
		this.fileChooserHome = fileChooserHome;
	}

	@Override
	public String getDefaultSpriteLibraryPath() {
		return defaultSpriteLibrary;
	}

	@Override
	public String getFileChooserHomePath() {
		return fileChooserHome;
	}
}

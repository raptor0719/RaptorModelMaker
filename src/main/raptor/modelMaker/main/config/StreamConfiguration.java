package raptor.modelMaker.main.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.ConfigurationException;

public class StreamConfiguration implements Configuration {
	private final String defaultSpriteLibrary;
	private final String fileChooserHome;

	public StreamConfiguration(final InputStream configStream) throws IOException, ConfigurationException {
		final Properties props = new Properties();
		props.load(configStream);

		this.defaultSpriteLibrary = props.getProperty("default-sprite-library");
		System.out.println(defaultSpriteLibrary);
		if (!isValidFile(defaultSpriteLibrary))
			throw new ConfigurationException("The property 'default-sprite-library' was not a valid file.");

		this.fileChooserHome = props.getProperty("file-chooser-home");
		if (!isValidDirectory(fileChooserHome))
			throw new ConfigurationException("The property 'file-chooser-home' was not a valid directory.");
	}

	@Override
	public String getDefaultSpriteLibraryPath() {
		return defaultSpriteLibrary;
	}

	@Override
	public String getFileChooserHomePath() {
		return fileChooserHome;
	}

	private boolean isValidFile(final String path) {
		final File file = new File(path);

		return file.exists() && file.isFile();
	}

	private boolean isValidDirectory(final String path) {
		final File file = new File(path);

		return file.exists() && file.isDirectory();
	}
}

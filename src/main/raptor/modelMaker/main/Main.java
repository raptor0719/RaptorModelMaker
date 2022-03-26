package raptor.modelMaker.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import raptor.modelMaker.main.config.Configuration;
import raptor.modelMaker.main.config.DirectConfiguration;
import raptor.modelMaker.main.config.StreamConfiguration;

public class Main {
	private static final Configuration DEFAULT_CONFIGURATION = new DirectConfiguration(null, System.getProperty("user.dir"));

	public static void main(final String[] args) {
		final File config = new File("modelmaker.properties");

		InputStream configStream = null;
		StreamConfiguration configuration = null;
		try {
			configStream = new FileInputStream(config);
			configuration = new StreamConfiguration(configStream);
		} catch (final Exception e) {
			// swallow
		} finally {
			try {
				if (configStream != null)
					configStream.close();
			} catch (Throwable t) {
				// swallow
			}
		}

		new ModelMaker((configuration == null) ? DEFAULT_CONFIGURATION : configuration);
	}
}

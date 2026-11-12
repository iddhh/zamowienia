package org.zamowienia;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class LoadSettings {
    Parameters params = new Parameters();
    FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
            new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(params.properties()
                            .setFileName("app.settings.properties"));
    public String Directory() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        String envDirectory = dotenv.get("FILE_DIRECTORY");
        if (envDirectory != null && !envDirectory.isBlank()) {
            return envDirectory.trim();
        }

        try {
            Configuration config = builder.getConfiguration();
            return config.getString("file.directory");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
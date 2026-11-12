package org.zamowienia;

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
    public String Directory(){
        try {
            Configuration config = builder.getConfiguration();
            return config.getString("file.directory");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}

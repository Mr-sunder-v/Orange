package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() throws IOException {

        prop = new Properties();

        // Load common config
        String commonPath = System.getProperty("user.dir")
                + "/src/test/resources/config.properties";

        try (FileInputStream commonFis = new FileInputStream(commonPath)) {
            prop.load(commonFis);
        }

        // Load local config if it exists
        String localPath = System.getProperty("user.dir")
                + "/src/test/resources/config.local.properties";

        File localFile = new File(localPath);

        if (localFile.exists()) {
            try (FileInputStream localFis = new FileInputStream(localFile)) {
                prop.load(localFis);
            }
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
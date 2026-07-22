package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties prop;
	
	public ConfigReader() throws IOException {
		String path = System.getProperty("user.dir")
		        + "/src/test/resources/config.properties";

		FileInputStream fis = new FileInputStream(path);
		prop = new Properties();
		prop.load(fis);
		
	}
	
	public String getUrl() {
		return prop.getProperty("url");
	}

}

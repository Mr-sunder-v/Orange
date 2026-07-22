package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties prop;
	
	public ConfigReader() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\sunder\\OneDrive\\Desktop\\SCA\\dvja-master\\Orange\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);
		
	}
	
	public String getUrl() {
		return prop.getProperty("url");
	}

}

package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Framework.PageDriver;

public class ScreenshotUtils{
	
	WebDriver driver = PageDriver.getDriver();

    public static String captureScreenshot(WebDriver driver, String testName) {

        // Convert WebDriver into TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot and store it temporarily
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Create screenshots folder if it doesn't exist
        String folderPath = System.getProperty("user.dir") + "/Screenshots";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Destination path
        String destination = folderPath + "/" + testName + ".png";

        try {
            Files.copy(source.toPath(),
                    new File(destination).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination;
    }
}
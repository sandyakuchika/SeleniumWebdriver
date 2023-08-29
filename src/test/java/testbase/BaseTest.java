package testbase;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle resourceBundle;

	
	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		resourceBundle=ResourceBundle.getBundle("config");
		//driver.get(resourceBundle.getString("appUrl"));
		//driver.manage().window().maximize();
	}
}

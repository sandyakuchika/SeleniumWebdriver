package testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testbase.BaseTest;

public class BrokenLinks extends BaseTest {

	public BrokenLinks() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Test
	public void brokenLinksTest() throws IOException {
		driver.get("https://testautomationpractice.blogspot.com/");
		
		List<WebElement> links= driver.findElements(By.tagName("a"));
		for(WebElement link: links) {
		String href= link.getAttribute("href");
		if(href==null || href.isEmpty())
			continue;
		
		URL url=new URL(href);
		HttpURLConnection conection=(HttpURLConnection) url.openConnection();
		conection.connect();
		if(conection.getResponseCode()>=400)
			System.out.println("Invalid link: "+ href);
		else
			System.out.println("Valid link: "+ href);
		conection.disconnect();
		}
	}

}

package testcases;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.BlazePage;
import testbase.BaseTest;

public class BlazeTest extends BaseTest {

	@Test
	public void findFlightTest() {
		driver.get("http://blazedemo.com");
		driver.manage().window().maximize();
		// logger.info("Home Page opend");
		SoftAssert myassert = new SoftAssert();
		BlazePage blagePage = new BlazePage(driver);
		blagePage.setFromPort("Boston");
		blagePage.setToPort("London");
		blagePage.clickFindFlight();
		myassert.assertEquals(blagePage.isflightsListTitle("Boston", "London"), true);

		int rows = driver.findElements(By.xpath("//table[@class='table']//tbody//tr")).size();
		System.out.println("number of rows in table:" + rows); // 5
		// List<String> price= new ArrayList<String>();
		String pricesArray[] = new String[rows];
		for (int i = 1; i <= rows; i++) {
			pricesArray[i - 1] = driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + i + "]//td[6]"))
					.getText();
		}
		Arrays.sort(pricesArray); //
		for (String price : pricesArray) {
			System.out.println(price);
		}

		String lowestPrice = pricesArray[0];
		for (int i = 1; i <= rows; i++) {

			if (lowestPrice.equals(
					driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + i + "]//td[6]")).getText())) {
				driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + i + "]//td[1]")).click();
				break;
			}
		}

		blagePage.setName("John");
		blagePage.setAddress("1403 American Beauty Ln");
		blagePage.setCity("Columbus");
		blagePage.setState("OH");
		blagePage.setZipCode("43240");
		blagePage.setCreditCardNumber("6789067345231267");
		blagePage.setCreditCardYear("2022");
		blagePage.setNameOnCard("John Canedy");
		blagePage.clickPurchageFlight();
		
		String msg=driver.findElement(By.xpath("//h1")).getText();
		
		myassert.assertEquals(msg, "Thank you for your purchase");
		driver.quit();
	}

}

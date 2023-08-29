package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import testbase.BaseTest;
import utilities.ExcelUtils;

public class FixedDepositCalculator extends BaseTest {

	public FixedDepositCalculator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void DepositCalculatorTest() throws IOException, InterruptedException {

		driver.get(
				"https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
		driver.manage().window().maximize();

		String file = System.getProperty("user.dir") + "\\testdata\\caldata.xlsx";

		int rows = ExcelUtils.getRowCount(file, "Sheet1");

		for (int i = 1; i <=rows; i++) {

			String prncipal = ExcelUtils.getCellData(file, "Sheet1", i, 0);
			String rate = ExcelUtils.getCellData(file, "Sheet1", i, 1);
			String period1 = ExcelUtils.getCellData(file, "Sheet1", i, 2);
			String period2 = ExcelUtils.getCellData(file, "Sheet1", i, 3);
			String frequency = ExcelUtils.getCellData(file, "Sheet1", i, 4);
			String expMaturity = ExcelUtils.getCellData(file, "Sheet1", i, 5);
			String expected = ExcelUtils.getCellData(file, "Sheet1", i, 6);

			driver.findElement(By.id("principal")).clear();
			driver.findElement(By.id("principal")).sendKeys(prncipal);
			driver.findElement(By.id("interest")).clear();
			driver.findElement(By.id("interest")).sendKeys(rate);
			driver.findElement(By.id("tenure")).clear();
			driver.findElement(By.id("tenure")).sendKeys(period1);

			WebElement we = driver.findElement(By.id("tenurePeriod"));
			Select periodSelect = new Select(we);
			periodSelect.selectByVisibleText(period2);

			WebElement we1 = driver.findElement(By.id("frequency"));
			Select freqSelect = new Select(we1);
			freqSelect.selectByVisibleText(frequency);

			WebElement calcElement = driver.findElement(By.xpath("//div[@class='CTR PT15']/a[1]"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", calcElement);

			String actual_maturity = driver.findElement(By.id("resp_matval")).getText();

			if (Double.parseDouble(actual_maturity) == Double.parseDouble(expMaturity)) {
				ExcelUtils.setCellData(file, "Sheet1", i, 7, "Passed");
				ExcelUtils.fillGreenColor(file, "Sheet1", i, 7);
			} else {
				ExcelUtils.setCellData(file, "Sheet1", i, 7, "Failed");
				ExcelUtils.fillRedColor(file, "Sheet1", i, 7);
			}
			Thread.sleep(3000);

			WebElement clearElement = driver.findElement(By.xpath("//div[@class='CTR PT15']/a[2]"));
			je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", clearElement);

		}
		
		driver.quit();

	}

}

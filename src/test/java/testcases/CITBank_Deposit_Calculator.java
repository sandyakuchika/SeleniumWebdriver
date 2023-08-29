package testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testbase.BaseTest;
import utilities.ExcelUtils;

public class CITBank_Deposit_Calculator extends BaseTest {

	public CITBank_Deposit_Calculator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void depositCalculatior() throws IOException {
		
		driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");
		driver.manage().window().maximize();
		
		String file=System.getProperty("user.dir")+"\\testdata\\caldata2.xlsx";
		int rows= ExcelUtils.getRowCount(file, "Sheet1");
		WebElement weDeposit=driver.findElement(By.xpath("//input[@id='mat-input-0']"));
		WebElement calbutton = driver.findElement(By.xpath("//button[@id='CIT-chart-submit']")); // 'Lets run the numbers' button
		
		for(int i=1;i<=rows;i++) {
			
			String deposit=ExcelUtils.getCellData(file, "Sheet1", i, 0);
			String months=ExcelUtils.getCellData(file, "Sheet1", i, 1);
			String interest=ExcelUtils.getCellData(file, "Sheet1", i, 2);
			String compundTenure=ExcelUtils.getCellData(file, "Sheet1", i, 3);
			String exptotal=ExcelUtils.getCellData(file,"Sheet1",i, 4);
			
			driver.findElement(By.xpath("//input[@id='mat-input-0']")).clear();
			driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys(deposit);
			driver.findElement(By.xpath("//input[@id='mat-input-1']")).clear();
			driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys(interest);
			driver.findElement(By.xpath("//input[@id='mat-input-2']")).clear();
			driver.findElement(By.xpath("//input[@id='mat-input-2']")).sendKeys(months);
			
			
			//Dropdown (Boostrap) - Not having Select Tag
			WebElement we = driver.findElement(By.xpath("//mat-select[@id='mat-select-0']"));
			we.click();
			
            List<WebElement> options=driver.findElements(By.xpath("//div[@id='mat-select-0-panel']//mat-option"));
			
			for(WebElement option:options)
			{
				if(option.getText().equals(compundTenure))
					option.click();
			}
			
			calbutton.click();   ///click on button to calculate cd calculation based on xl cell data
			String acttotal = driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();
			
			System.out.println("act total is: " + acttotal);
			System.out.println("exp total is: " + exptotal);
			
			if(exptotal.equals(acttotal)) {			//if expected total = actual total then			
				
				ExcelUtils.setCellData(file, "Sheet1",i, 6,"Passed");	//setting passed in 6th column (index start with zero)
				ExcelUtils.fillGreenColor(file, "Sheet1",i, 6);	//filling the color in 6th column if passed then greeen or faile then red.
			}
			else
			{
				ExcelUtils.setCellData(file, "Sheet1",i, 6,"Failed");
				ExcelUtils.fillRedColor(file, "Sheet1",i, 6);
			}
		}
			System.out.println("calculation has been completed");
			driver.close();
			
		}
	
}

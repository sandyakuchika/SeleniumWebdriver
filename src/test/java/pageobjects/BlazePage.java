package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BlazePage extends BasePage {

	public BlazePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name = "fromPort")
	WebElement fromPort;

	@FindBy(name = "toPort")
	WebElement toPort;

	@FindBy(xpath = "//input[@value='Find Flights']")
	WebElement findFlight;

	@FindBy(tagName = "h3")
	WebElement flightsListTitle;

	@FindBy(id = "inputName")
	WebElement name;

	@FindBy(id = "address")
	WebElement address;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "zipCode")
	WebElement zipCode;

	@FindBy(id = "creditCardNumber")
	WebElement creditCardNumber;

	@FindBy(id = "creditCardYear")
	WebElement creditCardYear;

	@FindBy(id = "nameOnCard")
	WebElement nameOnCard;

	@FindBy(xpath = "//input[@value='Purchase Flight']")
	WebElement purchageFlight;

	public void setFromPort(String from) {
		// fromPort.sendKeys(from);
		Select se = new Select(fromPort);
		se.selectByVisibleText(from);
	}

	public void setToPort(String to) {
		// fromPort.sendKeys(to);
		Select se = new Select(toPort);
		se.selectByVisibleText(to);
	}

	public void clickFindFlight() {
		findFlight.click();
	}

	public boolean isflightsListTitle(String from, String to) {
		return flightsListTitle.getText().equals("Flights from" + from + " to " + to + ": ");
	}

	public void setName(String name) {
		this.name.sendKeys(name);
	}

	public void setAddress(String address) {
		this.address.sendKeys(address);
	}

	public void setCity(String city) {
		this.city.sendKeys(city);
	}

	public void setState(String state) {
		this.state.sendKeys(state);
	}

	public void setZipCode(String zipCode) {
		this.zipCode.sendKeys(zipCode);
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber.sendKeys(creditCardNumber);
	}

	public void setCreditCardYear(String creditCardYear) {
		this.creditCardYear.clear();
		this.creditCardYear.sendKeys(creditCardYear);
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard.sendKeys(nameOnCard);
	}

	public void clickPurchageFlight() {
		this.purchageFlight.click();
	}

}

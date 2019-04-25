package com.ashok_Assignment.PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ashok_Assignment.Base.BaseClass;
import com.ashok_Assignment.utils.UtilClass;

public class HomePage extends BaseClass {
	private String currentDate = "";
	WebDriverWait wait = new WebDriverWait(driver, UtilClass.wedriverWait);

	@FindBy(xpath = "//a[@class='mmtLogo makeFlex']")
	private WebElement logo;

	@FindBy(xpath = "//ul[@class='makeFlex font12']/li[1]/a/span[text()='Flights']")
	private WebElement flights;

	@FindBy(xpath = "//div[@class='makeFlex']/ul/li[text()='Round Trip']")
	private WebElement roundTrip;

	@FindBy(xpath = "//label[@for='fromCity']//span[contains(text(),'From')]")
	private WebElement fromButton;

	@FindBy(xpath = "//div[@role='combobox']//input")
	private WebElement fromTextBox;

	@FindBy(xpath = "//div[@class='calc60']//p[@class='font16 appendBottom8' and contains(text(),'Delhi, India')]//parent::div")
	private WebElement selectDelhi;

	@FindBy(xpath = "//label[@for='toCity']//span[contains(text(),'To')]")
	private WebElement toButton;

	@FindBy(xpath = "//div[@role='combobox']//input")
	private WebElement toTextBox;

	@FindBy(xpath = "//div[@class='pushRight font14 lightGreyText latoBold' and text()='BLR']")
	private WebElement selectBangalore;

	@FindBy(xpath = "//div[@class='DayPicker-Day DayPicker-Day--today']//div//p")
	private WebElement todayDate;

	@FindBy(xpath = "//label[@for='departure']//span[text()='DEPARTURE']")
	private WebElement departureButton;

	@FindBy(xpath = "//label[@for='return']//span[text()='RETURN']")
	private WebElement returnButton;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	private WebElement nextMonth;

	@FindBy(xpath = "//a[text()='Search']")
	private WebElement searchButton;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnReturnDate() {
		returnButton.click();
	}

	//set return journy date
	public void selectReturnDate(String dateoftoday, int noOfdays) {

		int Day = Integer.parseInt(dateoftoday);
		
		int month=UtilClass.currentMonth();//get current month number
		int returnDay = noOfdays + Day;
		if(month%2==0&&returnDay<=30){
			
			String returnDate = Integer.toString(returnDay);

			String returndateXpath = "//div[@class='dateInnerCell']//p[text()='" + returnDate
					+ "']//parent::div//parent::div[@aria-disabled='false']";

			WebElement returndate = driver.findElement(By.xpath(returndateXpath));
			returndate.click();
			
		}else if(month%2==1&&returnDay<=31){
			
			String returnDate = Integer.toString(returnDay);

			String returndateXpath = "//div[@class='dateInnerCell']//p[text()='" + returnDate
					+ "']//parent::div//parent::div[@aria-disabled='false']";

			WebElement returndate = driver.findElement(By.xpath(returndateXpath));
			returndate.click();
			
		}else if (returnDay > 30 && returnDay<=61 ) {
			nextMonth.click();
			returnDay = returnDay - 30;
			
			String returnDate = Integer.toString(returnDay);

			String returndateXpath = "//div[@class='dateInnerCell']//p[text()='" + returnDate
					+ "']//parent::div//parent::div[@aria-disabled='false']";

			WebElement returndate = driver.findElement(By.xpath(returndateXpath));
			returndate.click();
			
		}else{
			
			System.out.println("returnDateAfterNoOfDays should not be exceed 61 when adding with current date ");
		}

		

	}

	//click on current date
	public String clickOnTodayDate() {
		wait.until(ExpectedConditions.elementToBeClickable(departureButton)).click();
		currentDate = todayDate.getText();
		todayDate.click();
		return currentDate;
	}

	//click on flights
	public void clickOnFlights() {
		wait.until(ExpectedConditions.elementToBeClickable(flights)).click();

	}

	//click on roundtrip
	public void clickOnRoundtrip() {
		wait.until(ExpectedConditions.elementToBeClickable(roundTrip)).click();

	}

	
	public String getLogoText() {
		wait.until(ExpectedConditions.invisibilityOf(logo));
		String logo_text = logo.getText();
		return logo_text;
	}

	//Enter city name in From texbox
	public void setDepartureCity(String cityName) {
		fromButton.click();
		fromTextBox.sendKeys(cityName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectDelhi.click();
	}

	//Enter city name in To texbox
	public void setArrivalCity(String cityName) {
		wait.until(ExpectedConditions.elementToBeClickable(toButton));
		toTextBox.sendKeys(cityName);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		selectBangalore.click();
	}

	//click on search button
	public FlightsPage clickOnSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		return new FlightsPage();
	}

	//get home page title
	public String getHomePageTitle() {
		return driver.getTitle();
	}

}

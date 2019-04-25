package com.ashok_Assignment.PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ashok_Assignment.Base.BaseClass;
import com.ashok_Assignment.utils.UtilClass;

public class FlightsPage extends BaseClass {
	
	WebDriverWait wait = new WebDriverWait(driver, UtilClass.wedriverWait);
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//div[2]//div[@class='fli-list splitVw-listing']")
	private List<WebElement> departureFlights;
	
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//div[2]//div[@class='fli-list splitVw-listing']")
	private List<WebElement> returnFlights;
	
	@FindBy(xpath="//span[@class='labeltext' and text()='Non Stop']//parent::span//parent::label[@for='filter_stop0']/span/span")
	private WebElement nonStop;
	
	@FindBy(xpath="//span[@class='labeltext' and text()='1 Stop']//parent::span//parent::label[@for='filter_stop1']/span/span")
	private WebElement oneStop;
	
	@FindBy(xpath="//div[@id='fli_filter__stops']//a[text()='Reset'][1]")
	private WebElement reset;
	
	@FindBy(xpath="//a[@class='active makeFlex hrtlCenter column']//span[@class='chNavIcon appendBottom2 chSprite chFlights active']")
    private WebElement activeFlightLogo;
	
	@FindBy(xpath="//div[@class='splitVw-footer-left ']//div[4]/p[@class='actual-price']/span")
	private WebElement actual_price_of_departure;
	
	@FindBy(xpath="//div[@class='splitVw-footer-right ']//div[4]/p[@class='actual-price']/span")
	private WebElement actual_price_of_return;
	
	@FindBy(xpath="//div[@class='footer-fare']/p/span/span")
	private WebElement totalPrice;
	
	@FindBy(xpath="//div[@class='pull-left font14 LatoBold splitVw-sort-sctn']/p")
	private List<WebElement> dateAndPlace;
	
	public FlightsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void get_Place_and_Date(){
		System.out.println("******Journy Details******");
		System.out.println("");
		int i=1;
		
		for(WebElement element:dateAndPlace){
			String placeDetails=element.getText();
			System.out.println(placeDetails);
			if(i==2){
				System.out.println("");
			}
			i++;
		}
		System.out.println("");
		
	}
	//Reset all checkboxes
	public void resetAllCheckBoxes(){
          reset.click();
	}
	
	//click on checkbox of Non Stop
	public void click_On_Non_Stop(){
		nonStop.click();
	}
	
	//click on checkbox of One Stop
	public void click_On_One_Stop(){
		oneStop.click();
	}
	
	//Scrolling page
	public void pageScrollDown() throws InterruptedException{
		Thread.sleep(5000);
		int scroll=2000;
		for(int i=0;i<=10;i++){
			String scrollStr=Integer.toString(scroll);
			UtilClass.scrollPageDown(driver,scrollStr);
			Thread.sleep(2000);
			scroll+=2000;
		}
		
		
		UtilClass.scrollPageDown(driver);
		Thread.sleep(5000);
		UtilClass.scrollPage(driver,activeFlightLogo);
		Thread.sleep(5000);

	}
	
	//get total noOf departure Flights
	public int get_noOf_departureFlights(){
		
		//wait.until(ExpectedConditions.visibilityOfAllElements(departureFlights));
		int totalnoOfFlights=departureFlights.size();
		
		return totalnoOfFlights;
	}
	
	//get total noOf return Flights
	public int get_noOf_returnFlights(){

		//wait.until(ExpectedConditions.visibilityOfAllElements(returnFlights));
		int totalnoOfFlights=returnFlights.size();
		
		return totalnoOfFlights;
	}
	
	//return active flight_logo WebElement
	public WebElement get_active_flight_logo(){
		return activeFlightLogo;
	}
	
	//return title of Flight Page
	public String get_title_Of_FlightPage(){
		return driver.getTitle();
	}
	
	//select departure flight
	public void select_departure_Flight(int num){
		String flightNum=Integer.toString(num);
		String flight="//div[@class='splitVw-sctn pull-left']//div[2]//div[@class='fli-list splitVw-listing']["+flightNum+"]//label[@class='splitVw-radio clearfix cursor_pointer']/div[1]/span[1]/span";
		
		UtilClass.scrollPage(driver,driver.findElement(By.xpath(flight)));
		WebElement radio=driver.findElement(By.xpath(flight));
		if(!radio.isSelected()){
			UtilClass.clickOnElement(driver, radio);
		}
	}
	
	//select return flight
	public void select_return_Flight(int num){
		String flightNum=Integer.toString(num);
		String flight="//div[@class='splitVw-sctn pull-right']//div[2]//div[@class='fli-list splitVw-listing']["+flightNum+"]//label[@class='splitVw-radio clearfix cursor_pointer']/div[1]/span[1]/span";
		
		UtilClass.scrollPage(driver,driver.findElement(By.xpath(flight)));
		WebElement radio=driver.findElement(By.xpath(flight));
		if(!radio.isSelected()){
			UtilClass.clickOnElement(driver, radio);
		}
	}
	
	//get price of DepartureFlight
	public int get_Price_of_DepartureFlight(int num){
		String flightNum=Integer.toString(num);
		String price="//div[@class='splitVw-sctn pull-left']//div[2]//div[@class='fli-list splitVw-listing']["+flightNum+"]//label[@class='splitVw-radio clearfix cursor_pointer']/div[2]/div[3]/p/span[text()]";
		
		UtilClass.scrollPage(driver,driver.findElement(By.xpath(price)));
		String priceOfFlight=driver.findElement(By.xpath(price)).getText().replaceAll("[^0-9]","");
		int priceInt=Integer.parseInt(priceOfFlight);
		
		return priceInt;
	}
	
	//get price of return flight
	public int get_Price_of_ReturnFlight(int num){
		String flightNum=Integer.toString(num);
		String price="//div[@class='splitVw-sctn pull-right']//div[2]//div[@class='fli-list splitVw-listing']["+flightNum+"]//label[@class='splitVw-radio clearfix cursor_pointer']/div[2]/div[3]/p/span[text()]";
		
		
		UtilClass.scrollPage(driver,driver.findElement(By.xpath(price)));
		
		
		String priceOfFlight=driver.findElement(By.xpath(price)).getText().replaceAll("[^0-9]","");
		int priceInt=Integer.parseInt(priceOfFlight);
		return priceInt;
	}
	
	//get actual price of departure flight
	public int get_actual_departure_price(){
		UtilClass.scrollPage(driver,actual_price_of_departure);
		String price=actual_price_of_departure.getText().replaceAll("[^0-9]","");
		
		int priceInt=Integer.parseInt(price);
		return priceInt;
	}
	
	//get actual price of return flight
	public int get_actual_return_price(){
		UtilClass.scrollPage(driver,actual_price_of_return);
		String price=actual_price_of_return.getText().replaceAll("[^0-9]","");
		int priceInt=Integer.parseInt(price);
		return priceInt;
	}
	
	//get total price of tickets
	public int get_total_price(){
		UtilClass.scrollPage(driver,totalPrice);
		String stringPrice=totalPrice.getText().replaceAll("[^0-9]","");
		
		int priceInt=Integer.parseInt(stringPrice);
		
		return priceInt;
	}
	
	public int get_top_flights(int maxflightsnum){
		
		int maxFlightsValue=0;
		
		if(maxflightsnum<10){
			maxFlightsValue=maxflightsnum;
		}else if(maxflightsnum>=10){
			maxFlightsValue=10;
		}
		
		return maxFlightsValue;
		
	}

}











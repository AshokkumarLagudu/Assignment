package com.PageTestClasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ashok_Assignment.Base.BaseClass;
import com.ashok_Assignment.PageClasses.FlightsPage;
import com.ashok_Assignment.PageClasses.HomePage;
import com.ashok_Assignment.utils.UtilClass;

public class HomePageTest extends BaseClass {

	public HomePage homePage;
	public FlightsPage flightsPage;
	public HomePageTest(){
		super();
	}
	
	//Application Initialization
	@BeforeClass
	public void openBrowser(){
		initialization();
		homePage=new HomePage();
		
	}
	 
	//validate home page title
	@Test(priority=1)
	public void verifyHomepageTitle(){
		String title=homePage.getHomePageTitle();
		Assert.assertEquals(title, UtilClass.homepageTitle);
	}
	
	//Enter the details in From and To texboxes 
	//click on the Search box
	@Test(priority=2)
	public void searchFlightsTest(){
		
		homePage.clickOnFlights();
		homePage.clickOnRoundtrip();
		homePage.setDepartureCity("Delhi");
		
		homePage.setDestinationCity("Bangalore");
		
		String dateoftoday=homePage.clickOnTodayDate();
		
		homePage.clickOnReturnDate();
		homePage.selectReturnDate(dateoftoday,UtilClass.returnDateAfterNoOfDays);
		
		flightsPage=homePage.clickOnSearch();
		
		System.out.println("Title of the page-->"+flightsPage.get_title_Of_FlightPage());
		Assert.assertEquals(flightsPage.get_title_Of_FlightPage(), UtilClass.flightPageTitle);
		
		
	}
	
	//close browser
	@AfterClass
	public void closeBrowser(){
	   killBrowser();
	}
	
	
}

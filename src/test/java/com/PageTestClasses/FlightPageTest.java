package com.PageTestClasses;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ashok_Assignment.Base.BaseClass;
import com.ashok_Assignment.PageClasses.FlightsPage;
import com.ashok_Assignment.PageClasses.HomePage;
import com.ashok_Assignment.utils.UtilClass;


public class FlightPageTest extends BaseClass {

	public HomePage homePage;
	public FlightsPage flightsPage;
	public int totalDFlights = 0;

	public int totalRFlights = 0;

	public FlightPageTest() {
		super();
	}

	// Initializing the Application
	@BeforeClass
	public void openBrowser() throws InterruptedException {
		initialization();
		homePage = new HomePage();

		Assert.assertEquals(homePage.getHomePageTitle(), UtilClass.homepageTitle);
		
		
		try {
			
			homePage.clickOnFlights();
			homePage.clickOnRoundtrip();
			homePage.setDepartureCity("Delhi");

			homePage.setDestinationCity("Bangalore");

			String dateoftoday = homePage.clickOnTodayDate();

			homePage.clickOnReturnDate();
			homePage.selectReturnDate(dateoftoday, UtilClass.returnDateAfterNoOfDays);
			flightsPage = homePage.clickOnSearch();
		} catch (Exception e) {
			
			homePage.closeImage();
			homePage.clickOnFlights();
			homePage.clickOnRoundtrip();
			homePage.setDepartureCity("Delhi");

			homePage.setDestinationCity("Bangalore");

			String dateoftoday = homePage.clickOnTodayDate();

			homePage.clickOnReturnDate();
			homePage.selectReturnDate(dateoftoday, UtilClass.returnDateAfterNoOfDays);
			flightsPage = homePage.clickOnSearch();
		}
		
	}

	// validating title of the flight page
	// validating active flight logo is present or not
	@Test(priority = 1)
	public void validateFlightPageTest() throws InterruptedException {

		boolean activeFlightLogo = flightsPage.get_active_flight_logo().isDisplayed();
		if(activeFlightLogo!=true){
			driver.navigate().refresh();
			Thread.sleep(3000);
			activeFlightLogo = flightsPage.get_active_flight_logo().isDisplayed();
		}
		Assert.assertEquals(activeFlightLogo, true);

		Assert.assertEquals(flightsPage.get_title_Of_FlightPage(), UtilClass.flightPageTitle);
		System.out.println("Title of the flight page-->" + flightsPage.get_title_Of_FlightPage());
		System.out.println("");

		
		try {
			flightsPage.get_Place_and_Date();
		} catch (Exception e) {
			
			System.out.println("****Error while geting Journey details****");
		}
		
		Assert.assertEquals(flightsPage.departureCity, "New Delhi");
		Assert.assertEquals(flightsPage.arrivalCity, "Bengaluru");
	}

	// print number of all type of Flights present on the page
	@Test(priority = 2,dependsOnMethods={"validateFlightPageTest"})
	public void display_noOf_FlightTest() throws InterruptedException {
		flightsPage.pageScrollDown();
		int totaldepartureFlights = flightsPage.get_noOf_departureFlights();

		System.out.println("Total NoOf_departureFlights = " + totaldepartureFlights);
		System.out.println("");

		int totalReturnFlights = flightsPage.get_noOf_returnFlights();

		System.out.println("Total NoOf_ReturnFlights = " + totalReturnFlights);

		System.out.println("");

		totalDFlights = totaldepartureFlights;
		totalRFlights = totalReturnFlights;
	}

	// print number of Non-Stop Flights present on the page
	@Test(priority = 3,dependsOnMethods={"validateFlightPageTest"})
	public void display_noOf_Non_Stop_FlightTest() throws InterruptedException {
		flightsPage.resetAllCheckBoxes();
		flightsPage.click_On_Non_Stop();

		flightsPage.pageScrollDown();

		int totaldepartureFlights = flightsPage.get_noOf_departureFlights();

		System.out.println("================No Of Non Stop Flights===================");
		System.out.println("Total NoOf_departureFlights = Rs." + totaldepartureFlights);

		System.out.println("");

		int totalReturnFlights = flightsPage.get_noOf_returnFlights();

		System.out.println("Total NoOf_ReturnFlights = Rs." + totalReturnFlights);

		System.out.println("");
		flightsPage.resetAllCheckBoxes();
	}

	// print number of Single-Stop Flights present on the page
	@Test(priority = 4,dependsOnMethods={"validateFlightPageTest"})
	public void display_noOf_One_Stop_FlightTest() throws InterruptedException {
		flightsPage.resetAllCheckBoxes();
		flightsPage.click_On_One_Stop();

		flightsPage.pageScrollDown();

		int totaldepartureFlights = flightsPage.get_noOf_departureFlights();

		System.out.println("================No Of One Stop Flights===================");
		System.out.println("Total NoOf_departureFlights = " + totaldepartureFlights);

		System.out.println("");

		int totalReturnFlights = flightsPage.get_noOf_returnFlights();

		System.out.println("Total NoOf_ReturnFlights = " + totalReturnFlights);

		System.out.println("");
		flightsPage.resetAllCheckBoxes();
	}

	// click on radio buttons of the flights
	// getting the price of the Flights
	// compare selected flight price and actual flight price displayed on bottom
	// of the page
	// validate total price of the Flights
	@Test(priority = 5,dependsOnMethods={"validateFlightPageTest"})
	public void selectFlightTest() throws InterruptedException {

		// get max departure flights numbers to select
		int maxDepFlightValue = flightsPage.get_top_ten_flights(totalDFlights);
		// get max flights number to select
		int maxRetFlightValue = flightsPage.get_top_ten_flights(totalRFlights);

		// validating top ten flights price ten times by selecting random
		// flights
		for (int i = 1; i <= 10; i++) {

			// get random departure flights numbers between 1 and
			// maxDepFlightValue
			int depFlightNum = UtilClass.get_Random_Number(maxDepFlightValue);
			
			// get random return flights numbers between 1 and maxRetFlightValue
			int retFlightNum = UtilClass.get_Random_Number(maxRetFlightValue);
			
			//if depFlightNum and retFlightNum is equal
			while (depFlightNum == retFlightNum) {
				retFlightNum = UtilClass.get_Random_Number(maxRetFlightValue);
			}

			// click on departure flight radio button
			flightsPage.select_departure_Flight(depFlightNum);
			// click on return flight radio button
			flightsPage.select_return_Flight(retFlightNum);

			// get price of selected departure flight
			int selectedDeparturePrice = flightsPage.get_Price_of_DepartureFlight(depFlightNum);
			// get price of selected return flight
			int selectedReturnPrice = flightsPage.get_Price_of_ReturnFlight(retFlightNum);

			// get actual price of departure flight displayed on bottom of the
			// page
			int actualDeparturePrice = flightsPage.get_actual_departure_price();
			// get actual price of return flight displayed on bottom of the page
			int actualReturnPrice = flightsPage.get_actual_return_price();

			// print flights price on console
			System.out.println("                          selected    Actual");
			System.out.println(depFlightNum + ". DepartureFlightPrice->Rs." + selectedDeparturePrice + "    Rs."
					+ actualDeparturePrice);
			System.out.println(retFlightNum + ". ReturnFlightPriceo  ->Rs." + selectedReturnPrice + "     Rs."
					+ actualReturnPrice);

			// get total price flights
			int totalSelected = selectedDeparturePrice + selectedReturnPrice;

			// print total price on console
			System.out.println("Total Selected Flights Price = Rs." + totalSelected);
			System.out.println("Tatal Actual Flights Price   = Rs." + flightsPage.get_total_price());
			System.out.println("============================================");

			// check flights price equal or not
			Assert.assertEquals(selectedDeparturePrice, actualDeparturePrice);
			Assert.assertEquals(selectedReturnPrice, actualReturnPrice);

			// check flights total price equal or not
			int totalprice = selectedDeparturePrice + selectedReturnPrice;
			Assert.assertEquals(totalprice, flightsPage.get_total_price());

		}

	}

	// close Browser
	@AfterClass
	public void closeBrowser() {
		killBrowser();
	}

}

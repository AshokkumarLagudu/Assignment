package com.ashok_Assignment.utils;


import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class UtilClass {

	public final static String homepageTitle="MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday";
	
	public final static String flightPageTitle="Makemytrip";
	
	//noOf days after current date 
	public final static int returnDateAfterNoOfDays=7;//number should be currentDate+number<=61;
	
	public final static int wedriverWait=30;
	public final static int pageloadTimeout=60;
	
	public final static String screenShotFilePath="C:\\Users\\ASHOK\\workspace\\Selenium_Coding_Assignment_21-04-2019\\Screenshots\\";
	public final static String propFilePath="C:\\Users\\ASHOK\\workspace\\Selenium_Coding_Assignment_21-04-2019\\src\\main\\"
				+ "java\\com\\ashok_Assignment\\configuration\\config.properties";
	//page scrolling down upto end
	public static void scrollPageDown(WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}
	
	//page scrolling upto given location
	public static void scrollPageDown(WebDriver driver,String num){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, "+ num+")");
		
	}
	
	//page scrolling upto element visibile
	public static void scrollPage(WebDriver driver,WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
		js.executeScript("arguments[0].scrollIntoView();",element );
	}
	
	//click on element using javaScripExecuter
	public static void clickOnElement(WebDriver driver,WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click()", element);
	}
	
	//get current month number in integer format
	public static int currentMonth(){
		Calendar cal=Calendar.getInstance();
        int thisMonth=cal.get(Calendar.MONTH)+1;
        return thisMonth;
	}
	
	//get random number between 1 and number
	public static int get_Random_Number(int number){
		Random rn = new Random();
    	int answer = rn.nextInt(number)+1;
    	return answer;
	}
	
	
	 
	
}


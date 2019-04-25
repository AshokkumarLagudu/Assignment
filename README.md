# Selenium_Coding_Assignment_24-04-2019 By Ashok kumar

This framework has script for following test cases

1) go to this url - https://www.makemytrip.com
2) Click on flights link and click on round trip
3) Select from: Delhi and To: Bangalore
4) Select departure date (today’s date) and return date: after 7 days 
5) Click on Search
6) Print total number of records of “Departure Flight” and “Return Flight” on the console
7) Select Non-Stop and 1 Stop filter options and print total number of records of “Departure Flight” and “Return Flight” on the console
8) Select radio button of top 10 options of “Departure Flight” and “Return Flight”
9) Verify the same Departure Flight price and Return Flight price are getting reflected at bottom of the page
10) Verify the correct total amount (Departure Flight price + Return Flight price) is getting reflected correctly

FrameWork Structure
=====================   
before execute please change 
-----------------------------
* browserDrivers paths in config.properties file
* propFilePath in UtilClass
* screenShotFilePath in UtilClass


src/main/java                                                        
 -> com.ashok_Assignment
        
	//ininitializ the properties file
      ->BaseClass
        
	//ininitializ the browser 
	 ->public void initialization()
          
	  //take screenShots
         ->public void takeScreenShot(String methodName)
         
     ->configuration
        config.prperties file
        
     ->Listeners
         //Generate extent report
        -> ExtentReportGen
        //take screenshots for fail testcases
        ->TestListener
     
     ->pageClasses
         ->FlightsPage
         ->HomePage         
    
     ->utils
        //It contains reusable methods and final static variables
         ->UtilClass
             public final static String homepageTitle
             public final static String flightPageTitle
             //noOf days after current date 
	           public final static int returnDateAfterNoOfDays=7
             public final static int wedriverWait
             public final static int pageloadTimeout
             
             public final static String propFilePath//properties file path
             
             //page scrolling down upto end
           	public static void scrollPageDown(WebDriver driver)
           
             //page scrolling upto given location
	          public static void scrollPageDown(WebDriver driver,String num)
            
            //click on element using javaScripExecuter
	          public static void clickOnElement(WebDriver driver,WebElement element)
         
           //get current month number in integer format
	          public static int currentMonth()
         
     ->resources
          //Run as testNgSuite
         //execute all pageTest classes
         ->Selenium_Coding_Assignment_21-04-2019_suite
         
  src/test/java
       ->PagetestClasses
       
           ->HomePageTest
           ->FlightPageTest
      
  
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
         
         

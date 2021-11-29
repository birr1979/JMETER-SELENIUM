package TestCases;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestShoppingTransaction {

	/**
	 * This test is for USDA PI 15 JMeter Enabler, Project demo for load testing,
	 * Client side UI Test This class is a test case for Costco.com webPage, to
	 * check for delivery ZipCode Update, Search for Products, and Adding/ removing
	 * Items to/form the cart All WebElement Locators are found in this class as a
	 * static variable Also a method is included to capture all the products
	 * description in the search result
	 * 
	 */

	public static WebDriver driver = null;
	public static WebDriverWait wait;
	// Given TestData
	public static String URL = "https://www.costco.com";
	public static String zipCode = "22031";
	public static String searchItem = "Breakfast";
	// Locators
	public static String Grocery_ID = "Home_Ancillary_0";
	public static String BeveragesAndWater_LINKTEXT = "Beverages & Water";
	public static String DeliveryZipCode_ID = "delivery-postal-change";
	public static String DeliveryZipCodeInput_ID = "postal-code-input";
	public static String SetDeliveryButton_ID = "postal-code-submit";
	public static String ChangeDeliveryZipCode_XPATH = "//button[@class='btn btn-primary btn-block']";
	public static String Brand_LINKTEXT = "Brand";
	public static String FiveHourEnergyBrand_XPATH = "//fieldset//div//label[@for='refinement_4-2']";
	public static String First5hrAddtoCart_ID = "addbutton-0";
	public static String FiveHrenergyHeader1 = "//h1[contains(text(),'5-hour Energy Beverages & Water')]";
	public static String SearchBar_ID = "search-field";
	public static String SearchResult_XPATH = "//h1[contains(text(),'We found')]";
	public static String ProductsName_XPATH = "(//div[@id='search-results'])//span//a";
	public static String Breakfast1stItem_XPATH = "((//div[@id='search-results'])//span//a)[1]";
	public static String searchBotton = "//button[@class='btn search-ico-button']";
	public static String breakFastAddToCart_XPATH = "//input[@id='add-to-cart-btn']";
	public static String cartItemAmount_XPATH = "//li//a[@id='cart-d']//div//span";
	public static String Cart_XPATH = "//a[@id='cart-d']";
	public static String cartHeader = "//div[@id='cart-title']//h1";
	public static String IncreaseItem = "//button[@name='plusQty']";
	public static String RemoveItem = "(//div[@class='col-xs-4 col-xl-7']//span)[2]";
	public static String cartTotal = "//span[@id='order-estimated-total']";

	@SuppressWarnings("deprecation")
	@Before
	public void BeforeTest() {
		// launching the Web-Browser
		System.out.println("Before test starting execution");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\birr_\\Desktop\\JMETERFILES\\chromedriver.exe");
		try {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			System.out.println("Info: Chrome Browser Launched successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error: wrong Browser Name is provided in the argument.");
		}
	}

	@After
	public void AfterTest() {
		System.out.println("Test execution Finished closing Browser");
		driver.close();
	}

	@Test
	public void CartTest() throws InterruptedException {

		// Navigating to the web-Page
		// driver.get(URL);
		// System.out.println("Info: Navigated to Costco Home Page titled:
		// "+driver.getTitle());
		//
		// //Clicking on "Beverages and Water" found in the "Grocery" Menu
		// action.moveToElement(driver.findElement(By.id(Grocery_ID))).build().perform();
		// System.out.println("Hovering on the Grocery tab.");
		// Thread.sleep(5000);
		// driver.findElement(By.linkText(BeveragesAndWater_LINKTEXT)).click();
		// System.out.println("Beverages & Water Link Clicked.");

		/**
		 * SInce the Costco home page is not responding due to the Web site
		 * compatibility issue I have bypassed the link to the beverage section which is
		 * the successive code of the above commented code.
		 */
		driver.get("https://www.costco.com/beverages.html");
		// Updating the Delivery ZipCode.
		Thread.sleep(2000);
		driver.findElement(By.id(DeliveryZipCode_ID)).click();

		// send key to the Zip Code input using Java script executer
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		WebElement zipCodeInput = driver.findElement(By.id(DeliveryZipCodeInput_ID));
		js.executeScript("arguments[0].value='" + zipCode + "'", zipCodeInput);
		System.out.println(zipCode + " is entered in the field");
		driver.findElement(By.id(SetDeliveryButton_ID)).click();
		// driver.findElement(By.xpath(ChangeDeliveryZipCode_XPATH)).click();
		// //>>>>>Optional some times needed
		System.out.println("On " + driver.getTitle() + " Page Delivery Zip Code Updated to: " + zipCode);

		// selecting "5-hour Energy" from the Brand Filter
		driver.findElement(By.linkText(Brand_LINKTEXT)).click();
		driver.findElement(By.xpath(FiveHourEnergyBrand_XPATH)).click();
		System.out.println("Page Header shown as " + driver.findElement(By.xpath(FiveHrenergyHeader1)).getText());// Verify??

		driver.findElement(By.id(First5hrAddtoCart_ID)).click();
		System.out.println("Cart is Updated and now has: "
				+ driver.findElement(By.xpath(cartItemAmount_XPATH)).getText() + " item in the cart.");
		System.out.println("The First 5 hour Energy Item is Added to the Cart and verified");
		Thread.sleep(2000);
		// Searching for Breakfast using search bar
		driver.findElement(By.id(SearchBar_ID)).sendKeys(searchItem);
		driver.findElement(By.xpath(searchBotton)).click();
		System.out.println("Serach results shown and Header appears saying: "
				+ driver.findElement(By.xpath(SearchResult_XPATH)).getText()); // verify??

		// Displaying all the name of the Product in the first page, search results
		wait = new WebDriverWait(driver, 1000); // to wait for elements that are being loaded to the DOM after some
												// action is performed.

		List<WebElement> ProductsName = driver.findElements(By.xpath(ProductsName_XPATH));
		System.out.println("The list of Products for the " + searchItem + " items are: ");
		String productsName = "";
		for (int i = 0; i < (ProductsName.size()); i++) {
			productsName = ProductsName.get(i).getText();
			Thread.sleep(2000);
			System.out.println("Product #" + (i + 1) + "--" + productsName);
			// Checking if ‘Syrup’ is a part of at least 1 product’s name that is displayed
			// above
			if (productsName.contains("Syrup")) {
				System.out.println("Syrup is IN the Products Name List.");
			} else {
				continue;
			}
		}

		// Adding the 2ND Item to the cart (the 1st item from the Breakfast search
		// result)
		driver.findElement(By.xpath(Breakfast1stItem_XPATH)).click();
		driver.findElement(By.xpath(breakFastAddToCart_XPATH)).click();
		// Verify cart is updated to 2 Items
		System.out.println("Cart is Updated and now has: "
				+ driver.findElement(By.xpath(cartItemAmount_XPATH)).getText() + " in the cart.");

		// Click on cart
		driver.findElement(By.xpath(Cart_XPATH)).click();
		System.out.println("Cart page Loaded as: " + driver.findElement(By.xpath(cartHeader)).getText());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cartTotal)));

		// Cart total for the first two item
		double CartTotal$ = Double.valueOf(driver.findElement(By.xpath(cartTotal)).getText().replace("$", ""));
		System.out.println("Cart Total for the first two is: $" + CartTotal$);

		// Increase the quantity of the 1st product in the cart using Java Script
		// Executer
		WebElement increaseItem = driver.findElement(By.xpath(IncreaseItem));
		js.executeScript("arguments[0].click();", increaseItem);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cartTotal)));

		// check the total in the cart after increasing the item
		double CartTotal1$ = Double.valueOf(driver.findElement(By.xpath(cartTotal)).getText().replace("$", ""));
		System.out.println(
				"The first Item in the cart is increased by quantity of 1 and Cart Total is =$ " + CartTotal1$);

		// Remove the 2nd product from the cart using java script executer
		WebElement remove = driver.findElement(By.xpath(RemoveItem));
		js.executeScript("arguments[0].click();", remove);
		System.out.println("Item 2 in the cart is removed from the cart.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cartTotal)));

		// check the total in the cart after removing the 2nd Item
		double CartTotal2$ = Double.valueOf(driver.findElement(By.xpath(cartTotal)).getText().replace("$", ""));
		System.out.println("Cart Total is: $" + CartTotal2$);
		if (CartTotal2$ > 0 && CartTotal2$ < CartTotal1$) {
			System.out.println("Cart total was: $" + CartTotal1$ + " and now Cart Total is: $" + CartTotal2$
					+ " which is less: $" + (CartTotal1$ - CartTotal2$) + " by Item 2 amount.");
		} else {
			System.out.println("The cart total is not captured correctly after an Item is removed.");
		}
		System.out.println("Costco Shopping Cart Functionality Test has Passed");
		System.out.println("*******************Test Case Execution Ended ***************");
	}

	@Test
	public void MakePayment() {
		System.out.println("Payment Execution...");
		fail("PAYMENT WAS NOT SUCCESSFULLY SUBMITTED, PLEASE TRY ANOTHER CREDIT CARD");
		
	}

}

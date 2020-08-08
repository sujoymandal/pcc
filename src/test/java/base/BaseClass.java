package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	// public WebDriver driver;
	public static WebDriver driver = null;
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String browser;

	// This is master class
	public BaseClass() {

		if (System.getenv("Browser") != null && !System.getenv("Browser").isEmpty()) {
			browser = System.getenv("Browser");
		} else
			browser = readProperty("Browser");
		if (driver == null) {
			String url = readProperty("Url");

			if (browser.equalsIgnoreCase("chrome")) {
				String driverPath = System.getProperty("user.dir") + "\\resources\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("IE")){
				String driverPath = System.getProperty("user.dir") + "\\resources\\IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", driverPath);
				driver = new InternetExplorerDriver();
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);

			// Reporting
			// setReport();

		}
	}

	public String readProperty(String key) {
		String value = null;
		String propPath = System.getProperty("user.dir") + "\\resources\\properties.properties";
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(propPath));
			prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

	public String takeScreenshot(String name) {
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + name + ".png";
		System.out.println(dest);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}

	public void quitBrowser() {
		// report.flush();
		driver.quit();
		driver = null;
	}

	public void setReport() {
		String reportPath = System.getProperty("user.dir") + "\\target\\TestReport.html";
		String configFilePath = System.getProperty("user.dir") + "\\resources\\ReportsConfig.xml";

		report = new ExtentReports(reportPath, true, DisplayOrder.OLDEST_FIRST);
		report.loadConfig(new File(configFilePath));

	}

	public static boolean setTestCase(String testCaseName) {
		boolean testStatus = false;
		String sheetPath = System.getProperty("user.dir") + "\\resources\\TestManager.xlsx";
		try {
			FileInputStream fis = new FileInputStream(new File(sheetPath));

			XSSFWorkbook book = new XSSFWorkbook(fis);
			XSSFSheet sheet = book.getSheet("Run Master");
			int rowNum = sheet.getLastRowNum();
			// System.out.println(rowNum);
			for (int i = 1; i <= rowNum; i++) {
				if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
					if (sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase("Yes")) {
						testStatus = true;
						break;
					}
				}
				//// String s=sheet.getRow(i).getCell(0).getStringCellValue();
				// System.out.println(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return testStatus;

	}

	// Webdriver reusable methods
	// ======================================================================================//

	public boolean clickElement(By element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element))).click();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean enterText(By element, String textToBeEntered) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element))).sendKeys(textToBeEntered);
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean clearAndEnterText(By element, String textToBeEntered) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			el.clear();
			el.sendKeys(textToBeEntered);
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public String getText(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String text = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element))).getText();
		return text;
	}

	public boolean isElementDisplayed(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		boolean present = false;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			present = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			present = false;

		}
		return present;
	}

	public boolean moveToElement(By element) {
		try {
			Actions act = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement el = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
			act.moveToElement(el);
			act.build().perform();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public boolean switchToWindow(String title) {

		try {

			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {

				String s1 = driver.switchTo().window(handle).getTitle();
				if (s1.contains(title)) {
					driver.switchTo().window(handle);
					break;
				}

			}

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean selectRadioButton(By element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element))).click();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean acceptAlert() {

		try {

			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
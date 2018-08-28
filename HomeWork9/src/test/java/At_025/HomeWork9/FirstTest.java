package At_025.HomeWork9;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

	@Parameters("browser")

	@BeforeClass

	public void beforeTest(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("fireFox")) {

			System.setProperty("webdriver.gecko.driver", "D:\\New\\geckodriver.exe");

			driver = new FirefoxDriver();

		}
	}

	private WebDriver driver;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}
	
	

	@Test

	public void ResultIsNotEmpty() {
		
		driver.get("https://mail.google.com");

		WebElement name = driver.findElement(By.name("identifier"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		name.clear();
		name.sendKeys("nastya.babliak@gmail.com");

		WebElement go = driver
				.findElement(By.xpath("//span[contains(@class,'RveJvd snByac')]  [contains(text(),'Далее')]"));
		go.click();

		WebElement password = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.name("password")));

		password.clear();
		password.sendKeys("nastya.babliak123");

		WebElement go2 = driver
				.findElement(By.xpath("//span[contains(@class,'RveJvd snByac')]  [contains(text(),'Далее')]"));
		go2.click();

		WebElement search = driver.findElement(By.name("q"));
		search.clear();
		search.sendKeys("Hello User\n");
		
	}

	
	@Test

	public void ResultIsEmpty() {

		driver.get("https://mail.google.com");

		WebElement name = driver.findElement(By.name("identifier"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		name.clear();
		name.sendKeys("nastya.babliak@gmail.com");

		WebElement go = driver
				.findElement(By.xpath("//span[contains(@class,'RveJvd snByac')]  [contains(text(),'Далее')]"));
		go.click();

		WebElement password = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.name("password")));

		password.clear();
		password.sendKeys("nastya.babliak123");

		WebElement go2 = driver
				.findElement(By.xpath("//span[contains(@class,'RveJvd snByac')]  [contains(text(),'Далее')]"));
		go2.click();

		WebElement search = driver.findElement(By.name("q"));
		search.clear();
		search.sendKeys("holiday\n");
		search.sendKeys(Keys.ENTER);

		WebElement searchResult = driver.findElement(By.xpath("//*[contains(text(),'дополнительные параметры')]"));

		AssertJUnit.assertTrue(searchResult.getText().contains("дополнительные параметры"));

	}

	@AfterMethod
	public void tearDownTest() {
		try {
			driver.manage().deleteAllCookies();
		} finally {
			driver.quit();
		}
	}
}
package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DemoA 
{
	@Test
	public void testA() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.plus2net.com/jquery/msg-demo/dropdown3.php");
		WebElement country = driver.findElement(By.id("country_code"));
		WebElement state = driver.findElement(By.id("state_id"));
		WebElement city  = driver.findElement(By.id("city_id"));
		Select countryList = new Select(country);
		Select statelist = new Select(state);
		Select citylist = new Select(city);
		
		Thread.sleep(2000);
		countryList.selectByVisibleText("INDIA");
		Thread.sleep(2000);
		statelist.selectByVisibleText("7:Karnataka");
		Thread.sleep(2000);
		citylist.selectByVisibleText("Bangalore");
		Thread.sleep(2000);
		driver.close();
	}

}

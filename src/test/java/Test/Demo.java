package Test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo 
{
	@Test(enabled = false)
	public void testA() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.actitime.com");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("./demo.png"));

		WebElement img = driver.findElement(By.xpath("//div[@class='atLogoImg']"));

		int x = img.getLocation().getX();
		int y = img.getLocation().getY();
		int h = img.getSize().getHeight();
		int w = img.getSize().getWidth();

		//crop the img
		BufferedImage orgimg = ImageIO.read(new File("./demo.png"));
		BufferedImage subimg = orgimg.getSubimage(x, y, w, h);

		ImageIO.write(subimg, "png", new File("./d.png"));


		//read act

		BufferedImage aimg = ImageIO.read(new File("./demo.png"));
		BufferedImage eimg = ImageIO.read(new File("./d.png"));

		DataBuffer aimgpix = aimg.getData().getDataBuffer();
		DataBuffer eimgpix = eimg.getData().getDataBuffer();

		int apixcount = aimgpix.getSize();
		int epixcount = eimgpix.getSize();

		Reporter.log("Actual img pix count = "+apixcount,true);
		Reporter.log("Excepted img pix count = "+epixcount,true);
		int matchcount =0;
		int count = 0;
		if(apixcount>epixcount)
		{
			count = epixcount;
		}
		else
		{
			count = apixcount;
		}

		for(int i =0; i<count; i++)
		{
			if(aimgpix.getElem(i) == eimgpix.getElem(i)) 
			{
				matchcount++;
			}
		}
		Reporter.log("Matching count = "+ matchcount,true);
		int percent = (matchcount*100)/epixcount;
		Reporter.log("percent = "+percent,true);
		Thread.sleep(2000);
		driver.close();
	}

}

package scripts;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import generics.BaseClass;

interface function {
	public void test(String s) throws IOException, InterruptedException;
}

public class CourseData extends BaseClass {
	int sl = 1;
	int pageNumber = 1;

	@Test (priority=3)
	public void getData() throws IOException, InterruptedException
	{
		createResultFile();
		initializeWriteExcelSheets("./report/index.xlsx");
		driver.get("https://catalog.apps.asu.edu/catalog/classes/classlist?campusOrOnlineSelection=A&honors=F&promod=F&searchType=open&term=2234");

		pause(25);
		//feedback
		driver.findElement(By.xpath("//button[.='Ok, I agree']")).click();

		function loadPage=(String s)->
		{
			boolean pageLoaded = false;
			while(pageLoaded==false)
			{
				try {
					driver.findElement(By.xpath("//button[(@class='page-link') and (.='"+pageNumber+"')]")).click();
					pause(1);
					pageLoaded = true;
				}
				catch(Exception e)
				{
					driver.findElement(By.xpath("//button[.='Next']")).click();
				}
			}
			pause(5);
		};

		function loadScreen=(String s)->
		{
			boolean page = true;
			while(page)
			{
				try {
					page = driver.findElement(By.xpath("//ul[@class='pagination']")).isDisplayed();
					if(page)
						break;
				}
				catch(Exception e) {}

				scrollDown(5000);
				pause(4);
				scrollUp(1000);
				pause(1);
			}
			scrollDown(15000);
		};



		function newTab =(String s)->
		{
			openNewTab();
			pause(1);
			pwh = driver.getWindowHandle();
			Set<String> owh = driver.getWindowHandles();
			for(String wh : owh)
			{
				if(!wh.equals(pwh))
				{
					driver.close();
					driver.switchTo().window(wh);
				}
			}
			driver.get("https://catalog.apps.asu.edu/catalog/classes/classlist?campusOrOnlineSelection=A&honors=F&promod=F&searchType=open&term=2234");
			pause(25);
			//feedback
			try {
				driver.findElement(By.xpath("//button[.='Ok, I agree']")).click();
			}
			catch(Exception e) {}
			loadScreen.test("");
			loadPage.test("");
		};



		function getCourseData=(String s)->
		{
			pause(9);
			List<WebElement> courses = driver.findElements(By.xpath("((//div[@class='class-accordion even']/div[1]) | (//div[@class='class-accordion odd']/div[1]))"));
			int size = courses.size();
			System.out.println("Total courses: "+size);

			String id;
			String title;
			String desc;

			//opening the title
			int err =1;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for(int i=1; i<=size; i++)
			{
				if(err>3)
				{
					err = 1;
					newTab.test("");
				}
				try {
					System.out.println(i);
					jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(((//div[@class='class-accordion even']/div[1]) | (//div[@class='class-accordion odd']/div[1])))["+i+"]")));
					Thread.sleep(500);

					try {
						wait8.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//h5[.='Course Description']/../p[1])["+i+"]")));
						id = driver.findElement(By.xpath("(((//div[@class='class-accordion even']/div[1]) | (//div[@class='class-accordion odd']/div[1])))["+i+"]")).getText();
						title = driver.findElement(By.xpath("(((//div[@class='class-accordion even']/div[2]) | (//div[@class='class-accordion odd']/div[2])))["+i+"]")).getText();
						desc = driver.findElement(By.xpath("(//h5[.='Course Description']/../p[1])["+i+"]")).getText();
						System.out.println(sl+". "+id+" ; "+title +" ; "+desc);
						setExcelData("Sheet1", sl, 0, id);
						setExcelData("Sheet1", sl, 1, title);
						setExcelData("Sheet1", sl, 2, desc);
						sl++;
					}
					catch(Exception e1) {

					}

				}
				catch(Exception e) {
					Thread.sleep(1000);
					err++;
				}
			}

		};








		//from 1st page
		loadScreen.test("");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		for(int pages=1; pages<64; pages++)
		{
			System.out.println("Page number #"+pages);
			pageNumber = pages; 
			loadPage.test("");
			getCourseData.test("");
			driver.findElement(By.xpath("//button[.='Next']")).click();
		}
	}

	@Test(priority = 4 )
	public void finish() throws InterruptedException, IOException
	{
		saveReport();
	}

}

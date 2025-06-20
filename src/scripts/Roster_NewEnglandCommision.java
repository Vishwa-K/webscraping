package scripts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import generics.BaseClass;
public class Roster_NewEnglandCommision extends BaseClass{


	@Test(priority=3, groups={"Sanity","Daily","Regression","Smoke"})
	public void test() throws Exception, IOException, GeneralSecurityException
	{
		ArrayList<String> data1 = new ArrayList<String>();
		ArrayList<String> data2 = new ArrayList<String>();


		for(int z=1; z<36; z++)
		{
			try {
				List<WebElement> courseTitles = driver.findElements(By.xpath("//div[@class='e-con-inner']/div/div[2]//h2"));
				List<WebElement> courseLoc  = driver.findElements(By.xpath("//div[@class='e-con-inner']/div/div[3]//h2"));
				String title, place;

				String firstCourse = driver.findElement(By.xpath("(//div[@class='e-con-inner']/div/div[2]//h2)[1]")).getText().strip();
				
				for(int i=1; i<=courseTitles.size(); i++)
				{
					title = driver.findElement(By.xpath("(//div[@class='e-con-inner']/div/div[2]//h2)["+i+"]")).getText().strip();
					place = driver.findElement(By.xpath("(//div[@class='e-con-inner']/div/div[3]//h2)["+i+"]")).getText().strip();

					data1.add(title);
					data2.add(place);
				}

				jsClick("//div[.='Next']");
				wait15.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[.='"+firstCourse+"']")));
				Thread.sleep(1000);
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("err");
			}
		}

		int sl = 1;
		System.out.println("Size: "+data1.size());
		System.out.println("\n\n\n");
		for(String dt : data1)
		{
			System.out.println(dt);
		}
		System.out.println("\n\n\n");

	}
}

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

public class MembershipDirectory_MSA_Cess extends BaseClass {

	@Test(priority=3, groups={"Sanity","Daily","Regression","Smoke"})
	public void test() throws Exception, IOException, GeneralSecurityException
	{
		ArrayList<String> data1 = new ArrayList<String>();
		ArrayList<String> data2 = new ArrayList<String>();


		for(int z=2; z<32; z++)
		{
			try {
				List<WebElement> names = driver.findElements(By.xpath("//tbody/tr/td[1]"));
				List<WebElement> statuses  = driver.findElements(By.xpath("//tbody/tr/td[2]"));

				String title, stat;

				for(int i=1; i<=names.size(); i++)
				{
					title = driver.findElement(By.xpath("(//tbody/tr/td[1])["+i+"]")).getText().strip();
					stat = driver.findElement(By.xpath("(//tbody/tr/td[2])["+i+"]")).getText().strip();

					data1.add(title);
					data2.add(stat);
				}

				if(z<31)
				{
					wait8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='page'  and .='"+z+"'])")));
					jsClick("(//a[@class='page'  and .='"+z+"'])");
					System.out.println("Z is: "+z);
					Thread.sleep(2000);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("err");
			}
		}

		int sl = 1;
		System.out.println("Size1: "+data1.size());
		System.out.println("Size2: "+data2.size());

		System.out.println("\n\n\n");
		for(String dt : data1)
		{
			System.out.println(dt);
		}
		System.out.println("\n\n\n");
		for(String dt : data2)
		{
			System.out.println(dt);
		}
	}
}

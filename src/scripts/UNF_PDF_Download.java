package scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import generics.BaseClass;

public class UNF_PDF_Download extends BaseClass {
	@Test(priority = 3, groups={"Sanity","Test","Regression"})
	public void test() throws Exception
	{
		int sl = 1;
		List<WebElement> pdfs = driver.findElements(By.xpath("//p[@class='pdf']/a"));
		for(WebElement pdf :pdfs)
		{
			String href = pdf.getAttribute("href");
			download(href, "./log", "file"+sl+".pdf");
			sl++;
			System.out.println(sl+" downloaded!");
		}
	}
}

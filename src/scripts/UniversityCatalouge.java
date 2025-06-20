package scripts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import generics.BaseClass;

interface catalougeFunction{
	public void test(int i) throws Exception, InterruptedException;
}

public class UniversityCatalouge extends BaseClass {

	String data;
	String body;

	String id;
	String title;
	String desc;
	String out;
	String[] sent;
	int row=1;
	List<WebElement> courses;


	@Test(priority =3)
	public void test1() throws Exception
	{
		catalougeFunction setPage=(int page)->
		{
			jsClick("//a[@aria-label='Page "+page+"']");
		};
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for(int page= 52; page<65; page++)
		{
			if(page>1)
				setPage.test(page);
			courses = driver.findElements(By.xpath("//table[@class='table_default']/tbody/tr/td[2]/a"));
			//opening the courses
			for(int i=1 ; i<=courses.size(); i++)
			{
				jsClick("(//table[@class='table_default']/tbody/tr/td[2]/a)["+i+"]");
			}
			pause(2);
			for(int i=1 ; i<=courses.size(); i++)
			{
				//course id and title

				data = driver.findElement(By.xpath("(//table[@class='table_default']/tbody/tr/td[2]/a/..//h3)["+i+"]")).getText();
				sent = data.split("-",2);
				id = sent[0].trim();
				title = sent[1].trim();

				//course desc
				body = driver.findElement(By.xpath("(//table[@class='table_default']/tbody/tr/td[2]/a/../table//td/div[2])["+i+"]")).getText();
				body = body.replaceAll(data, "");
				sent = body.split("Student Learning Outcomes", 2);
				desc = sent[0].trim();
				out = sent[1].trim();

				setExcelData("Sheet1", row, 0, String.valueOf(row));
				setExcelData("Sheet1", row, 1, id);
				setExcelData("Sheet1", row, 2, title);
				setExcelData("Sheet1", row, 3, desc);
				setExcelData("Sheet1", row, 4, out);
				System.out.println("Row num: "+row);
				row++;
				
			}
		}
	}


	@Test(priority = 10)
	public void finish() throws Exception
	{
		saveReport();
	}



}

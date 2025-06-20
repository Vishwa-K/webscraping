package scripts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import generics.BaseClass;

public class JU_Catalog extends BaseClass {

	@Test(priority=3, groups={"Sanity","Daily","Regression","Smoke"})
	public void test() throws Exception
	{
		initializeWriteExcelSheets("./report/index.xlsx");
		String uri = "https://secureweb.ju.edu/Student/Courses/SearchAsync";
		List<WebElement> sections = driver.findElements(By.xpath("//section[@class='esg-list-group']//a"));		
		getTPRequests();
		String subjectCode, number, title, credits, description;
		ObjectMapper objectMapper = new ObjectMapper();
		int sl =1;
		//		for(int i = 1 ; i<=3; i++)
		for(int i = 1 ; i<=sections.size(); i++)
		{
			wait8.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//section[@class='esg-list-group']//a)["+i+"]")));
			String sctn = driver.findElement(By.xpath("(//section[@class='esg-list-group']//a)["+i+"]")).getText().strip();
			setExcelData("Sheet1", sl, 6, sctn);
			jsClick("(//section[@class='esg-list-group']//a)["+i+"]");
			waitUntil(uri, 10);

			String response = getTRequestResponses(uri);
			try {
				JsonNode rootNode = objectMapper.readTree(response);
				JsonNode coursesNode = rootNode.path("Courses");

				for (JsonNode course : coursesNode) {
					subjectCode = course.path("SubjectCode").asText();
					number = course.path("Number").asText();
					title = course.path("Title").asText();
					credits = course.path("MinimumCredits").asText();
					description = course.path("Description").asText();

					System.out.println(sl+". "+subjectCode+" "+number+" : "+title+" : "+credits);
					System.out.println("Description-> " + description);
					System.out.println("------------------------------");

					setExcelData("Sheet1", sl, 0, String.valueOf(sl));
					setExcelData("Sheet1", sl, 1, subjectCode);
					setExcelData("Sheet1", sl, 2, number);
					setExcelData("Sheet1", sl, 3, title);
					setExcelData("Sheet1", sl, 4, description);
					setExcelData("Sheet1", sl, 5, credits);

					sl++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			driver.navigate().back();
			APIrequests.clear();

		}

		disconnect();
		saveReport();
	}

	public void waitUntil(String uri, int t) throws Exception
	{
		int count = 1;
		while(true)
		{
			if(count == t)
				break;
			if(APIrequests.contains(uri))
				break;
			else
				Thread.sleep(1000);
			count++;
		}
	}
}

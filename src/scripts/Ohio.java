package scripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import generics.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Response;
import org.openqa.selenium.devtools.v116.network.model.ResponseReceived;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ohio extends BaseClass{
	private String tableName;
	String responseBody;
	
	


	@Test(priority = 3, groups={"Sanity","Test","Regression"})
	public void test() throws Exception
	{

		System.out.println("Staart");
		int sl =1;
		driver.navigate().refresh();

		for(int page= 1;  page < 667; page++)
		{
			getTPRequests();
//			System.out.println("Open new page");
			wait50.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//thead//tr")));
			Thread.sleep(8000);
			if(page==1)
				Thread.sleep(25000);
			
			String jsonString = getTRequestResponses("https://ais.kube.ohio.edu/api/course-offerings/search/query?selectedTab=ATHN&page="+page+"&pageSize=50");
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				// Parse the JSON string
				JsonNode jsonNode = objectMapper.readTree(jsonString);

				// Extract subject, catalogNumber, and title from each result
				JsonNode resultsNode = jsonNode.get("results");

				for (JsonNode resultNode : resultsNode) {
					String subject = resultNode.get("subject").asText();
					String catalogNumber = resultNode.get("catalogNumber").asText();
					String title = resultNode.get("title").asText();

					// Do something with the extracted values
					System.out.println(sl+". "+subject+" "+catalogNumber+" : "+title);
					sl++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//click on next
			jsClick("(//span[.=' Next '])[1]");
		}
		devTools.disconnectSession();
	}



}

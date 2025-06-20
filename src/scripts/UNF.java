package scripts;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import generics.BaseClass;

public class UNF extends BaseClass{
	
	@Test(priority = 3, groups={"Sanity","Test","Regression"})
	public void test() throws Exception
	{
		
		String jsonString = getDataFromFile("./data/json.txt");
		initializeWriteExcelSheets("./report/index.xlsx");
		
		System.out.println("hi");
		
		//subjectCode
		//number
		//title
		//description
		//creditHours
		int sl =1;
		 ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            JsonNode jsonNode = objectMapper.readTree(jsonString);

	            for (JsonNode courseNode : jsonNode) {
	                String subjectCode = courseNode.get("subjectCode").asText();
	                String number = courseNode.get("number").asText();
	                String title = courseNode.get("title").asText();
	                String description = courseNode.get("description").asText().strip();
	                String creditHours = courseNode.get("creditHours").asText();

	                description = description.replaceAll("<BR><i>", "").replaceAll("&#160;</i>", "").replaceAll("<i>", "");
	                // Print or use the fetched information as needed
	                System.out.println(sl+". "+subjectCode+" "+number+" : "+title+" : "+creditHours);
	                System.out.println(sl+"."+ description);
	                System.out.println();
	                setExcelData("Sheet1", sl, 0, String.valueOf(sl));
	                setExcelData("Sheet1", sl, 1, subjectCode);
	                setExcelData("Sheet1", sl, 2, number);
	                setExcelData("Sheet1", sl, 3, title);
	                setExcelData("Sheet1", sl, 4, description);
	                setExcelData("Sheet1", sl, 5, creditHours);
	                sl++;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        saveReport();
	}

}

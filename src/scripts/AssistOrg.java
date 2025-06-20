package scripts;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import generics.BaseClass;

public class AssistOrg extends BaseClass{
	Workbook workbook = new XSSFWorkbook();
	Sheet sheet;
	CellStyle wrapTextStyle  = workbook.createCellStyle();
	Row row; Cell cell;
	ArrayList<String> URLs = new ArrayList<String>();
	ArrayList<String> countryNames = new ArrayList<String>();




	//	@Test(priority=4, groups={"Sanity","Daily","Regression","Smoke"})
	public void scrapeLoops() throws Exception, Exception
	{
		//Academic year
		driver.findElement(By.xpath("//input[@id='transferAcademicYear']")).click();
		//		jsClick("//input[@id='transferAcademicYear']");
		pause(2);

		List<WebElement> academicyears = driver.findElements(By.xpath("//div[@role='listbox']/div//span"));

		for(int i=1; i<= academicyears.size(); i++)
		{
			jsClick("(//div[@role='listbox']/div//span)["+i+"]");
			pause(2);

			//Community college
			driver.findElement(By.xpath("//div[.='Select a Community College']//input")).click();
			//			jsClick("//div[.='Select a Community College']//input");
			pause(2);

			List<WebElement> communityColleges = driver.findElements(By.xpath("//div[@role='listbox']/div//span"));

			for(int cc = 1; cc<= communityColleges.size(); cc++)
			{
				jsClick("(//div[@role='listbox']/div//span)["+i+"]");
				pause(3);
				//CSU Transferable course radio button
				jsClick("//input[@id='CSUTC']/..//label");
				pause(3);
				//View transferability list
				jsClick("//button[.=' View Transferability Lists']");
				pause(5);

				//				//depts
				//				List<WebElement> depts = driver.findElements(By.xpath(""));
				//				for(int dpts = 1; dpts<= depts.size(); dpts++)
				//				{
				//					jsClick("");
				//					pause(2);
				//					//fetch table
				//					{
				//
				//					}
				//					pause(2);
				//
				//				}

				//click on all dept
				jsClick("//div[.=' All Departments ']");


				break;
			}
			break;
		}
		print("done...");

	}


	@Test(priority=4, groups={"Sanity","Daily","Regression","Smoke"})
	public void decodeShadow() throws Exception, Exception
	{
		WebElement shadowHost = driver.findElement(By.cssSelector("awc-transferable-courses"));
		SearchContext shadowRoot = shadowHost.getShadowRoot();

		// Find tables inside the shadow root
		List<WebElement> tables = shadowRoot.findElements(By.cssSelector("div.content > div"));

		//		for (int table = 1; table <= tables.size(); table++) // Adjust the table iteration as needed

		for (int table = 1; table < 2; table++) // Adjust the table iteration as needed
		{
			// Find the header for the current table
			WebElement header = shadowRoot.findElement(By.cssSelector("div.content > div:nth-child(" + table + ") > h4"));
			System.out.println(header.getText());

			// Find all rows within the current table
			List<WebElement> rows = shadowRoot.findElements(By.cssSelector("div.content > div:nth-child(" + table + ") > table > tbody > tr"));

			for (int row = 1; row <= rows.size(); row++) 
			{
				// Find all columns (th or td) within the current row
				List<WebElement> cols = shadowRoot.findElements(By.cssSelector("div.content > div:nth-child(" + table + ") > table > tbody > tr:nth-child(" + row + ") > th, " +
						"div.content > div:nth-child(" + table + ") > table > tbody > tr:nth-child(" + row + ") > td"));

				for (int col = 1; col <= cols.size(); col++) 
				{
					// Get the data from the specific cell
					WebElement data = shadowRoot.findElement(By.cssSelector("div.content > div:nth-child(" + table + ") > table > tbody > tr:nth-child(" + row + ") > th:nth-child(" + col + "), " +
							"div.content > div:nth-child(" + table + ") > table > tbody > tr:nth-child(" + row + ") > td:nth-child(" + col + ")"));
					System.out.println(data.getText());
					System.out.println();
				}
			}
		}


	}




	//	@Test(priority=4, groups={"Sanity","Daily","Regression","Smoke"})
	public void scrapeData() throws Exception, Exception
	{
		for(int i = 0; i<URLs.size(); i++)
		{
			int rr = 0;
			driver.get(URLs.get(i));
			sheet = workbook.createSheet(countryNames.get(i));

			System.out.println(i+1+". Country: "+countryNames.get(i));
			try {
				String h1 = driver.findElement(By.xpath("(//div[contains(@id, 'PageBody')]//h1/..)//h1")).getText().strip();
				row = sheet.createRow(rr);
				cell = row.createCell(0);
				cell.setCellValue(h1);
				wrapTextStyle.setWrapText(true);
				cell.setCellStyle(wrapTextStyle);
				rr++;
				System.out.println("h1: "+h1);
			}
			catch(Exception e) {}

			List<WebElement> tables = driver.findElements(By.xpath("(//div[contains(@id, 'PageBody')]//h1/..)//table"));
			for(int j=1; j<=tables.size(); j++)
			{
				try {
					List<WebElement> headers = driver.findElements(By.xpath("(                (((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]/preceding-sibling::h4[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)["+(j-1)+"]/preceding-sibling::h4)])  |       ((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]/preceding-sibling::li[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)["+(j-1)+"]/preceding-sibling::li)]    |   ((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]/preceding::h3[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)["+(j-1)+"]/preceding::h3)]     |               ((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]/preceding-sibling::ul[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)["+(j-1)+"]/preceding-sibling::ul)]  )"));
					for(WebElement hdr : headers)
					{
						String dt = hdr.getText();
						row = sheet.createRow(rr);
						cell = row.createCell(0);
						cell.setCellValue(dt);
						wrapTextStyle.setWrapText(true);
						cell.setCellStyle(wrapTextStyle);
						rr++;
						System.out.println(dt);
					}
				}
				catch(Exception e) {}

				List<WebElement> rows = driver.findElements(By.xpath("((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]//tr"));
				for(int z = 1; z<=rows.size();z++)
				{
					int cc = 0;
					row = sheet.createRow(rr);
					List<WebElement> cols = driver.findElements(By.xpath("(        (((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]//tr)["+z+"]/th     |   (((//div[contains(@id, 'PageBody')]//h1/..)//table)["+j+"]//tr)["+z+"]/td      )"));
					for(WebElement col : cols)
					{
						String data = col.getText().strip();
						cell = row.createCell(cc);
						cell.setCellValue(data);
						wrapTextStyle.setWrapText(true);
						cell.setCellStyle(wrapTextStyle);
						cc++;
						System.out.println(data);
					}
					rr++;

				}
			}

			sheet.setDefaultColumnWidth(60);
		}

	}

	//	@Test(priority=5, groups={"Sanity","Daily","Regression","Smoke"})
	public void saveGrades() throws Exception
	{
		try (FileOutputStream fileOut = new FileOutputStream("./report/AssistOrg.xlsx")) {
			workbook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing the workbook
		try {
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n\n\nDone!!!");
	}

}



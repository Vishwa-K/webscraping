package scripts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import generics.BaseClass;

public class Aacrao extends BaseClass{
	Workbook workbook = new XSSFWorkbook();
	Sheet sheet;
	CellStyle wrapTextStyle  = workbook.createCellStyle();
	Row row; Cell cell;
	ArrayList<String> URLs = new ArrayList<String>();
	ArrayList<String> countryNames = new ArrayList<String>();



	@Test(priority=3, groups={"Sanity","Daily","Regression","Smoke"})
	public void getURLs() throws Exception, IOException, GeneralSecurityException
	{
//		wait25.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//ul[@class='submenu']/li/a"), 200));
//		List<WebElement> countries = driver.findElements(By.xpath("//ul[@class='submenu']/li/a"));
//		for(int i=1; i<=countries.size(); i++)
//		{
//			String text = driver.findElement(By.xpath("(//ul[@class='submenu']/li/a)["+i+"]")).getAttribute("textContent").strip().replaceAll(":", "");
//			countryNames.add(text);
//			text = text.toLowerCase().replaceAll(" ","-").replaceAll("\\.", "").replaceAll(",","");
//			URLs.add("https://www.aacrao.org/edge/country/grading/"+text);
//		}

		URLs.add("https://www.aacrao.org/edge/country/grading/congo-democratic-republic-of");
		URLs.add("https://www.aacrao.org/edge/country/grading/congo-republic-of");
		URLs.add("https://www.aacrao.org/edge/country/grading/republic-of-cote-divoire");
		URLs.add("https://www.aacrao.org/edge/country/grading/gambia-the");
		
		URLs.add("https://www.aacrao.org/edge/country/grading/Korea-Republic-of");
		URLs.add("https://www.aacrao.org/edge/country/grading/hong-kong-sar-china");
		URLs.add("https://www.aacrao.org/edge/country/grading/macau-sar-china");
		URLs.add("https://www.aacrao.org/edge/country/grading/yemen-republic-of");
		URLs.add("https://www.aacrao.org/edge/country/grading/turkey");
		URLs.add("https://www.aacrao.org/edge/country/grading/netherlands-the");
		URLs.add("https://www.aacrao.org/edge/country/grading/usa");

		
		countryNames.add("congo democratic republic of");
		countryNames.add("congo republic of");
		countryNames.add("republic of cote divoire");
		countryNames.add("gambia the");
		countryNames.add("Korea Republic of");
		countryNames.add("hong kong sar china");
		countryNames.add("macau sar china");
		countryNames.add("yemen republic of");
		countryNames.add("turkey");
		countryNames.add("netherlands the");
		countryNames.add("usa");

		int sl =1 ;
		for(String ul : URLs)
		{
			print(sl+". "+ul);
			sl++;
		}

	}


	@Test(priority=4, groups={"Sanity","Daily","Regression","Smoke"})
	public void scrapeData() throws Exception, IOException, GeneralSecurityException
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

	@Test(priority=5, groups={"Sanity","Daily","Regression","Smoke"})
	public void saveGrades() throws Exception, IOException, GeneralSecurityException
	{
		try (FileOutputStream fileOut = new FileOutputStream("./report/Aacrao.xlsx")) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n\nDone!!!");
	}

}




/*
 * 
 * URLs.add("https://www.aacrao.org/edge/country/grading/congo-democratic-republic-of");
URLs.add("https://www.aacrao.org/edge/country/grading/congo-republic-of");
URLs.add("https://www.aacrao.org/edge/country/grading/republic-of-cote-divoire");
URLs.add("https://www.aacrao.org/edge/country/grading/gambia-the");
URLs.add("https://www.aacrao.org/edge/country/grading/Korea-Republic-of");
URLs.add("https://www.aacrao.org/edge/country/grading/hong-kong-sar-china");
URLs.add("https://www.aacrao.org/edge/country/grading/macau-sar-china");
URLs.add("https://www.aacrao.org/edge/country/grading/yemen-republic-of");
URLs.add("https://www.aacrao.org/edge/country/grading/turkey");
URLs.add("https://www.aacrao.org/edge/country/grading/netherlands-the");
URLs.add("https://www.aacrao.org/edge/country/grading/usa");

 */






//       			(                (((//div[contains(@id, 'PageBody')]//h1/..)//table)[1]/preceding-sibling::h4[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)[0]/preceding-sibling::h4)])  |       ((//div[contains(@id, 'PageBody')]//h1/..)//table)[1]/preceding-sibling::li[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)[0]/preceding-sibling::li)]    |   ((//div[contains(@id, 'PageBody')]//h1/..)//table)[1]/preceding::h3[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)[0]/preceding::h3)]     |               ((//div[contains(@id, 'PageBody')]//h1/..)//table)[1]/preceding-sibling::ul[not(.=((//div[contains(@id, 'PageBody')]//h1/..)//table)[0]/preceding-sibling::ul)]  )				

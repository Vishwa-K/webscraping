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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.amazonaws.services.dynamodbv2.document.Expected;

import generics.BaseClass;

public class ScholaroDatabase extends BaseClass {

	String countryClearBtn = "(//span[@title='clear'])[1]";
	String gradingClearBtn = "(//span[@title='clear'])[2]";

	String countryTextBox = "//input[@name='CountryID_input']";
	String gradingTextBox = "//input[@name='GradingScaleID_input']";

	String allBtn = "//label[.='All']/../input";
	String viewAll = "//a[.='View All']";
	String countryDropdown = "(//span[@title='clear'])[1]/../button";
	String gradingDropdown = "(//span[@title='clear'])[2]/../button";
	ArrayList<String> countryNames = new ArrayList<String>();

	//	@Test(priority=3, groups={"Sanity","Daily","Regression","Smoke"})
	public void initExcel() throws Exception, IOException, GeneralSecurityException
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		CellStyle wrapTextStyle = workbook.createCellStyle();
		wrapTextStyle.setWrapText(true);

		Row row = sheet.createRow(0);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
		cell.setCellValue("Hikdfhbhskfbsdjfbdsjbsdjhbsd jfsdbfjbsdfsdjhsdbfjsdbjhdsbf");
		cell.setCellStyle(wrapTextStyle);

		try (FileOutputStream fileOut = new FileOutputStream("./data/sample.xlsx")) {
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


	}


	@Test(priority=4, groups={"Sanity","Daily","Regression","Smoke"})
	public void countryNames() throws Exception, IOException, GeneralSecurityException
	{
		jsClick(countryDropdown);
		pause(1);
		List<WebElement> countries = driver.findElements(By.xpath("//ul[@id='CountryID_listbox']/li/span"));
		for(WebElement cnt : countries)
		{
			countryNames.add(cnt.getText().strip());

		}
		System.out.println("Total country size: "+countryNames.size());


	}

	@Test(priority=5, groups={"Sanity","Daily","Regression","Smoke"})
	public void gradingData() throws Exception, IOException, GeneralSecurityException
	{
		//for(int c=0; c<countryNames.size(); c++)
		for(int c=150; c<213; c++) //total 213
		{
			String cName = countryNames.get(c);
			print(c+". Country is: "+cName);

			Workbook workbook = new XSSFWorkbook();

			jsClick(countryDropdown);
			//enter button
			driver.findElement(By.xpath(countryTextBox)).sendKeys(cName);
			pause(2);
			driver.findElement(By.xpath(countryTextBox)).sendKeys(Keys.ENTER);
			pause(2);
			jsClick(allBtn);
			pause(3);
			jsClick(gradingDropdown);
			pause(2);

			List<WebElement> gradingOptions = driver.findElements(By.xpath("//ul[@id='GradingScaleID_listbox']/li"));
			for(int j = 1; j<=gradingOptions.size(); j++)
			{
				String gradingScaleName = driver.findElement(By.xpath("(//ul[@id='GradingScaleID_listbox']/li)["+j+"]")).getText().strip();
				System.out.println("gsn: "+gradingScaleName);

				if(gradingScaleName.length()>30)
					gradingScaleName = gradingScaleName.substring(0, 30);
				Sheet sheet = null;

				int attt = 1;
				while(true)
				{
					try {
						sheet = workbook.createSheet(gradingScaleName);
						break;
					}
					catch(Exception e) {
						try {
							gradingScaleName = gradingScaleName.substring(0, gradingScaleName.length()-attt).concat(String.valueOf(getRandomNumber(0, 9, 1).get(0)));
							sheet = workbook.createSheet(gradingScaleName);
							break;
						}
						catch(Exception e2) {
							if(attt==98) {
								System.out.println("Sheet is null: tried for 98 times");
								e2.printStackTrace();
							}
						}
					}
					attt++;
					if(attt>100)
						break;
				}

				sheet.setDefaultColumnWidth(40);
				sheet.setDefaultRowHeightInPoints(2);

				CellStyle wrapTextStyle = workbook.createCellStyle();
				wrapTextStyle.setWrapText(true);

				Row row; Cell cell;

				jsClick("(//ul[@id='GradingScaleID_listbox']/li)["+j+"]");
				pause(1);
				//fetch the data 
				try {
					wait5.until(ExpectedConditions.textToBePresentInElementLocated((By.xpath("//div[@id='grading-scale-div']//h3")), gradingScaleName+" Grading Scale:"));
				} catch(Exception e) {}
				String scaleName = driver.findElement(By.xpath("//div[@id='grading-scale-div']//h3")).getText();
				System.out.println("Scalename: "+scaleName);

				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellValue(scaleName);
				cell.setCellStyle(wrapTextStyle);
				sheet.getRow(0).setHeightInPoints(30);

				String header, data;

				List<WebElement> rows = driver.findElements(By.xpath("(//div[@id='detailsDiv'])//tr"));
				for(int r = 1; r<=rows.size(); r++)
				{
					row = sheet.createRow(r);
					List<WebElement> totalCols = driver.findElements(By.xpath("(//div[@id='grading-scale-div']/div[@id='detailsDiv']//table)[1]//col"));
					for(int col=1; col<=totalCols.size(); col++)
					{
						data = driver.findElement(By.xpath("(((((//div[@id='detailsDiv'])//tr)["+r+"])//th   |   (((//div[@id='detailsDiv'])//tr)["+r+"])//td))["+col+"]")).getText();
						System.out.println(data);
						cell = row.createCell(col-1);
						cell.setCellValue(data);
						cell.setCellStyle(wrapTextStyle);
					}
				}

				String info = driver.findElement(By.xpath("//div[@id='grading-scale-div']//i/../..")).getText();
				System.out.println("Info: "+info);

				row = sheet.createRow(rows.size()+2);
				cell = row.createCell(0);
				cell.setCellValue(info);


				sheet.getRow(rows.size()+2).setHeightInPoints(40);
				sheet.setDefaultColumnWidth(40);
				sheet.setDefaultRowHeightInPoints(2);

				jsClick(gradingDropdown);
				pause(2);
			}
			//to next country
			jsClick(countryClearBtn);
			pause(2);

			//closing the workbook
			try (FileOutputStream fileOut = new FileOutputStream("./data/"+cName+".xlsx")) {
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



		}
	}

}

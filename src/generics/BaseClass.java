package generics;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.http.util.Asserts;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Response;
import org.openqa.selenium.devtools.v116.network.model.ResponseReceived;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass extends Variables{
	String lh = df.getData(0, 1);
	/**
	 * Generic method to launch headless chrome browser
	 * @param browser (1 = Windows Chrome, 2 = MS Edge, 3 = Mozilla Firefox, 4 = Safari)
	 * @param hdls 
	 * if hdls="headless" : headless browser
	 * if hdls="" : Non headless browser
	 * @return 
	 */
	public void openBrowser(int browser)
	{
		if(browser==1) {
			browserName  = "Chrome";
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();

			if(!lh.equals(""))
			{
				options.setExperimentalOption("debuggerAddress", "localhost:"+lh);
				System.out.println("Debugger address: "+lh);
			}
			driver = new ChromeDriver(options);

			//uncomment if it requires network APIs data fetching
//			devTools = ((HasDevTools) driver).getDevTools();
//			devTools.createSession();

			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait25 = new WebDriverWait(driver,  Duration.ofSeconds(25));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		else if(browser==2) {
			browserName  = "Microsoft EDGE";

			//			WebDriverManager.edgedriver().setup();
			System.out.println("MS Edge browser");
			EdgeOptions edgeOptions = new EdgeOptions();
			//			edgeOptions.setExperimentalOption("debuggerAddress", "localhost:65053");
			driver = new EdgeDriver(edgeOptions);


			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}

		else if(browser==3) {
			browserName  = "Mozilla Firefox";
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		else if(browser==4) {
			browserName  = "Safari";
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		driver.manage().window().maximize();
		Capabilities cap= ((ChromiumDriver) driver).getCapabilities();
		Map<String, Object> myCap=cap.asMap();
		//local host
		Object localHost = myCap.get("goog:chromeOptions");
		System.out.println(localHost);
	}

	public void openBrowser(int browser , String lh)
	{
		if(browser==1) {
			browserName  = "Chrome";
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "localhost:"+lh);
			driver = new ChromeDriver(options);

			//uncomment if it requires network APIs data fetching
//			devTools = ((HasDevTools) driver).getDevTools();
//			devTools.createSession();

			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		else if(browser==2) {
			browserName  = "Microsoft EDGE";
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}

		else if(browser==3) {
			browserName  = "Mozilla Firefox";
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		else if(browser==4) {
			browserName  = "Safari";
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			wait100 = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait150 = new WebDriverWait(driver,  Duration.ofSeconds(150));
			wait200 = new WebDriverWait(driver,  Duration.ofSeconds(200));
			wait5= new WebDriverWait(driver,  Duration.ofSeconds(5));
			wait8= new WebDriverWait(driver,  Duration.ofSeconds(8));
			wait12= new WebDriverWait(driver,  Duration.ofSeconds(12));;
			wait15= new WebDriverWait(driver,  Duration.ofSeconds(15));
			wait20 = new WebDriverWait(driver,  Duration.ofSeconds(20));
			wait50 = new WebDriverWait(driver,  Duration.ofSeconds(50));
			wait70 = new WebDriverWait(driver,  Duration.ofSeconds(70));
			a=new Actions(driver);
		}
		driver.manage().window().maximize();
		Capabilities cap= ((ChromiumDriver) driver).getCapabilities();
		Map<String, Object> myCap=cap.asMap();
		//local host
		Object localHost = myCap.get("goog:chromeOptions");
		print(localHost);
	}

	public void openWebsite(String url)
	{
		if(lh.equals(""))
			driver.get(url);
	}

	public void login() throws InterruptedException {
		scrollUp(5000);
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(((//div/a[.='Login']/..)[1]) | (//button//div[contains(.,'Log In')]/..) )")));
		Thread.sleep(1700);
		driver.findElement(By.xpath("(((//div/a[.='Login']/..)[1]) | (//button//div[contains(.,'Log In')]/..) )")).click();
		wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']")));
		// https://weblogin.asu.edu/cas/login
		driver.findElement(By.xpath("//input[@id='username']"))
		.sendKeys(asuRite + Keys.TAB + asuPassword + Keys.TAB + Keys.ENTER);
		Thread.sleep(5000);
		driver.switchTo().frame("duo_iframe");
		wait25.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Send Me a Push ']")));
		driver.findElement(By.xpath("//button[.='Send Me a Push ']")).click();
		wait150.until(ExpectedConditions.urlContains("edu/dashboard"));
		System.out.println("Logged in!");
		driver.switchTo().defaultContent();
	}


	public void closeBrowser()
	{
		driver.quit();
	}

	/**
	 * @author Vishwanath
	 * @param data
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void paste(String data) throws AWTException, InterruptedException
	{
		StringSelection s;
		s= new StringSelection(data);
		Clipboard cp =Toolkit.getDefaultToolkit().getSystemClipboard();
		cp.setContents(s, null);
		Thread.sleep(2000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void initializeReadExcelSheets(String inputFilePath) throws EncryptedDocumentException, IOException
	{

		fis = new FileInputStream(inputFilePath);
		wb = WorkbookFactory.create(fis);
		fos = new FileOutputStream(inputFilePath);
	}

	public static void initializeWriteExcelSheets(String inputFilePath) throws EncryptedDocumentException, IOException
	{
		fis1 = new FileInputStream(inputFilePath);
		wb1 = WorkbookFactory.create(fis1);
		fos1 = new FileOutputStream(inputFilePath);

	}


	/**
	 * @author Vishwanath
	 * Generic method to fetch data from Excel sheet
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static String getExcelData(String sheetname, int rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		String value=null;
		try {
			value= wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		}
		catch(Exception e)
		{
			value=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).toString();
		}
		return value;	
	}
	/**
	 * @author Vishwanath
	 * Generic method to write data to Excel sheet
	 */
	public static void setExcelData(String sheetname, int rownum, int cellnum, String value) throws EncryptedDocumentException, IOException
	{
		try {
			wb1.getSheet(sheetname).getRow(rownum).getCell(cellnum).setCellValue(value);
		}
		catch(Exception e){
			wb1.getSheet(sheetname).getRow(rownum).createCell(cellnum).setCellStyle(cellStyle);
			wb1.getSheet(sheetname).getRow(rownum).getCell(cellnum).setCellValue(value);
		}
	}
	/**
	 * @author Vishwanath
	 * Generic method to save the excel data
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void saveReport() throws IOException, InterruptedException
	{
		try {
			//copying data from data sheet
			wb.write(fos);
			wb.close();
		}
		catch(Exception e) {
		}
		try {
			// Writing results to index sheet
			wb1.write(fos1);
			wb1.close();
		}
		catch(Exception e) {
		}
	}

	/**
	 * @author Vishwanath
	 * @return
	 * @throws InterruptedException
	 */
	public boolean check404Error() throws InterruptedException
	{
		boolean error=false;
		Thread.sleep(1000);
		String title=driver.getTitle();
		if(title.contains("404") | title.startsWith("Page not found") | title.startsWith("Site under main"))
			error=true;
		return error;
	}

	/**
	 * @author Vishwanath
	 * @return
	 */
	public synchronized static String getTime()
	{
		Date date = new Date();
		Object times = new Timestamp(date.getTime());
		time=String.valueOf(times);
		return time;
	}

	/**
	 * @author Vishwanath
	 * @param myAccountEmail
	 * @param password
	 * @param recepients
	 * @param msgSubject
	 * @param body
	 * @param attachment1Path
	 * @param attachment1Name
	 * @param attachment2Path
	 * @param attachment2Name
	 * @throws Exception
	 */
	public synchronized static void sendMail(String myAccountEmail, String password, String recepients, String msgSubject , String body, String attachment1Path, String attachment1Name, String attachment2Path,String attachment2Name) throws Exception
	{ 
		Properties properties= new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message= prepareMessage(session, myAccountEmail, recepients);
		message.setSubject(msgSubject);
		BodyPart msgBodyPart = new MimeBodyPart();
		msgBodyPart.setText(body);

		MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 


		File f1 = new File(attachment1Path);
		String path1=f1.getAbsolutePath();
		messageBodyPart2.attachFile(path1);
		messageBodyPart2.setFileName(attachment1Name);

		MimeBodyPart messageBodyPart3 = new MimeBodyPart();  
		File f2 = new File(attachment2Path);
		String path2=f2.getAbsolutePath();
		messageBodyPart3.attachFile(path2);
		messageBodyPart3.setFileName(attachment2Name);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart2);
		multipart.addBodyPart(msgBodyPart);
		multipart.addBodyPart(messageBodyPart3);

		message.setContent(multipart);
		Transport.send(message);

		System.out.println("Message sent successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepients)  
	{
		try {
			Message  message= new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));

			String[] rcpts = recepients.split(",");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(rcpts[0].trim()));
			for(int m=1; m<rcpts.length;m++)
			{
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(rcpts[m].trim()));
			}

			return message;
		}
		catch(Exception e)
		{}
		return null;
	}

	/**
	 * @author Vishwanath
	 * Generic method to generate random Numbers
	 * @param from
	 * @param to
	 * @param count
	 * @return
	 */
	public static ArrayList<Integer> getRandomNumber(int from, int to, int count) {
		Random r = new Random();
		Set<Integer> list = new TreeSet<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		int number;
		while (list.size() < count) {
			while (true) {
				number = r.nextInt(to + 1);
				if (number >= from)
					break;
			}
			list.add(number);
		}
		list2.addAll(list);
		Collections.sort(list2);
		return list2;
	}


	/**
	 * Generic method to scroll down the webpage
	 * @param length
	 */
	public void scrollDown(int length)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,"+length+")", "");
	}
	/**
	 * Generic method to scroll up the webpage
	 * @param length
	 */
	public void scrollUp(int length)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,"+-length+")", "");
	}

	public  void drawLine()
	{
		System.out.println();
		System.out.println("_____________________________________________________________________________________________");
	}

	public void scrollTo(WebElement element)
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	/**
	 * @author Vishwanath
	 * @param s
	 * @param t
	 * @throws InterruptedException
	 */
	public static void slowEffect(String s, int t) throws InterruptedException
	{
		Reporter.log(s);
		for(int i=0; i<s.length();i++) 
		{
			char ch=s.charAt(i);
			System.out.print(ch);
			Thread.sleep(t);
		}
		System.out.println();
	}

	/**
	 * @author Vishwanath
	 * Generic method to fetch the absolute paths of the all the files in a given folder
	 * @param folderPath
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> fetchFiles(String folderPath) throws IOException
	{
		ArrayList<String> filesPath = new ArrayList<String>();
		//From a folder 
		File f = new File(folderPath);
		String absPath=f.getAbsolutePath();

		File[] files = new File(absPath).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				filesPath.add(absPath.concat(file.getName()));
			}
		}
		return filesPath;
	}
	/**
	 * @author Vishwanath
	 * Generic method to remove non digits from a String
	 * @param s
	 * @return
	 */
	public static String removeNonDigits(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		return s.replaceAll("\\D+", "");
	}


	public static ArrayList<String> readFromGS(String readRange, int columnNum) throws IOException, GeneralSecurityException
	{
		gsTexts.clear();
		// Build a new authorized API client service.
		try {
			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(gsCreds, HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME)
					.build();

			ValueRange response = service.spreadsheets().values()
					.get(gsSheetId, readRange)
					.execute();

			List<List<Object>> values = response.getValues();
			if (values == null || values.isEmpty()) {
				System.out.println("No data found in the Google sheet.");
			}
			else {
				String text;
				for (int v=1; v<values.size(); v++) {
					try {
						text=(String)values.get(v).get(columnNum);
						if(text.equals("")==false && text!=null)
						{
							//System.out.println(text);
							gsTexts.add(text);
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return gsTexts;
	}

	public static void writeToGSheets(final String CREDENTIALS_FILE_PATH, String writeSpreadsheetId, String data,String overWriteRange ) throws IOException, GeneralSecurityException
	{
		checked= new Object();
		service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(CREDENTIALS_FILE_PATH, HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		checked = data;
		{
			service.spreadsheets().values().update(writeSpreadsheetId, overWriteRange, new ValueRange().setValues(Collections.singletonList(Arrays.asList(checked))))
			.setValueInputOption(valueInputOption)
			.execute();
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	public static Credential getCredentials(final String CREDENTIALS_FILE_PATH, NetHttpTransport HTTP_TRANSPORT) throws IOException 
	{
		// Load client secrets.
		FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
		if (in == null)
		{
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public void closeFeedBack()
	{
		if(Feedback1==true)
		{
			try {
				wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='No thanks']")));
				driver.findElement(By.xpath("//button[.='No thanks']")).click();
				Feedback1=false;
			}
			catch(Exception e) {}
		}
	}

	public void  goToTab(String tab)
	{
		jsClick("//button//div/a[contains(.,'"+tab+"')]");
	}

	public void addCourses(int count) throws InterruptedException
	{
		try{courseAddedNames.clear();}
		catch(Exception e) {}

		try{courseAddedIds.clear();}
		catch(Exception e) {}

		int check=0;
		List<WebElement> courses = driver.findElements(By.xpath("//span[.='Add Course ']/.."));
		if(courses.size()>0)
		{
			for(int i=1; i<=courses.size(); i++)
			{
				if(check==count)
					break;
				jsClick("(//span[.='Add Course ']/..)[1]");
				pause(2);
				courseAddedNames.add(driver.findElement(By.xpath("(//button[contains(@class,'options-in-add')]/div/img/../../../../../../../../../../../div[1]/div[2])["+i+"]")).getText().trim());
				courseAddedIds.add(driver.findElement(By.xpath("(//button[contains(@class,'options-in-add')]/div/img/../../../../../../../../../../../div[1]/div[1])["+i+"]")).getText().trim());
				check++;
			}
		}
	}

	public void verifyCoursesInDashboard()
	{
		boolean disp = true;
		for(int i =0; i<courseAddedIds.size(); i++)
		{
			try {
				String s1 = courseAddedIds.get(i).trim();
				String s2 = courseAddedNames.get(i).trim();
				System.out.println(s1+" : "+s2);
				wait15.until(ExpectedConditions.urlContains("/dashboard"));
				wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']")));
				pause(1);
				disp = driver.findElement(By.xpath("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']")).isDisplayed();
				System.out.println("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']");
				System.out.println("disp is: "+disp);
			}
			catch(Exception e) {
				disp = false;
				break;
			}
		}
		System.out.println("verifyCoursesInDashboard, exp true->"+disp);
		Assert.assertEquals(disp, true);
	}

	public void testOptionsAndEnrollButton() throws InterruptedException
	{
		String btntext;
		System.out.println("added courses size is: "+courseAddedIds.size());
		//clicking on paypal continue button
		for(int i =0; i<courseAddedIds.size(); i++)
		{
			try {
				String s1 = courseAddedIds.get(i).trim();
				String s2 = courseAddedNames.get(i).trim();
				wait15.until(ExpectedConditions.urlContains("/dashboard"));
				wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']")));
				//enroll,pay button
				btntext = driver.findElement(By.xpath("(//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button)[2]")).getText().trim();
				System.out.println("Button name is -"+btntext);
				if(btntext.equalsIgnoreCase("Enroll and pay"))
				{
					jsClick("(//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button)[2]");
					wait15.until(ExpectedConditions.urlContains("/courses-checkout"));
					wait25.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='continueButton']")));
					pause(1);

					driver.findElement(By.xpath("//div[@class='continueButton']")).click();
					try {
						wait8.until(ExpectedConditions.numberOfWindowsToBe(2));
					}
					catch(Exception e) {
						jsClick("//div[@class='continueButton']");
					}
					wait8.until(ExpectedConditions.numberOfWindowsToBe(2));

					pwh = driver.getWindowHandle();
					Set<String> owh= driver.getWindowHandles();
					Assert.assertEquals(owh.size(), 2);
					for(String wh : owh)
					{
						if(!wh.equals(pwh))
						{
							driver.switchTo().window(wh);
							driver.close();
						}
					}
					driver.switchTo().window(pwh);
					pause(2);
					goToTab("Dashboard");
					break;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}

		//options button
		for(int i =0; i<courseAddedIds.size(); i++)
		{
			try {
				String s1 = courseAddedIds.get(i).trim();
				String s2 = courseAddedNames.get(i).trim();
				wait15.until(ExpectedConditions.urlContains("/dashboard"));
				pause(3);
				wait15.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']")));
				//options button
				jsClick("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button[contains(.,'Options')]");
				pause(1);
				//course details
				List<WebElement> options =driver.findElements(By.xpath("(//div[@role='list']/div/div[2])"));
				int o=1;
				for(o=1; o<options.size();o++)
				{
					try {
						jsClick("(//div[@role='list']/div/div[1])["+o+"]");
						pause(2);
						//closing desc
						jsClick("(//div[@class='displayInfo']//i)[1]");
						break;
					}
					catch(Exception e) {}
				}
				//unenroll
				pause(1);
				jsClick("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button[contains(.,'Options')]");
				pause(1);
				jsClick("(//div[@role='list']/div/div[2])["+o+"]");
				pause(2);
				jsClick("//div[.='No, go back']/..");
				pause(1);
				jsClick("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button[contains(.,'Options')]");
				pause(1);
				jsClick("(//div[@role='list']/div/div[2])["+o+"]");
				pause(1);
				jsClick("//div[.='No, go back']/../../../../..//i");
				pause(1);
				jsClick("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button[contains(.,'Options')]");
				pause(1);
				jsClick("(//div[@role='list']/div/div[2])["+o+"]");
				pause(1);
				jsClick("//span/div[.='Yes, remove']/../../..");

				wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[.='"+s1+"']/../../..//strong[.='"+s2+"']/../../../..//button[contains(.,'Options')]")));
				pause(3);

			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		//verifying it in add Courses page
		//		goToTab("Add Courses");
		jsClick("//div[@class='addCourses']/a");
		wait15.until(ExpectedConditions.urlContains("courses-add"));
		waitTillScreenLoad();

		ArrayList<String> tempIds;
		ArrayList<String> tempCourseNames;
		tempIds = courseAddedIds;
		tempCourseNames = courseAddedNames;

		addCourses(2);
		Assert.assertEquals(tempIds, courseAddedIds);
		Assert.assertEquals(tempCourseNames, courseAddedNames);


	}


	public void waitTillScreenLoad()
	{
		try {
			wait30.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class,'loading')]"), 1));
		}
		catch(Exception e) {}
	}

	public boolean addToSchedule(String add)
	{
		try {
			jsClick("(//div[@class='reviewSelec'])[1]");
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public static String randomText(String type)
	{
		String s="abcdefghijklmnopqrstuvwxyz";
		String word="";
		if(type.equals("name"))
		{
			ArrayList<Integer> digits =getRandomNumber(1,25, 6);
			for(int digit:digits)
			{
				word=word.concat(String.valueOf(s.charAt(digit)));
			}
			return word;
		}
		else if(type.equals("email"))
		{
			ArrayList<Integer> digits =getRandomNumber(1,25, 5);
			for(int digit:digits)
			{
				word=word.concat(String.valueOf(s.charAt(digit)));
			}
			word=word.concat("@");
			ArrayList<Integer> digits2 =getRandomNumber(1,25, 3);
			for(int digit:digits2)
			{
				word=word.concat(String.valueOf(s.charAt(digit)));
			}
			word=word.concat(".");
			ArrayList<Integer> digits3 =getRandomNumber(1,25, 3);
			for(int digit:digits3)
			{
				word=word.concat(String.valueOf(s.charAt(digit)));
			}
			return word;
		}
		else
			return null;
	}

	public void pause(int seconds) throws InterruptedException
	{
		Thread.sleep(seconds*1000);
	}



	public void createResultFile()
	{
		File source = new File("./directory/index.xlsx");
		File dest = new File("./report/");
		try {
			FileUtils.copyFileToDirectory(source, dest);
		} catch (IOException e) {}

		File source1 = new File("./directory/index.xlsx");
		File dest1 = new File("./report2/");
		try {
			FileUtils.copyFileToDirectory(source1, dest1);
		} catch (IOException e) {}

	}


	public void openNewTab()
	{
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void jsClick(String xpath)
	{
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
	}






	public ArrayList<String> getDataFromJson(String jsonString, String... keys) throws Exception 
	{
		ArrayList<String> values = new ArrayList<String>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);
			JsonNode recordsNode = rootNode.get(keys[0]);
			JsonNode idNode = null;
			JsonNode tempNode;

			if (recordsNode != null && recordsNode.size() > 0 && recordsNode.isArray()==false) 
			{

				if(keys.length>1)
				{
					idNode = recordsNode.get(keys[1]);
					if (idNode != null) {
						JsonNode temp = idNode;
						if(keys.length>2)
						{
							idNode = idNode.get(keys[2]);
							//System.out.println(idNode);
							if(idNode==null)
								idNode = temp;
							values.add(idNode.asText()); 
						}
						else {
							values.add(idNode.asText()); 
						}
					}
				}
				else
					values.add(idNode.asText()); 
			}
			else if(recordsNode != null && recordsNode.size() > 0 && recordsNode.isArray()==true)
			{
				JsonNode temp;
				for(int z= 0 ;z<recordsNode.size(); z++)
				{
					tempNode = recordsNode.get(z);
					if(keys.length>1)
					{
						idNode = tempNode.get(keys[1]);
						if (idNode != null) 
						{
							temp = idNode;
							if(keys.length>2)
							{
								idNode = idNode.get(keys[2]);
								if(idNode==null)
									idNode = temp;
								values.add(idNode.asText()); 
							}
							else {
								values.add(idNode.asText()); 
							}
						}
					}
					else
						values.add(idNode.asText()); 
				}
			}
			if(values.size()==0)
			{
				values.add("NA");
			}
		}
		catch(Exception e) {
			System.out.println("Error getting json key");
			e.printStackTrace();
		}
		return values;
	}

	public ArrayList<String> getAcademicDataFromJson(String jsonString, String... keys) throws Exception 
	{
		jsonString = jsonString.toLowerCase();
		String altKeys[] = {"academicrecord" , "course" , "value"};

		if(jsonString.contains("coursedetails"))
			System.out.println("coursedetails present");
		else {
			System.out.println("coursedetails not present");
			keys = altKeys;
		}

		ArrayList<String> values = new ArrayList<String>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);
			JsonNode recordsNode = rootNode.get(keys[0]);
			JsonNode idNode = null;
			JsonNode tempNode, tempNode2;

			//academicrecord
			//coursedetails
			//course
			//value

			if (recordsNode != null && recordsNode.size() > 0 && recordsNode.isArray()==false) 
			{
				if(keys.length>1)
				{
					idNode = recordsNode.get(keys[1]);
					if (idNode != null) {
						JsonNode temp = idNode;
						if(keys.length>2)
						{
							idNode = idNode.get(keys[2]);
							if(idNode!=null)
							{
								JsonNode temp2 = idNode;
								if(keys.length>3)
								{
									idNode = idNode.get(keys[3]);
									if(idNode==null)
										idNode = temp2;
									values.add(idNode.asText()); 
								}
								else 
								{
									values.add(idNode.asText()); 
								}
							}
							else
							{
								values.add(temp.asText()); 
							}
						}
						else 
						{
							values.add(idNode.asText()); 
						}
					}
				}
				else
					values.add(idNode.asText()); 
			}
			else if(recordsNode != null && recordsNode.size() > 0 && recordsNode.isArray()==true)
			{
				for(int z= 0 ;z<recordsNode.size(); z++)
				{
					tempNode = recordsNode.get(z);
					if(keys.length>1)
					{
						idNode = tempNode.get(keys[1]);
						if (idNode != null) 
						{
							JsonNode temp = idNode;
							if(keys.length>2 && idNode.isArray()==false)
							{
								idNode = idNode.get(keys[2]);
								if(idNode!=null)
								{
									JsonNode temp2 = idNode;
									if(keys.length>3)
									{
										idNode = idNode.get(keys[3]);
										if(idNode==null)
											idNode = temp2;
										values.add(idNode.asText()); 
									}
									else 
									{
										values.add(idNode.asText()); 
									}
								}
								else
								{
									values.add(temp.asText()); 
								}
							}
							else if(keys.length>2 && idNode.isArray()==true)
							{
								JsonNode iNode;
								for(int j =0; j<idNode.size() ; j++)
								{
									tempNode2 = idNode.get(j);
									if(keys.length>2)
									{
										iNode = tempNode2.get(keys[2]);
										if (iNode != null) 
										{
											JsonNode temp2 = iNode;
											if(keys.length>3 && iNode.isArray()==false)
											{
												iNode = iNode.get(keys[3]);
												if(iNode==null)
													iNode = temp2;
												values.add(iNode.asText()); 
											}
											else
											{
												values.add(iNode.asText()); 
											}
										}
										else
										{
											values.add(iNode.asText()); 
										}

									}
									else
									{
										values.add(tempNode2.asText()); 
									}

								}
							}
							else 
							{
								values.add(idNode.asText()); 
							}
						}
					}
					else
					{
						values.add(idNode.asText()); 
					}
				}
			}
			if(values.size()==0)
			{
				values.add("NA");
			}
		}
		catch(Exception e) {
			System.out.println("Error getting json key");
			e.printStackTrace();
		}
		return values;
	}


	public String getDataFromFile(String filePath)
	{
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			StringBuilder fileContent = new StringBuilder();

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				fileContent.append(line).append("\n");
			}

			bufferedReader.close();
			fileReader.close();

			String content = fileContent.toString();

			return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String encode(String s)
	{
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(s.getBytes());
	}

	public String decode(String s)
	{
		Base64.Decoder dec = Base64.getDecoder();
		return new String(dec.decode(s));
	}
	public void print(Object s)
	{
		System.out.println(s);
	}

	public void getTPRequests() throws Exception
	{
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.responseReceived(), responseReceieved -> {
			try {
				//				System.out.print("....");
				APIrequests.add(responseReceieved);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		});
	}

	public String getTRequestResponses(String uri)
	{
		org.openqa.selenium.devtools.v116.network.model.RequestId[] requestId = new org.openqa.selenium.devtools.v116.network.model.RequestId[1];
		String responseBody = null;
		try {
			for(int i=0; i<APIrequests.size(); i++)
			{
				responseBody = null;
				org.openqa.selenium.devtools.v116.network.model.ResponseReceived responseReceieved = (ResponseReceived) APIrequests.get(i);
				Response response = responseReceieved.getResponse();
				String url = response.getUrl();
				if(url.equals(uri))
				{
					int count = 1; 
					while(true)
					{
						if(count == 10)
							break;
						try {
							requestId[0] = responseReceieved.getRequestId();
							responseBody = devTools.send(Network.getResponseBody(requestId[0])).getBody();
							if(responseBody!=null)
								break;
							else
								Thread.sleep(1000);
							count++;
						}
						catch(Exception e) {}
					}
				}
				if(responseBody!=null)
					break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//		responseBody = responseBody.toLowerCase();
		return responseBody;
	}

	public void disconnect()
	{
		devTools.disconnectSession();
	}

	public String getAPI(String uri)
	{
		request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		return response.body();
	}

	public Path download(String sourceURL, String targetDirectory, String fname) throws Exception
	{
		try {
			URL url = new URL(sourceURL);
			Path targetPath = new File(targetDirectory + File.separator + fname).toPath();
			Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			return targetPath;
		}
		catch(Exception e) {
			return null;
		}
	}

	PDDocument document;
	PDFTextStripper pdfStripper;
	String data;
	String word;
	String[] words;
	Pattern pattern;
	Matcher matcher;

	public void convertW2P(String inputPath, String outputPath) throws Exception
	{
		File inputWord = new File(inputPath);
		File outputFile = new File(outputPath);
		OutputStream outputStream = null;
		try  {
			InputStream docxInputStream = new FileInputStream(inputWord);
			outputStream = new FileOutputStream(outputFile);
			//	PDFParser docxInputStream = new PDFParser(new FileInputStream(inputWord));
			//	PDFParser outputStream = new PDFParser(new FileInputStream(outputFile));
			IConverter converter = LocalConverter.builder().build();
			converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
			System.out.println("success");
		} catch (Exception e) {
			System.out.println("Conversion failed!");
			e.printStackTrace();
		}
		finally {
			outputStream.close();
		}
	}

	public void createResultFile(String sourcePath, String destPath) throws Exception
	{
		File source = new File(sourcePath);
		File dest = new File(destPath);
		try {
			FileUtils.copyFileToDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean isDateGreaterThan(String s1, String s2) throws Exception
	{
		Date d1;
		Date d2;
		String pattern = "E MMM dd HH:mm:ss zzz YYYY";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		d1 = sdf.parse(s1);
		d2 = sdf.parse(s2);
		if(d1.after(d2))
			return false;
		else
			return true;
	}

	public void print(String s)
	{
		log.info(s);
		System.out.println(s);
	}

}

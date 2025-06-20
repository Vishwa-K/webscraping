package generics;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
public class Variables {

	public static WebDriver driver;
	public static ChromeOptions options;
	public static int browserNumber;
	public static WebDriverWait wait100;
	public static WebDriverWait wait150;
	public static WebDriverWait wait200;
	public static WebDriverWait wait3;
	public static WebDriverWait wait5;
	public static WebDriverWait wait8;
	public static WebDriverWait wait12;
	public static WebDriverWait wait15;
	public static WebDriverWait wait20;
	public static WebDriverWait wait25;
	public static WebDriverWait wait30;
	public static WebDriverWait wait50;
	public static WebDriverWait wait70;
	
	public static FileInputStream fis; 
	public static Workbook wb;
	public static FileOutputStream fos;

	public static FileInputStream fis1; 
	public static Workbook wb1;
	public static FileOutputStream fos1;

	
	//Google Sheets
	public static final String APPLICATION_NAME = "ULC_SF";
	public static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	public static final String TOKENS_DIRECTORY_PATH = "tokens";
	public static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS,SheetsScopes.DRIVE, SheetsScopes.DRIVE_FILE);
	protected final static NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static String valueInputOption = "RAW";
	public static Object checked;
	public static Sheets service;
	public static String gsSheetId;
	public static String gsCreds;
	public static ArrayList<String> gsTexts;

	
	public Data df = new Data();
	//Reports
	public static ExtentReports extent;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest test;
	public static String browserName;
	public static int screenShot=1;
	public static String time;

	//Login
	public static String websiteUrl;
	public static String registrationPageUrl;
	public static String loginPageUrl;
	public static String asuRite;
	public static String asuPassword;
	public static boolean Feedback1 = true;
	
	//Course Added
	public static ArrayList<String> courseAddedNames;
	public static ArrayList<String> courseAddedIds;
	
	public static Actions a;
	public static String pwh;
	public static Robot robot;
	
	public static JavascriptExecutor jse;
	
	public static CellStyle cellStyle; 
	
	public static Properties OR = new Properties();
	public static org.apache.logging.log4j.Logger log; 
	public static Properties config = new Properties();
	
	public static DevTools devTools;
	public static List APIrequests = new ArrayList();
	
	public static HttpRequest request;
	public static HttpResponse<String> response ;
}

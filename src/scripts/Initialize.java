package scripts;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import generics.BaseClass;
public class Initialize extends BaseClass{

	@Test(priority=1, groups={"Sanity","Daily","Regression","Smoke"})
	public void setUp() throws Exception, IOException, GeneralSecurityException {
		log = org.apache.logging.log4j.LogManager.getLogger(BaseClass.class);
		print("Initialized logs");

	}
	
	@Test(priority = 2)
	public void initialization() throws IOException, InterruptedException
	{
		websiteUrl = df.getData(0, 2);
		
		openBrowser(1);
		openWebsite(websiteUrl);
		a = new Actions(driver);
		jse = (JavascriptExecutor) driver;
	}
}


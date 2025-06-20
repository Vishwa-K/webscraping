package generics;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Local {
	public static void main(String[] args) {


//		WebDriverManager.edgedriver().setup();
//		EdgeDriver driver = new EdgeDriver();
//		Capabilities cap = driver.getCapabilities();
//
//
//		Map<String, Object> myCap = cap.asMap();
//		Object localhost = myCap.get("goog:edgeOptions");
//		System.out.println(myCap);
		
		
		
		ChromeDriver driver = new ChromeDriver();
//      // getCapabilities will return all browser capabilities
      Capabilities cap = driver.getCapabilities();

      // asMap method will return all capability in MAP
      Map<String, Object> myCap = cap.asMap();

      // print the map data-
      System.out.println(myCap);
      Object localHost = myCap.get("goog:chromeOptions");
      System.out.println(localHost);


	}
}


import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NavigationTests {

    public WindowsDriver driver;

    // Ran before each Test Method
    @BeforeMethod
    public void setUp() {

        setUpHubSession();
    }


    // Ran after each Test Method
    @AfterMethod
    private void tearDown() throws InterruptedException {
        driver.closeApp();
        driver.quit();
        Thread.sleep(2000);
    }

    @Test
    public void openSequencer() throws InterruptedException {

        String project = "Projects";
        String studio = "Automation Studio";
        String openSeq = "Open v0.0.0";

        driver.findElementByName(studio).click();
        driver.findElementByName(project).click();
        driver.findElementByName(openSeq).click();


    }

    // Creates a root desktop session that has access to every Window the desktop can see
    private void setUpDesktopSession() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");

        try {
            //create webdriver instance
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //provide implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setUpHubSession(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String appDirectory = "C:\\Users\\cawoo\\AppData\\Local\\Programs\\BCHub\\BCHub.exe";

        capabilities.setCapability("app", appDirectory);

        try {
            //create webdriver instance
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //provide implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


}

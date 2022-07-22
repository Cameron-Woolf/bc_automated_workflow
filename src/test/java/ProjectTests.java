import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ProjectTests {

    public WindowsDriver driver;

    @BeforeClass
    public void setUp() {

        setUpHubSession();

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        driver.closeApp();
        driver.quit();
        Thread.sleep(2000);
    }


    public void navigateToStudio() {
        String studio1 = "Automation Studio 2";
        driver.findElementByName(studio1).click();
    }

    @Test
    public void createProjectFromStudioHome() throws InterruptedException {

        navigateToStudio();

        String projectNew = "New Project";
        String projectName = "Project name";
        String submitButton = "Submit";

        // Clicks the New Project button
        driver.findElementByName(projectNew).click();

        Thread.sleep(1000);

        // Clicks the Project Name field
        driver.findElementByName(projectName).click();

        Thread.sleep(1000);

        // Types in the Project Name
        driver.findElementByName(projectName).sendKeys("Test Project");

        // Find the submit button
        driver.findElementByName(submitButton).click();

        Thread.sleep(2000);

    }

    @Test
    public void createNewProjectFromStudioProjectTab() {

    }

    @Test
    public void renameProject() {

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

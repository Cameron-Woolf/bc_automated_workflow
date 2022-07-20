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
    private boolean setUpCompleted = false;

    // Ran before each Test Method
    @BeforeMethod
    private void setUp() {

        if (!setUpCompleted) {
            setUpHubSession();
        }
    }

    // Ran after each Test Method
    @AfterMethod
    private void tearDown() throws InterruptedException {
//        driver.closeApp();
//        driver.quit();
//        Thread.sleep(2000);
    }
    @Test
    public void navigateBetweenHomeTabs() {

        // Home Tab names
        String homeTab = "To get missing image descriptions, open the context menu.";
        String youTab = "For you";
        String exploreTab = "Explore";
        String projectsTab = "Projects";
        String myProfile = "My Profile";

        // Navigate between profile tabs when BC first opens
        // Must do this before navigating to a studio or test will fail
        driver.findElementByName(homeTab).click();
        driver.findElementByName(youTab).click();
        driver.findElementByName(exploreTab).click();
        driver.findElementByName(projectsTab).click();
        driver.findElementByName(myProfile).click();

    }
    @Test
    public void navigateBetweenStudios() {

        String studio1 = "Automation Studio 1";
        String studio2 = "Automation Studio 2";
        String studio3 = "Automation Studio 3";

        driver.findElementByName(studio1).click();
        driver.findElementByName(studio2).click();
        driver.findElementByName(studio3).click();

    }

    @Test
    public void navigateBetweenTrayIcons() {

        // Tray icon names
        String helpIcon = "?";

        // Navigate to try icons
        driver.findElementByName(helpIcon).click();
    }
    @Test
    public void navigateBetweenStudioTabs() {

        // Studio Tab names
        String studio1 = "Automation Studio 1";
        String homeTab = "Home";
        String membersTab = "Members";
        String projectsTab = "Projects";
        String settingsTab = "Settings";

        // Navigate to Automation Studio 1
        driver.findElementByName(studio1).click();

        // Navigate to each tab inside the studio
        driver.findElementByName(homeTab).click();
        driver.findElementByName(membersTab).click();
        driver.findElementByName(projectsTab).click();
        driver.findElementByName(settingsTab).click();

    }

//    @Test
    public void navigateToProject() {

          /*
        No way to click on the project title itself because the Name
        property dynamically changes with the Edited X Days Ago field
         */

        // Element Names
        String projectsTab = "Projects";
        String studio1 = "Automation Studio 1";
        String project1 = "AS1 Project1";

        // Navigate to the correct studio
        driver.findElementByName(studio1).click();

        // Navigate to the projects tab
        driver.findElementByName(projectsTab).click();

        // Find the project
        driver.findElementByName(project1);
    }

    @Test
    public void openSequencer() {

        String studio = "Automation Studio 1";
        String projectTab = "Projects";
        /*
        No way to click on the project title itself because the Name
        property dynamically changes with the Edited X Days Ago field
         */
        String project = "AS1 Project1";

        // Just opening the first project that the is visible to the user.
        String openSeq = "Open v2.0.0";

        driver.findElementByName(studio).click();
        driver.findElementByName(projectTab).click();
//        driver.findElementByName(project).click();
        driver.findElementByName(openSeq).click();


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
        setUpCompleted = true;

    }


}

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OtherTests {

    public WindowsDriver driver;

//    @Test
    public void createProject() {

        String studio = "Automation Studio";
        String newProject = "New Project";

        driver.findElementByName(studio).click();
        driver.findElementByName(newProject).click();


    }

    //    @Test
    public void createStudio() {
        String addStudioButton_Xpath = "/Pane[@ClassName=\"#32769\"][@Name=\"Desktop 2\"]/Pane[@ClassName=\"Chrome_WidgetWin_1\"][@Name=\"BeatConnect\"]/Document[@ClassName=\"Chrome_RenderWidgetHostHWND\"][@Name=\"BeatConnect\"]/Group[@AutomationId=\"root\"]/Group/Group/Hyperlink";

        addStudioButton_Xpath = "//Document[@ClassName=\"Chrome_RenderWidgetHostHWND\"][@Name=\"BeatConnect\"]/Group[@AutomationId=\"root\"]/Group/Group/Hyperlink";

        driver.findElementByXPath(addStudioButton_Xpath).click();

    }

//    @Test
    public void openSequencer() throws InterruptedException {

        String project = "Projects";
        String studio = "Automation Studio";
        String openSeq = "Open v0.0.0";

        driver.findElementByName(studio).click();
        driver.findElementByName(project).click();
        driver.findElementByName(openSeq).click();


    }

//    @Test
    public void closeSequencer() throws InterruptedException {

//        setUpDesktopSession();
        openSequencer();

        String sequencerIcon = "BeatConnectDaw - 1 running window";
        String sequencerID = "BeatConnect";
        String sequencerCloseButton = "close.BeatConnect";
        String closeWindow = "close";

        // Accessibility ID is the AutomationId
        driver.findElementByAccessibilityId(sequencerID);

        driver.findElementByAccessibilityId(sequencerCloseButton).click();

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

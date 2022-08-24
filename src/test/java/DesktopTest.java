import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import javax.lang.model.element.Element;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesktopTest {

    public WindowsDriver rootDriver;


    @BeforeClass
    public void setUpDriver() throws MalformedURLException {

        rootDriver = setUpRootDriver();


    }


    @Test
    public void openBugForm() throws MalformedURLException {
        rootDriver = setUpRootDriver();
        WebElement bugForm = rootDriver.findElementByName("bug_form_v1");

        Actions action = new Actions(rootDriver);
        action.moveToElement(bugForm);
        action.doubleClick();
//        action.build();
        action.perform();

        getValueOfNotepad();
        addDateToNotePad();
        getValueOfNotepad();

    }

//    @Test
    private void getValueOfNotepad() {
       WebElement notepad = rootDriver.findElementByName("Text Editor");
       String text = notepad.getAttribute("Value.Value");
       System.out.println(text);
    }

    public String getDateFromTray() throws MalformedURLException {

        String trayClass = "TrayClockWClass";
        String dateAutomationId = "DateTextBlock";

        rootDriver.findElementByClassName("TrayClockWClass").click();
        WebElement date = rootDriver.findElementByAccessibilityId(dateAutomationId);

        System.out.println(date.getText());

//        rootDriver.quit();

        return date.getText();

    }

//    @Test
    public void addDateToNotePad() throws MalformedURLException {

        String date = getDateFromTray();

        NotepadPO notepad = new NotepadPO(rootDriver);

//        String test = "Test Me";
        notepad.textArea().click();
        notepad.textArea().sendKeys(date);

//        notepadDriver.quit();

    }

    private WindowsDriver setUpRootDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        WindowsDriver driver =  new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;

    }

    private WindowsDriver setUpNotePadDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "c:\\windows\\system32\\notepad.exe");
        return  new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);

    }

}

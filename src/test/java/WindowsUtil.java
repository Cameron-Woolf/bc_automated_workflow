import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class WindowsUtil {

    WindowsDriver rootDriver;

    public WindowsUtil(WindowsDriver rootDriver) {
        this.rootDriver = rootDriver;
    }

    public void maximizeFocusedWindow() {
            Actions keyPress = new Actions(rootDriver);
            keyPress.keyDown(Keys.COMMAND)
                    .sendKeys(Keys.ARROW_UP)
                    .perform();
            keyPress.keyUp(Keys.COMMAND).perform();
//        rootDriver.manage().window().maximize();

        }

    public void minimizeAllWindows() {
        Actions actions = new Actions(rootDriver);
        actions.keyDown(Keys.COMMAND)
                .sendKeys("m")
                .perform();
        actions.keyUp(Keys.COMMAND).perform();
    }

    public void scrollDown() throws InterruptedException {
        //   Scroll Down
//          String websiteWindow = "js-repo-pjax-container";
//          WebElement webWindow = rootDriver.findElementByAccessibilityId(websiteWindow);
//          Actions clickWindow = new Actions(rootDriver);
//          clickWindow.moveToElement(webWindow);
//          clickWindow.click();
//          clickWindow.perform();

        Thread.sleep(2000);
        Actions pageDown = new Actions(rootDriver);
        pageDown.sendKeys(Keys.PAGE_DOWN);
        pageDown.perform();
    }
}




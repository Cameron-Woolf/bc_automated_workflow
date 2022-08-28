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
}




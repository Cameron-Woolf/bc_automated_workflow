package utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class WindowsUtil {

    WindowsDriver rootDriver;
    Actions action;

    public WindowsUtil(WindowsDriver rootDriver) {

        this.rootDriver = rootDriver;
        this.action = new Actions(rootDriver);

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
        action.keyDown(Keys.COMMAND)
                .sendKeys("m")
                .perform();
        action.keyUp(Keys.COMMAND).perform();
    }


    public void openChrome() {
        WebElement chrome = rootDriver.findElementByName("Cameron (BC Main) - Chrome");
        action.moveToElement(chrome);
        action.doubleClick();
//        action.build();
        action.perform();


    }

    public void openSearchBar() {
        action.keyDown(Keys.CONTROL)
                .sendKeys("f")
                .perform();
        action.keyUp(Keys.CONTROL).perform();

    }

    public void openUrl(String url) {
//        String gitHubIssuesUrl = "https://github.com/BeatConnect/bc_js_workspace/issues";
//        String gitHubIssuesUrl = "https://github.com/Cameron-Woolf/bc_workflow_automated/issues";
        StringSelection selection = new StringSelection(url);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        WebElement addressBar = rootDriver.findElementByName("Address and search bar");
        Actions action = new Actions(rootDriver);
        action.moveToElement(addressBar);
        action.doubleClick();
//        action.build();
        action.perform();

        addressBar.sendKeys(Keys.CONTROL + "v");
        addressBar.sendKeys(Keys.RETURN);
    }

    public void copyToClipBoard(String content) {
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
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




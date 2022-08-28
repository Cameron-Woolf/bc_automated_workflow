import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import io.github.bonigarcia.wdm.WebDriverManager;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class CreateGithubIssue {


    public WindowsDriver rootDriver;
    private BugUtil bugUtil;

    private String bugType = "bc_hub_bug";

    private String bugFileName, bugDirectory;

    private String date, month, day, dailyBugCount;

    private String bugForm, bugName;


    @BeforeClass
    public void setUpDriver() throws MalformedURLException {
        rootDriver = setUpRootDriver();
        bugUtil = new BugUtil();
    }

    @Test
    public void createGitHubIssue() throws InterruptedException {

        // Minimize Test
        openChrome();
        Thread.sleep(2000);
        minimizeChrome();
        Thread.sleep(2000);
        addPhotoToIssue();

//       getCompletedBugForm(1);
//       getBugName();
//       openChrome();
//       openGitHubIssues();
//       openNewIssue();
//       inputIssueTitle();
//       inputIssueBody();
//       submitIssue();
//       addPhotoToIssue();
////       addvideoToIssue();

    }

    private void getCompletedBugForm(int bugNumber) {

        getDateAndBugCount();
        setFileName();

        // Manuel passing the bug number as an argument. Allows the choice of which bug to create an issue
        bugFileName = bugFileName;

        // Need to get the correct Directory and file name
        bugDirectory = "C:\\Users\\cawoo\\Desktop\\" + bugFileName+bugNumber;

        String bugFormFilePath = bugDirectory + "\\" + bugFileName + bugNumber +  "_form.txt";
        System.out.println(bugFormFilePath);
        bugForm = bugUtil.readBugFormFile(bugFormFilePath);
        System.out.println(bugForm);
    }

    private void getBugName() {

        int endOfName = bugForm.indexOf("\n");
        bugName = bugForm.substring(0, endOfName);
        System.out.println("Bug Name: " + bugName);

    }

    private void openChrome() {
        WebElement chrome = rootDriver.findElementByName("Cameron (BC Main) - Chrome");
        Actions action = new Actions(rootDriver);
        action.moveToElement(chrome);
        action.doubleClick();
//        action.build();
        action.perform();


    }


    private void openGitHubIssues() {
//        String gitHubIssuesUrl = "https://github.com/BeatConnect/bc_js_workspace/issues";
        String gitHubIssuesUrl = "https://github.com/Cameron-Woolf/bc_automated_workflow/issues";

        StringSelection selection = new StringSelection(gitHubIssuesUrl);
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

    private void openNewIssue() {
        WebElement addressBar = rootDriver.findElementByName("New issue");
        Actions action = new Actions(rootDriver);
        action.moveToElement(addressBar);
        action.click();
//        action.build();
        action.perform();
    }

    private void inputIssueBody() {

        // Copy the Bug Form to the clipboard
        StringSelection selection = new StringSelection(bugForm);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        String issueBodyId = "issue_body";
        WebElement issueBody = rootDriver.findElementByAccessibilityId(issueBodyId);
        Actions action = new Actions(rootDriver);
        action.moveToElement(issueBody);
        action.click();
//        action.build();
        action.perform();

        issueBody.sendKeys(Keys.CONTROL + "v");

        // Need to perform some kind of paste action here

    }

    private void inputIssueTitle() {

        // Copy the Bug Form to the clipboard
        StringSelection selection = new StringSelection(bugName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        String issueBodyId = "issue_title";
        WebElement issueBody = rootDriver.findElementByAccessibilityId(issueBodyId);
        Actions action = new Actions(rootDriver);
        action.moveToElement(issueBody);
        action.click();
//        action.build();
        action.perform();

        issueBody.sendKeys(Keys.CONTROL + "v");

    }

    private void submitIssue() throws InterruptedException {
        WindowsUtil windowsUtil = new WindowsUtil(rootDriver);
        windowsUtil.maximizeFocusedWindow();
        Thread.sleep(2000);
        String submitButton = "Submit new issue";
        WebElement issueBody = rootDriver.findElementByName(submitButton);
        Actions action = new Actions(rootDriver);
        action.moveToElement(issueBody);
        action.click();
//        action.build();
        action.perform();
    }

    private void maximizeBrowserWindow() {
        Actions keyPress = new Actions(rootDriver);
        keyPress.keyDown(Keys.COMMAND)
                .sendKeys(Keys.ARROW_UP)
                .perform();
        keyPress.keyUp(Keys.COMMAND).perform();

    }
    
    private void addPhotoToIssue() throws InterruptedException {

        minimizeChrome();
        Thread.sleep(2000);
        // Open the Bug folder..
        String bugDirectoryTest = "counter_bug"; // Just for testing
        System.out.println(bugDirectoryTest);
        WebElement bugFolder = rootDriver.findElementByName(bugDirectoryTest);
        Actions actions = new Actions(rootDriver);
        actions.moveToElement(bugFolder);
        actions.doubleClick();
        actions.perform();
//        copyPicFile();
//        maximizeChrome();
//        scrollDown();
//        pastPicFile();
//        scrollDown();
//        WebElement commentButton = rootDriver.findElementByName("Comment");
//        Actions action = new Actions(rootDriver);
//        action.moveToElement(commentButton);
//        action.click();
//        action.perform();


    }

    private void addvideoToIssue() {

    }

    private void minimizeChrome() {
        Actions actions = new Actions(rootDriver);
        actions.keyDown(Keys.COMMAND)
                .sendKeys("m")
                .perform();
        actions.keyUp(Keys.COMMAND).perform();
    }

    private void scrollDown() throws InterruptedException {
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

    private void getDateAndBugCount() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");;
        LocalDateTime now = LocalDateTime.now();
        date = dateTimeFormatter.format(now);
        month = date.substring(5,7);
        day = date.substring(8);

    }

    private void setFileName() {
        bugFileName =
                bugType +"_" +
                        month + "_" +
                        day + "_" +
                        0;

        System.out.println(bugFileName);

    }

    private WindowsDriver setUpRootDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        WindowsDriver driver =  new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;

    }

}

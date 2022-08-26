import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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

    private String bugForm;


    @BeforeClass
    public void setUpDriver() throws MalformedURLException {
        rootDriver = setUpRootDriver();
        bugUtil = new BugUtil();
    }

    @Test
    public void createGitHubIssue() {

       getCompletedBugForm(2);
//       getBugName();
       openChrome();
       openGitHubIssues();
       openNewIssue();
//       inputIssueTitle();
       inputIssueBody();
//       submitIssue();

    }

    private void getCompletedBugForm(int bugNumber) {

        getDateAndBugCount();
        setFileName();

        // Manuel passing the bug number as an argument. Allows the choice of which bug to create an issue
        bugFileName = bugFileName;

        // Need to get the correct Directory and file name
        bugDirectory = "C:\\Users\\cawoo\\Desktop\\" + bugFileName+bugNumber;

        String bugFormFilePath = bugDirectory + "\\" + bugFileName + bugNumber +  ".txt";
        System.out.println(bugFormFilePath);
        bugForm = bugUtil.readBugFormFile(bugFormFilePath);
        System.out.println(bugForm);
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
        String gitHubIssuesUrl = "https://github.com/BeatConnect/bc_js_workspace/issues";

        WebElement addressBar = rootDriver.findElementByName("Address and search bar");
        Actions action = new Actions(rootDriver);
        action.moveToElement(addressBar);
        action.doubleClick();
//        action.build();
        action.perform();

        addressBar.sendKeys(gitHubIssuesUrl);
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

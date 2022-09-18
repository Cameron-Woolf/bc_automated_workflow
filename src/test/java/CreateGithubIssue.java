import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BugUtil;
import utils.Drivers;
import utils.WindowsUtil;
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
    private WindowsUtil windowsUtil;

//    private String bugType = "bc_hub_bug";
    private String bugType = "bc_seq_bug";

    private String bugFileName, bugDirectory;

    private String date, month, day, dailyBugCount;

    private String bugForm, bugName;


    @BeforeClass
    public void setUpDriver() throws MalformedURLException {
        rootDriver = new Drivers().setUpRootDriver();
        bugUtil = new BugUtil();
        windowsUtil = new WindowsUtil(rootDriver);
    }

    @Test
    public void createGitHubIssue() throws InterruptedException {

        // Minimize Test
//        testFeatures();
        runWorkFlow();

    }

    private void testFeatures()  {
        // Minimize Test
        try {
            openChrome();
            Thread.sleep(2000);
            windowsUtil.minimizeAllWindows();
            Thread.sleep(2000);
            addPhotoToIssue();
        }
        catch (Exception e) {

        }
    }

    private void runWorkFlow() throws InterruptedException {
        getCompletedBugForm(11);
        getBugName();
        openChrome();
        openGitHubIssues();
        openNewIssue();
        inputIssueTitle();
        inputIssueBody();
        setLabels();
//        submitIssue();
//       addPhotoToIssue();
//       addvideoToIssue();
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
        bugName = bugForm.substring(5, endOfName);
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
        // Url for the Hub
//        String gitHubIssuesUrl = "https://github.com/BeatConnect/bc_js_workspace/issues";
        // Url for personal gitHub for testing
//        String gitHubIssuesUrl = "https://github.com/Cameron-Woolf/bc_workflow_automated/issues";
       // Url for the Sequencer
        String gitHubIssuesUrl = "https://github.com/BeatConnect/BeatConnect2/issues";

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


    }

    private void inputIssueTitle() {

        // Copy the Bug Form to the clipboard
        StringSelection selection = new StringSelection(bugName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        String issueBodyId = "issue_title"; // Automation ID from github
        WebElement issueBody = rootDriver.findElementByAccessibilityId(issueBodyId);
        Actions action = new Actions(rootDriver);
        action.moveToElement(issueBody);
        action.click();
//        action.build();
        action.perform();

        issueBody.sendKeys(Keys.CONTROL + "v");

    }

    private void setLabels() {

        String labels = "Labels";
        String searchBarAutomationId = "label-filter-field";
        String versionNumberLabel = "v0.1.13-alpha";
        String bugTextLabel = "bug Something isn't working";

        WebElement labelSection = rootDriver.findElementByName(labels);

        Actions actions = new Actions(rootDriver);
        actions.moveToElement(labelSection);
        actions.click();
        actions.perform();

        WebElement searchBar = rootDriver.findElementByAccessibilityId(searchBarAutomationId);

        actions.moveToElement(searchBar);
        actions.click();
        actions.perform();

        searchBar.sendKeys(bugTextLabel);
        WebElement bugLabel = rootDriver.findElementByName(bugTextLabel);
        actions.moveToElement(bugLabel);
        actions.click();
        actions.perform();
        searchBar.clear();

//        searchBar.sendKeys(versionNumberLabel);
//        WebElement versionLabel = rootDriver.findElementByName(versionNumberLabel);
//        actions.moveToElement(versionLabel);
//        actions.click();
//        actions.perform();
//        searchBar.clear();

        searchBar.sendKeys(Keys.ESCAPE);

    }

    private void submitIssue() throws InterruptedException {
        WindowsUtil windowsUtil = new WindowsUtil(rootDriver);
        windowsUtil.maximizeFocusedWindow();

        String submitButton = "Submit new issue";
        WebElement issueBody = rootDriver.findElementByName(submitButton);
        Actions action = new Actions(rootDriver);
        action.moveToElement(issueBody);
        action.click();
//        action.build();
        action.perform();
    }

    private void addPhotoToIssue() throws InterruptedException {

        windowsUtil.minimizeAllWindows();
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

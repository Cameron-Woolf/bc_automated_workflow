import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BugUtil;
import utils.Drivers;
import utils.WindowsUtil;

import java.net.MalformedURLException;

public class PostGitIssuesToTestRail {

    /* This automation assumes to following flow
    User is running through test rails, and finds a failed case
    User marks the test as failed, notes the testCase number into the automation
    User documents the bug and runs CreateGitHubIssue
    User completes the submission process for the GitIssue
    User runs PostGitIssuesToTestRail to copy a link from the issue, and post it into the result of test case.

     */

    public WindowsDriver rootDriver;
    private WindowsUtil windowsUtil;
    private Actions action;

    @BeforeClass
    public void setUpDriver() throws MalformedURLException {
        rootDriver = new Drivers().setUpRootDriver();
        windowsUtil = new WindowsUtil(rootDriver);
        action = new Actions(rootDriver);
    }

    @Test
    public void postGitIssuesToTestRail() throws InterruptedException {
         String issueNumber = "12";
         String testCaseNumber = "T1266";

         String gitIssueUrl = "https://github.com/BeatConnect/bc_js_workspace/issues/" + issueNumber;
         String testRailUrl = "https://beatconnectcw2.testrail.io/index.php?/runs/view/19&group_by=cases:section_id&group_order=asc";

         openTestRail(testRailUrl);
         Thread.sleep(2000);
         findTestCase(testCaseNumber);
         Thread.sleep(2000);
         openAddResult();
//         pasteGitIssueUrl();
//         submitResult();
    }

    private void openTestRail(String url) {

        windowsUtil.openChrome();
        windowsUtil.openUrl(url);

    }

    private void findTestCase(String testCaseNumber)  {

        windowsUtil.openSearchBar();

        WebElement searchBar = rootDriver.findElementByName("Find");
        searchBar.clear();
        action.sendKeys(searchBar, testCaseNumber).perform();

        WebElement testCase = rootDriver.findElementByName(testCaseNumber);
        action.moveToElement(testCase);
        action.click();
        action.perform();

    }

    private void openAddResult(){
        windowsUtil.openSearchBar();

        WebElement searchBar = rootDriver.findElementByName("Find");
        searchBar.clear();
        action.sendKeys(searchBar, "Add Result").perform();

        WebElement addButton = rootDriver.findElementByName("Add Result");
//        addButton.click();
        action.moveToElement(addButton);
        action.click();
        action.perform();

    }

    private void postGitIssueUrl() {

        String commentBodyId = "addResultComment_display";
        String submitResultId = "addResultSubmit";
        WebElement commentBody = rootDriver.findElementByAccessibilityId(commentBodyId);
        WebElement submitButton = rootDriver.findElementByAccessibilityId(submitResultId);


    }

}

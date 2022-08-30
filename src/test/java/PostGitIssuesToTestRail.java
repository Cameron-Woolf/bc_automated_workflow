import io.appium.java_client.windows.WindowsDriver;
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

    @BeforeClass
    public void setUpDriver() throws MalformedURLException {
        rootDriver = new Drivers().setUpRootDriver();
        windowsUtil = new WindowsUtil(rootDriver);
    }

    @Test
    public void postGitIssuesToTestRail() {
         String issueNumber = "318";
         String testCaseNumber = "T1020";

         String gitIssueUrl = "https://github.com/BeatConnect/bc_js_workspace/issues/" + issueNumber;
         String testRailUrl = "https://beatconnectcw2.testrail.io/index.php?/runs/view/18&group_by=cases:section_id&group_order=asc";

         openTestRail(testRailUrl);
        // findTestCase(
    }

    private void openTestRail(String url) {

        windowsUtil.openChrome();
        windowsUtil.openUrl(url);

    }

}

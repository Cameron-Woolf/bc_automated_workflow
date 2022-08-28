package utils;


import org.testng.annotations.Test;

public class ResetCounters {

    @Test
    public void resetBugCounters() {
        BugUtil bugUtil = new BugUtil();
        bugUtil.resetBugCounters();
    }
}

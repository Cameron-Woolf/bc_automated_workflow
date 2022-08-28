package utils;

import org.testng.annotations.Test;
import utils.BugUtil;

public class ResetCounters {


    @Test
    public void resetBugCounters() {
        BugUtil bugUtil = new BugUtil();
        bugUtil.resetBugCounters();
    }
}

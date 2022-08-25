import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class BugCounterUtil {

    private String bugCounterFileLocation;
    private static final Gson gson = new Gson();

    private static class BugCounter {

        private String date, bugCount;
        private String bugCounterFileLocation;

        public BugCounter(String date, String bugCount) {
            this.date = date;
            this.bugCount = String.valueOf(Integer.valueOf(bugCount) + 1);

        }

        private String getDate() {
            return date;
        }

        private String getBugCount() {
            return bugCount;
        }

    }
    // Read the current Bug Counter File
    // Read From File Utility
    private void readBugCounterFile() {
        // File: An abstract representation of file and directory pathnames.
        // User interfaces and operating systems use system-dependent pathname strings to name files and directories.
        File bugCounterFile= new File(bugCounterFileLocation);

        if (!bugCounterFile.exists())
            System.out.println("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(bugCounterFile), StandardCharsets.UTF_8);

            JsonReader myReader = new JsonReader(isReader);
            BugCounter bugCounter = gson.fromJson(myReader, BugCounter.class);

            System.out.println("Date: " + bugCounter.getDate() + "\n" +
                    "Bug Count:" + bugCounter.getBugCount());


        } catch (Exception e) {

        }
    }
}

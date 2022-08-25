import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class BugCounterUtil {

    private String bugCounterFileLocation= "C:\\Users\\cawoo\\Desktop\\bug_counter_v1.txt";
    private static final Gson gson = new Gson();

    public class BugCounter {

        private String date;
        private int bugCount;


        public BugCounter(String date, int bugCount) {
            this.date = date;
            this.bugCount = bugCount;

        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;

        }

        public int getBugCount() {
            return bugCount;
        }

        public void increaseBugCount() {
            bugCount = bugCount + 1;
        }

    }
    // Read the current Bug Counter File
    // Read From File Utility
    public BugCounter readBugCounterFile() {
        // File: An abstract representation of file and directory pathnames.
        // User interfaces and operating systems use system-dependent pathname strings to name files and directories.
        File bugCounterFile= new File(bugCounterFileLocation);
        BugCounter bugCounter = null;

        if (!bugCounterFile.exists())
            System.out.println("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(bugCounterFile), StandardCharsets.UTF_8);

            JsonReader myReader = new JsonReader(isReader);
            bugCounter = gson.fromJson(myReader, BugCounter.class);

//            System.out.println("Date: " + bugCounter.getDate() + "\n" +
//                    "Bug Count:" + bugCounter.getBugCount());

            if(bugCounter == null) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");;
                LocalDateTime now = LocalDateTime.now();
                String date = dateTimeFormatter.format(now);

                bugCounter = new BugCounter(date, 1);
            }

        } catch (Exception e) {

        }

        return bugCounter;
    }

    // Save to file Utility
        public void writeBugCounterFile(BugCounter bugCounter) {

        String jsonBugCount = gson.toJson(bugCounter);

        File bugCounterFile = new File(bugCounterFileLocation);

        try {

            // Convenience class for writing character files
            FileWriter fileWriter;
            fileWriter = new FileWriter(bugCounterFile.getAbsoluteFile(), false);
            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.flush();
            bufferWriter.write(jsonBugCount.toString());
            bufferWriter.close();

        } catch (IOException e) {

        }
    }
}

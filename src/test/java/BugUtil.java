import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class BugUtil {

    private String bugCounterHubFileLocation= "C:\\Users\\cawoo\\Desktop\\counter_bug\\counter_hub_bug_v1.txt";
    private String bugCounterSeqFileLocation= "C:\\Users\\cawoo\\Desktop\\counter_bug\\counter_seq_bug_v1.txt";
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
    public BugCounter readBugCounterFile(int bugType) {

        File bugCounterFile = null;
        BugCounter bugCounter = null;

        if(bugType == 1) {
            bugCounterFile= new File(bugCounterHubFileLocation);

        } else if (bugType == 2) {
            bugCounterFile= new File(bugCounterSeqFileLocation);

        }


        if (!bugCounterFile.exists())
            System.out.println("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(bugCounterFile), StandardCharsets.UTF_8);

            JsonReader myReader = new JsonReader(isReader);
            bugCounter = gson.fromJson(myReader, BugCounter.class);

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

    public String readBugFormFile(String fileLocation) {

        String bugForm = "";
        System.out.println("Bug Util:"+ fileLocation);
        File bugFormFile = new File(fileLocation);
        if (!bugFormFile.exists())
            System.out.println("File doesn't exist");

        try{
//            isReader = new InputStreamReader(new FileInputStream(bugFormFile), StandardCharsets.UTF_8);
            bugForm = new BufferedReader(
                    new InputStreamReader(new FileInputStream(bugFormFile), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return bugForm;
    }


    // Save to file Utility
    public void writeBugCounterFile(int bugType, BugCounter bugCounter) {

        String jsonBugCount = gson.toJson(bugCounter);

            File bugCounterFile = null;

            if(bugType == 1) {
                bugCounterFile= new File(bugCounterHubFileLocation);

            } else if (bugType == 2) {
                bugCounterFile= new File(bugCounterSeqFileLocation);
            }

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

    public void resetBugCounters() {

        try {

            File hubBugCounterFile= new File(bugCounterHubFileLocation);
            File seqBugCounterFile = new File(bugCounterSeqFileLocation);

            FileWriter fileWriter = new FileWriter(hubBugCounterFile.getAbsoluteFile(), false);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            bufferWriter.flush();;
            bufferWriter.close();

            fileWriter = new FileWriter(seqBugCounterFile.getAbsoluteFile(), false);
            bufferWriter = new BufferedWriter(fileWriter);

            bufferWriter.flush();
            bufferWriter.close();

        } catch (IOException e) {

        }

    }
}

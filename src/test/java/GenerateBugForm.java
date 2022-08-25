import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class GenerateBugForm {

    public WindowsDriver rootDriver;
    public NotepadPO notepad;

    private String bugType = "bc_hub_bug";

    private String date, month, day, dailyBugCount;
    private String fileName;



    @BeforeClass
    public void setUpDriver() throws IOException {

            rootDriver = setUpRootDriver();
            getDateAndBugCount();
            setFileName();
            generateBugFormFile();

    }

    @Test
    private void openBugForm() {

        WebElement bugForm = rootDriver.findElementByName(fileName);
        Actions action = new Actions(rootDriver);
        action.moveToElement(bugForm);
        action.doubleClick();
//        action.build();
        action.perform();
    }

    public void generateBugFormFile() throws IOException {

        String bugForm = "C:\\Users\\cawoo\\Desktop\\" + fileName + ".txt";
        File file = new File(bugForm); //initialize File object and passing path as argument
        boolean result = file.createNewFile();
        if (result) {
            formatBugForm(bugForm);

        }
        else {
            System.out.println("File name not unqiue.");
            Assert.assertTrue(result);
        }


    }

    private void formatBugForm(String fileName) {

        String hub_bug_form =
                "Name: \n"
                        + "ID: bc_hub_bug_"+month+"_"+day+"_"+dailyBugCount +"\n"
                        + "Date: "+ date + "\n"
                        + "OS/Browser: Windows, Scorpion \n"
                        + "Hub/Sequencer Version: Copyright 0.1.12-alpha / BeatConnect DAW 3.0.15/ BeatConnectLib 4.0.10 \n\n"
                        + "Steps: \n\n\n"
                        + "Description: ";


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);  // true for append mode
            byte[] bugFormData = hub_bug_form.getBytes();
            fileOutputStream.write(bugFormData);
            fileOutputStream.close();
            System.out.println("file saved.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDateAndBugCount() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");;
        LocalDateTime now = LocalDateTime.now();
        date = dateTimeFormatter.format(now);
        month = date.substring(5,7);
        day = date.substring(8);
        dailyBugCount = "0" + String.valueOf(2);
    }

    private void setFileName() {
        fileName =
        bugType +"_" +
        month + "_" +
        day + "_" +
        dailyBugCount;

    }

    private String getBugCount() {
        return "";
    }

    private String setBugCount() {
        return "";
    }

    private WindowsDriver setUpRootDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        WindowsDriver driver =  new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;

    }

}

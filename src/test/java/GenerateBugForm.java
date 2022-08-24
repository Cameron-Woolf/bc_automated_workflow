import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GenerateBugForm {

    private final String hub_bug_form =
     "Name: \n"
    + "ID: bc_hub_bug_X_XX_XX \n"
    + "Date: X/XX/XX \n"
    + "OS/Browser: Windows, Scorpion \n"
    + "Hub/Sequencer Version: Copyright 0.1.12-alpha / BeatConnect DAW 3.0.15/ BeatConnectLib 4.0.10 \n\n"
    + "Steps: \n\n\n"
    + "Description: ";

    public WindowsDriver notepadDriver;
    public NotepadPO notepad;


    @BeforeClass
    public void setUpDriver() throws MalformedURLException {

            notepadDriver = setUpNotePadDriver();

    }

    @Test
    public void generateBugForm() {

        // Create the notepad PO
        notepad = new NotepadPO(notepadDriver);
        notepad.textArea().sendKeys(hub_bug_form);

    }

    private void saveNotepad() {

    }

    private WindowsDriver setUpNotePadDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "c:\\windows\\system32\\notepad.exe");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;

    }
}

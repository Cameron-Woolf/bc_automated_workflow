import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;


public class NotepadPO {
    WindowsDriver driver = null;
    public NotepadPO(WindowsDriver wd){
        driver = wd;

    }

    public WebElement minimize(){
        return driver.findElementByName("Minimize");
    }

    public WebElement maximize(){
        return driver.findElementByName("Maximize");
    }

    public WebElement close(){
        return driver.findElementByName("Close");
    }

    public WebElement menuFile(){
        return driver.findElementByName("File");
    }

    public WebElement textArea(){
        return driver.findElementByClassName("Edit");
    }

    public WebElement dialogSave(){
        return driver.findElementByName("Save");
    }

    public WebElement dialogDontSave(){
        return driver.findElementByName("Don't Save");
    }

    public WebElement dialogCancel(){
        return driver.findElementByName("Cancel");
    }
}

package utils;

import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteAllDesktopBugs {

    @Test
    public void deleteAllDekstopBugs() {


        String desktopDirectory = "C:\\Users\\cawoo\\Desktop\\";
        // You have to progmaically get the date again..
        // Or read the counter file..
        String bugName = "bc_hub_bug_8_27_0";
        ArrayList<String> bugArray = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            String fullBugPath = desktopDirectory+bugName + i;
            System.out.println("Adding path"+ fullBugPath);
            bugArray.add(fullBugPath);
        }

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Are you sure you want to delete all Desktop Bugs?: Y/N");
//        String choice = sc.nextLine();
        String choice = "Y";
        if(choice.equals("Y")) {
            runDelete(bugArray);
        }
        else {

        }
    }

    private void runDelete(ArrayList<String> directoryNames) {

        System.out.println("run delete");

        for (String bugPath: directoryNames)
        {
            // It's not a file it's a directory..
            /*
            Java isn't able to delete folders with data in it.
            You have to delete all files before deleting the folder.
             */
            File bugFile = new File(bugPath);
            if(bugFile.exists()) {
                System.out.println("File found: "+ bugPath);
//                bugFile.delete();
            }
            else {
                System.out.println("File not found: "+ bugPath);
            }
        }
    }
}

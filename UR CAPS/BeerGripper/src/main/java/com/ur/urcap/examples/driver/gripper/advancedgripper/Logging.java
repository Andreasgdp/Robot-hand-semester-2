package com.ur.urcap.examples.driver.gripper.advancedgripper;

import java.awt.event.ActionEvent;
import java.io.IOException;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class

public class Logging {
    private String filePath;


    public Logging(){
        this.filePath = "MyLogFile.txt";
    }

    public void changeFile(String file) {
        this.filePath =  "/home/ur/ursim/ursim-5.9.1.1031110/programs.UR5/Robot-hand-semester-2/UR CAPS/BeerGripper" + file + ".txt";
    }

    public void logCodeRun(String fileName, String functionName) {
        this.changeFile(fileName + functionName);
        this.createFile();
        this.writeFile(fileName, functionName);
    }

    private void createFile() {
        try {
            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeFile(String fileName, String functionName) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write("The The file run here is: '" + fileName + "' and the function is " + functionName + " seconds");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
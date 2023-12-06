package com.example.training;

import com.example.steaming.Stemming;
import java.io.FileReader;
import java.io.IOException;


import java.io.*;
import java.util.*;

public class ProcessData {
    Stemming stemming = new Stemming();
    FrequencyTableGenerate frequencyTableGenerate = new FrequencyTableGenerate();

    private  void readCSV(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            String lines;
            sc.nextLine();
            int i=0;
            while (sc.hasNext())
            {
                lines = sc.nextLine();
                String[] words = lines.split(",");
                //System.out.println(words[0]+"\n\n "+words[1]);
                stemming(words[0],words[1]);
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

       // frequencyTableGenerate.printDetails();
        frequencyTableGenerate.countEachCategoryWord();
    }

    public void serializeObject(){
        String filename = "file.ser";

        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(frequencyTableGenerate);
            out.close();
            file.close();

            System.out.println("Object has been serialized");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void stemming(String line, String type){
        ArrayList<String> words =  stemming.stem(line);
        frequencyTableGenerate.createFrequencyTable(words,type);
    }





    public static void main(String[] args) {
        ProcessData processData = new ProcessData();
        processData.readCSV("news.csv");
        //processData.readCSV("news.csv");
        processData.serializeObject();


    }
}

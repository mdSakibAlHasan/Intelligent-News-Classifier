package com.example.testing;

import com.example.steaming.Stemming;
import com.example.training.FrequencyTableGenerate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NewDataAdd {
    public void writeData(String text, String type){
        Stemming stemming = new Stemming();

        ArrayList<String> words =  stemming.stem(text);
        FrequencyTableGenerate frequencyTableGenerate = getObject();
        frequencyTableGenerate.createFrequencyTable(words,type);
        frequencyTableGenerate.countEachCategoryWord();

        serializeObject(frequencyTableGenerate);
    }

    private FrequencyTableGenerate getObject(){
        NaiveBayes naiveBayes = new NaiveBayes();
        return naiveBayes.getDataObject();
    }

    private void serializeObject(FrequencyTableGenerate frequencyTableGenerate){
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
}

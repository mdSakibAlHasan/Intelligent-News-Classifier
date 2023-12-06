package com.example.training;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTableGenerate implements Serializable {
    public Map<String, Integer> newsCategory = new HashMap<>();
    public int[] eachCategoryWord = new int[10];
    public Map<String, Map<Integer,Integer>> frequencyTable = new HashMap<>();


    public FrequencyTableGenerate(){
        newsCategory.put("sport",0);
        newsCategory.put("business",1);
        newsCategory.put("tech",2);
        newsCategory.put("politics",3);
        newsCategory.put("entertainment",4);
    }


    public void addWord(String str, int category){
        Map<Integer, Integer> row = frequencyTable.get(str);
        if(row == null){
            row = new HashMap<>();
            row.put(category,1);
        }
        else{
            int value=1;
           if(row.containsKey(category)){
               value = row.get(category);
               value++;
           }
           row.put(category,value);
        }
        frequencyTable.put(str,row);
    }



    public void createFrequencyTable(ArrayList<String> line, String type){
        for(String word: line){
            int category = newsCategory.get(type);
            //System.out.println(category+" category "+category);
            addWord(word,category);
        }
    }

    public void countEachCategoryWord(){
        frequencyTable.forEach((key, innerMap) -> {
            //System.out.println("Key: " + key);

            innerMap.forEach((innerKey, value) -> {
                eachCategoryWord[innerKey] += value;
                //System.out.println("Inner Key: " + innerKey + ", Value: " + value);
            });
        });

        System.out.println("Total word for each category");
        for(int i=0;i<5;i++){
            System.out.println(eachCategoryWord[i]);
        }

    }

    public void printDetails(){
        System.out.println(frequencyTable.size());
        System.out.println(frequencyTable);
    }


}

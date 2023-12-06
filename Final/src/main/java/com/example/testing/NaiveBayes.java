package com.example.testing;
import com.example.steaming.Stemming;
import com.example.training.FrequencyTableGenerate;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class NaiveBayes {
    int[] largeNumber = new int[10];
    double[] prob = new double[10];
    public FrequencyTableGenerate getDataObject(){
        FrequencyTableGenerate frequencyTableGenerate= null;
        try
        {
            FileInputStream file = new FileInputStream("file.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            frequencyTableGenerate = (FrequencyTableGenerate) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized ");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return frequencyTableGenerate;
    }

    public int findWord(String word, int category, FrequencyTableGenerate frequencyTableGenerate){
        if (frequencyTableGenerate.frequencyTable.containsKey(word)) {
            Map<Integer, Integer> innerMap = frequencyTableGenerate.frequencyTable.get(word);
            if(innerMap.containsKey(category)){
                return innerMap.get(category)+1;
            }
            else{
                return 1;
            }
        }
        return 1;
    }

    public void calculation(FrequencyTableGenerate frequencyTableGenerate, String text){
        int totalWord = 0;
        for(int i=0;i<5;i++){
           totalWord += frequencyTableGenerate.eachCategoryWord[i];
        }
        ArrayList<String> words = formatUserInput(text);
        int finalTotalWord = totalWord;
        frequencyTableGenerate.newsCategory.forEach((category, value)->{
            double probability= ((double)frequencyTableGenerate.eachCategoryWord[value]/ (double)finalTotalWord);
            for(String word: words){
                probability *= (double)findWord(word,value,frequencyTableGenerate)/(double)frequencyTableGenerate.eachCategoryWord[value];
                if(probability<1E-300){
                  probability = largeData(probability,value);
                }
            }
            prob[value] = largeData(probability,value);
            //System.out.println("Probability for "+category+" is "+probability+" and "+largeNumber[value]);
        });

    }

    public double largeData(double probability, int value){
        String valueAsString = String.valueOf(probability);
        int indexE = valueAsString.indexOf('E');
        if (indexE != -1 && indexE + 1 < valueAsString.length()) {
            String exponentPart = valueAsString.substring(indexE + 1);
            String doublePart = valueAsString.substring(0,indexE-1);
            int exponent = Integer.parseInt(exponentPart);
            double doubleNumber = Double.parseDouble(doublePart);
            largeNumber[value] += exponent;

            return doubleNumber;
        }

        return 1;
    }

    public ArrayList<String> formatUserInput(String text){
//        System.out.println("Input news: ");
//        Scanner sc = new Scanner(System.in);
//        String line = sc.nextLine();
        Stemming stemming = new Stemming();
        ArrayList<String> words = stemming.stem(text);

        return words;
    }

    public double[] probabilityCalculation(){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<5;i++){           //find large number
            if(largeNumber[i]>max){
                max = largeNumber[i];
            }
        }
        max *= -1;
        for(int i=0;i<5;i++){
           largeNumber[i] += max;
        }

        double total=0.0;
        for(int i=0;i<5;i++){
            prob[i] *= Math.pow(10,largeNumber[i]);
            total += prob[i];
        }

        for(int i=0;i<5;i++){
            prob[i] /= total;
            System.out.println("\n Probability is: "+prob[i]);
        }

        return prob;
    }

    public String returnClassification(String text){
        String[] category = {"sport","business","tech","politics","entertainment"};
        FrequencyTableGenerate frequencyTableGenerate =  getDataObject();
        calculation(frequencyTableGenerate, text);
        double[] probability = probabilityCalculation();
        double maxProbability=0.0;
        int index = -1;
        for(int i=0;i<5;i++){
            if(maxProbability < probability[i]){
                maxProbability = probability[i];
                index = i;
            }
        }

        return category[index];
    }



    public static void main(String[] args) {
//        NaiveBayes naiveBayes = new NaiveBayes();
//        FrequencyTableGenerate frequencyTableGenerate =  naiveBayes.getDataObject();
//        naiveBayes.calculation(frequencyTableGenerate);
//        naiveBayes.probabilityCalculation();
    }

}

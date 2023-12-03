package testing;
import steaming.Stemming;
import training.FrequencyTableGenerate;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class NaiveBayes {
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

    public void calculation(FrequencyTableGenerate frequencyTableGenerate){
        int totalWord = 0;
        for(int i=0;i<5;i++){
           totalWord += frequencyTableGenerate.eachCategoryWord[i];
        }
        ArrayList<String> words = formatUserInput();
        int finalTotalWord = totalWord;
        frequencyTableGenerate.newsCategory.forEach((category, value)->{
            double probability= ((double)frequencyTableGenerate.eachCategoryWord[value]/ (double)finalTotalWord);
            //System.out.println("Porbability is "+probability);
            for(String word: words){
                probability *= (double)findWord(word,value,frequencyTableGenerate)/(double)frequencyTableGenerate.eachCategoryWord[value];
            }
            System.out.println("Probability for "+category+" is "+probability);
        });

    }

    public ArrayList<String> formatUserInput(){
        System.out.println("Input news: ");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Stemming stemming = new Stemming();
        ArrayList<String> words = stemming.stem(line);

        return words;
    }



    public static void main(String[] args) {
        NaiveBayes naiveBayes = new NaiveBayes();
        FrequencyTableGenerate frequencyTableGenerate =  naiveBayes.getDataObject();
        naiveBayes.calculation(frequencyTableGenerate);
//        for(int i=0;i<5;i++){
//            System.out.println(frequencyTableGenerate.eachCategoryWord[i]);
//        }
    }

}

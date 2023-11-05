package training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTableGenerate {
    Map<String, Integer> newsCategory = new HashMap<>();
    Map<String, Map<Integer,Integer>> frequencyTable = new HashMap<>();
    //ArrayList<ArrayList<Integer>> frequencyTable = new ArrayList<>();


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

    public void createFrequencyTable(String[][] data, int size){
        for(int i=0;i<size;i++){
            //System.out.println(data[i][2]+" "+newsCategory);
            int category = newsCategory.get(data[i][2]);
            System.out.println(category+" category "+data[i][2]);
            String[] words = data[i][1].split(" ");
            for(String word: words){
                //System.out.print(":"+word);
                addWord(word,category);
            }
        }
        System.out.println(frequencyTable.size());
        System.out.println(frequencyTable);
    }

//    public static void main(String[] args) {
//        FrequencyTableGenerate frequencyTableGenerate = new FrequencyTableGenerate();
//        frequencyTableGenerate.initializeMap();
//        frequencyTableGenerate.addWord("play",3);
//        frequencyTableGenerate.addWord("play",3);
//        frequencyTableGenerate.addWord("sds",3);
//        System.out.println(frequencyTableGenerate.frequencyTable.size());
//    }

}

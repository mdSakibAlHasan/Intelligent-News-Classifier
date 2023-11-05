package training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTableGenerate {
    Map<String, Integer> newsCategory = new HashMap<>();
    Map<String, ArrayList<Integer>> frequencyTable = new HashMap<>();
    //ArrayList<ArrayList<Integer>> frequencyTable = new ArrayList<>();


    public void initializeMap(){
        newsCategory.put("sport",0);
        newsCategory.put("business",1);
        newsCategory.put("tech",2);
        newsCategory.put("politics",3);
        newsCategory.put("entertainment",4);

        newsCategory.get("sport");
    }

    public void addWord(String str, int category){
        ArrayList<Integer> row = new ArrayList<>();
        for(int i=0;i<5;i++){
            if(i==category){
                row.add(2);
            }
            else{
                row.add(1);
            }
        }
        frequencyTable.add(row);
    }


}

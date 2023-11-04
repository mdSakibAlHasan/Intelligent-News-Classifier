package training;

import java.util.HashMap;
import java.util.Map;

public class FrequencyTable {
    Map<String, Integer> newsCategory = new HashMap<>();

    public void initializeMap(){
        newsCategory.put("sport",0);
        newsCategory.put("business",1);
        newsCategory.put("tech",2);
        newsCategory.put("politics",3);
        newsCategory.put("entertainment",4);

        newsCategory.get("sport");
    }


}

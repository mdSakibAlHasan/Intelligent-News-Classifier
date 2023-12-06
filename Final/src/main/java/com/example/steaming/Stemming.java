package com.example.steaming;
import java.util.ArrayList;

public class Stemming {
    public ArrayList<String> stem(String text) {
        General_Processing general_processing = new General_Processing();
        Porter_Stemming_Algo porter_stemming_algo = new Porter_Stemming_Algo();

        String[] wordsArray = general_processing.do_general_processing(text);
        ArrayList<String> root_word = new ArrayList<>();

        for (String word : wordsArray) {
            root_word.add(porter_stemming_algo.apply_porter_stemming(word));
        }
//        for(String w : root_word)
//        {
//            System.out.print(w + " ");
//        }
        return root_word;
    }
}

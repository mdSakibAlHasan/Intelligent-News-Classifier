package com.example.steaming;
public class Decision {
    public boolean is_vowel(char x) {
        return (x == 'a') || (x == 'e') || (x == 'i') || (x == 'o') || (x == 'u');
    }

    public boolean is_star_v_star(String word) {
        // Check if the word ends with "ed" or "ing"
        if (word.endsWith("ed")) {
            word = word.substring(0, word.length() - 2);
        }
        if(word.endsWith("ing")){
            word = word.substring(0, word.length() - 3);
        }

        for (int i = 0; i < word.length(); i++) {
            char currentChar = Character.toLowerCase(word.charAt(i));
            if (is_vowel(currentChar)) {
                return true;
            }
        }

        return false;
    }
}

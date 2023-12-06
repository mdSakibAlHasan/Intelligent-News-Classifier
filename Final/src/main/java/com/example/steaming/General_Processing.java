package com.example.steaming;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class General_Processing {

    public static String convertToLowerCase(String inputString) {
        String resultString = inputString.toLowerCase();
        return resultString;
    }

    public String removePunctuation(String inputString) {
        // Define a regular expression to match punctuation marks
        String regex = "[\\p{Punct}]";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Remove punctuation marks from the input string
        String resultString = pattern.matcher(inputString).replaceAll("");

        return resultString;
    }

    public String removePrepositionsAndArticles(String inputString) {
        // Define a set of common prepositions and articles
        Set<String> excludedWords = new HashSet<>(Arrays.asList(
                "a", "an", "the",
                "in", "on", "at",
                "to", "for", "with",
                "by", "of", "about",
                "and","upon","to",
                "around","up","down",
                "into","over","along",
                "with","above","below",
                "within","off","at",
                "until","by","breside",
                "than","from","for",
                "it","this","that",
                "he","his","she",
                "her","my","mine",
                "we","our","us",
                "am","as","is",
                "are","was","were",
                "be","been","have","has"));

        // Split the input string into an array of words
        String[] wordsArray = inputString.split("\\s+");

        // Filter out prepositions and articles
        StringBuilder result = new StringBuilder();
        for (String word : wordsArray) {
            if (!excludedWords.contains(word.toLowerCase())) {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

    public String[] getWordsArray(String inputString) {
        // Split the input string into an array of words
        String[] wordsArray = inputString.split("\\s+"); // "\\s+" matches one or more whitespace characters

        return wordsArray;
    }

    public String[] do_general_processing(String inputString){
        inputString = convertToLowerCase(inputString);
        inputString = removePunctuation(inputString);
        inputString = removePrepositionsAndArticles(inputString);
        return getWordsArray(inputString);
    }
}

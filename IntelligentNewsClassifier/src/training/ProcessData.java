package training;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ProcessData {
    String[][] data = new String[1500][3];
    private  void readCSV(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            sc.useDelimiter(",");
            int i=0;
            while (sc.hasNext())
            {
                data[i/3][i%3] = sc.next();
                System.out.print(data[i/3][i%3]+"  -----  "+i+"   ");
                if(i<30)
                    i++;
                else
                    break;
            }
            sc.close();

            for(i=0;i<10;i++){
                System.out.println(data[i][0]+" "+data[i][1]+" "+data[i][2]+"----------------------------------");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTags(int index) {
        // Define a regular expression to match HTML tags
        String regex = "<[^>]*>";
        data[index][1].replaceAll(regex, "");
    }

    public void removePunctuationsSpecialCharsNumbers(int index) {
        // Use regular expression to remove punctuations, special characters, and numbers
        String regex = "[^a-zA-Z\\s]";
        data[index][1].replaceAll(regex, "");
    }

    public void convertToLower(int index) {
        data[index][1].toLowerCase();
    }

    public void removeStopwords(int index) {
        // Define a set of English stopwords
        Set<String> stopWords = new HashSet<>(Arrays.asList("a", "an", "the", "and", "in", "on", "at", "to", "of", "for"));

        // Tokenize the text and remove stopwords
        String[] words = data[index][1].split("\\s");
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (!stopWords.contains(word)) {
                filteredWords.add(word);
            }
        }

        data[index][1] = String.join(" ", filteredWords);
    }

    public static void process(ProcessData processData) {
        for(int i=0;i<3;i++){
            processData.removeTags(i);
            System.out.println("Text without tags: " + processData.data[1][1]);

            // Remove punctuations, special characters, and numbers
           processData.removePunctuationsSpecialCharsNumbers(i);
            System.out.println("Cleaned text: " +  processData.data[1][1]);

            // Convert to lowercase
            processData.convertToLower(i);
            System.out.println("Lowercase text: " +  processData.data[1][1]);

            // Remove stopwords
            processData.removeStopwords(i);
            System.out.println("Text without stopwords: " +  processData.data[1][1]);
        }
    }

    public static void main(String[] args) {
        ProcessData processData = new ProcessData();
        processData.readCSV("BBC News Train.csv");
        process(processData);
    }
}

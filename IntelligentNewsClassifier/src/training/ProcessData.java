package training;

import java.io.File;
import java.util.*;

public class ProcessData {
    String[][] data = new String[1500][3];
    Stemmer stemmer = new Stemmer();
    private  void readCSV(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            String line;
            sc.nextLine();
            int i=0;
            while (sc.hasNext())
            {
                line = sc.nextLine();
                data[i] = line.split(",");
                if(i<3)
                    i++;
                else
                    break;
            }
            sc.close();

            for(i=0;i<3;i++){
                System.out.println(data[i][0]+" ## "+data[i][1]+" "+data[i][2]+"----------------------------------");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTags(int index) {
        String regex = "<[^>]*>";
        data[index][1] = data[index][1].replaceAll(regex, "");
    }

    public void removePunctuationsSpecialCharsNumbers(int index) {
        // Use regular expression to remove punctuations, special characters, and numbers
        String regex = "[^a-zA-Z\\s]";
        data[index][1] = data[index][1].replaceAll(regex, "");
    }

    public void convertToLower(int index) {
        data[index][1] = data[index][1].toLowerCase();
    }

    public void removeStopwords(int index) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("a", "an", "the", "and", "in", "on", "at", "to", "of", "for"));

        String[] words = data[index][1].split("\\s");
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (!stopWords.contains(word)) {
                filteredWords.add(word);
            }
        }

        data[index][1] = String.join(" ", filteredWords);
    }

    public void stemming(int index){
        data[index][1] =  stemmer.convertStemmer(data[index][1]);
    }


    public static void process(ProcessData processData) {
        for(int i=0;i<3;i++){
            processData.removeTags(i);
            processData.removePunctuationsSpecialCharsNumbers(i);
            processData.convertToLower(i);
            processData.removeStopwords(i);
            processData.stemming(i);

            System.out.println("Text with processing: " +  processData.data[i][1]);
        }
    }

    public static void main(String[] args) {
        ProcessData processData = new ProcessData();
        processData.readCSV("BBC News Train.csv");
        process(processData);

    }
}

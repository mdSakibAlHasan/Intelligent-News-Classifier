package training;

import steaming.Stemming;

import java.io.File;
import java.util.*;

public class ProcessData {
    Stemming stemming = new Stemming();
    FrequencyTableGenerate frequencyTableGenerate = new FrequencyTableGenerate();
    private  void readCSV(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            String lines;
            sc.nextLine();
            int i=0;
            while (sc.hasNext())
            {
                lines = sc.nextLine();
                String[] words = lines.split(",");
                stemming(words[1],words[2]);
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        frequencyTableGenerate.printDetails();
        frequencyTableGenerate.countEachCategoryWord();
    }

    public void serializeObject(){
        Demo object = new Demo(1, "geeksforgeeks");
        String filename = "file.ser";
        
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }


    public void stemming(String line, String type){
        ArrayList<String> words =  stemming.stem(line);
        frequencyTableGenerate.createFrequencyTable(words,type);
    }





    public static void main(String[] args) {
        ProcessData processData = new ProcessData();
        processData.readCSV("BBC News Train.csv");

    }
}

package training;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ProcessData {
    private  void readCSV(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            sc.useDelimiter(",");   //sets the delimiter pattern
            int i=0;
            while (sc.hasNext())  //returns a boolean value
            {
                System.out.print(sc.next()+" ----- ");  //find and returns the next complete token from this scanner
                if(i<12)
                    i++;
                else
                    break;
            }
            sc.close();  //closes the scanner

//            FileReader filereader = new FileReader(file);
//            System.out.println("This part is done");
//            CSVReader csvReader = new CSVReader(filereader);
//            String[] nextRecord;
//            System.out.println("Done part");
//            int i=0;
//            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
//                if(i<10){
//                    i++;
//                }
//                else {
//                    break;
//                }
//            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage()+" the erro mmessage");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessData processData = new ProcessData();
        processData.readCSV("BBC News Train.csv");
    }
}

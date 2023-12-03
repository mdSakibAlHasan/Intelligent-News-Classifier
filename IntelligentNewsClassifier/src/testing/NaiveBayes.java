package testing;
import training.FrequencyTableGenerate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NaiveBayes {
    public FrequencyTableGenerate getDataObject(){
        FrequencyTableGenerate frequencyTableGenerate= null;
        try
        {
            FileInputStream file = new FileInputStream("file.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            frequencyTableGenerate = (FrequencyTableGenerate) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized ");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return frequencyTableGenerate;
    }

    public static void main(String[] args) {
        NaiveBayes naiveBayes = new NaiveBayes();
        FrequencyTableGenerate frequencyTableGenerate =  naiveBayes.getDataObject();
        //frequencyTableGenerate.printDetails();
        for(int i=0;i<5;i++){
            System.out.println(frequencyTableGenerate.eachCategoryWord[i]);
        }
    }

}

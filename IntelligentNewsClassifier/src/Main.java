import WebCrawler.SimpleWebCrawler;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome to this news crawling!");


        SimpleWebCrawler SimpleCrawl = new SimpleWebCrawler();
        ArrayList<String> categoryList=  new ArrayList<String>();

        ArrayList<String> BaseUrl = new ArrayList<>();
//        BaseUrl.add("https://www.dhakatribune.com");
        BaseUrl.add("https://www.thedailystar.net");

//        String baseUrl ="https://www.thedailystar.net";

        
//        categoryList.add("/bangladesh/politics/");
        categoryList.add("/news/world/");
        categoryList.add("/entertainment/");
        categoryList.add("/news/bangladesh/");
        categoryList.add("/tech-startup/");
        categoryList.add("/business/");
        categoryList.add("/sports/");


        for(String baseUrl : BaseUrl){
            for (String category : categoryList) {
                // Combine baseUrl, category, and requiredURL to form the complete URL
                String completeURL = baseUrl + category;

                // Call your crawl method or perform any other action with the completeURL
                SimpleCrawl.crawl(1, completeURL, category, new ArrayList<String>());
            }
        }


        String inputFilePath = "news.csv";
        String outputFilePath = "news2.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath)) {

            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println("Dhukse");
                // Remove commas from each line
                String cleanedLine = line.replace(",", "");

                // Write the cleaned line to the output file
                writer.write(cleanedLine + "\n");
            }

            System.out.println("Commas removed and new CSV written to " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
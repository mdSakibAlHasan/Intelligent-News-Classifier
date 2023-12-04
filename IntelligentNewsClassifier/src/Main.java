import WebCrawler.SimpleWebCrawler;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome to this news crawling!");


        SimpleWebCrawler SimpleCrawl = new SimpleWebCrawler();
        ArrayList<String> categoryList=  new ArrayList<String>();

        String baseUrl ="https://www.thedailystar.net";

        categoryList.add("/entertainment/");
        categoryList.add("/news/bangladesh/");
        categoryList.add("/tech-startup/");
        categoryList.add("/business/");
        categoryList.add("/sports/");


        for (String category : categoryList) {
            // Combine baseUrl, category, and requiredURL to form the complete URL
            String completeURL = baseUrl + category;

            // Call your crawl method or perform any other action with the completeURL
            SimpleCrawl.crawl(1, completeURL, category, new ArrayList<String>());
        }


    }
}
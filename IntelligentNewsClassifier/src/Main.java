import WebCrawler.SimpleWebCrawler;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome to this news crawling!");


        SimpleWebCrawler SimpleCrawl = new SimpleWebCrawler();
        String requiredURL = "/sports/";

        String url ="https://www.thedailystar.net/sports/";
        SimpleCrawl.crawl(1,url,requiredURL,new ArrayList<String>());


    }
}
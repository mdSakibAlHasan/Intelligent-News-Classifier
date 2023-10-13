import WebCrawler.SimpleWebCrawler;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        SimpleWebCrawler SimpleCrawl = new SimpleWebCrawler();
        String url ="https://en.wikipedia.org/";
        SimpleCrawl.crawl(1,url,new ArrayList<String>());


    }
}
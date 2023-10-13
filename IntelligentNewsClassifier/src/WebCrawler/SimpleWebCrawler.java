package WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleWebCrawler {
    public static void crawl (int level, String url, ArrayList<String> visited){
        if(level<=5){
            Document doc = request(url,visited);

            if(doc!=null){
                for(Element link : doc.select("a[href]")){
                    String next_link = link.absUrl("href");
                    if(visited.contains(next_link) == false){
                        crawl(level++,next_link,visited);
                    }
                }
//                printPageContent(doc);
            }
        }

    }

    private static Document request(String url, ArrayList<String>v){
        try{
            Connection con =Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode()==200){
//                System.out.println("Dhuksee");
                System.out.println("Link:" + url);
                System.out.println(doc.title());

                //page content
                Elements articleContent = doc.select("div span");
                StringBuilder newsContent = new StringBuilder();
                for (Element paragraph : articleContent) {
                    newsContent.append(paragraph.text()).append("\n");
                }
                System.out.println("News Content:\n" + newsContent.toString());



                v.add(url);

                return doc;
            }
            return null;

        }catch (IOException e){
            return null;
        }
    }

    private static void printPageContent(Document doc) {
        String pageContent = doc.text(); // .html() for the HTML content.
        System.out.println("Page Content:\n" + pageContent);
    }

}

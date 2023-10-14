package WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleWebCrawler {
    public static void crawl (int level,String url,String requiredURL, ArrayList<String> visited){
        ArrayList<String> vl =  new ArrayList<String>();
        if (url.contains(requiredURL)) {
            if (level <= 5) {
                Document doc = request(url, visited,vl);

                if (doc != null) {
                    for (Element link : doc.select("a[href]")) {
                        String next_link = link.absUrl("href");
                        if (visited.contains(next_link) == false) {
                            crawl(level++, next_link,requiredURL, visited);
                        }
                    }
                }
            }
        }else {
            System.out.println("This link is not targeted");
        }

    }

    private static Document request(String url, ArrayList<String>v, ArrayList<String>vl){
        try{
            Connection con =Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode()==200){
//                System.out.println("Dhuksee");
                System.out.println("Link:" + url);
                System.out.println(doc.title());

                //page content
                Elements articleContent = doc.select("div strong > p,div>p");
                StringBuilder newsContent = new StringBuilder();
                for (Element paragraph : articleContent) {
                    newsContent.append(paragraph.text()).append("\n");
                }

                Elements links = doc.select("a[href]");
                System.out.println("Links:");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    if(vl.contains(linkHref)==false && linkHref.contains("/sports/")){
                        vl.add(linkHref);
                        System.out.println(linkHref);
                    }
                }
                System.out.println("Prime Content:\n" + newsContent.toString());



                v.add(url);

                return doc;
            }
            return null;

        }catch (IOException e){
            return null;
        }
    }

//    private static void printPageContent(Document doc) {
//        String pageContent = doc.text(); // .html() for the HTML content.
//        System.out.println("Page Content:\n" + pageContent);
//    }

}

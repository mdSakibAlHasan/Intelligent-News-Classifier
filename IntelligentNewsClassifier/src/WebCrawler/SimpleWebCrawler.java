package WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//for CSV
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;


public class SimpleWebCrawler {
    public static void crawl (int level,String url,String requiredURL, ArrayList<String> visited){
        ArrayList<String> lList=  new ArrayList<String>();
        if (url.contains(requiredURL)) {
            if (level <= 5) {
                Document doc = request(url, visited);

                if (doc != null) {
                    Elements links = doc.select("a[href]");
                    for (Element link : links) {
                        String next_link = link.attr("href");
                        next_link="https://www.thedailystar.net"+next_link;
                        if(visited.contains(next_link) == false && next_link.contains("/sports/")){
                                crawl(level++, next_link,requiredURL, visited);

                        }
                    }

                }
            }
        }else {
            System.out.println("This link is not targeted");
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
                Elements articleContent = doc.select("div strong > p:not(.title), div > p:not(.title)");
                StringBuilder newsContent = new StringBuilder();
                for (Element paragraph : articleContent) {
                    newsContent.append(paragraph.text()).append("\n");
                }
                System.out.println("Prime Content:\n" + newsContent.toString());


                //write on CSV file
                try (FileWriter writer = new FileWriter("sportsNews.csv", true); // Open the file in append mode
                     CSVWriter csvWriter = new CSVWriter(writer)) {

                    // Format the news content
                    String formattedContent = newsContent.toString().replace("\n", " ");
//                    formattedContent = "\"" + formattedContent.replace("\"", "\"\"") + "\"";

                    // Write the data to the CSV file
                    String[] row = new String[]{doc.title(), formattedContent};
                    csvWriter.writeNext(row);
                }

                v.add(url);
                return doc;
            }
            return null;

        }catch (IOException e){
            return null;
        }
    }
    
}

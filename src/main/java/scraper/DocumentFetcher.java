package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentFetcher {
    public static Document getDocumentFromUrl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("url not found : " + url);
        }
        return doc;
    }
}

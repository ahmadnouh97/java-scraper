package main.java.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CollectionsFetcher {

    public static ArrayList<String> fetchAllCollectionsFromUrl(String url){
        ArrayList<String> collectionsLinks = new ArrayList<>();

        Document doc = DocumentFetcher.getDocumentFromUrl(url);

        if (doc != null) {
            Elements collectionDivTags = doc.getElementsByClass("grid");

            for (Element div : collectionDivTags) {
                Elements collectionATags = div.getElementsByTag("a");
                for (Element aTag : collectionATags) {
                        String link = aTag.attr("href");
                    collectionsLinks.add(link);
                }
            }
        }

        return collectionsLinks;
    }
}

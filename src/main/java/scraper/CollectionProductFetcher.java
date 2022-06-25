package scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CollectionProductFetcher {

    public static ArrayList<String> fetchAllCollectionProducts(String url) {
        ArrayList<String> collectionProducts = new ArrayList<>();
        Document doc = DocumentFetcher.getDocumentFromUrl(url);
        if (doc == null) {
            return collectionProducts;
        }
        Element productsListTag = doc.getElementById("product-loop");

        if (productsListTag != null) {
            Elements listItemTags = productsListTag.getElementsByTag("li");
            for (Element itemTag : listItemTags) {
                Element aTag = itemTag.getElementsByTag("a").first();
                if (aTag != null) {
                    String product = aTag.attr("href");
                    collectionProducts.add(product);
                }
            }
        }
        return collectionProducts;
    }
}

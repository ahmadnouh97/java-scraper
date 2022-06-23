package scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CollectionProductFetcher {

    public static ArrayList<String> fetchAllCollectionProducts(String url) {
        ArrayList<String> collectionProducts = new ArrayList<>();
        Document doc = DocumentFetcher.getDocumentFromUrl(url);
        Element productsListTag = doc.getElementById("models.product-loop");

        Elements aTags = null;
        if (productsListTag != null) {
            aTags = productsListTag.select("a[href]");
            for (Element aTag : aTags) {
                String product = aTag.attr("href");
                collectionProducts.add(product);
            }
        }
        return collectionProducts;
    }
}

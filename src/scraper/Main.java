package scraper;

import models.Product;

import java.io.FileReader;
import java.util.Properties;
import java.util.ArrayList;

public class Main {
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("config")) {
            properties.load(reader);
        } catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        Properties properties = loadProperties();
        String url = properties.getProperty("app.scrapingUrl");
        ArrayList<String> collectionsLinks = CollectionsFetcher.fetchAllCollectionsFromUrl(url);

        String collectionUrl = collectionsLinks.get(0);
        ArrayList<String> products = CollectionProductFetcher.fetchAllCollectionProducts(url + "/ar" + collectionUrl);
        Product product = ProductFetcher.fetchProductData(url + products.get(0));
        System.out.println(product);

    }
}
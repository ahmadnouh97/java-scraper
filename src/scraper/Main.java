package scraper;

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
        ArrayList<String> collections = CollectionsFetcher.fetchAllCollectionsFromUrl(url);

        String collectionUrl = collections.get(0);
        System.out.println(collectionUrl);

        //        ArrayList<String> products = CollectionProductFetcher.fetchAllCollectionProducts(collectionUrl);
        //
        //        for (String product : products) {
        //            System.out.println(product);
        //        }
    }
}
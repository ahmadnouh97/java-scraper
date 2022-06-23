package main.java;

import main.java.scraper.CollectionProductFetcher;
import main.java.scraper.CollectionsFetcher;
import main.java.scraper.ProductFetcher;

import java.io.FileReader;
import java.util.Properties;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


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

    public static void main(String[] args) throws InterruptedException {
        Properties properties = loadProperties();
        String url = properties.getProperty("app.scrapingUrl");
        System.out.println(url);
        int sleepTime = Integer.parseInt(properties.getProperty("app.scrapingSleepTime"));

        ArrayList<String> collections = CollectionsFetcher.fetchAllCollectionsFromUrl(url);

        for (String collection : collections) {
            String collectionUrl = url + "/ar" + collection;
            ArrayList<String> productsPaths = CollectionProductFetcher.fetchAllCollectionProducts(collectionUrl);

            for (String productPath : productsPaths) {
                String productUrl = url + productPath;
                models.Product product = ProductFetcher.fetchProductData(productUrl);
                System.out.println(product);
                TimeUnit.SECONDS.sleep(sleepTime);
            }

        }
    }
}
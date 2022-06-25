import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Scraper {
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("config")) {
            properties.load(reader);
        } catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }

    public void run() throws InterruptedException {
        // load configs
        Properties properties = loadProperties();
        String url = properties.getProperty("app.scrapingUrl");
        int sleepTime = Integer.parseInt(properties.getProperty("app.scrapingSleepTime"));
        String dbHost = properties.getProperty("app.dbHost");
        int dbPort = Integer.parseInt(properties.getProperty("app.dbPort"));
        String dbName = properties.getProperty("app.dbName");
        String dbCollectionName = properties.getProperty("app.dbCollectionName");


        // create db connection
        DBConnection connection = new DBConnection(dbName, dbHost, dbPort);
        MongoCollection mongoCollection = connection.getCollection(dbCollectionName);


        ArrayList<String> collections = main.java.scraper.CollectionsFetcher.fetchAllCollectionsFromUrl(url);

        for (String collection : collections) {
            String collectionUrl = url + "/ar" + collection;
            ArrayList<String> productsPaths = main.java.scraper.CollectionProductFetcher.fetchAllCollectionProducts(collectionUrl);

            for (String productPath : productsPaths) {
                String productUrl = url + productPath;
                models.Product product = main.java.scraper.ProductFetcher.fetchProductData(productUrl);

                Document document = new Document();
                document.putAll(product.toMap());
                mongoCollection.insertOne(document);

                System.out.println(product);
                TimeUnit.SECONDS.sleep(sleepTime);
            }
        }
    }
}

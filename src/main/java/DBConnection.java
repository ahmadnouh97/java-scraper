import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
    String databaseName;
    String host;
    int port;

    MongoDatabase database;

    DBConnection(String databaseName, String host, int port) {
        this.databaseName = databaseName;
        this.host = host;
        this.port = port;

        this.connectDB();
    }

    private void connectDB() {
        MongoClient mongoClient = new MongoClient(this.host, this.port);
        this.database = mongoClient.getDatabase(this.databaseName);
    }


    protected MongoCollection getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

}

import com.mongodb.*;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Manu on 17/07/15.
 */
public class ServerConnection {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT= 8080;
    private static final String NAME_DB = "shortURL";
    private static final String PORT_DB = "27017";

    public static void main(String[] args) throws Exception{
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new Resource(new DAO(mongo()));

    }

    private static DB mongo() throws Exception{
        String host = System.getenv("192.168.99.100");
        if(host == null){
            MongoClient mongoClient = new MongoClient("192.168.99.100");
            return mongoClient.getDB(NAME_DB);
        }
        int port = Integer.parseInt(PORT_DB);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(NAME_DB);
        return db;
    }
}

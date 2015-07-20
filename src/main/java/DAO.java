import com.google.gson.Gson;
import com.mongodb.*;
import entity.ShortUrl;
import org.bson.types.ObjectId;
import utility.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 17/07/15.
 */
public class DAO {
    private static DAO dao = null;
    private DB db;

    private DBCollection collection;

    public DAO() {
        try {
            this.db = mongo();

            this.collection = db.getCollection(Constants.NAME_COLLECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void addShortUrl(String body) {
        ShortUrl shortUrl = new Gson().fromJson(body, ShortUrl.class);

        collection.insert(new BasicDBObject(Constants.URL_SHORT_FIELD, shortUrl.getShortUrl())
                .append(Constants.URL_LONG_FIELD, shortUrl.getLongUrl())
                .append(Constants.CONTINENTS_FIELD, shortUrl.getContinents())
                .append(Constants.COUNTRIES_FIELD, shortUrl.getCountries())
                .append(Constants.CITIES_FIELD, shortUrl.getCities()));




    }

    public ShortUrl update(String id, String body){
        ShortUrl shortUrl = new Gson().fromJson(body, ShortUrl.class);
        collection.update(new BasicDBObject("_id", new ObjectId(id)),
                new BasicDBObject("$set", new BasicDBObject(Constants.URL_SHORT_FIELD, shortUrl.getShortUrl())
                        .append(Constants.URL_LONG_FIELD, shortUrl.getLongUrl())
                        .append(Constants.CONTINENTS_FIELD, shortUrl.getContinents())
                        .append(Constants.COUNTRIES_FIELD, shortUrl.getCountries())
                        .append(Constants.CITIES_FIELD, shortUrl.getCities())));
        return this.find(id);
    }

    public List<ShortUrl> findAll(){
        List<ShortUrl> urls = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()){
            DBObject dbObject = dbObjects.next();
            urls.add(new ShortUrl((BasicDBObject) dbObject));
        }
        return urls;
    }

    public ShortUrl find(String id){
        return new ShortUrl((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    private DB mongo() throws Exception {
        String host = System.getenv(Constants.ADDRESS_MONGO_CONNECTION);
        if (host == null) {
            MongoClient mongoClient = new MongoClient(Constants.ADDRESS_MONGO_CONNECTION);
            return mongoClient.getDB(Constants.NAME_DB);
        }

        /* Codice non funzionante boot2docker
        if (host == null) {
            host = System.getenv(Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER);
            if (host == null){
                MongoClient mongoClient = null;
                try {
                    mongoClient = new MongoClient(Constants.ADDRESS_MONGO_CONNECTION);
                } catch (UnknownHostException e) {
                    mongoClient = new MongoClient(Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER);
                }
                return mongoClient.getDB(Constants.NAME_DB);
            }

        }
         */
        int port = Integer.parseInt(Constants.PORT_DB);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(Constants.NAME_DB);
        return db;
    }

}

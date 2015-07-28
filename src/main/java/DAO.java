import com.google.gson.Gson;
import com.mongodb.*;
import entity.ShortUrl;
import org.bson.types.ObjectId;
import utility.Constants;
import utility.GeoIPReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
                .append(Constants.CITIES_FIELD, shortUrl.getCities())
                .append(Constants.CREATED_ON_FIELD, new Date()));




    }

    public ShortUrl update(String id){
        ShortUrl shortUrl = find(id);
        List<String> continentsClick = shortUrl.getContinents();
        List<String> countriesClick = shortUrl.getCountries();
        List<String> citiesClick = shortUrl.getCities();
        GeoIPReader geoIPReader = new GeoIPReader();
        continentsClick.add(continentsClick.size(), geoIPReader.getContinent());
        countriesClick.add(countriesClick.size(), geoIPReader.getCountry());
        citiesClick.add(citiesClick.size(), geoIPReader.getCity());


        collection.update(new BasicDBObject(Constants.URL_SHORT_FIELD, shortUrl.getShortUrl()),
                new BasicDBObject("$set", new BasicDBObject(Constants.URL_SHORT_FIELD, shortUrl.getShortUrl())
                        .append(Constants.URL_LONG_FIELD, shortUrl.getLongUrl())
                        .append(Constants.CONTINENTS_FIELD, continentsClick)
                        .append(Constants.COUNTRIES_FIELD, countriesClick)
                        .append(Constants.CITIES_FIELD, citiesClick)
                        .append(Constants.CREATED_ON_FIELD, shortUrl.getCreatedOn())));
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

    public String autoGenerate(String id){
        String string = new String("http://shrinkYo.Url/");
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
            StringBuilder sb = new StringBuilder(6);
        do {
            for (int i = 0; i < 6; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
        } while (collection.findOne(new BasicDBObject("urlShort", sb.toString())) != null);
        string += sb;
        return string;
    }

    public ShortUrl find(String id){
        return new ShortUrl((BasicDBObject) collection.findOne(new BasicDBObject("urlShort", id)));
    }

    private DB mongo() throws Exception {
        String host = System.getenv(Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER);
        if (host == null) {
            MongoClient mongoClient = new MongoClient(Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER);
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

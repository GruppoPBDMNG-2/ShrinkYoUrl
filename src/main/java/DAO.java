import com.google.gson.Gson;
import com.mongodb.*;
import entity.ShortUrl;
import org.bson.types.ObjectId;
import utility.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Classe DAO contenente tutte le operazioni che si possono effettuare sul database
 */
public class DAO {
    private DB db;

    private DBCollection collection;

    /**
     * Costruttore che crea una nuova connessione al database e seleziona la collection
     * su cui saranno effettuate tutte le operazioni
     */
    public DAO() {
        try {

            this.db = mongo();

            this.collection = db.getCollection(Constants.NAME_COLLECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo per l'aggiunta di uno shortUrl al database
     * @param body Json contenente i dati della nuova istanza
     * @return true se l'operazione va a buon fine
     */
    public boolean addShortUrl(String body){
        ShortUrl shortUrl = new Gson().fromJson(body, ShortUrl.class);
        collection.insert(new BasicDBObject(Constants.URL_SHORT_FIELD, shortUrl.getShortUrl())
                    .append(Constants.URL_LONG_FIELD, shortUrl.getLongUrl())
                    .append(Constants.CONTINENTS_FIELD, shortUrl.getContinents())
                    .append(Constants.COUNTRIES_FIELD, shortUrl.getCountries())
                    .append(Constants.CITIES_FIELD, shortUrl.getCities())
                    .append(Constants.CREATED_ON_FIELD, new Date()));
        return true;
    }

    /**
     * Metodo per la modifica di uno shortUrl già presente nel database
     * @param id versione ridotta dell'url da modificare
     * @return lo shortUrl modificato
     */
    public ShortUrl update(String id){
        ResetString resetString = new ResetString();
        id = resetString.resetString(id);
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

    /**
     * Metodo per la stampa a video di tutte le istanze del database
     * @return lista degli shortUrl presenti nel database
     */
    public List<ShortUrl> findAll(){
        List<ShortUrl> urls = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()){
            DBObject dbObject = dbObjects.next();
            urls.add(new ShortUrl((BasicDBObject) dbObject));
        }
        return urls;
    }

    /**
     * Metodo per autogenerare un nuovo shortUrl non ancora presente nel database
     * @param id longUrl di cui si vuole creare lo shortUrl
     * @return shortUrl generato
     */
    public String autoGenerate(String id){
        String string = new String("http://shrinkYo.Url/");
        String shortUrl;
        RandomString randomString = new RandomString();
        do {
            shortUrl = randomString.randomString();
        } while (collection.findOne(new BasicDBObject("urlShort", shortUrl)) != null);
        string += shortUrl;
        return string;
    }

    /**
     * Metodo per la ricerca dei dati relativi ad uno shortUrl
     * @param id versione ridotta dell'url di cui si vuole ottenere informazioni
     * @return istanza del database relativa all'url ricercato
     */
    public ShortUrl find(String id){
        ResetString resetString = new ResetString();
        id = resetString.resetString(id);
        return new ShortUrl((BasicDBObject) collection.findOne(new BasicDBObject("urlShort", id)));
    }

    /**
     * Metodo per la connessione al database MongoDB
     * @return istanza del database
     * @throws Exception
     */
    private DB mongo() throws Exception {
        String host = System.getenv(Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER);
        if (host == null) {
            MongoClient mongoClient = new MongoClient(Constants.ADDRESS_MONGO);
            return mongoClient.getDB(Constants.NAME_DB);
        }

        int port = Integer.parseInt(Constants.PORT_DB);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(Constants.NAME_DB);
        return db;
    }

}

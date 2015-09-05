package entity;

import com.google.gson.annotations.SerializedName;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import utility.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe per l'entity shortUrl
 */
public class ShortUrl {
    @SerializedName("shortUrl")

    /**
     * id dello shortUrl
     */
    private String id;
    /**
     * versione ridotta dell'url
     */
    private String  urlShort;
    /**
     * versione estesa dell'url
     */
    private String urlLong;
    /**
     * array di continenti da cui è stato visitato lo shortUrl
     */
    private List<String> continentsClicks;
    /**
     * array di nazioni da cui è stato visitato lo shortUrl
     */
    private List<String> countriesClicks;
    /**
     * array di città da cui è stato visitato lo shortUrl
     */
    private List<String> citiesClicks;
    /**
     * data di creazione dello shortUrl
     */
    private Date createdOn = new Date();

    public ShortUrl(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.urlShort = dbObject.getString("urlShort");
        this.urlLong = dbObject.getString("urlLong");
        if(dbObject.get(Constants.CONTINENTS_FIELD) == null){

            continentsClicks = new ArrayList<String>();
            countriesClicks = new ArrayList<String>();
            citiesClicks = new ArrayList<String>();
        } else {
            continentsClicks = (List<String>) dbObject.get(Constants.CONTINENTS_FIELD);
            countriesClicks = (List<String>) dbObject.get(Constants.COUNTRIES_FIELD);
            citiesClicks = (List<String>) dbObject.get(Constants.CITIES_FIELD);
        }
        this.createdOn = dbObject.getDate("createdOn");
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setUrlShort(String url) {
        this.urlShort = url;
    }

    public void setUrlLong(String url) {
        this.urlLong = url;
    }

    public String getId(){
        return id;
    }

    public String getLongUrl() {
        return urlLong;
    }

    public String getShortUrl() {
        return urlShort;
    }

    public List<String> getContinents() {
        return continentsClicks;
    }

    public List<String> getCities() {
        return citiesClicks;
    }

    public List<String> getCountries() {
        return countriesClicks;
    }

}

package entity;

import com.google.gson.annotations.SerializedName;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 17/07/15.
 */
public class ShortUrl {
    @SerializedName("shortUrl")

    private String id;
    private String  urlShort;
    private String urlLong;
    private List<String> continentsClicks;
    private List<String> countriesClicks;
    private List<String> citiesClicks;

    public ShortUrl(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.urlShort = dbObject.getString("short");
        this.urlLong = dbObject.getString("long");
        continentsClicks = new ArrayList<String>();
        countriesClicks = new ArrayList<String>();
        citiesClicks = new ArrayList<String>();
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

package entity;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 17/07/15.
 */
public class ShortUrl {

    private String id;
    private String  shortUrl;
    private String longUrl;
    private List<String> continents;
    private List<String> countries;
    private List<String> cities;

    public ShortUrl(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.shortUrl = dbObject.getString("shortUrl");
        this.longUrl = dbObject.getString("longUrl");
    }


    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getId(){
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}

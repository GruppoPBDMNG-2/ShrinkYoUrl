import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import entity.ShortUrl;

/**
 * Created by Manu on 17/07/15.
 */
public class DAO {
    private static DAO dao = null;
    private final DB db;
    private final DBCollection collection;

    public DAO(DB db) {
        this.db = db;
        this.collection = db.getCollection("url");
    }



    public void addShortUrl(String body) {
        ShortUrl shortUrl = new Gson().fromJson(body, ShortUrl.class);

        collection.insert(new BasicDBObject("shortUrl", shortUrl.getShortUrl())
                .append("longUrl", shortUrl.getLongUrl()));




    }

    public Object findLongUrl(String params) {
        return null;
    }



}

import entity.ShortUrl;
import junit.framework.TestCase;

/**
 * Created by Roger on 01/08/2015.
 */
public class TestDAO extends TestCase {
    private DAO dao;

    private String[] shortUrls = {
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], \"urlLong\":\"facebook.com\", " +
                    "\"urlShort\": \"fb.com\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"http://facebook.com\", " + "\"urlShort\": \"http://fb.com\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"http://facebook.com/\", \"urlShort\": \"fb.com/\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"http://gm.com/\", \"urlShort\": \"https://mail.google.com/\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"http://yt.com/\", \"urlShort\": \"https://www.youtube.com//\"}",
    };

    private String[] toFind = {
            "fb.com",
            "fb.com",
            "fb.com"
    };

    protected void setUp() throws Exception {
        dao = new DAO();
        super.setUp();
    }
    protected void tearDown() throws Exception {
        dao = null;
        super.tearDown();
    }

    public void testCheckDAO() {
        for (int i = 0; i < shortUrls.length; i++) {
            dao.addShortUrl(shortUrls[i]);
            try{
                if(dao.find(toFind[i]) == null){

                }
            }catch (NullPointerException e){
                assertEquals("Test " + i + " failed", toFind[i], "Non aggiunta");
            }
        }
    }

}

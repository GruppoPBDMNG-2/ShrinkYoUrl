import entity.ShortUrl;
import junit.framework.TestCase;

/**
 * Created by Roger on 01/08/2015.
 */

/**
 * Test per verificare il corretto funzionamento della classe DAO.
 */
public class TestDAO extends TestCase {
    private DAO dao;

    private String[] shortUrls = {
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"http://facebook.com/\", \"urlShort\": \"fb.com\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"https://mail.google.com\", \"urlShort\": \"gm.com\"}",
            "{\"continentsClicks\":[], \"countriesClicks\":[], \"citiesClicks\":[], " +
                    "\"urlLong\":\"https://www.youtube.com\", \"urlShort\": \"yt.com\"}",
    };

    private String[] toFind = {
            "fb.com",
            "gm.com",
            "yt.com"
    };

    protected void setUp() throws Exception {
        dao = new DAO();
        super.setUp();
    }
    protected void tearDown() throws Exception {
        dao = null;
        super.tearDown();
    }

    /**
     * Metodo per testare sia il metodo addShortUrl, sia il metodo find
     */
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

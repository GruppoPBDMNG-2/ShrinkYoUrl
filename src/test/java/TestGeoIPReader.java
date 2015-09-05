import junit.framework.TestCase;
import utility.GeoIPReader;

/**
 * Created by Manu on 01/08/15.
 */

/**
 * Test per verificare il corretto funzionamento di GeoIPReader con vari indirizzi IP
 * provenienti da più parti del mondo
 */
public class TestGeoIPReader extends TestCase {
    private GeoIPReader geoIPReader;

    private String[] cases = {
            "79.41.29.243",
            "151.45.27.30",
            "154.117.192.1",
            "161.25.0.2",
            "202.65.32.4",
            "212.77.0.15",
            "31.29.0.89",
            "5.62.80.10",
            "183.182.96.210",
            "23.16.0.7",
    };

    private String[] resultsCont = {
            "Europe",
            "Europe",
            "Africa",
            "South America",
            "Oceania",
            "Europe",
            "Asia",
            "Europe",
            "Asia",
            "North America",
    };

    private String[] resultsCountry = {
            "Italy",
            "Italy",
            "Burundi",
            "Chile",
            "Cook Islands",
            "Vatican City",
            "Kyrgyzstan",
            "Isle of Man",
            "Laos",
            "Canada",
    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        geoIPReader = null;
        super.tearDown();
    }

    /**
     * metodo per testare il funzionamento di getContinent e getCountry
     */
    public void testGeoIPReader() {
        for (int i = 0; i < cases.length; i++){
            geoIPReader = new GeoIPReader(cases[i]);
            assertEquals("Test " + i + " on getContinent failed", resultsCont[i], geoIPReader.getContinent());
            assertEquals("Test " + i + " on getCountry failed", resultsCountry[i], geoIPReader.getCountry());
        }
    }
}

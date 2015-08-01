package utility;

/**
 * Created by Manu on 19/07/15.
 */
public class Constants {
    public static final String IP_ADDRESS_SPARK = "localhost";
    public static final int PORT_SPARK= 8080;

    public static final String ADDRESS_MONGO_CONNECTION = "192.168.99.100";
    public static final String ADDRESS_MONGO_CONNECTION_BOOT2DOCKER = "192.168.59.103";
    public static final String NAME_DB = "shortURL";
    public static final String PORT_DB = "27017";

    public static final String NAME_COLLECTION = "url";

    public static final String URL_SHORT_FIELD = "urlShort";
    public static final String URL_LONG_FIELD = "urlLong";
    public static final String CONTINENTS_FIELD = "continentsClicks";
    public static final String COUNTRIES_FIELD = "countriesClicks";
    public static final String CITIES_FIELD = "citiesClicks";
    public static final String CREATED_ON_FIELD = "createdOn";

    public static final int LIST_CLICKS_ALL = 0;
    public static final int LIST_CLICKS_EUROPE = 1;
    public static final int LIST_CLICKS_AMERICA = 2;
    public static final int LIST_CLICKS_ASIA = 3;
    public static final int LIST_CLICKS_AFRICA = 4;
    public static final int LIST_CLICKS_OCEANIA = 5;
}

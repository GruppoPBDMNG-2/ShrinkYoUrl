import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.IspResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by Manu on 15/07/15.
 */
public class GeoIPReader {
    public static void main (String[] args) throws IOException, GeoIp2Exception {
        String path = System.getProperty("user.dir") + "/geoip_db/GeoLite2-City.mmdb";
        File database = new File(path);

        DatabaseReader reader = new DatabaseReader.Builder(database).build();


        InetAddress ipAddress = InetAddress.getByName(getIpAddress());
        System.out.println(getIpAddress());
        CityResponse response = reader.city(ipAddress);


        System.out.println(response.getCity() + " " + response.getLocation());
        System.out.println(response.getCountry() + " " + response.getContinent());

    }

    public static String getIpAddress()
    {
        URL myIP;
        try {
            myIP = new URL("http://api.externalip.net/ip/");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myIP.openStream())
            );
            return in.readLine();
        } catch (Exception e)
        {
            try
            {
                myIP = new URL("http://myip.dnsomatic.com/");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(myIP.openStream())
                );
                return in.readLine();
            } catch (Exception e1)
            {
                try {
                    myIP = new URL("http://icanhazip.com/");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(myIP.openStream())
                    );
                    return in.readLine();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        return null;
    }
}

import utility.Constants;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Manu on 17/07/15.
 */
public class ServerConnection {


    public static void main(String[] args) throws Exception{
        setIpAddress(Constants.IP_ADDRESS_SPARK);
        setPort(Constants.PORT_SPARK);
        staticFileLocation("/public");
        DAO dao = new DAO();
        new Resource(dao);

    }



}

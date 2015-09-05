import utility.Constants;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Classe per eseguire il server
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

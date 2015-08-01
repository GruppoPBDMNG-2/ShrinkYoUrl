import utility.Constants;

/**
 * Created by Manu on 01/08/15.
 */
public class Docker {
    private static String ip_docker = Constants.ADDRESS_MONGO_CONNECTION;
    private static boolean firstAccess = true;

    public static boolean setIp_docker(String docker) {
        if (docker.equals("boot2docker")){
            ip_docker = Constants.ADDRESS_MONGO_CONNECTION_BOOT2DOCKER;
        } else {
            ip_docker = Constants.ADDRESS_MONGO_CONNECTION;
        }
        firstAccess = false;
        return firstAccess;
    }

    public static String getIp_docker() {
        return ip_docker;
    }

    public static boolean isFirstAccess() {
        return firstAccess;
    }
}

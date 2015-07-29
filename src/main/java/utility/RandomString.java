package utility;

import com.mongodb.BasicDBObject;

import java.util.Random;

/**
 * Created by Roger on 29/07/2015.
 */
public class RandomString {
    private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Random rnd = new Random();

    public RandomString(){

    }

    public String randomString(){
        StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}

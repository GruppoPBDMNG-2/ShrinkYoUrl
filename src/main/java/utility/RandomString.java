package utility;

import com.mongodb.BasicDBObject;

import java.util.Random;

/**
 * Classe per generare stringhe casuali
 */
public class RandomString {
    private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Random rnd = new Random();

    public RandomString(){

    }

    /**
     * Metodo per generare una stringa casuale
     * @return stringa generata
     */
    public String randomString(){
        StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}

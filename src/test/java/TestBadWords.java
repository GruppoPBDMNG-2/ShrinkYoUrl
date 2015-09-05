/**
 * Created by Manu on 01/08/15.
 */
import junit.framework.TestCase;
import utility.BadWords;

/**
 * Test per la classe che controlla se all'interno di una stringa c'è una badWord
 */
public class TestBadWords extends TestCase {

    private BadWords badWords;

    private String[] cases = {
            "word",
            "mangiarse", //badwords alla fine della stringa
            "paxxxo",
            "casses", //badwords alla fine della stringa
            "bombies",
            "banana",
            "car",
            "you_shall_not_p_a_s_s",
            "avada-kedavra",
            "look_that_b1tch_!!",
            "chrome",
            "steam",

    };

    private boolean[] results = {
            true,
            false,
            false,
            false,
            true,
            true,
            true,
            false,
            true,
            false,
            true,
            true,
    };

    @Override
    protected void setUp() throws Exception {
        badWords = new BadWords();
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        badWords = null;
        super.tearDown();
    }

    /**
     * metodo per testare il corretto funzionamento del metodo checkString all'interno della
     * classe badWords
     */
    public void testCheckString(){
        for(int i = 0; i < cases.length; i++){
            assertEquals("Test " + i + " failed", results[i], badWords.checkString(cases[i]));
        }
    }
}

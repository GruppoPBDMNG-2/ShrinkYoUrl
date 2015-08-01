/**
 * Created by Manu on 01/08/15.
 */
import junit.framework.TestCase;
import utility.BadWords;

public class TestBadWords extends TestCase {

    private BadWords badWords;

    private String[] cases = {
            "word",
            "mangiarse",
            "paxxxo",
            "casses",
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

    public void testCheckString(){
        for(int i = 0; i < cases.length; i++){
            assertEquals("Test " + i + " failed", results[i], badWords.checkString(cases[i]));
        }
    }
}

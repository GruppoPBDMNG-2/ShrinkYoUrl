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
    };

    private boolean[] results = {
            true,
            false,
    };

    protected void setUp() throws Exception {
        badWords = new BadWords();
        super.setUp();
    }
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

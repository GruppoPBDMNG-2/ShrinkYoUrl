import junit.framework.TestCase;
import utility.ResetString;

/**
 * Created by Roger on 01/08/2015.
 */

/**
 * Test per verificare il corretto funzionamento della classe ResetString
 */
public class TestResetString extends TestCase {

    private ResetString resetString;

    private String[] cases = {
            "*", // stringa formata da un solo *
            "**",
            "***", // stringa formata da soli *
            "*string",
            "string*",
            "*string*",
            "str*ing",
            "*str*ing*",
            "http:**google.it", // * al centro
            "*http:**google.it", // * all'inizio e al centro
            "*http:**google.it*", // * all'inizio al centro e alla fine
    };

    private String[] results = {
        "/",
        "//",
        "///",
        "/string",
        "string/",
        "/string/",
        "str/ing",
        "/str/ing/",
        "http://google.it",
        "/http://google.it",
        "/http://google.it/",
    };

    protected void setUp() throws Exception {
        resetString = new ResetString();
        super.setUp();
    }
    protected void tearDown() throws Exception {
        resetString = null;
        super.tearDown();
    }

    /**
     * Metodo per testare resetString
     */
    public void testCheckString() {
        for (int i = 0; i < cases.length; i++) {
            assertEquals("Test " + i + " failed", results[i], resetString.resetString(cases[i]));
        }
    }
}

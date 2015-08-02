import junit.framework.TestCase;
import utility.ResetString;

/**
 * Created by Roger on 01/08/2015.
 */
public class TestResetString extends TestCase {

    private ResetString resetString;

    private String[] cases = {
            "*",
            "**",
            "***",
            "*string",
            "string*",
            "*string*",
            "str*ing",
            "*str*ing*",
            "http:**google.it",
            "*http:**google.it",
            "*http:**google.it*",
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

    public void testCheckString() {
        for (int i = 0; i < cases.length; i++) {
            assertEquals("Test " + i + " failed", results[i], resetString.resetString(cases[i]));
        }
    }
}

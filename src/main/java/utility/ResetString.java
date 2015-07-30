package utility;

/**
 * Created by Roger on 30/07/2015.
 */
public class ResetString {
    public ResetString(){

    }
    public String resetString(String string){
        string = string.replace("*", "/");
        return string;
    }
}

package utility;

/**
 * Classe per sostituire il simbolo * all'interno di una stringa col simbolo /
 */
public class ResetString {
    public ResetString(){

    }

    /**
     * Metodo che sostituisce * con /
     * @param string stringa su cui effettuare l'operazione di sostituzione
     * @return stringa con * sostituiti con /
     */
    public String resetString(String string){
        string = string.replace("*", "/");
        return string;
    }
}

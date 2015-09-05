package utility;
import java.util.*;

/**
 * Classe per l'ordinamento di elementi contenunti in map
 */
public class MapUtil
{
    /**
     * Metodo che ordina gli elementi della map in ordine decresente
     * @param map map da ordinare
     * @param <K> tipo della chiave del map
     * @param <V> tipo del valore del map
     * @return map con elementi ordinati in ordine decrescente
     */
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }

        return result;
    }
}

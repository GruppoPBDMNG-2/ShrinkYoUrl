import com.sun.corba.se.spi.ior.ObjectKey;
import entity.ShortUrl;
import utility.MapUtil;

import java.util.*;

/**
 * Created by Manu on 28/07/15.
 */
public class Stats {

    public Map<ShortUrl, Integer> mostClicked(){
        DAO dao = new DAO();
        List<ShortUrl> urls = dao.findAll();

        LinkedHashMap<ShortUrl, Integer> map = new LinkedHashMap<ShortUrl, Integer>(urls.size());
        for(ShortUrl s: urls){
            if (s.getContinents().size() != 0){
                map.put(s, s.getContinents().size());
            }

        }
        Map mapSorted = MapUtil.sortByValue(map);

        return mapSorted;
    }

    public Map<ShortUrl, Integer> mostClicked(String cont){
        DAO dao = new DAO();
        List<ShortUrl> urls = dao.findAll();

        LinkedHashMap<ShortUrl, Integer> map = new LinkedHashMap<ShortUrl, Integer>(urls.size());
        for(ShortUrl s: urls){
            Integer count = 0;
            for (int i =0; i < s.getContinents().size(); i++){
                if (s.getContinents().get(i).equalsIgnoreCase(cont)){
                    count++;
                }
            }
            if (count != 0){
                map.put(s, count);
            }

        }
        Map mapSorted = MapUtil.sortByValue(map);

        //DA QUI prende i dati per stamparli
        Set list = mapSorted.keySet();
        Iterator iter = list.iterator();

        while (iter.hasNext()){
            Object key = iter.next();
            ShortUrl url = (ShortUrl) key;
            Integer count = (Integer) mapSorted.get(key);
            System.out.println(url.getShortUrl() + " count " + count);
        }
        //FINO A QUIAggiunteAggiun
        return mapSorted;
    }

    public List<ShortUrl> getShortUrlMostClicked(String cont){
        List<ShortUrl> urls = new ArrayList<>();

        Map mapSorted;
        if (cont.equals("all")){
            mapSorted = mostClicked();
        } else {
            mapSorted = mostClicked(cont);
        }

        Set list = mapSorted.keySet();
        Iterator iter = list.iterator();

        while (iter.hasNext()) {
            Object key = iter.next();
            ShortUrl url = (ShortUrl) key;
            urls.add(url);
        }
        return urls;
    }

    public List<Integer> getCountsMostClicked(String cont){
        List<Integer> counts = new ArrayList<>();
        Map mapSorted;
        if (cont.equals("")){
            mapSorted = mostClicked();
        } else {
            mapSorted = mostClicked(cont);
        }

        Set list = mapSorted.keySet();
        Iterator iter = list.iterator();

        while (iter.hasNext()) {
            Object key = iter.next();
            Integer count = (Integer) mapSorted.get(key);
            counts.add(count);
        }
        return counts;
    }

}

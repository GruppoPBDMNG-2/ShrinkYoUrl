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
            if (urls.size() <= 10){
                urls.add(url);
            } else {
                break;
            }

        }
        return urls;
    }

    public List<Integer> getCountsMostClicked(String cont){
        List<Integer> counts = new ArrayList<>();
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
            Integer count = (Integer) mapSorted.get(key);
            if (counts.size() <= 10){
                counts.add(count);
            } else {
                break;
            }

        }
        return counts;
    }

}

import com.sun.corba.se.spi.ior.ObjectKey;
import entity.ShortUrl;
import utility.MapUtil;

import java.util.*;

/**
 * Created by Manu on 28/07/15.
 */
public class Stats {
    private int numClickUrl;
    private int numClickEur;
    private int numClickAmer;
    private int numClickAfr;
    private int numClickOce;
    private int numClickAsia;



    private Map<ShortUrl, Integer> mostClicked(){
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

    private Map<ShortUrl, Integer> mostClicked(String cont){
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

    public List<ShortUrl> getShortUrlsMostClicked(String cont){
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

    public List<Integer> statsShortUrl(String shortUrl) {
        DAO dao = new DAO();
        ShortUrl url = dao.find(shortUrl);
        List<Integer> list = new ArrayList<>();

        //inizializzazione contatori
        numClickUrl = url.getContinents().size();
        numClickEur = 0;
        numClickAmer = 0;
        numClickAfr = 0;
        numClickOce = 0;
        numClickAsia = 0;

        for (int i = 0; i < url.getContinents().size(); i++) {
            if (url.getContinents().get(i).equals("Europe")) {
                numClickEur++;
            } else if (url.getContinents().get(i).equals("America")) {
                numClickAmer++;
            } else if (url.getContinents().get(i).equals("Asia")) {
                numClickAsia++;
            } else if (url.getContinents().get(i).equals("Africa")) {
                numClickAfr++;
            } else if (url.getContinents().get(i).equals("Oceania")) {
                numClickOce++;
            }
        }
        list.add(numClickUrl);
        list.add(numClickEur);
        list.add(numClickAmer);
        list.add(numClickAsia);
        list.add(numClickAfr);
        list.add(numClickOce);
        return list;
    }

}

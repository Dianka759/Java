import java.util.HashMap;
import java.util.Set;

public class FirstHashMap {
    public void trackList() {
        //  track titles =  keys, lyrics = values
        HashMap<String, String> trackList = new HashMap<String, String>();
        trackList.put("ASong", "These are totally lyrics to some song");
        trackList.put("Pretend", "Let's keep pretending this is a song");
        trackList.put("Na Na Na", "Na na-na na-na na-na na-na");
        trackList.put("Baby Shark", "doo doo doo doo doo doo");

        //pull out one song's lyrics
        System.out.println(trackList.get("ASong"));
        System.out.println("");


        //format Track: Lyrics
        Set<String> keys = trackList.keySet();
        for(String key: keys) {
            System.out.println(key + "; " + trackList.get(key));
        }
    }
}
package finalproject;
 
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class LastFM {
        protected JSONObject album = null;
        protected JSONObject url = null;
        protected JSONArray albumimages = null;
        protected String albumimgurl = null;
        protected String key = "074e34ca5e265fd19982de245af03154";
        protected String artist = null;
        protected String track = null;
        protected String artists_url = null;
        protected ArrayList<String> lastfmtags = new ArrayList<String>();
       
        public LastFM(String artist, String track){
                this.artist = artist.replaceAll(" ", "%20");
                this.track = track.replaceAll(" ", "%20");
        }
       
        public URL getAlbumArt() throws JSONException, IOException{
                /* Sample of grabbing album art assuming you have the artist and track name
                 *      if the artists name has spaces they need to be replaced with %20
                 *  ie
                 * 
                 *  "the glitch mob"
                 *  needs to be:
                 *  "the%20glitch%20mob"
                 *
                 */
                URL imgurl = null;
                String urlargs = new String();
                urlargs = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=" + key + "&artist=" + artist + "&track=" + track + "&autocorrect=1&format=json";
                URL btceq = new URL(urlargs);
                URLConnection uc = btceq.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                String result = new String();
                while (in.ready()) {
                        result += in.readLine();
                }
                in.close();
                JSONObject job = new JSONObject(result);
                JSONObject trackob = (JSONObject) job.get("track");
                album = (JSONObject) trackob.get("album");
                albumimages = (JSONArray) album.getJSONArray("image");
                //change the value of the of the "3" to get a bigger or smaller image, 1-5
                albumimgurl = ((JSONObject)albumimages.get(3)).getString("#text");
                Image image = null;
        try {
            imgurl = new URL(albumimgurl.toString());
            
        } catch (IOException e) {
                e.printStackTrace();
        }
        return imgurl;
        }
       
        //public getTop5Genres
        public void populateFields() throws IOException, JSONException{
                String urlargs = new String();
                urlargs = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=" + artist + "&api_key=" + key + "&format=json";
                URL apiurl = new URL(urlargs);
                URLConnection apiconn = apiurl.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(apiconn.getInputStream()));
                String result = new String();
                while (in.ready()) {
                        result += in.readLine();
                }
                in.close();
 
                JSONObject job = new JSONObject(result);
                //get the artists "tags" (not id3) according to lastfm
                for(int i = 0; i < 5; i++){
                        JSONObject tags = ((JSONObject) job.get("artist")).getJSONObject("tags");
                        JSONArray tag = tags.getJSONArray("tag");
                        lastfmtags.add(((JSONObject) tag.get(i)).getString("name"));
                }
                //get their lastfm profile url
                artists_url = ((JSONObject) job.get("artist")).getString("url");
        }
       
        public String getArtistURL()
        {
            return artists_url;
        }
       
        public ArrayList<String> getArtistTags()
        {
            return lastfmtags;
        }
 
}
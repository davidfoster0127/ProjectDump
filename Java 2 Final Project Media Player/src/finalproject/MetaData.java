package finalproject;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class MetaData 
{
	private final File myMp3;
	private final Mp3File mp3file;
	private LastFM lastFM;
	private final long duration;
	private String artist;
	private String artistURL;
	private String album;
	private String track;
	private String albumArtPath;
	private String title;
	private String[] genre;
        public String durationS;

	public MetaData(File f) throws UnsupportedTagException, InvalidDataException, IOException, JSONException
	{
		myMp3 = f;
		mp3file = new Mp3File(myMp3.getAbsolutePath());
		duration = mp3file.getLengthInSeconds();
                durationS = getFormattedLength(duration);
		if (mp3file.hasId3v2Tag()) 
		{
			artist = mp3file.getId3v2Tag().getArtist();
			album = mp3file.getId3v2Tag().getAlbum();
			track = mp3file.getId3v2Tag().getTrack();
			title = mp3file.getId3v2Tag().getTitle();
		}
		else if (mp3file.hasId3v1Tag())
		{
			artist = mp3file.getId3v1Tag().getArtist();
			album = mp3file.getId3v1Tag().getAlbum();
			track = mp3file.getId3v1Tag().getTrack();
			title = mp3file.getId3v1Tag().getTitle();
		}

		// If art is already in folder ignore to speed up process
		File[] fileList = myMp3.getParentFile().listFiles();
		boolean needArt = true;
	
		for (File k : fileList)
		{
			if (k.getName().equals("albumArt.png")) needArt = false;
                        File albumArt = new File(myMp3.getParent()+ "\\albumArt.png");
                        albumArtPath = albumArt.toURI().toString();
                        
		}

		if (needArt) 
		{
			lastFM = new LastFM(artist, title);
                        lastFM.populateFields();
                        BufferedImage bi = ImageIO.read(lastFM.getAlbumArt());
			File albumArt = new File(myMp3.getParent() + "\\albumArt.png");
			ImageIO.write(bi, "png", albumArt);
			albumArtPath = albumArt.toURI().toString();
			artistURL = lastFM.getArtistURL();
                       
		}
	}
	public File getFile(){
            return myMp3;
        }
	public String getArtist() {
		return artist;
	}

	public String getArtistURL()
	{
		return artistURL;
	}

	public String getAlbum() {
		return album;
	}

	public String getTrack() {
		return track;
	}

	public String getTitle() {
		return title;
	}

	public String[] getGenre() {
		return genre;
	}

	public String getAlbumArt() {
		return albumArtPath;
	}
        
	public static String getFormattedLength(long seconds)
	{
		DecimalFormat df = new DecimalFormat("#00");
		long sec = seconds % 60;
		long min = seconds / 60;
		return df.format(min) + ":" + df.format(sec);
	}

        @Override
	public String toString()
	{
		return myMp3.getAbsolutePath() + "," + getTitle() + "," + getArtist() + "," + getAlbum() + 
				"," + getFormattedLength(duration);
	}

}
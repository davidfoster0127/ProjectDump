/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package finalproject;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.json.JSONException;

public class PlayerGUIController implements Initializable {
    
    @FXML
    private VBox base;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuHelp;
    @FXML
    private static TableView<MetaData> songTable;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonPlayPause;
    @FXML
    private Button buttonSkip;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TableView<?> playlistTable;
    @FXML
    private TableColumn<?, ?> favCol;
    @FXML
    private TableColumn<MetaData,String> songCol;
    @FXML
    private TableColumn<MetaData,String> artistCol;
    @FXML
    private TableColumn<MetaData,String> albumCol;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtArtist;
    @FXML
    private TableColumn<?, ?> playlistCol;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Slider seeker;
    @FXML
    private Label labelTime;
    @FXML
    private MenuItem MIAddSong;
    @FXML
    private MenuItem MIAddFolder;
    @FXML
    private MenuItem MINewPlaylist;
    @FXML
    private MenuItem MIClose;
    @FXML
    private MenuItem MICopy;
    @FXML
    private MenuItem MIPaste;
    @FXML
    private MenuItem MICut;
    @FXML
    private MenuItem MIDelete;
    @FXML
    private Menu menuView;
    @FXML
    private MenuItem MIChangeView;
    @FXML
    private Menu menuPlayback;
    @FXML
    private MenuItem MIPlayPause;
    @FXML
    private MenuItem MISkip;
    @FXML
    private MenuItem MIBack;
    @FXML
    private CheckMenuItem MIShuffle;
    @FXML
    private CheckMenuItem MIRepeat;
    @FXML
    private MenuItem MIVolumeUp;
    @FXML
    private MenuItem MIVolumeDown;
    private File f2open;
    private long duration;
    private String sourceString;
    private Mp3File song;
    private Media media;
    private MediaPlayer mp;
    private Duration durationO;
    private boolean pp = false;
    @FXML
    private MenuItem MIAbout;
    @FXML
    private MenuItem CMNewPlaylist;
    @FXML
    private MenuItem CMDelete2;
    @FXML
    private MenuItem CMPlaySong;
    @FXML
    private Menu CMenuAddToPlayList;
    @FXML
    private MenuItem CMPlaylist1;
    @FXML
    private MenuItem CMPlaylist2;
    @FXML
    private MenuItem CMPlaylist3;
    @FXML
    private MenuItem CMFavorite;
    @FXML
    private MenuItem CMDelete;
    private boolean shuffle = false;
    private boolean repeat = false;
    private boolean muted = false;
    @FXML
    private Button buttonMute;
    @FXML
    private MenuItem MIMute;
    private static PrintWriter pw;
    private static long numSongs;
    static ObservableList<MetaData> data = FXCollections.observableArrayList();
    private static MetaData md;
    private static int selectedIndex;
    private static double volume = .5;
    @FXML
    private SplitPane midSplitPane;
    @FXML
    private AnchorPane leftAnchor;
    private Image image;
    @FXML
    private ImageView imageView;
    private double currentTime;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        SplitPane.setResizableWithParent(leftAnchor, Boolean.FALSE);
        
        playlistTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        songTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        songCol.setCellValueFactory(new PropertyValueFactory<MetaData,String>("title"));
        albumCol.setCellValueFactory(new PropertyValueFactory<MetaData,String>("album"));
        artistCol.setCellValueFactory(new PropertyValueFactory<MetaData,String>("artist"));
        
        
        
        songTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if(songTable.getSelectionModel().getSelectedItem() != null) {
                    if (mp!=null){
                        mp.pause();
                        
                        mp = null;
                    }
                    
                    md = (MetaData)newValue;
                    image = new Image(md.getAlbumArt());
                    imageView.setImage(image);
                    txtName.setText(md.getTitle());
                    txtArtist.setText(md.getArtist());
                    
                    
                    selectedIndex = data.indexOf(newValue);
                    f2open = md.getFile();
                    
                    sourceString = f2open.getAbsolutePath();
                    try {
                        song = new Mp3File(sourceString);
                    } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                        Logger.getLogger(PlayerGUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sourceString = f2open.toURI().toString();
                    media = new Media(sourceString);
                    duration = song.getLengthInSeconds();
                    seeker.setMax(duration);
                    currentTime=0;
                    seeker.setValue(currentTime);
                    labelTime.setText(MetaData.getFormattedLength((long)currentTime)+"/"+MetaData.getFormattedLength(duration));
                    mp = new MediaPlayer(media);
                    durationO = mp.getMedia().getDuration();
                    mp.setVolume(volume);
                    mp.play();
                    pp = true;
                    buttonPlayPause.setText("Pause");
                    MIPlayPause.setText("Pause");
                    
                    
                    
                }
            }
        });
        
        
        
        
        
    }
    
    @FXML
    private void back(ActionEvent event) {
        if (mp!=null){
            mp.pause();
            
            mp = null;
        }
        if (selectedIndex == 0){
            selectedIndex = data.size()-1;
        }
        else selectedIndex-=1;
        md = data.get(selectedIndex);
        image = new Image(md.getAlbumArt());
        imageView.setImage(image);
        txtName.setText(md.getTitle());
        txtArtist.setText(md.getArtist());
        f2open = md.getFile();
        sourceString = f2open.getAbsolutePath();
        try {
            song = new Mp3File(sourceString);
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            Logger.getLogger(PlayerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sourceString = f2open.toURI().toString();
        media = new Media(sourceString);
        duration = song.getLengthInSeconds();
        seeker.setMax(duration);
        currentTime=0;
        seeker.setValue(currentTime);
        
        
        labelTime.setText(MetaData.getFormattedLength((long)currentTime)+"/"+MetaData.getFormattedLength(duration));       
        mp = new MediaPlayer(media);
        durationO = mp.getMedia().getDuration();
        mp.setVolume(volume);
        mp.play();
        pp = true;
        buttonPlayPause.setText("Pause");
        MIPlayPause.setText("Pause");
    }
    
    @FXML
    private void playPause(ActionEvent event) {
        if (pp){
            mp.pause();
            pp = false;
            buttonPlayPause.setText("Play");
            MIPlayPause.setText("Play");
        }
        else if (!pp){
            mp.play();
            pp = true;
            buttonPlayPause.setText("Pause");
            MIPlayPause.setText("Pause");
            
        }
        
    }
    
    @FXML
    private void skip(ActionEvent event) {
        if (mp!=null){
            mp.pause();
            
            mp = null;
        }
        if (selectedIndex == data.size()-1){
            selectedIndex = 0;
        }
        else selectedIndex+=1;
        md = data.get(selectedIndex);
        image = new Image(md.getAlbumArt());
        imageView.setImage(image);
        txtName.setText(md.getTitle());
        txtArtist.setText(md.getArtist());
        f2open = md.getFile();
        sourceString = f2open.getAbsolutePath();
        try {
            song = new Mp3File(sourceString);
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            Logger.getLogger(PlayerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sourceString = f2open.toURI().toString();
        media = new Media(sourceString);
        duration = song.getLengthInSeconds();
        seeker.setMax(duration);
        currentTime=0;
        seeker.setValue(currentTime);
        
        
        labelTime.setText(MetaData.getFormattedLength((long)currentTime)+"/"+MetaData.getFormattedLength(duration));       
        mp = new MediaPlayer(media);
        durationO = mp.getMedia().getDuration();
        mp.setVolume(volume);
        mp.play();
        pp = true;
        buttonPlayPause.setText("Pause");
        MIPlayPause.setText("Pause");
    }
    
    @FXML
    private void changeVolumeDrag(MouseEvent event) {
        mp.setVolume(volumeSlider.getValue());
        volume = volumeSlider.getValue();
        
    }
    
    @FXML
    private void changeVolumeClick(MouseEvent event) {
        mp.setVolume(volumeSlider.getValue());
        volume = volumeSlider.getValue();
    }
    
    @FXML
    private void seekDrag(MouseEvent event) {
        mp.setMute(true);
        currentTime = seeker.getValue();
       labelTime.setText(MetaData.getFormattedLength((long)currentTime)+"/"+MetaData.getFormattedLength(duration));
        mp.seek(Duration.seconds(seeker.getValue()));
        mp.setMute(false);
    }
    
    @FXML
    private void seekClick(MouseEvent event) {
        currentTime = seeker.getValue();
        
       labelTime.setText(MetaData.getFormattedLength((long)currentTime)+"/"+MetaData.getFormattedLength(duration));
        mp.seek(Duration.seconds(seeker.getValue()));
        
    }
    
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void newPlaylist(ActionEvent event) {
        //create a new playlist object
    }
    
    @FXML
    private void copy(ActionEvent event) {
        //dunno what it can be used for but seems standard
    }
    
    @FXML
    private void paste(ActionEvent event) {
        //see above
    }
    
    @FXML
    private void cut(ActionEvent event) {
        //see above above
    }
    
    @FXML
    private void delete(ActionEvent event) {
        data.remove(selectedIndex);
    }
    
    @FXML
    private void changeViews(ActionEvent event) {
        //i doubt we are gonna implement this at this point but its here if we wanted to do video
    }
    
    @FXML
    private void shuffleToggle(ActionEvent event) {
        if (shuffle){
            shuffle = false;
        }
        else if (!shuffle){
            shuffle = true;
        }
    }
    
    @FXML
    private void repeatToggle(ActionEvent event) {
        if (repeat){
            repeat = false;
        }
        else if (!repeat){
            repeat = true;
        }
    }
    
    @FXML
    private void volumeUpInc(ActionEvent event) {
        if (mp.getVolume()<.9){
            mp.setVolume(mp.getVolume()+.1);
            volume +=.1;
            volumeSlider.setValue(volume);
        }
        
    }
    
    @FXML
    private void volumeDownInc(ActionEvent event) {
        if (mp.getVolume()>.1){
            mp.setVolume(mp.getVolume()-.1);
            volume -=.1;
            volumeSlider.setValue(volume);
        }
    }
    
    @FXML
    private void about(ActionEvent event) {
       
        // maybe bring up a message dialouge about the program and who we are etc.
    }
    
    @FXML
    private void addToPlaylist(ActionEvent event) {
        //get the selected playlist here and add the song to it
    }
    
    @FXML
    private void addToFavorite(ActionEvent event) {
        //add the song to the favorites playlist
    }
    
    
    @FXML
    private void chooseFolder(ActionEvent event) throws FileNotFoundException, IOException, UnsupportedTagException, InvalidDataException, JSONException {
        
        
        File openFile = null;
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Choose a folder to search through for mp3 files");
        try
        {
            
            openFile = dc.showDialog(null);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Input File Not Selected");
            return;
        }
        
        // Check number of songs against our MetaData to see if need to update our library
        getNumSongs(openFile);
        //System.out.println("" + numSongs);
        
        File checkFile = new File(openFile.getParentFile() +
                "\\MetaData.jj");
        
        if (checkFile.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(openFile.getParentFile() +
                    "\\MetaData.jj"));
            
            String line = "";
            line = br.readLine();
            
            // if there is a num of songs
            if (line != null)
            {
                long numMp3OnFile = Long.parseLong(line);
                
                if (numSongs > numMp3OnFile)
                {
                    System.out.println("Need Load");
                    br.close();
                    loadMedia(openFile);
                    br = new BufferedReader(new FileReader(openFile.getParentFile() +
                            "\\MetaData.jj"));
                }
                
                // if there were mp3 files found create Array for use
                if (numSongs > 0)
                {
                    line = "";
                    String[] strArr;
                    while((line = br.readLine()) != null)
                    {
                        strArr = line.split(",");
                        //colSongName = strArr[0];
                        //colArtist = strArr[1];
                    }
                }
                else JOptionPane.showMessageDialog(null, "No MP3 found");
            }
        }
        
        // if MetaData does not exist create MetaData and load media into a library
        else
        {
            //System.out.println("Media Loading..");
            loadMedia(openFile);
        }
        //System.out.println("Media Loaded");
        
        
    }
    
    public static void loadMedia(File f) throws IOException, UnsupportedTagException, InvalidDataException, JSONException
    {
        pw = new PrintWriter(new FileWriter(f.getParentFile() + "\\MetaData.jj"));
        
        // keep track of number of files in our library for later updating
        pw.println("" + numSongs);
        
        if (f.isDirectory())
        {
            File[] fileList = f.listFiles();
            
            for (File k : fileList)
            {
                getSongs(k);
            }
        }
        else getSongs(f);
        pw.close();
    }
    
    public static void getSongs(File f) throws IOException, UnsupportedTagException, InvalidDataException, JSONException
    {
        
        if (f.isDirectory())
        {
            for (File k : f.listFiles())
            {
                getSongs(k);
            }
        }
        else
        {
            if (f.getName().endsWith("mp3"))
            {
                md = new MetaData(f);
                
                pw.println(md.toString());
            }
        }
    }
    
    public static void getNumSongs(File f) throws UnsupportedTagException, InvalidDataException, IOException, JSONException
    {
        
        if (f.isDirectory())
        {
            for (File k : f.listFiles())
            {
                getNumSongs(k);
            }
        }
        else
        {
            if (f.getName().endsWith("mp3"))
            {
                md = new MetaData(f);
                data.add(md);
                songTable.setItems(data);
                numSongs++;
                
            }
        }
    }
    
    @FXML
    private void mute(ActionEvent event) {
        if (!muted){
            mp.setMute(!muted);
            muted = true;
        }
        else if (muted){
            mp.setMute(!muted);
            muted = false;
        }
    }
    
    @FXML
    private void choosePlaylist(MouseEvent event) {
        //select a playlist from the table to display in the songs table
    }
    
    @FXML
    private void newSong(ActionEvent event) throws JSONException {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        fc.setTitle("Choose a song to add to your library");
        fc.getExtensionFilters().add(filter);
        try {
            f2open = fc.showOpenDialog(null);
            data.add(new MetaData(f2open));
            songTable.setItems(data);
            
        }
        catch (IOException | UnsupportedTagException | InvalidDataException e){
            
        }
        
    }
    
    
    
    
    
}

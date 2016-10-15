/*
 *  TCSS 305 - Assignment 3: SnapShop
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * Creates a GUI environment used for displaying a selected image and
 * applying filters to said image.
 * 
 * @author David Foster
 * @version Autumn 2015
 */
public class SnapShopGUI extends JFrame {
    /**
     * A generalized serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -2144739668936849562L;
    /**
     * Center JPanel where image will be shown.
     */
    private final JPanel myCenterPanel = new JPanel();
    /**
     * Bottom JPanel where open, save, close image buttons are.
     */
    private final JPanel myBottomPanel = new JPanel();
    /**
     * Top JPanel where the filter buttons are located.
     */
    private final JPanel myTopPanel = new JPanel();
    /**
     * Button for opening files for filtering.
     */
    private JButton myOpenB;
    /**
     * Button for saving filtered images.
     */
    private JButton mySaveAsB;
    /**
     * Button that closes the image being modified.
     */
    private JButton myCloseImageB;
    /**
     * JFileChooser used for opening sand saving files.
     */
    private final JFileChooser myFC = new JFileChooser();
    /**
     * Label used for displaying image as an icon.
     */
    private final JLabel myLabel = new JLabel();
    /**
     * ArrayList that contains all buttons used for filtering images.
     */
    private final List<JButton> myButtons = new ArrayList<>();
    /**
     * PixelImage named here for class visibility and access.
     */
    private PixelImage myPImage;
    
    
    /**
     * Calls JFrame constructor.
     */
    public SnapShopGUI() {
        super("TCSS 305 SnapShot");
    }
    
    /**
     * Sets up JFrame and all components inside it.
     */
    public void start() { 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        add(myCenterPanel, BorderLayout.CENTER);
        add(myTopPanel, BorderLayout.NORTH);
        add(myBottomPanel, BorderLayout.SOUTH);
        makeButtons();
        setComponents();
        setActionListeners();
        pack();
        setVisible(true);
    }
    /**
     * Helper method to instantiate buttons to be added to the window.
     */
    private void makeButtons() {
        final List<Filter> filterButtons = new ArrayList<>();
        filterButtons.add(new EdgeDetectFilter());
        filterButtons.add(new EdgeHighlightFilter());
        filterButtons.add(new FlipHorizontalFilter());
        filterButtons.add(new FlipVerticalFilter());
        filterButtons.add(new GrayscaleFilter());
        filterButtons.add(new SharpenFilter());
        filterButtons.add(new SoftenFilter());


        for (final Filter filter : filterButtons) {
            myButtons.add(createButton(filter));
        }

        for (int i = 0; i < myButtons.size(); i++) {
            myTopPanel.add(myButtons.get(i));
            myButtons.get(i).setEnabled(false);
        }
        myOpenB = new JButton("Open...");
        mySaveAsB = new JButton("Save As...");
        myCloseImageB = new JButton("Close Image");
    }
    /**
     * Helper class that creates buttons with filters associated with them.
     * @param theFilter filter object used to name and make the buttons actionlistener.
     * @return button with an actionlistener associated with a filter object.
     */
    private JButton createButton(final Filter theFilter) {
        final JButton button = new JButton(theFilter.getDescription());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFilter.filter(myPImage);
                myLabel.setIcon(new ImageIcon(myPImage));
            }
        });

        return button;
    }

    /**
     * Helper method for putting components where they need to go and 
     * setting their initial states.
     */
    private void setComponents() {
        //bottom panel buttons
        myBottomPanel.add(myOpenB);
        myBottomPanel.add(mySaveAsB);
        mySaveAsB.setEnabled(false);
        myBottomPanel.add(myCloseImageB);
        myCloseImageB.setEnabled(false);
        
        //center panel components
        myCenterPanel.add(myLabel);
        
    }
    /**
     * Helper method for setting buttons action listeners.
     */
    private void setActionListeners() {
        myOpenB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final int result = myFC.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File imageFile = myFC.getSelectedFile();
                    try {
                        setMinimumSize(null);
                        myPImage = PixelImage.load(imageFile);
                        myFC.setCurrentDirectory(imageFile);
                        myLabel.setIcon(new ImageIcon(myPImage));
                        enableButtons();
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(null, "This is not a valid file!"); 
                    }
                    pack();
                    setMinimumSize(getSize());
                } else if (result == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(null, "An Error has occured trying "
                                    + "to open that file!");
                }
            }
        });    
        mySaveAsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final int result = myFC.showSaveDialog(null);    
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File saveFile = myFC.getSelectedFile();
                    try {
                        myPImage.save(saveFile);
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        myCloseImageB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myLabel.setIcon(null);
                setMinimumSize(null);
                pack();
                disableButtons();
            }
        });        
    }
    /**
     * Helper method for enabling all buttons easily.
     */
    private void enableButtons() {
        for (int i = 0; i < myButtons.size(); i++) {
            myButtons.get(i).setEnabled(true);
        }
        mySaveAsB.setEnabled(true);
        myCloseImageB.setEnabled(true);
        
    }
    /**
     * Helper method for disabling all buttons except open easily.
     */
    private void disableButtons() {
        for (int i = 0; i < myButtons.size(); i++) {
            myButtons.get(i).setEnabled(false);
        }
        mySaveAsB.setEnabled(false);
        myCloseImageB.setEnabled(false);
    }

}


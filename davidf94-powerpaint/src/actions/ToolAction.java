/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package actions;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import tools.Tool;
/**
 * 
 * @author David
 * @version Autumn 2015
 */
public class ToolAction extends AbstractAction {

    /** A generated serial number. */
    private static final long serialVersionUID = -492689897412272999L;

    /** Error message used when an Icon is NOT found. */
    private static final String ERROR_MESSAGE = "Resource not found: ";

    /** Tool used for the action. */
    private final Tool myTool;



    /** 
     * Constructor for a Tool action.
     * @param theTool Tool used to create the action.
     */
    public ToolAction(final Tool theTool) {
        super(theTool.toString());
        myTool = theTool;
        putValue(SHORT_DESCRIPTION, myTool.toString());

        setIcon();
        setMnemonics();
    }

    /**
     * Helper method to set up Mnemonic and Accelerator. 
     */
    private void setMnemonics() {
        //to set the mnemonic in an Action, we need to send the VK key code. 
        //here is a way to get the VK key code if you just have a char.
        final int keyCode = KeyStroke.getKeyStroke(myTool.whichTool(), 0).getKeyCode();
        putValue(MNEMONIC_KEY, keyCode);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character(myTool.whichTool()), 
                                                         ActionEvent.ALT_MASK));

                 
    }

    /**
     * Helper method to set up the Image Icon. 
     */
    private void setIcon() {
        final String imgLocation = "images/" 
                        + myTool.toString().toLowerCase()
                        + ".gif";

        //When using ToolBarExample.class.getResource, runtime 
        //is expecting the images to be in the same location as the 
        //.class file for THIS class. In an eclipse project, that is
        //bin/*package folder*
        final URL imageURL = ToolAction.class.getResource(imgLocation);

        if (Objects.nonNull(imageURL)) {                      
            //image found
            putValue(SMALL_ICON, new ImageIcon(imgLocation, myTool.toString()));
        } else {                                     
            //no image found
            System.err.println(ERROR_MESSAGE + imgLocation);
        }       
    }

    /** Specified whenever a tool action is created. */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
    }


}

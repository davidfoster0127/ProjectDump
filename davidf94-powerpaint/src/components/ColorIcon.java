/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * A custom Icon that can have its color changed.
 * @author David
 * @version Autumn 2015
 */
public class ColorIcon implements Icon {

    /** Default height for the icon. */
    public static final int DEFAULT_HEIGHT = 10;
    
    /** Default width for the icon. */
    public static final int DEFAULT_WIDTH = 10;
    
    /** Default position x for the icon within the component. */
    public static final int ICON_X_POS = 5;
    
    /** Default position y for the icon within the component. */
    public static final int ICON_Y_POS = 5;
    
    /** The icon height. */
    private final int myHeight;
    
    /** The icon width. */
    private final int myWidth;
    
    /** The icon color. */
    private Color myColor;

    
    /**
     * Constructor for a ColorIcon object.
     * @param theColor Color of the icon.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
        myHeight = DEFAULT_HEIGHT;
        myWidth = DEFAULT_WIDTH;
        
        
    }
    
    /**
     * Method to set the color of the icon.
     * @param theColor Color to fill in the icon.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    @Override
    public int getIconHeight() {
        return myHeight;
    }

    @Override
    public int getIconWidth() {
        return myWidth;
    }

    @Override
    public void paintIcon(final Component theC, final Graphics theG, 
                          final int theX, final int theY) {
        
        theG.setColor(myColor);
        theG.fillRect(ICON_X_POS, ICON_Y_POS, myWidth, myHeight);

    }

}

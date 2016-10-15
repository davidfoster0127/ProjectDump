/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */

package gui;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
/**
 * Class for slider with property change listener.
 * @author David
 * @version Autumn 2015
 */
public class MyTSlider extends JSlider {

    /** */
    private static final long serialVersionUID = 3812205138698531381L;

    /** */
    private static final int MAX_VALUE = 20;
    
    /** */
    private static final int MIN_VALUE = 0;
    
    /** */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /** */
    private static final int MINOR_TICK_SPACING = 1;
    
    /** */
    private static final int INITIAL_VALUE = 1;
    
    /** */
    private int myValue = INITIAL_VALUE;
    
    /**
     * Constructor for a MyTSlider.
     * @param theChanger the changelistener to apply to this slider.
     */
    public MyTSlider(final ChangeListener theChanger) {
        super(MIN_VALUE, MAX_VALUE, INITIAL_VALUE);
        setMajorTickSpacing(MAJOR_TICK_SPACING);
        setMinorTickSpacing(MINOR_TICK_SPACING);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(theChanger);
    }

    /**
     * Getter for slider value.
     * @return myValue
     */
    public int getMyValue() {
        return myValue;
    }

    /**
     * Setter for my value.
     * @param theValue value to set slider to.
     */
    public void setMyValue(final int theValue) {
        myValue = theValue;
    }

}

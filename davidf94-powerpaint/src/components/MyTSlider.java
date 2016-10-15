/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package components;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
/**
 * Class for slider with property change listener.
 * @author David
 * @version Autumn 2015
 */
public class MyTSlider extends JSlider {

    /** A generated serial ID. */
    private static final long serialVersionUID = 3812205138698531381L;

    /** Max value for the slider. */
    private static final int MAX_VALUE = 20;
    
    /** Min value for the slider. */
    private static final int MIN_VALUE = 0;
    
    /** Major tick spacing for the slider. */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /** Minor tick spacing for the slider. */
    private static final int MINOR_TICK_SPACING = 1;
    
    /** Initial value of the slider. */
    private static final int INITIAL_VALUE = 1;
    
    /** Value of the slider. */
    private int myValue = INITIAL_VALUE;
    
    /**
     * Constructor for a custom slider with a change listener attached.
     * @param theChanger the change listener that notifies the drawing 
     * panel when value is changed.
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
     * @param theValue Value to set slider to.
     */
    public void setMyValue(final int theValue) {
        myValue = theValue;
    }

}

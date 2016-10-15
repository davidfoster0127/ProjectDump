/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;

import shapes.DrawableShape;
import shapes.MyEllipse;

/**
 * Tool class used to draw ellipses.
 * @author David
 * @version Autumn 2015
 */
public class EllipseTool extends AbstractTool implements Tool {

    /**
     * Constructor for the EllipseTool.
     * @param theStrokeWidth Stroke width for the tool.
     * @param theColor Color for the tool.
     */
    public EllipseTool(final int theStrokeWidth, final Color theColor) {
        super(theStrokeWidth, theColor);
    }

    @Override
    public DrawableShape createShape(final int theInX, final int theInY, 
                                     final int theEndX, final int theEndY) {
        
        int startX = theInX;
        int startY = theInY;
        int width = theEndX - theInX;
        int height = theEndY - theInY;
        //handles situation where end point is negative of the start
        if (startX > theEndX) {
            width = startX - theEndX;
            startX = theEndX;
        }
        if (startY > theEndY) {
            height = startY - theEndY;
            startY = theEndY;
        }

        final MyEllipse ellipse = new MyEllipse(startX, startY, 
                                                      width, height);
        return new DrawableShape(ellipse, getMyColor(), getMyStrokeWidth());
    }

    @Override
    public char whichTool() {
        return 'e';
    }

    @Override
    public String toString() {
        return "Ellipse";
    }
    
}

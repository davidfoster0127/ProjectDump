/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import shapes.DrawableShape;
import shapes.MyPath;

/**
 * Tool class used to draw paths.
 * @author David
 * @version Autumn 2015
 */
public class PencilTool extends AbstractTool implements Tool {

    /** MyPath used to store whole path. */
    private MyPath myPath = new MyPath(0, 0);
    
    /** A DrawableShape that stored the MyPath as it builds. */
    private DrawableShape myShape = new DrawableShape(myPath, 
                                                      getMyColor(), getMyStrokeWidth());
    
    /** A list of points used to store points along the MyPath. */
    private final List<Point> myPoints = new ArrayList<Point>();
    
    /**
     * Constructor for the PencilTool.
     * @param theStrokeWidth Stroke width for the tool.
     * @param theColor Color for the tool.
     */
    public PencilTool(final int theStrokeWidth, final Color theColor) {
        super(theStrokeWidth, theColor);
        myPoints.add(new Point(0, 0));
    }

    @Override
    public DrawableShape createShape(final int theInX, final int theInY, 
                                     final int theEndX, final int theEndY) {
        
        //Test to determine if the path being drawn is a new path or an addition
        //to the existing path. Points are stored in the myPoints list and then
        //a new path is made from all points in the list. If the initial points
        //in the list do not match the initial values brought in by the method,
        //then the list is cleared a new initial point is set for the path.
        if (myPoints.get(0).getX() == theInX && myPoints.get(0).getY() == theInY) {
            myPoints.add(new Point(theEndX, theEndY));
            myPath = new MyPath(theInY, theInY);
            myPath.moveTo(theInX, theInY);
            for (final Point p : myPoints) {
                myPath.lineTo(p.getX(), p.getY());
            }
            myShape =  new DrawableShape(myPath, getMyColor(), getMyStrokeWidth());
        } else {
            myPoints.clear();
            myPoints.add(new Point(theInX, theInY));
        }
        return myShape;
    }
    
    @Override
    public char whichTool() {
        return 'p';
    }

    @Override
    public String toString() {
        return "Pencil";
    }
}

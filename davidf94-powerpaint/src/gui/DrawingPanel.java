/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import shapes.DrawableShape;
import shapes.MyLine;
import tools.Tool;
/**
 * A drawing panel to draw shapes in.
 * @author David
 * @version Autumn 2015
 */
public class DrawingPanel extends JPanel implements ComponentListener {

    /** A generated serial ID. */
    private static final long serialVersionUID = 2828915839475207700L;

    /** Preferred dimension for the drawing panel. */
    private static final Dimension PREF_DIMENSION = new Dimension(400, 200);

    /** Default grid spacing. */
    private static final int GRID_SPACING = 10;

    /** A list of all drawable shapes to be drawn on the drawing panel. */
    private final List<DrawableShape> myShapes;

    /** A list that contains lines that for a grid. */
    private final List<MyLine> myGrid = new ArrayList<MyLine>();

    /** Used to determine if grid is on so the grid will persist when the jframe is resized.*/
    private boolean myGridOn;

    /** Initial x value of the shape. */
    private int myInX;

    /** Initial y value of the shape. */
    private int myInY;

    /** Ending x value of the shape. */
    private int myEndX;

    /** Ending y value of the shape. */
    private int myEndY;

    /** Current x position of the mouse when dragged. */
    private int myCurrentX;

    /** Current y position of the mouse when dragged.*/
    private int myCurrentY;

    /** DrawableShape used to store the shape currently being drawn. */
    private DrawableShape myCurrentShape = new DrawableShape(new MyLine(0, 0, 0, 0), 
                                                             Color.black, 1);
    /** The tool currently used for drawing. */
    private Tool myCurrentTool;

    /** Instance of the JFrame the panel is inside. */
    private final PowerPaintGUI myGUI;


    /**
     * Constructor for a DrawingPanel used for drawing shapes.
     * @param theGUI theGUI the drawing panel is in.
     */
    public DrawingPanel(final PowerPaintGUI theGUI) {
        super();
        setOpaque(true);
        this.addMouseListener(new MyMouseAdapter());
        this.addMouseMotionListener(new MyMouseAdapter());
        setPreferredSize(PREF_DIMENSION);
        setBackground(Color.white);
        addComponentListener(this);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        myShapes = new ArrayList<DrawableShape>();
        myGUI = theGUI;

    }



    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (final DrawableShape shape : myShapes) {
            g2d.setPaint(shape.getMyColor());
            g2d.setStroke(new BasicStroke(shape.getMyStrokeWidth()));
            g2d.draw(shape.getMyShape());
        }

        for (final MyLine line : myGrid) {
            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(line);
        }

        g2d.setPaint(myCurrentShape.getMyColor());
        g2d.setStroke(new BasicStroke(myCurrentShape.getMyStrokeWidth()));
        g2d.draw(myCurrentShape.getMyShape());
    }

    /**
     * Getter for the current tool.
     * @return the myTool
     */
    public Tool getCurrentTool() {
        return myCurrentTool;
    }

    /**
     * Setter for the current tool. Used by actions in the GUI.
     * @param theTool the tool to set as your current one
     */
    public void setCurrentTool(final Tool theTool) {
        myCurrentTool = theTool;
    }

    /**
     * Method for creating a grid via an arraylist of drawn lines that is either empty,
     * or filled with MyLines.
     * @param theState True if checked, false if not.
     */
    public void setGrid(final boolean theState) {
        myGridOn = theState;
        if (theState) {
            myGrid.clear();
            for (int i = 0; i < getHeight(); i += GRID_SPACING) {
                myGrid.add(new MyLine(0, i, getWidth(), i));
            }
            for (int i = 0; i < getWidth(); i += GRID_SPACING) {
                myGrid.add(new MyLine(i, 0, i, getHeight()));
            }
        } else {
            myGrid.clear();
        }

        repaint();
    }

    /** 
     * Method for removing all drawn shapes from the drawing panel.
     */
    public void clearShapes() {
        myShapes.clear();
        myCurrentShape = new DrawableShape(new MyLine(0, 0, 0, 0), 
                                           Color.black, 1);
        repaint();
    }
    
    @Override
    public void componentHidden(final ComponentEvent theEvent) {
    }

    @Override
    public void componentMoved(final ComponentEvent theEvent) {
    }

    @Override
    public void componentResized(final ComponentEvent theEvent) {
        setGrid(myGridOn);
    }

    @Override
    public void componentShown(final ComponentEvent theEvent) {
    }


    /**
     * Mouse input adapter for listening for mouse events on the drawing panel.
     * Basis of drawing shapes with the mouse.
     * @author David
     * @version Autumn 2015
     */
    class MyMouseAdapter extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myInX = theEvent.getX();
            myInY = theEvent.getY();
        }

        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myEndX = theEvent.getX();
            myEndY = theEvent.getY();
            if (myCurrentTool.getMyStrokeWidth() != 0) {
                myShapes.add(myCurrentTool.createShape(myInX, myInY, 
                                                       myEndX, myEndY));
                myGUI.getMyUndoAll().setEnabled(true);
            }
            repaint();
            myInX = 0;
            myInY = 0;
            myEndX = 0;
            myEndY = 0;
            myCurrentX = 0;
            myCurrentY = 0;
        }
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentX = theEvent.getX();
            myCurrentY = theEvent.getY();
            if (myCurrentTool.getMyStrokeWidth() != 0) {
                myCurrentShape = myCurrentTool.createShape(myInX, myInY, 
                                                           myCurrentX, myCurrentY);
            }
            repaint();
        }


    }

}



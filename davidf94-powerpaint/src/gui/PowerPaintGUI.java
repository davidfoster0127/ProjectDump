/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package gui;

import actions.ToolAction;

import components.ColorIcon;
import components.MyTSlider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.Tool;



/**
 * Class used to contain a drawing panel and control all the components included.
 * @author David
 * @version Autumn 2015
 */
public class PowerPaintGUI extends JFrame implements ChangeListener {

    /** Default stroke width of the tools. */
    private static final int DEFAULT_STROKE_WIDTH = 1;

    /** Default color of the colors. */
    private static final Color DEFAULT_COLOR = Color.black;

    /** A generated serial ID. */
    private static final long serialVersionUID = -6870003831272220140L;

    /** Drawing panel contained in the GUI. */
    private DrawingPanel myDP;

    /** Action associated with a pencil tool. */
    private PencilToolAction myPTA;

    /** Action associated with a line tool. */
    private LineToolAction myLTA;

    /** Action associated with a rectangle tool. */
    private RectangleToolAction myRTA;

    /** Action associated with a ellipse tool. */
    private EllipseToolAction myETA;

    /** Pencil tool used for drawing paths. */
    private Tool myPencilTool;

    /** Line tool used for drawing lines. */
    private Tool myLineTool;

    /** Rectangle tool used for drawing rectangles. */
    private Tool myRectangleTool;

    /** Ellipse tool used for drawing ellipse. */
    private Tool myEllipseTool;
    
    /** Undo all menu item. Declared here to give class access so the drawing panel 
     * could notify when there are shapes drawn in the space.*/
    private final JMenuItem myUndoAll = new JMenuItem(new UndoAllAction());
    





    /** Constructor for a PowerPaintGUI. */
    public PowerPaintGUI() {
        super("PowerPaint");
    }

    /** Method used to set all components in the GUI. */
    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("images/ppicon.png").getImage());
        setUpToolsAndActions();
        myDP = new DrawingPanel(this);
        myDP.setCurrentTool(myPencilTool);
        add(myDP, BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    /**
     * Method to return the menu item so that drawing panel can access it and update it
     * when shapes have been drawn.
     * @return myUndoAll the menu item used for clearing the drawing panel.
     */
    public JMenuItem getMyUndoAll() {
        return myUndoAll;
    }

    /**
     * Method used to set the tools and actions to their default values.
     */
    private void setUpToolsAndActions() {

        myPencilTool = new PencilTool(DEFAULT_STROKE_WIDTH, DEFAULT_COLOR);
        myLineTool = new LineTool(DEFAULT_STROKE_WIDTH, DEFAULT_COLOR);
        myRectangleTool = new RectangleTool(DEFAULT_STROKE_WIDTH, DEFAULT_COLOR);
        myEllipseTool = new EllipseTool(DEFAULT_STROKE_WIDTH, DEFAULT_COLOR);
        myPTA = new PencilToolAction(myPencilTool);
        myLTA = new LineToolAction(myLineTool);
        myRTA = new RectangleToolAction(myRectangleTool);
        myETA = new EllipseToolAction(myEllipseTool);


    }
    /**
     * Helper method for creating the toolbar.
     * @return toolbar a toolbar to add to the bottom of power paint
     */
    private JToolBar createToolBar() {
        final JToolBar toolbar = new JToolBar();
        final ButtonGroup tbBG = new ButtonGroup();

        final JToggleButton pencil = new JToggleButton(myPTA);
        tbBG.add(pencil);
        toolbar.add(pencil);

        final JToggleButton line = new JToggleButton(myLTA);
        tbBG.add(line);
        toolbar.add(line);

        final JToggleButton rectangle = new JToggleButton(myRTA);
        tbBG.add(rectangle);
        toolbar.add(rectangle);

        final JToggleButton ellipse = new JToggleButton(myETA);
        tbBG.add(ellipse);
        toolbar.add(ellipse);

        return toolbar;
    }

    /**
     * Helper method for creating the menubar and all the menu items with actions attached.
     * @return menubar the menubar to use on the drawing panel
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menubar = new JMenuBar();

        //file menu
        final JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        myUndoAll.setEnabled(false);
        file.add(myUndoAll);
        file.addSeparator();
        final JMenuItem exit = new JMenuItem(new ExitAction());
        file.add(exit);
        menubar.add(file);

        //options menu
        final JMenu options = new JMenu("Options");
        options.setMnemonic(KeyEvent.VK_O);
        final JCheckBoxMenuItem grid = new JCheckBoxMenuItem(new GridAction());
        options.add(grid);
        options.addSeparator();
        final JMenu thickness = new JMenu("Thickness");
        thickness.setMnemonic(KeyEvent.VK_T);
        thickness.add(new MyTSlider(this));
        options.add(thickness);
        options.addSeparator();
        final JMenuItem color = new JMenuItem(new ColorAction());
        options.add(color);
        menubar.add(options);


        //tools menu
        final ButtonGroup menuBG = new ButtonGroup();
        final JMenu tools = new JMenu("Tools");
        tools.setMnemonic(KeyEvent.VK_T);
        final JRadioButtonMenuItem pencil = new JRadioButtonMenuItem(myPTA);
        menuBG.add(pencil);
        tools.add(pencil);
        final JRadioButtonMenuItem line = new JRadioButtonMenuItem(myLTA);
        menuBG.add(line);
        tools.add(line);
        final JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem(myRTA);
        menuBG.add(rectangle);
        tools.add(rectangle);
        final JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem(myETA);
        menuBG.add(ellipse);
        tools.add(ellipse);
        menubar.add(tools);

        //help menu
        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        final JMenuItem about = new JMenuItem(new AboutAction());
        help.add(about);
        menubar.add(help);

        return menubar;

    }
    
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        myPencilTool.setMyStrokeWidth(((MyTSlider) theEvent.getSource()).getValue());
        myLineTool.setMyStrokeWidth(((MyTSlider) theEvent.getSource()).getValue());
        myRectangleTool.setMyStrokeWidth(((MyTSlider) theEvent.getSource()).getValue());
        myEllipseTool.setMyStrokeWidth(((MyTSlider) theEvent.getSource()).getValue());
        
    }

    /**
     * Inner class for defining an action associated with the pencil tool.
     * @author David
     * @version Autumn 2015
     */
    public class PencilToolAction extends ToolAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = 1353742082603738497L;
        
        /**
         * Constructor for PencilToolAction. Sets the initially selected tool.
         * @param theTool tool to implement
         */
        public PencilToolAction(final Tool theTool) {
            super(theTool);
            putValue(SELECTED_KEY, true);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDP.setCurrentTool(myPencilTool);
            putValue(SELECTED_KEY, true);
        }

    }

    /**
     * Inner class for defining an action associated with the Line tool.
     * @author David
     * @version Autumn 2015
     */
    public class LineToolAction extends ToolAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -1276559049030296310L;

        /**
         * Constructor for LineToolAction.
         * @param theTool tool to implement
         */
        public LineToolAction(final Tool theTool) {
            super(theTool);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDP.setCurrentTool(myLineTool);
            putValue(SELECTED_KEY, true);
        }

    }

    /**
     * Inner class for defining an action associated with the rectangle tool.
     * @author David
     * @version Autumn 2015
     */
    public class RectangleToolAction extends ToolAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = 1705743732108045250L;

        /**
         * Constructor for RectangleToolAction.
         * @param theTool tool to implement
         */
        public RectangleToolAction(final Tool theTool) {
            super(theTool);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDP.setCurrentTool(myRectangleTool);
            putValue(SELECTED_KEY, true);
        }

    }

    /**
     * Inner class for defining an action associated with the ellipse tool.
     * @author David
     * @version Autumn 2015
     */
    public class EllipseToolAction extends ToolAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = 1353742082603738497L;
        
        /**
         * Constructor for EllipseToolAction.
         * @param theTool tool to implement
         */
        public EllipseToolAction(final Tool theTool) {
            super(theTool);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDP.setCurrentTool(myEllipseTool);
            putValue(SELECTED_KEY, true);
        }

    }

    /**
     * Inner class for defining the exit action.
     * @author David
     * @version Autumn 2015
     */
    public class ExitAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -4263587945929176092L;

        /** Constructor for an ExitAction. */
        public ExitAction() {
            super("Exit");
            putValue(SHORT_DESCRIPTION, "Close program");
            putValue(MNEMONIC_KEY, KeyEvent.VK_X);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('x'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            System.exit(EXIT_ON_CLOSE);

        }

    }

    /**
     * Inner class for defining the grid action.
     * @author David
     * @version Autumn 2015
     */
    public class GridAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -2860436121009876455L;

        /** Constructor for a grid action. */
        public GridAction() {
            super("Grid");
            putValue(SHORT_DESCRIPTION, "Add grid to panel");
            putValue(MNEMONIC_KEY, KeyEvent.VK_G);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('g'),
                                                             ActionEvent.ALT_MASK));
            putValue(SELECTED_KEY, false); 
        }

        @Override
        public void actionPerformed(final ActionEvent theEvent) {     
            myDP.setGrid((boolean) getValue(SELECTED_KEY));
        }
    }

    /**
     * Inner class for defining the Color action.
     * @author David
     * @version Autumn 2015
     */
    public class ColorAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = 4802845864033996709L;
        
        /** ColorIcon for use as the label. */
        private final ColorIcon myIcon;

        
        /** Constructor for a ColorAction. */
        public ColorAction() {
            super("Color...");
            putValue(MNEMONIC_KEY, KeyEvent.VK_C);
            putValue(SHORT_DESCRIPTION, "Choose a color");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('c'),
                                                             ActionEvent.ALT_MASK));
            myIcon = new ColorIcon(DEFAULT_COLOR);
            putValue(SMALL_ICON, myIcon);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            final Color result = JColorChooser.showDialog(null, "A Color Chooser", null);
            if (result != null) {
                myPencilTool.setMyColor(result);
                myLineTool.setMyColor(result);
                myRectangleTool.setMyColor(result);
                myEllipseTool.setMyColor(result);
                myIcon.setColor(result);
            }
        }
    }
    
    /**
     * Inner class for defining the Undo All action.
     * @author David
     * @version Autumn 2015
     */
    public class UndoAllAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -4263587945929176092L;

        /** Constructor for an UndoAllAction. */
        public UndoAllAction() {
            super("Undo All");
            putValue(SHORT_DESCRIPTION, "Removes all shapes");
            putValue(MNEMONIC_KEY, KeyEvent.VK_U);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('u'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDP.clearShapes();
            ((JMenuItem) theEvent.getSource()).setEnabled(false);
        }
    }
    
    /**
     * Inner class for defining an About action.
     * @author David
     * @version Autumn 2015
     */
    public class AboutAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -388995737059795951L;

        /** Constructor for an AboutAction. */
        public AboutAction() {
            super("About...");
            putValue(SHORT_DESCRIPTION, "About");
            putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, Autumn 2015");         
        }      
    }
}




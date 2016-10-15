package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.Board;

/**
 * 
 * @author David
 * @version Autumn 2015
 */
public class ScorePanel extends JPanel implements PropertyChangeListener {

    /** A generated serial ID number. */
    private static final long serialVersionUID = -1411553094744014633L;

    /** */
    private static final String INITIAL_SCORE = "0";

    /** */
    private static final int PREFERRED_WIDTH = 200;

    /** */
    private final NextPiecePanel myNextPanel;

    /** */
    private JPanel myStatPanel;

    /** */
    private JPanel myButtonPanel;

    /** */
    private final JLabel mySpaceLbl = new JLabel();

    /** */
    private JLabel myScoreLabel;

    /** */
    private JLabel myLinesLabel;

    /** */
    private JLabel myDiffLabel;

    /**
     * Constructor for a score panel which contains valuable information on game status.
     * @param theGUI the game GUI 
     * @param theBoard the game board
     */
    public ScorePanel(final TetrisGUI theGUI, final Board theBoard) {
        super(new GridLayout(0, 1), true);
        setPreferredSize(new Dimension(PREFERRED_WIDTH, theGUI.getHeight()));
        myNextPanel = new NextPiecePanel(theBoard);
        myNextPanel.setFocusable(false);
        myStatPanel = setUpStatPanel();
        myButtonPanel = setUpBPanel(theGUI);
        add(myNextPanel);
        add(myStatPanel);
        add(myButtonPanel);        
    }

    /** Simple method to repaint the nextPanel and display next piece. */
    public void notifyNextPanel() {
        myNextPanel.repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        final String property = theEvent.getPropertyName();
        switch (property) {
            case "MyLines":
                myLinesLabel.setText(((Integer) theEvent.getNewValue()).toString());
                break;
            case "MyScore":
                myScoreLabel.setText(((Integer) theEvent.getNewValue()).toString());
                break;
            case "MyDifficulty":
                myDiffLabel.setText(((Integer) theEvent.getNewValue()).toString());
                break;
            case "Playing":
                if ((boolean) theEvent.getNewValue()) {
                    ((JButton) myButtonPanel.getComponent(0)).setText("Pause");
                    break;
                } else {
                    ((JButton) myButtonPanel.getComponent(0)).setText("Play");
                    break;
                }
            case "GameOver":
                myButtonPanel.getComponent(0).setEnabled(false);
                myButtonPanel.getComponent(0).enableInputMethods(false);
                myButtonPanel.getComponent(1).setEnabled(false);
                myButtonPanel.getComponent(0).enableInputMethods(false);
                myButtonPanel.getComponent(2).setEnabled(true);
                myButtonPanel.getComponent(0).enableInputMethods(true);
                break;
            case "NewGame":
                myButtonPanel.getComponent(0).setEnabled(true);
                myButtonPanel.getComponent(0).enableInputMethods(true);
                myButtonPanel.getComponent(1).setEnabled(true);
                myButtonPanel.getComponent(0).enableInputMethods(true);
                myButtonPanel.getComponent(2).setEnabled(false);
                myButtonPanel.getComponent(0).enableInputMethods(false);
                break;
            default:
                break;
        }
    }

    /**
     * Helper method for setting up the statistics panel.
     * @return myStatPanel
     */
    private JPanel setUpStatPanel() {
        myStatPanel = new JPanel(new GridLayout(0, 2), true);

        myStatPanel.add(new JLabel("Left:  'A'"));
        myStatPanel.add(new JLabel("Right:  'D'"));
        myStatPanel.add(new JLabel("Down:  'S'"));
        myStatPanel.add(new JLabel("Drop:  SpaceBar"));
        myStatPanel.add(new JLabel("Rotate:  'W'"));

        myStatPanel.add(mySpaceLbl);
        myStatPanel.add(new JSeparator());
        myStatPanel.add(new JSeparator());

        myStatPanel.add(new JLabel("Score: "));
        myScoreLabel = new JLabel(INITIAL_SCORE);
        myStatPanel.add(myScoreLabel);
        myStatPanel.add(new JLabel("Lines: "));
        myLinesLabel = new JLabel(INITIAL_SCORE);
        myStatPanel.add(myLinesLabel);
        myStatPanel.add(new JLabel("Difficulty: "));
        myDiffLabel = new JLabel("1");
        myStatPanel.add(myDiffLabel);

        myStatPanel.setFocusable(false);
        return myStatPanel;     
    }

    /**
     * Helper method for setting up the button panel.
     * @param theGUI used for access to the actions for the buttons
     * @return myButtonPanel
     */
    private JPanel setUpBPanel(final TetrisGUI theGUI) {
        myButtonPanel = new JPanel(new GridLayout(0, 1));

        final JButton pauseStart = new JButton(theGUI.getMyPSAction());
        pauseStart.setFocusable(false);
        final JButton endGame = new JButton(theGUI.getMyEGAction());
        endGame.setFocusable(false);
        final JButton newGame = new JButton(theGUI.getMyNGAction());
        newGame.setFocusable(false);
        newGame.setEnabled(false);
        myButtonPanel.add(pauseStart);
        myButtonPanel.add(endGame);
        myButtonPanel.add(newGame);

        return myButtonPanel;
    }
}

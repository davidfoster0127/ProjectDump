import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class StartScreen extends JFrame implements ActionListener {

	JFrame frame;
	ButtonGroup buttonGroup = new ButtonGroup();
	JTextField textName;
	TextArea textArea = new TextArea();
	String name="";
	JRadioButton FireMonster;
	JRadioButton WaterMonster;
	JRadioButton GrassMonster;
	JButton btnChoose = new JButton("Choose");
	JLabel lblNewLabel = new JLabel("Select your new Javamon");
	JLabel lblNewLabel_1 = new JLabel("New label");
	JLabel lblNewLabel_2 = new JLabel("New label");
	JLabel lblName = new JLabel("Name:");
	JLabel label = new JLabel("New label");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();


		frame.setBounds(100, 100, 681, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		lblNewLabel.setBounds(260, 11, 194, 25);
		frame.getContentPane().add(lblNewLabel);


		lblNewLabel_1.setBounds(66, 49, 146, 176);
		frame.getContentPane().add(lblNewLabel_1);


		lblNewLabel_2.setBounds(270, 48, 146, 178);
		frame.getContentPane().add(lblNewLabel_2);


		label.setBounds(460, 48, 146, 178);
		frame.getContentPane().add(label);

		FireMonster = new JRadioButton("FIRE MONSTER ");
		FireMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFire pMon = new pFire("Undecided");//be sure that the data are identical if you change value
				textArea.setText("Info for Fire Monster: \n"+pMon.toString());
				
			}
		});
		buttonGroup.add(FireMonster);
		FireMonster.setBounds(41, 262, 109, 23);
		frame.getContentPane().add(FireMonster);

		WaterMonster = new JRadioButton("WATER MONSTER ");
		WaterMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Javamon YourJavamon= new Javamon(1,"you decided");//be sure that the data are identical if you change value
				textArea.setText("Info for WATER MONSTER: \n"+YourJavamon.toString());

			}
		});
		buttonGroup.add(WaterMonster);
		WaterMonster.setBounds(41, 288, 109, 23);
		frame.getContentPane().add(WaterMonster);

		GrassMonster = new JRadioButton("GRASS MONSTER ");
		GrassMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Javamon YourJavamon= new Javamon(2,"you decided");//be sure that the data are identical if you change value
				textArea.setText("Info for GRASS MONSTER: \n"+YourJavamon.toString());

			}
		});
		buttonGroup.add(GrassMonster);
		GrassMonster.setBounds(41, 314, 109, 23);
		frame.getContentPane().add(GrassMonster);

		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name=textName.getText();
				if (FireMonster.isSelected()){

					pFire pMon= new pFire(name);//you might put the different value between comma
					MapScreen MP=new MapScreen(YourJavamon);
					MP.show();
					frame.setVisible(false);
				}
				if (WaterMonster.isSelected()){
					YourJavamon= new Javamon(1,name);//you might put the value between comma
					MapScreen MP=new MapScreen(YourJavamon);
					MP.show();
					frame.setVisible(false);
				}
				if (GrassMonster.isSelected()){
					YourJavamon= new Javamon(2,name);//you might put the value between comma
					MapScreen MP=new MapScreen(YourJavamon);
					MP.show();
					frame.setVisible(false);
				}
				
			}
		});
		btnChoose.setBounds(41, 366, 89, 23);
		frame.getContentPane().add(btnChoose);


		lblName.setBounds(41, 344, 46, 14);
		frame.getContentPane().add(lblName);

		textName = new JTextField();
		textName.setBounds(78, 341, 134, 20);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		textArea.setEditable(false);


		textArea.setBounds(225, 236, 440, 170);
		frame.getContentPane().add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}

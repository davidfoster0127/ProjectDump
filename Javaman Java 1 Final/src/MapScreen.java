import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;


public class MapScreen extends JFrame implements ActionListener {

	JFrame frame = new JFrame();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static pFire pMon;
	private static int AreaType;
	
	private static int LAKE=0;
	private static int MOUNTAIN=1;
	private static int CAVE=2;
	private static int JUNGLE=3;
	private static int CLOUD=4;
	
	final JRadioButton rdbtnLake = new JRadioButton("Lake");
	final JRadioButton rdbtnMountain = new JRadioButton("Mountain");
	final JRadioButton rdbtnCave = new JRadioButton("Cave");
	final JRadioButton rdbtnJungle = new JRadioButton("Jungle");
	final JRadioButton rdbtnCloud = new JRadioButton("Cloud");
	JRadioButton rdbtnYourJavamon = new JRadioButton("Your Javamon");
	JLabel lblSelectWhereYou = new JLabel("Select where you want to fight");
	JLabel lblNewLabel = new JLabel("New label");
	JButton btnChoose = new JButton("Choose");
	
	final TextArea textArea = new TextArea();
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 */
	public MapScreen(final Javamon YourJavamon) {
		frame.setVisible(true);
		this.YourJavamon=YourJavamon;

		textArea.setEditable(false);

		frame.setBounds(100, 100, 681, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		lblSelectWhereYou.setBounds(232, 11, 207, 14);
		frame.getContentPane().add(lblSelectWhereYou);

		
		lblNewLabel.setBounds(35, 40, 586, 182);
		frame.getContentPane().add(lblNewLabel);

		
		rdbtnLake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for Lake: \n"+ "Type of Monster you might meet:\n"
						+ "WATER MONSTER; ACID MONSTER;");
			}
		});


		buttonGroup.add(rdbtnLake);
		rdbtnLake.setBounds(32, 252, 109, 23);
		frame.getContentPane().add(rdbtnLake);

		
		rdbtnMountain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for Mountain: \n"+ "Type of Monster you might meet:\n"
						+ "FIRE MONSTER; ICED MONSTER\nThis place is found on top of Mount Wannahockalugee. There are many temples and ruins found on its sides and only the very"
						+ "top is covered in snow all year round.");
			}
		});

		buttonGroup.add(rdbtnMountain);
		rdbtnMountain.setBounds(32, 278, 109, 23);
		frame.getContentPane().add(rdbtnMountain);

		
		rdbtnCave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for Cave: \n"+ "Type of Monster you might meet:\n"
						+ "GRAVEL MONSTER; ACID MONSTER");
			}
		});
		buttonGroup.add(rdbtnCave);
		rdbtnCave.setBounds(32, 304, 109, 23);
		frame.getContentPane().add(rdbtnCave);

		
		rdbtnJungle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for Jungle: \n"+ "Type of Monster you might meet:\n"
						+ "PLANT MONSTER; BIRD MONSTER");
			}
		});
		buttonGroup.add(rdbtnJungle);
		rdbtnJungle.setBounds(32, 330, 109, 23);
		frame.getContentPane().add(rdbtnJungle);

		
		rdbtnCloud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for Cloud: \n"+ "Type of Monster you might meet:\n"
						+ "ELECTRIC MONSTER; BIRD MONSTER");
			}
		});
		buttonGroup.add(rdbtnCloud);
		rdbtnCloud.setBounds(32, 356, 109, 23);
		frame.getContentPane().add(rdbtnCloud);

		
		rdbtnYourJavamon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Info for your Javamon:\n"+YourJavamon.toString());
			}
		});
		buttonGroup.add(rdbtnYourJavamon);
		rdbtnYourJavamon.setBounds(6, 383, 109, 23);
		frame.getContentPane().add(rdbtnYourJavamon);

		
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnLake.isSelected()){
					Random rn= new Random();
					int rd= (rn.nextInt(2)+0);
					if (rd==0){
						eWater eMon = new eWater(pMon.getLevel());
					}
					else{
						ePoison eMon = new ePoison(pMon.getLevel());
					}
					FightScreen FS=new FightScreen(AreaType,YourJavamon);
					FS.show();
					frame.setVisible(false);
				}
				if (rdbtnMountain.isSelected()){
					AreaType=MOUNTAIN;
					FightScreen FS=new FightScreen(AreaType,YourJavamon);
					FS.show();
					frame.setVisible(false);
				}
				if (rdbtnCave.isSelected()){
					AreaType=CAVE;
					FightScreen FS=new FightScreen(AreaType,YourJavamon);
					FS.show();
					frame.setVisible(false);
				}
				if (rdbtnJungle.isSelected()){
					AreaType=JUNGLE;
					FightScreen FS=new FightScreen(AreaType,YourJavamon);
					FS.show();
					frame.setVisible(false);
				}
				if (rdbtnCloud.isSelected()){
					AreaType=CLOUD;
					FightScreen FS=new FightScreen(AreaType,YourJavamon);
					FS.show();
					frame.setVisible(false);
				}
			}
		});
		btnChoose.setBounds(125, 383, 89, 23);
		frame.getContentPane().add(btnChoose);


		textArea.setBounds(215, 228, 440, 151);
		frame.getContentPane().add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

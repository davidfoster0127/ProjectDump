import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class CopyOfjavamonGUI {

	private JFrame frame;
	private JTextArea txtStartSName;
	private JTextArea txtFightSYourHealth;
	private JTextArea txtFightSEnemyHealth;
	private JTextArea txtFightSRunsLeft;
	private final ButtonGroup buttonGroupMap = new ButtonGroup();
	private final ButtonGroup buttonGroupStart = new ButtonGroup();
	private final ButtonGroup buttonGroupFight = new ButtonGroup();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CopyOfjavamonGUI window = new CopyOfjavamonGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CopyOfjavamonGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final CardLayout cardLayout = new CardLayout();
		final JPanel parentComponent = new JPanel(cardLayout);
		frame.getContentPane().add(parentComponent);
		
		
		
		
		//This is all the start screen
		JPanel card1 = new JPanel();
		parentComponent.add(card1, "card1");
		card1.setLayout(null);
		
		JButton tempNavigationBtn1 = new JButton("New button");
		tempNavigationBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show( parentComponent, "card2" );
			}
		});
		tempNavigationBtn1.setBounds(464, 0, 89, 23);
		card1.add(tempNavigationBtn1);
		
		JLabel lblStartSTop = new JLabel("Select your new Javamon");
		lblStartSTop.setBounds(239, 11, 194, 25);
		card1.add(lblStartSTop);
		
		JLabel lblStartSPic1 = new JLabel("New label");
		lblStartSPic1.setBounds(45, 49, 146, 176);
		card1.add(lblStartSPic1);
		
		JLabel lblStartSPic2 = new JLabel("New label");
		lblStartSPic2.setBounds(249, 48, 146, 178);
		card1.add(lblStartSPic2);
		
		JLabel lblStartSPic3 = new JLabel("New label");
		lblStartSPic3.setBounds(439, 48, 146, 178);
		card1.add(lblStartSPic3);
		
		JRadioButton rbStartSPickFire = new JRadioButton("FIRE MONSTER ");
		buttonGroupStart.add(rbStartSPickFire);
		rbStartSPickFire.setBounds(20, 262, 109, 23);
		card1.add(rbStartSPickFire);
		
		JRadioButton rbStartSPickWater = new JRadioButton("WATER MONSTER ");
		buttonGroupStart.add(rbStartSPickWater);
		rbStartSPickWater.setBounds(20, 288, 109, 23);
		card1.add(rbStartSPickWater);
		
		JRadioButton rbStartSPickGrass = new JRadioButton("GRASS MONSTER ");
		buttonGroupStart.add(rbStartSPickGrass);
		rbStartSPickGrass.setBounds(20, 314, 109, 23);
		card1.add(rbStartSPickGrass);
		
		JButton btnStartSChoose = new JButton("Choose");
		btnStartSChoose.setBounds(20, 366, 89, 23);
		card1.add(btnStartSChoose);
		
		JLabel lblStartSName = new JLabel("Name:");
		lblStartSName.setBounds(20, 344, 46, 14);
		card1.add(lblStartSName);
		
		txtStartSName = new JTextArea();
		txtStartSName.setWrapStyleWord(true);
		txtStartSName.setLineWrap(true);
		txtStartSName.setColumns(10);
		txtStartSName.setBounds(57, 341, 134, 20);
		card1.add(txtStartSName);
		
		JScrollPane scrollPaneStartS = new JScrollPane();
		scrollPaneStartS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneStartS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneStartS.setBounds(251, 248, 404, 158);
		card1.add(scrollPaneStartS);
		
		JTextArea txtStartSInfo = new JTextArea();
		txtStartSInfo.setEditable(false);
		scrollPaneStartS.setViewportView(txtStartSInfo);
		txtStartSInfo.setWrapStyleWord(true);
		txtStartSInfo.setLineWrap(true);
		//End of start screen
		
		
		
		
		
		//This is the Map Screen
		JPanel card2 = new JPanel();
		parentComponent.add(card2, "card2");
		card2.setLayout(null);
		
		JButton tempNavigationBtn2 = new JButton("Start Screen");
		tempNavigationBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(parentComponent, "card1" );
			}
		});
		tempNavigationBtn2.setBounds(505, 11, 89, 23);
		card2.add(tempNavigationBtn2);
		
		JLabel lblMapSTop = new JLabel("Select where you want to fight");
		lblMapSTop.setBounds(232, 11, 207, 14);
		card2.add(lblMapSTop);
		
		JLabel lblMapSPicMap = new JLabel("New label");
		lblMapSPicMap.setBounds(35, 40, 586, 182);
		card2.add(lblMapSPicMap);
		
		JRadioButton rbMapSLake = new JRadioButton("Lake");
		buttonGroupMap.add(rbMapSLake);
		rbMapSLake.setBounds(32, 252, 109, 23);
		card2.add(rbMapSLake);
		
		JRadioButton rbMapSMountain = new JRadioButton("Mountain");
		buttonGroupMap.add(rbMapSMountain);
		rbMapSMountain.setBounds(32, 278, 109, 23);
		card2.add(rbMapSMountain);
		
		JRadioButton rbMapSCave = new JRadioButton("Cave");
		buttonGroupMap.add(rbMapSCave);
		rbMapSCave.setBounds(32, 304, 109, 23);
		card2.add(rbMapSCave);
		
		JRadioButton rbMapSJungle = new JRadioButton("Jungle");
		buttonGroupMap.add(rbMapSJungle);
		rbMapSJungle.setBounds(32, 330, 109, 23);
		card2.add(rbMapSJungle);
		
		JRadioButton rbMapSCloud = new JRadioButton("Cloud");
		buttonGroupMap.add(rbMapSCloud);
		rbMapSCloud.setBounds(32, 356, 109, 23);
		card2.add(rbMapSCloud);
		
		JRadioButton rbMapSYourJavaInfo = new JRadioButton("Your Javamon");
		buttonGroupMap.add(rbMapSYourJavaInfo);
		rbMapSYourJavaInfo.setBounds(6, 383, 109, 23);
		card2.add(rbMapSYourJavaInfo);
		
		JButton btnMapSChoose = new JButton("Choose");
		btnMapSChoose.setBounds(125, 383, 89, 23);
		card2.add(btnMapSChoose);
		
		JButton tempNavigationBtn3 = new JButton("Fight Screen");
		tempNavigationBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(parentComponent, "card3");
			}
		});
		tempNavigationBtn3.setBounds(52, 11, 89, 23);
		card2.add(tempNavigationBtn3);
		
		JScrollPane scrollPaneMapS = new JScrollPane();
		scrollPaneMapS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneMapS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMapS.setBounds(251, 251, 404, 158);
		card2.add(scrollPaneMapS);
		
		JTextArea txtMapSInfo = new JTextArea();
		txtMapSInfo.setEditable(false);
		scrollPaneMapS.setViewportView(txtMapSInfo);
		txtMapSInfo.setWrapStyleWord(true);
		txtMapSInfo.setLineWrap(true);
		//End of the Map Screen
		
		
		
		
		
		//Start of the Fight Screen
		JPanel card3 = new JPanel();
		parentComponent.add(card3, "card3");
		card3.setLayout(null);
		
		JLabel lblFightSPicYourJavamon = new JLabel("New label");
		lblFightSPicYourJavamon.setBounds(26, 23, 149, 177);
		card3.add(lblFightSPicYourJavamon);
		
		JLabel lblFightSPicEnemyJavamon = new JLabel("New label");
		lblFightSPicEnemyJavamon.setBounds(443, 23, 149, 177);
		card3.add(lblFightSPicEnemyJavamon);
		
		JLabel lblFightSVs = new JLabel("vs.");
		lblFightSVs.setBounds(287, 104, 46, 14);
		card3.add(lblFightSVs);
		
		txtFightSYourHealth = new JTextArea();
		txtFightSYourHealth.setText("Health: 0");
		txtFightSYourHealth.setEditable(false);
		txtFightSYourHealth.setColumns(10);
		txtFightSYourHealth.setBounds(24, 211, 95, 20);
		card3.add(txtFightSYourHealth);
		
		txtFightSEnemyHealth = new JTextArea();
		txtFightSEnemyHealth.setText("Health: 0");
		txtFightSEnemyHealth.setEditable(false);
		txtFightSEnemyHealth.setColumns(10);
		txtFightSEnemyHealth.setBounds(436, 211, 95, 20);
		card3.add(txtFightSEnemyHealth);
		
		JRadioButton rbFightSMove1 = new JRadioButton("Move 1");
		buttonGroupFight.add(rbFightSMove1);
		rbFightSMove1.setBounds(10, 238, 109, 23);
		card3.add(rbFightSMove1);
		
		JRadioButton rbFightSMove2 = new JRadioButton("Move 2");
		buttonGroupFight.add(rbFightSMove2);
		rbFightSMove2.setBounds(10, 264, 109, 23);
		card3.add(rbFightSMove2);
		
		JRadioButton rbFightSMove3 = new JRadioButton("Move 3");
		buttonGroupFight.add(rbFightSMove3);
		rbFightSMove3.setBounds(10, 290, 109, 23);
		card3.add(rbFightSMove3);
		
		JRadioButton rbFightSMove4 = new JRadioButton("Move 4");
		buttonGroupFight.add(rbFightSMove4);
		rbFightSMove4.setBounds(10, 316, 109, 23);
		card3.add(rbFightSMove4);
		
		JButton btnFightSAtack = new JButton("Attack");
		btnFightSAtack.setBounds(10, 346, 89, 23);
		card3.add(btnFightSAtack);
		
		JButton btnFightSRun = new JButton("Run");
		btnFightSRun.setBounds(123, 346, 89, 23);
		card3.add(btnFightSRun);
		
		txtFightSRunsLeft = new JTextArea();
		txtFightSRunsLeft.setText("Runs Left: 5");
		txtFightSRunsLeft.setEditable(false);
		txtFightSRunsLeft.setColumns(10);
		txtFightSRunsLeft.setBounds(100, 380, 109, 20);
		card3.add(txtFightSRunsLeft);
		
		JButton tempNavigationbtn4 = new JButton("Start Screen");
		tempNavigationbtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(parentComponent, "card1");
			}
		});
		tempNavigationbtn4.setBounds(473, 11, 89, 23);
		card3.add(tempNavigationbtn4);
		
		JScrollPane scrollPaneFightS1 = new JScrollPane();
		scrollPaneFightS1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFightS1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneFightS1.setBounds(271, 242, 187, 169);
		card3.add(scrollPaneFightS1);
		
		JTextArea txtFightSWhatYouDid = new JTextArea();
		scrollPaneFightS1.setViewportView(txtFightSWhatYouDid);
		txtFightSWhatYouDid.setWrapStyleWord(true);
		txtFightSWhatYouDid.setLineWrap(true);
		txtFightSWhatYouDid.setEditable(false);
		
		JScrollPane scrollPaneFightS2 = new JScrollPane();
		scrollPaneFightS2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFightS2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneFightS2.setBounds(468, 242, 187, 169);
		card3.add(scrollPaneFightS2);
		
		JTextArea txtFightSWhatTheyDid = new JTextArea();
		scrollPaneFightS2.setViewportView(txtFightSWhatTheyDid);
		txtFightSWhatTheyDid.setWrapStyleWord(true);
		txtFightSWhatTheyDid.setLineWrap(true);
		txtFightSWhatTheyDid.setEditable(false);
		//End of the Fight Screen
		
		
		
		
		
		
		
	}
}

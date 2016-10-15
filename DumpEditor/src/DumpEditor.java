import java.awt.EventQueue;





import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


//David Foster Week0203
public class DumpEditor {


	private JFrame frmDavidFosterWeek;
	private JTextField txtFileName;
	private File f2open;
	private JTextField txtFileLength;
	private JTextField txtStart;
	private JTextField txtEnd;
	FileInputStream fis;
	PrintStream ps;
	private JTextField txtWhere;
	private JTextField txtToAdd;
	private final ButtonGroup btngroup = new ButtonGroup();
	JLabel lblWhere;
	JLabel lblToAdd;
	JLabel lblSelectWhatType; 
	JRadioButton rdDouble;
	protected JButton btnAdd;
	protected JRadioButton rdStr;
	protected JRadioButton rdByte;
	protected JRadioButton rdChar;
	protected JRadioButton rdShort;
	protected JRadioButton rdInt;
	protected JRadioButton rdLong;
	protected JRadioButton rdFloat;
	JButton btnWhatsHere;
	int address = 0;
	RandomAccessFile raf = null;




	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DumpEditor window = new DumpEditor();
					window.frmDavidFosterWeek.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DumpEditor() {
		initialize();
	}

	private void initialize() {

		frmDavidFosterWeek = new JFrame();
		frmDavidFosterWeek.setTitle("David Foster Week0203");
		frmDavidFosterWeek.setBounds(100, 100, 921, 609);
		frmDavidFosterWeek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDavidFosterWeek.getContentPane().setLayout(null);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 133, 538, 427);
		frmDavidFosterWeek.getContentPane().add(scrollPane);

		final JTextArea txtOut = new JTextArea();
		txtOut.setEditable(false);
		scrollPane.setViewportView(txtOut);

		txtFileName = new JTextField();
		txtFileName.setBounds(10, 11, 380, 20);
		frmDavidFosterWeek.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);

		JButton btnFindFile = new JButton("New button");
		btnFindFile.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
					txtOut.append("Not a good file I guess");
				}
				else{


					f2open = jfc.getSelectedFile();

					txtFileName.setText(f2open.getAbsolutePath());

					txtFileLength.setText("There are "+f2open.length()+" bytes.");


				}
			}
		}
				);
		btnFindFile.setBounds(400, 10, 41, 23);
		frmDavidFosterWeek.getContentPane().add(btnFindFile);

		JButton btnDump = new JButton("Dump that $#?!");
		btnDump.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {

				try{
					try {
						fis = new FileInputStream(txtFileName.getText());
						ps = new PrintStream( new FileOutputStream(txtFileName.getText()+".dmp"));	
					}
					catch (Exception d){

					}
					int address=0;
					int[] c=null;
					byte[] b = new byte[16];

					while (fis.available() != 0){


						if ((fis.available()) < 16) { 
							byte[] d = new byte[fis.available()];
							fis.read(d);
							c = new int[d.length];
							for(int i = 0; i < d.length; i++) {
								c[i] = (int) d[i];
								if (c[i] < 0) c[i] += 256;
							}

						} else{
							fis.read(b);
							c = new int[b.length];
							for (int i = 0; i<b.length;i++){

								c[i] = (int) b[i];

								if (c[i]<0) c[i]+=256;
							}
						}

						txtOut.append(formatDump(address, c));
						ps.println(formatDump(address, c));
						address+=16;
					}



					ps.close();
					fis.close();
				}
				catch(Exception e){
					txtOut.append("oops\n");
				}

			}
		});
		btnDump.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDump.setBounds(10, 39, 538, 81);
		frmDavidFosterWeek.getContentPane().add(btnDump);

		txtFileLength = new JTextField();
		txtFileLength.setEditable(false);
		txtFileLength.setText("File length: ");
		txtFileLength.setBounds(464, 11, 257, 20);
		frmDavidFosterWeek.getContentPane().add(txtFileLength);
		txtFileLength.setColumns(10);

		txtStart = new JTextField();
		txtStart.setToolTipText("Enter a long");
		txtStart.setBounds(558, 64, 86, 20);
		frmDavidFosterWeek.getContentPane().add(txtStart);
		txtStart.setColumns(10);

		JLabel lblStartAddress = new JLabel("Start Address");
		lblStartAddress.setBounds(558, 39, 90, 14);
		frmDavidFosterWeek.getContentPane().add(lblStartAddress);

		txtEnd = new JTextField();
		txtEnd.setToolTipText("Enter a Long");
		txtEnd.setColumns(10);
		txtEnd.setBounds(558, 112, 86, 20);
		frmDavidFosterWeek.getContentPane().add(txtEnd);

		JLabel lblEndAddress = new JLabel("End Address");
		lblEndAddress.setBounds(558, 90, 86, 14);
		frmDavidFosterWeek.getContentPane().add(lblEndAddress);

		JButton btnShowMe = new JButton("Show Me");
		btnShowMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				long s = Long.parseLong(txtStart.getText());
				long e = Long.parseLong(txtEnd.getText());


				long sAd = (int) (s/16 *16);
				long eAd = (int) (e/16 *16);
				int address = (int) sAd;

				try{
					raf = new RandomAccessFile(f2open, "rw");
					int[] c = null;
					byte[] b = null;

					while (address <= e) { 

						if (address == sAd) {
							raf.seek(s);
							if (e < (sAd + 16)) {
								b = new byte[(int) (e - s + 1)];
							} else {
								b = new byte[(int) (address + 16 - s)];
							}
							raf.read(b);
							c = new int[b.length];
							for(int i = 0; i < b.length; i++) {
								c[i] = (int) b[i];
								if (c[i] < 0) c[i] += 256;
							}

							txtOut.append(formatDump(address, c));

						} else if (address == eAd) {  
							raf.seek(address);
							b = new byte[(int) (e - address + 1)];
							raf.read(b);
							c = new int[b.length];
							for(int i = 0; i < b.length; i++) {
								c[i] = (int) b[i];
								if (c[i] < 0) c[i] += 256;
							}
							txtOut.append(formatDump(address, c));


						} else {
							raf.seek(address);
							b = new byte[16];
							raf.read(b);
							c = new int[b.length];
							for(int i = 0; i < b.length; i++) {
								c[i] = (int) b[i];
								if (c[i] < 0) c[i] += 256;
							}

							txtOut.append(formatDump(address, c));

						}
						address+=16;
					}
					raf.close();
				}
				catch (Exception g){

				}

				txtWhere.setEnabled(true);
				lblWhere.setEnabled(true);
				lblToAdd.setEnabled(true);
				txtToAdd.setEnabled(true);
				btnAdd.setEnabled(true);
				rdStr.setEnabled(true);
				rdByte.setEnabled(true);
				rdChar.setEnabled(true);
				rdShort.setEnabled(true);
				rdInt.setEnabled(true);
				rdLong.setEnabled(true);
				rdFloat.setEnabled(true);
				rdDouble.setEnabled(true);
				lblSelectWhatType.setEnabled(true);
				btnWhatsHere.setEnabled(true);


			}
		});
		btnShowMe.setBounds(672, 111, 89, 23);
		frmDavidFosterWeek.getContentPane().add(btnShowMe);

		txtWhere = new JTextField();
		txtWhere.setEnabled(false);
		txtWhere.setToolTipText("This will be interpreted as hexidecimal");
		txtWhere.setBounds(558, 209, 111, 20);
		frmDavidFosterWeek.getContentPane().add(txtWhere);
		txtWhere.setColumns(10);

		lblWhere = new JLabel("Write where you want to insert your edit:");
		lblWhere.setEnabled(false);
		lblWhere.setBounds(558, 192, 257, 14);
		frmDavidFosterWeek.getContentPane().add(lblWhere);

		txtToAdd = new JTextField();
		txtToAdd.setToolTipText("Short, int, long, float, and decimal numbers will be interpreted as decimal numbers, but byte data will be interpreted as hex.");
		txtToAdd.setEnabled(false);
		txtToAdd.setBounds(558, 263, 215, 20);
		frmDavidFosterWeek.getContentPane().add(txtToAdd);
		txtToAdd.setColumns(10);

		lblToAdd = new JLabel("Write what you wish to add:");
		lblToAdd.setEnabled(false);
		lblToAdd.setBounds(558, 240, 163, 14);
		frmDavidFosterWeek.getContentPane().add(lblToAdd);

		btnAdd = new JButton("Add this $#?!");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					raf = new RandomAccessFile(f2open, "rw");
					long address = Long.parseLong(txtWhere.getText());
					raf.seek(address);
					if (rdByte.isSelected()) {
						byte x = (byte) Integer.parseInt(txtToAdd.getText(), 16);
						raf.writeByte(x);
					}
					if (rdChar.isSelected()) {
						if(txtToAdd.getText().length() != 1) {
							txtOut.append("Please enter only 1 character\n");
							return;
						}
						char x = txtToAdd.getText().charAt(0);
						raf.writeChar(x);
					}
					if (rdShort.isSelected()) {
						short x = Short.parseShort(txtToAdd.getText());
						raf.writeShort(x);
					}
					if (rdInt.isSelected()) {
						int x = Integer.parseInt(txtToAdd.getText());
						raf.writeInt(x);
					}
					if (rdLong.isSelected()) {
						long x = Long.parseLong(txtToAdd.getText());
						raf.writeLong(x);
					}
					if (rdFloat.isSelected()) {
						float x = Float.parseFloat(txtToAdd.getText());
						raf.writeFloat(x);
					}
					if (rdDouble.isSelected()) {
						double x = Double.parseDouble(txtToAdd.getText());
						raf.writeDouble(x);
					}
					if (rdStr.isSelected()) {
						String x = txtToAdd.getText();
						raf.writeUTF(x);
					}
					txtOut.append("Should be good to go\n");
				} catch (Exception c) {
					txtOut.append("Somethin bugged\n");
					return;
				} finally {
					try {
						raf.close();
					} catch (Exception b) {
						return;
					}

				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdd.setEnabled(false);
		btnAdd.setBounds(558, 362, 337, 95);
		frmDavidFosterWeek.getContentPane().add(btnAdd);

		rdStr = new JRadioButton("String");
		rdStr.setEnabled(false);
		btngroup.add(rdStr);
		rdStr.setBounds(554, 306, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdStr);

		rdByte = new JRadioButton("Byte");
		rdByte.setEnabled(false);
		btngroup.add(rdByte);
		rdByte.setBounds(554, 332, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdByte);

		rdChar = new JRadioButton("Char");
		rdChar.setEnabled(false);
		btngroup.add(rdChar);
		rdChar.setBounds(613, 306, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdChar);

		rdShort = new JRadioButton("Short");
		rdShort.setEnabled(false);
		btngroup.add(rdShort);
		rdShort.setBounds(613, 332, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdShort);

		rdInt = new JRadioButton("Int");
		rdInt.setEnabled(false);
		btngroup.add(rdInt);
		rdInt.setBounds(672, 306, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdInt);

		rdLong = new JRadioButton("Long");
		rdLong.setEnabled(false);
		btngroup.add(rdLong);
		rdLong.setBounds(672, 332, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdLong);

		rdFloat = new JRadioButton("Float");
		rdFloat.setEnabled(false);
		btngroup.add(rdFloat);
		rdFloat.setBounds(731, 306, 57, 23);
		frmDavidFosterWeek.getContentPane().add(rdFloat);

		rdDouble = new JRadioButton("Double");
		rdDouble.setEnabled(false);
		btngroup.add(rdDouble);
		rdDouble.setBounds(731, 332, 72, 23);
		frmDavidFosterWeek.getContentPane().add(rdDouble);

		lblSelectWhatType = new JLabel("Select what type of data you're entering:");
		lblSelectWhatType.setEnabled(false);
		lblSelectWhatType.setBounds(558, 285, 230, 14);
		frmDavidFosterWeek.getContentPane().add(lblSelectWhatType);

		btnWhatsHere = new JButton("Whats here?");
		btnWhatsHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					raf = new RandomAccessFile(f2open, "rw");

					long here = Long.parseLong(txtWhere.getText());
					raf.seek(here);

					byte single = raf.readByte();
					String whatThere = Integer.toHexString((int)single);
					txtOut.append("Byte # "+here+" is: "+whatThere+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnWhatsHere.setEnabled(false);
		btnWhatsHere.setBounds(705, 208, 110, 23);
		frmDavidFosterWeek.getContentPane().add(btnWhatsHere);
	}

	public String hex2str(int toConvert, int minSize){
		String aLine = null;
		aLine = Integer.toHexString(toConvert);
		while (aLine.length()<minSize) aLine = "0" + aLine;
		return aLine;
	}

	public String formatDump(int address, int[] info){
		String out;
		StringBuilder output = new StringBuilder();

		output.append(hex2str(address, 8)+ " ");

		for (int i=0 ; i<info.length; i++){
			output.append(hex2str(info[i], 2)+ " ");
		}

		if (info.length<16){
			for (int j = 0; j < 16 - info.length; j++) output.append("   ");
		}

		for (int l = 0; l<info.length; l++){
			String line = Integer.toHexString(info[l]);
			int m = info[l];
			if (m>=32 && m<128){
				char a = (char)Integer.parseInt(line, 16);
				output.append(a);
			}
			else output.append(".");

		}
		output.append("\n");
		out = output.toString();
		return out;
	}
}


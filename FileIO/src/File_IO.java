import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;


public class File_IO {

	private JFrame frame;
	private JTextField textFQFS;
	private File f2open;
	private FileInputStream fis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File_IO window = new File_IO();
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
	public File_IO() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textFQFS = new JTextField();
		textFQFS.setBounds(10, 27, 355, 20);
		frame.getContentPane().add(textFQFS);
		textFQFS.setColumns(10);

		JButton btnFindFQFS = new JButton("New button");
		btnFindFQFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					f2open = jfc.getSelectedFile();
					textFQFS.setText(f2open.getAbsolutePath());
				}
			}
		});
		btnFindFQFS.setBounds(375, 26, 49, 23);
		frame.getContentPane().add(btnFindFQFS);

		JButton btnReadIt = new JButton("Read it!");
		btnReadIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					/*FileWriter fw = new FileWriter("C:\\Users\\dstraayer\\Documents\\george.eh");
					PrintWriter pw = new PrintWriter(fw);
					pw.println("1256");
					pw.println("3.14159265");
					pw.close();*/
					DataOutputStream mf = new DataOutputStream( new FileOutputStream("C:\\Users\\dstraayer\\Documents\\george.eh"));
					mf.writeInt(1256);
					mf.writeDouble(3.14159265);
					mf.writeUTF("A string with numbers: 1256,3.14159265");

					mf.close();
					fis = new FileInputStream("C:\\Users\\dstraayer\\Documents\\george.eh");
					int c;
					byte[] cy = new byte[10];
					while ((c = fis.read()) != -1) {
						String x = Integer.toHexString(c);
						while (x.length()<2) x = "0" + x;
						System.out.println("c = " + x + " " + (c>=32 && c<128 ? ((char) c): "."));
					}
					fis.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog
					(null, "Hmm... couldn't open: " + f2open.getAbsolutePath());
					return;
				}


			}
		});
		btnReadIt.setBounds(10, 71, 89, 23);
		frame.getContentPane().add(btnReadIt);
	}
}

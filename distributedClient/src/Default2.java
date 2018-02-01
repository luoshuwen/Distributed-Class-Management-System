import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;

public class Default2
{

	public JFrame frame;
    private String managerID;
    private JFrame default1;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Default2(String str,JFrame d1)
	{
		this.managerID=str;
		this.default1=d1;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{

		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 275, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"OK", "Error", "Reboot"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(24, 20, 100, 24);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Create Teacher Record");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CreateTRecord ctr=new CreateTRecord(managerID,frame,comboBox.getSelectedIndex());
				ctr.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(24, 60, 216, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateStudentRecord = new JButton("Create Student Record");
		btnCreateStudentRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CreateSRecord csr=new CreateSRecord(managerID,frame,comboBox.getSelectedIndex());
				csr.frame.setVisible(true);
			}
		});
		btnCreateStudentRecord.setBounds(24, 100, 216, 25);
		frame.getContentPane().add(btnCreateStudentRecord);
		
		JButton btnGetRecordCounts = new JButton("Get Record Counts");
		btnGetRecordCounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(managerID.trim().charAt(0)=='M')//mtl
					new Thread(new TestThread1(comboBox.getSelectedIndex(),Integer.parseInt(managerID.substring(3,managerID.trim().length())),2,25000,0,1)).start();
				if(managerID.trim().charAt(0)=='L')//lvl
					new Thread(new TestThread1(comboBox.getSelectedIndex(),Integer.parseInt(managerID.substring(3,managerID.trim().length())),2,25000,1,1)).start();
				if(managerID.trim().charAt(0)=='D')//ddo
					new Thread(new TestThread1(comboBox.getSelectedIndex(),Integer.parseInt(managerID.substring(3,managerID.trim().length())),2,25000,2,1)).start();
			}
		});
		btnGetRecordCounts.setBounds(24, 140, 216, 25);
		frame.getContentPane().add(btnGetRecordCounts);
		
		JButton btnEditRecord = new JButton("Edit Record");
		btnEditRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				EditRecord er=new EditRecord(managerID,frame,comboBox.getSelectedIndex());
				er.frame.setVisible(true);
			}
		});
		btnEditRecord.setBounds(24, 180, 216, 25);
		frame.getContentPane().add(btnEditRecord);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				default1.setVisible(true);
				frame.dispose();
			}
		});
		btnReturn.setBounds(24, 300, 216, 25);
		frame.getContentPane().add(btnReturn);
		
		JButton btnTransferRecord = new JButton("Transfer Record");
		btnTransferRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				TransferRecord tr=new TransferRecord(managerID,frame,comboBox.getSelectedIndex());
				tr.frame.setVisible(true);
			}
		});
		btnTransferRecord.setBounds(24, 220, 216, 25);
		frame.getContentPane().add(btnTransferRecord);
		
		Panel panel = new Panel();
		panel.setBounds(180, 34, 10, 10);
		frame.getContentPane().add(panel);
		
		
		JButton btnPrintDatabase = new JButton("Print Database");
		btnPrintDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String reply=AskPrintThread.udpAsk("print", 50012);
				JOptionPane.showMessageDialog(panel, reply, "Title",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnPrintDatabase.setBounds(24, 260, 216, 25);
		frame.getContentPane().add(btnPrintDatabase);
		

		

		
		//client log

		//log end
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Panel;

public class TransferRecord
{

	public JFrame frame;
	private JTextField textField;
	private int sucNo;
	private String managerID;
	private JFrame default2;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public TransferRecord(String str,JFrame D2,int suc)
	{
		this.managerID=str;
		this.default2=D2;
		this.sucNo=suc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 160);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RecordID:");
		lblNewLabel.setBounds(15, 15, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(90, 15, 130, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Panel panel = new Panel();
		panel.setBounds(220, 140, 10, 10);
		frame.getContentPane().add(panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MTL", "LVL", "DDO"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(161, 45, 60, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblDestinationserver = new JLabel("DestinationServer:");
		lblDestinationserver.setBounds(15, 50, 150, 15);
		frame.getContentPane().add(lblDestinationserver);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String rid = textField.getText().trim();
				String regEx = "^(TR|SR)\\d{5}$";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(rid);
				boolean rs = matcher.matches();
				if (rs == false)
				{
					JOptionPane.showMessageDialog(panel, "Wrong RecordID!", "Title", JOptionPane.WARNING_MESSAGE);
					textField.setText("");
					return;
				}
				if((managerID.charAt(0)=='M'&&comboBox.getSelectedIndex()==0)||(managerID.charAt(0)=='L'&&comboBox.getSelectedIndex()==1)||(managerID.charAt(0)=='D'&&comboBox.getSelectedIndex()==2))
				{
					JOptionPane.showMessageDialog(panel, "Wrong Destination!", "Title", JOptionPane.WARNING_MESSAGE);
					//textField.setText("");
					return;
				}
				//ok
				int p=0,lo=0;
				if(managerID.charAt(0)=='M')
				{
					p=30000;
					lo=0;
				}
				if(managerID.charAt(0)=='L')
				{
					p=20000;
					lo=1;
				}
				if(managerID.charAt(0)=='D')
				{
					p=10000;
					lo=2;
				}
				String rn="";
				if(comboBox.getSelectedIndex()==0)
				{
					rn="MTL";
				}
				if(comboBox.getSelectedIndex()==1)
				{
					rn="LVL";
				}
				if(comboBox.getSelectedIndex()==2)
				{
					rn="DDO";
				}
				//System.out.println(managerID.substring(3, 7)+"~"+4+"~"+p+"~"+lo+"~"+1+"~"+textField.getText().trim()+"~"+rn);
				new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, 7)), 4,p, lo, 1, textField.getText().trim(),rn)).start();
			}
		});
		btnNewButton.setBounds(130, 80, 100, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRetuen = new JButton("Return");
		btnRetuen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				default2.setVisible(true);
				frame.dispose();
			}
		});
		btnRetuen.setBounds(15, 80, 100, 25);
		frame.getContentPane().add(btnRetuen);
		
	}
}

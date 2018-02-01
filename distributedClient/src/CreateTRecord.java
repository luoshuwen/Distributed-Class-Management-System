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
import javax.swing.JPanel;

public class CreateTRecord
{

	public JFrame frame;
	private String managerID;
	private JFrame default2;
	private int sucNo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public CreateTRecord(String str,JFrame d2,int suc)
	{
		this.managerID=str;
		this.default2=d2;
		this.sucNo=suc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 380, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(310, 45, 10, 10);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("FirstName:");
		lblNewLabel.setBounds(12, 12, 90, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(122, 12, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLastname = new JLabel("LastName:");
		lblLastname.setBounds(12, 40, 90, 15);
		frame.getContentPane().add(lblLastname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 40, 114, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(12, 68, 90, 15);
		frame.getContentPane().add(lblAddress);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 68, 250, 19);
		frame.getContentPane().add(textField_2);
		
		JLabel lblSpecialization = new JLabel("Specialization:");
		lblSpecialization.setBounds(12, 124, 120, 15);
		frame.getContentPane().add(lblSpecialization);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(122, 124, 114, 19);
		frame.getContentPane().add(textField_3);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(12, 152, 90, 15);
		frame.getContentPane().add(lblLocation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MTL", "LVL", "DDO"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(122, 152, 60, 24);
		frame.getContentPane().add(comboBox);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				default2.setVisible(true);
				frame.dispose();
			}
		});
		btnReturn.setBounds(12, 185, 117, 25);
		frame.getContentPane().add(btnReturn);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber:");
		lblPhonenumber.setBounds(12, 96, 120, 15);
		frame.getContentPane().add(lblPhonenumber);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(122, 96, 114, 19);
		frame.getContentPane().add(textField_4);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String fn=textField.getText().trim();
				String regEx = "^[A-Za-z]+$";
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(fn);
			    boolean rs = matcher.matches();
			    if(rs==false)
			    {
			    	JOptionPane.showMessageDialog(panel, "Wrong FisrtName!", "Title",JOptionPane.WARNING_MESSAGE);
			    	textField.setText("");
			    	return;
			    }
			    
				String ln=textField_1.getText().trim();
				matcher = pattern.matcher(ln);
			    rs = matcher.matches();
			    if(rs==false)
			    {
			    	JOptionPane.showMessageDialog(panel, "Wrong LastName!", "Title",JOptionPane.WARNING_MESSAGE);
			    	textField_1.setText("");
			    	return;
			    }
			    
				String sp=textField_3.getText().trim();
				matcher = pattern.matcher(sp);
			    rs = matcher.matches();
			    if(rs==false)
			    {
			    	JOptionPane.showMessageDialog(panel, "Wrong Specialization!", "Title",JOptionPane.WARNING_MESSAGE);
			    	textField_3.setText("");
			    	return;
			    }
			    
			    String ph=textField_4.getText().trim();//phone
			    regEx = "^[0-9]*$";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(ph);
			    rs = matcher.matches();
			    if(rs==false)
			    {
			    	JOptionPane.showMessageDialog(panel, "Wrong Phone Number!", "Title",JOptionPane.WARNING_MESSAGE);
			    	textField_4.setText("");
			    	return;
			    }
			    
			    
			    String ad=textField_2.getText().trim().replaceAll(" ", "-");
			    String loc=comboBox.getSelectedItem().toString().trim();
			    //System.out.println(ad);
			    if(managerID.trim().charAt(0)=='M')//mtl
					new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),0,30000,0,1      ,fn,ln,ad,ph,sp,loc)).start();
			    if(managerID.trim().charAt(0)=='L')//lvl
			    	new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),0,20000,1,1      ,fn,ln,ad,ph,sp,loc)).start();
			    if(managerID.trim().charAt(0)=='D')//ddo
			    	new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),0,10000,2,1      ,fn,ln,ad,ph,sp,loc)).start();
			}
		});
       
		
		btnEnter.setBounds(141, 185, 117, 25);
		frame.getContentPane().add(btnEnter);

	}
}

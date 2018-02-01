import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.*;

public class CreateSRecord
{

	public JFrame frame;
	private String managerID;
	private int sucNo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame default2;



	public CreateSRecord(String str,JFrame d2,int suc)
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
		frame.setBounds(100, 100, 275, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(249, 40, 10, 10);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("FirstName:");
		label.setBounds(12, 12, 90, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(122, 12, 114, 19);
		frame.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("LastName:");
		label_1.setBounds(12, 40, 90, 15);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 40, 114, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel lblCoursesintervelOfSpace = new JLabel("Courses(interval of space):");
		lblCoursesintervelOfSpace.setBounds(12, 68, 200, 15);
		frame.getContentPane().add(lblCoursesintervelOfSpace);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 96, 247, 19);
		frame.getContentPane().add(textField_2);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(12, 130, 90, 15);
		frame.getContentPane().add(lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"inactive", "active"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(77, 124, 90, 24);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("Return");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				default2.setVisible(true);
				frame.dispose();
			}
		});
		button.setBounds(12, 157, 117, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Enter");
		button_1.addMouseListener(new MouseAdapter() {
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
			    
			    String str=textField_2.getText().trim();
			    ArrayList<String> al=new ArrayList();
			    StringTokenizer tokenizer = new StringTokenizer(str);
			    while (tokenizer.hasMoreTokens())
				{							
					al.add(tokenizer.nextToken());
				}
			    String[] cr=new String[al.size()];
			    for(int i=0;i<al.size();i++)
			    {
			    	cr[i]=al.get(i).trim();
			    }
			    
			    boolean sta=(comboBox.getSelectedIndex()==0)?false:true;
			    
			    if(managerID.trim().charAt(0)=='M')//mtl
					new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),1,30000,0,1      ,fn,ln,sta,cr)).start();
			    if(managerID.trim().charAt(0)=='L')//lvl
			    	new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),1,20000,1,1      ,fn,ln,sta,cr)).start();
			    if(managerID.trim().charAt(0)=='D')//ddo
			    	new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3,managerID.trim().length())),1,10000,2,1      ,fn,ln,sta,cr)).start();
			    
			}
		});
		button_1.setBounds(141, 157, 117, 25);
		frame.getContentPane().add(button_1);
		

	}
}

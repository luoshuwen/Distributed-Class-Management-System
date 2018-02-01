import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EditRecord
{

	public JFrame frame;
	private String managerID;
	private int sucNo;
	private JTextField textField;
	private JTextField textField_2;
	private JFrame default2;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public EditRecord(String str, JFrame d2,int suc)
	{
		this.managerID = str;
		this.default2 = d2;
		this.sucNo=suc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblRecordid = new JLabel("RecordID:");
		lblRecordid.setBounds(12, 12, 90, 15);
		frame.getContentPane().add(lblRecordid);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(122, 12, 140, 19);
		frame.getContentPane().add(textField);

		JLabel lblFieldname = new JLabel("FieldName:");
		lblFieldname.setBounds(12, 40, 90, 15);
		frame.getContentPane().add(lblFieldname);

		JLabel lblNewvalue = new JLabel("NewValue(if multiple, interval by space):");
		lblNewvalue.setBounds(12, 67, 300, 15);
		frame.getContentPane().add(lblNewvalue);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 97, 300, 19);
		frame.getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Address", "Phone", "Location", "Course Regestered", "status" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(122, 37, 140, 24);
		frame.getContentPane().add(comboBox);

		JPanel panel = new JPanel();
		panel.setBounds(287, 40, 10, 10);
		frame.getContentPane().add(panel);

		JButton button = new JButton("Return");
		button.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frame.setVisible(false);
				default2.setVisible(true);
				frame.dispose();
			}
		});
		button.setBounds(12, 130, 117, 25);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Enter");
		button_1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
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

                if(rid.trim().charAt(0)=='T')
                {
                	if(comboBox.getSelectedIndex() == 0)//address
                	{
    					String add = textField_2.getText().trim().replaceAll(" ", "-");

    					if (managerID.trim().charAt(0) == 'M')// mtl
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),    								3, 30000, 0, 1, rid, "address", add)).start();
    					if (managerID.trim().charAt(0) == 'L')// lvl
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),    								3, 20000, 1, 1, rid, "address", add)).start();
    					if (managerID.trim().charAt(0) == 'D')// ddo
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),    								3, 10000, 2, 1, rid, "address", add)).start();
                	}
                	if(comboBox.getSelectedIndex() == 1)//phone
                	{
    					String pho = textField_2.getText().trim();
    					regEx = "^[0-9]*$";
    					pattern = Pattern.compile(regEx);
    					matcher = pattern.matcher(pho);
    					rs = matcher.matches();
    					if (rs == false)
    					{
    						JOptionPane.showMessageDialog(panel, "Wrong phone number!", "Title",
    								JOptionPane.WARNING_MESSAGE);
    						textField_2.setText("");
    						return;
    					} 
    					else
    					{
    						if (managerID.trim().charAt(0) == 'M')// mtl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 0, 1, rid, "phone", pho)).start();
    						if (managerID.trim().charAt(0) == 'L')// lvl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 1, 1, rid, "phone", pho)).start();
    						if (managerID.trim().charAt(0) == 'D')// ddo
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 2, 1, rid, "phone", pho)).start();
    					}
                	}
                	
                	if(comboBox.getSelectedIndex() == 2)//location
                	{
    					String loc = textField_2.getText().trim();
    					regEx = "^[A-Za-z]+$";
    					pattern = Pattern.compile(regEx);
    					matcher = pattern.matcher(loc);
    					rs = matcher.matches();
    					if (rs == false)
    					{
    						JOptionPane.showMessageDialog(panel, "Wrong location!", "Title",
    								JOptionPane.WARNING_MESSAGE);//Only LVL, MTL and DDO are ok.
    						textField_2.setText("");
    						return;
    					} 
    					else
    					{
    						if (managerID.trim().charAt(0) == 'M')// mtl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 0, 1, rid, "location", loc)).start();
    						if (managerID.trim().charAt(0) == 'L')// lvl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 1, 1, rid, "location", loc)).start();
    						if (managerID.trim().charAt(0) == 'D')// ddo
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 2, 1, rid, "location", loc)).start();
    					}
                	}
                	if(comboBox.getSelectedIndex()==3||comboBox.getSelectedIndex()==4)
                	{
    					JOptionPane.showMessageDialog(panel,
    							"Wrong Field Name!\nEditable fieldname for teacher record are Address, Phone and Location\nEditable fieldname for student record are courses registered and status.",
    							"Title", JOptionPane.WARNING_MESSAGE);
    					textField_2.setText("");
    					return;
                	}
                }
                else
                {
                	if(comboBox.getSelectedIndex() == 3)//courseRegestered
                	{
    					String str = textField_2.getText().trim();
    					ArrayList<String> al = new ArrayList();
    					StringTokenizer tokenizer = new StringTokenizer(str);
    					while (tokenizer.hasMoreTokens())
    					{
    						al.add(tokenizer.nextToken());
    					}
    					String[] cr = new String[al.size()];
    					for (int i = 0; i < al.size(); i++)
    					{
    						cr[i] = al.get(i).trim();
    					}

    					if (managerID.trim().charAt(0) == 'M')// mtl
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    								3, 25000, 0, 1,       rid, "courseRegistered", cr)).start();
    					if (managerID.trim().charAt(0) == 'L')// lvl
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    								3, 25000, 1, 1,       rid, "courseRegistered", cr)).start();
    					if (managerID.trim().charAt(0) == 'D')// ddo
    						new Thread(new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    								3, 25000, 2, 1,       rid, "courseRegistered", cr)).start();
                	}
                	
                	if(comboBox.getSelectedIndex() == 4)//activestatus
                	{
    					String sta = textField_2.getText().trim();
    					regEx = "^(active|inactive)$";
    					pattern = Pattern.compile(regEx);
    					matcher = pattern.matcher(sta);
    					rs = matcher.matches();
    					if (rs == false)
    					{
    						JOptionPane.showMessageDialog(panel, "Wrong status! Only active and inactive", "Title",
    								JOptionPane.WARNING_MESSAGE);
    						textField_2.setText("");
    						return;
    					} 
    					else
    					{
    						String str=(sta.charAt(0)=='a')?"true":"false";
    						if (managerID.trim().charAt(0) == 'M')// mtl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 0, 1,     rid, "activeStatus", str)).start();
    						if (managerID.trim().charAt(0) == 'L')// lvl
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 1, 1,     rid, "activeStatus", str)).start();
    						if (managerID.trim().charAt(0) == 'D')// ddo
    							new Thread(
    									new TestThread1(sucNo,Integer.parseInt(managerID.substring(3, managerID.trim().length())),
    											3, 25000, 2, 1,     rid, "activeStatus", str)).start();
    					}
                	}
                	if(comboBox.getSelectedIndex() == 0||comboBox.getSelectedIndex() == 1||comboBox.getSelectedIndex() == 2)
                	{
    					JOptionPane.showMessageDialog(panel,
    							"Wrong Field Name!\nEditable fieldname for teacher record are Address, Phone and Location\nEditable fieldname for student record are courses registered and status.",
    							"Title", JOptionPane.WARNING_MESSAGE);
    					textField_2.setText("");
    					return;
                	}
                }
			}
		});
		button_1.setBounds(141, 130, 117, 25);
		frame.getContentPane().add(button_1);

	}
}

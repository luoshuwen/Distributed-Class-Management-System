import java.awt.*;
import java.util.regex.*;
import javax.swing.*;
import java.awt.event.*;

public class Default1
{

	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		Meta.setRegisterPortNoMTL(41126);
		Meta.setUdpAnswerPortNoMTL(31126);
		Meta.setRegisterPortNoLVL(41226);
		Meta.setUdpAnswerPortNoLVL(31226);
		Meta.setRegisterPortNoDDO(41326);
		Meta.setUdpAnswerPortNoDDO(31326);
		TestThread1.setCc(0);
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Default1 window = new Default1();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Default1()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(127, 103, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblManagerid = new JLabel("ManagerID:");
		lblManagerid.setBounds(23, 96, 136, 32);
		frame.getContentPane().add(lblManagerid);
		
		JPanel panel = new JPanel();
		panel.setBounds(65, 209, 10, 10);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str=textField.getText().trim();
			    String regEx = "^(MTL|LVL|DDO)\\d{4}$";
			    Pattern pattern = Pattern.compile(regEx);
			    //Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			    Matcher matcher = pattern.matcher(str);
			    boolean rs = matcher.matches();
			    if(rs==false)//discrepant
			    {
			    	JOptionPane.showMessageDialog(panel, "Wrong ManagerID!", "Title",JOptionPane.WARNING_MESSAGE);
			    }
			    else//accord with
			    {
			    	frame.setVisible(false);
			    	Default2 window2=new Default2(textField.getText().trim(),frame);
			    	window2.frame.setVisible(true);
			    }
			}
		});
		btnNewButton.setBounds(267, 100, 117, 25);
		frame.getContentPane().add(btnNewButton);
		

	}
}

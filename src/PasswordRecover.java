import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.swing.JComboBox;
public class PasswordRecover extends JFrame {
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField usertextField;
	private ResultSet rs=null;
	JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordRecover frame = new PasswordRecover();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public PasswordRecover() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Login().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 414, 272);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 102, 73, 26);
		contentPane.add(lblNewLabel);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		emailTextField.setBounds(123, 101, 263, 30);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(152, 251, 152));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice=comboBox.getSelectedItem().toString();
				if(usertextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please provide user name");
					return;
				}
				if(emailTextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please provide an email");
					return;
				}
				Pattern p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
				Matcher mt=p.matcher(emailTextField.getText());
				if(!(mt.find()&&mt.group().equals(emailTextField.getText())))
				{
					JOptionPane.showMessageDialog(null, "Please enter correct email address","Error Message",JOptionPane.ERROR_MESSAGE);
					emailTextField.requestFocus();
					return;
				}
				if(!Login.testInternetConnection())
				{
					JOptionPane.showMessageDialog(null, "Please connect to internet");
					return;
				}
				if(choice.equals("Choose Your Designation"))
				{
					JOptionPane.showMessageDialog(null, "Plese select your designation");
					return;
				}
				if(choice.equals("User"))
				{
					Connection con=OracleConnection.getConnection();
					try {
						PreparedStatement ps=con.prepareStatement("select email,password from verifieduser where username=?");
						ps.setString(1, MyCrypto.encrypt(usertextField.getText()));
						rs=ps.executeQuery();
						if(!rs.next())
						{
							JOptionPane.showMessageDialog(null, "User name doesn't exist");
							return;
						}
						if(!MyCrypto.decrypt(rs.getString(1)).equals(emailTextField.getText()))
						{
							JOptionPane.showMessageDialog(null, "email doesn't exist");
							return;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(choice.equals("Admin"))
				{
					Connection con=OracleConnection.getConnection();
					try {
						PreparedStatement ps=con.prepareStatement("select email,password from admin where username=?");
						ps.setString(1, MyCrypto.encrypt(usertextField.getText()));
						rs=ps.executeQuery();
						if(!rs.next())
						{
							JOptionPane.showMessageDialog(null, "User name doesn't exist");
							return;
						}
						if(!MyCrypto.decrypt(rs.getString(1)).equals(emailTextField.getText()))
						{
							JOptionPane.showMessageDialog(null, "email doesn't exist");
							return;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Properties props=new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port","465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth","true");
				props.put("mail.smtp.port", "465");
				Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator()
						{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication("narayanraghuvansh@gmail.com","Rn@124356");
					}
						});
				try {
					Message message=new MimeMessage(session);
					message.setFrom(new InternetAddress("narayanraghuvansh@gmail.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTextField.getText()));
					message.setSubject("Password Recovery Message");
					message.setText("Hey! "+usertextField.getText()+", Your Password is "+MyCrypto.decrypt(rs.getString(2)));
					Transport.send(message);
					JOptionPane.showMessageDialog(null, "Password has been sent. Please! check your email");
					usertextField.setText("");
					emailTextField.setText("");
					usertextField.requestFocus();
					comboBox.setSelectedIndex(0);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		btnNewButton.setBounds(123, 188, 115, 30);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 402, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Recover Your Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(45, 11, 295, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblUserName.setBounds(20, 61, 93, 26);
		contentPane.add(lblUserName);
		
		usertextField = new JTextField();
		usertextField.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		usertextField.setColumns(10);
		usertextField.setBounds(123, 60, 263, 30);
		contentPane.add(usertextField);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		btnCancel.setBounds(269, 188, 115, 30);
		contentPane.add(btnCancel);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblDesignation.setBounds(20, 139, 93, 26);
		contentPane.add(lblDesignation);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		comboBox.addItem("Choose Your Designation");
		comboBox.addItem("Admin");
		comboBox.addItem("User");
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(123, 142, 263, 30);
		contentPane.add(comboBox);
	}
}
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.event.ItemListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField usertextField;
	private JPasswordField passwordField;
	private JButton btnRegisterWithUs;
	private JTextField recapcha;
	private JTextField capcha;
	private String s;
	private JProgressBar progressBar;
	private Timer t;
	private int i;
	private JComboBox comboBox;
	public static String user,name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Login() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Welcome().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 553, 477);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(this.getClass().getResource("/keyring-icon.png")));
		label.setBounds(10, 72, 130, 132);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(208, 85, 94, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(208, 118, 84, 22);
		contentPane.add(lblPassword);
		
		usertextField = new JTextField();
		usertextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usertextField.setBounds(348, 85, 142, 22);
		contentPane.add(usertextField);
		usertextField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user=usertextField.getText();
				String password=passwordField.getText();
				String str=(String)comboBox.getSelectedItem();
				if(user.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter username");
					return;
				}
				if(password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter password");
					usertextField.requestFocus();
					return;
				}
				if(capcha.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter capcha");
					capcha.requestFocus();
					return;
				}
				/*if(!testInternetConnection())
				{
					JOptionPane.showMessageDialog(null, "Please connect to internet");
					return;
				}*/
				if(recapcha.getText().equals(capcha.getText()))
				{
					if(str.equals("User"))
					{
						Connection con=OracleConnection.getConnection();
						try {
							PreparedStatement ps=con.prepareStatement("select * from verifieduser where username=? and password=?");
							ps.setString(1, MyCrypto.encrypt(user));
							ps.setString(2, MyCrypto.encrypt(password));
							ResultSet rs=ps.executeQuery();
							if(rs.next())
							{
								name=MyCrypto.decrypt(rs.getString(3))+" "+MyCrypto.decrypt(rs.getString(4));
								progressBar.setVisible(true);
								btnLogin.setEnabled(false);
								t.start();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Username or Password incorrect");
								usertextField.setText("");
								passwordField.setText("");
								capcha.setText("");
								comboBox.setSelectedIndex(0);
								char[] pass=MyCapcha.geek_Password(6);
								s="";
								for(char c:pass)
								{
									s=s+c;
								}
								recapcha.setText(s);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e);
						}
						finally {
							try {
								con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,e);
							}
						}
					}
					else if(str.equals("Admin"))
					{
						user=usertextField.getText();
						password=passwordField.getText();
						Connection con=OracleConnection.getConnection();
						try {
							PreparedStatement ps=con.prepareStatement("select * from admin where username=? and password=?");
							ps.setString(1, MyCrypto.encrypt(user));
							ps.setString(2, MyCrypto.encrypt(password));
							ResultSet rs=ps.executeQuery();
							if(rs.next())
							{
								progressBar.setVisible(true);
								btnLogin.setEnabled(false);
								t.start();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Admin Username or Password incorrect");
								usertextField.setText("");
								passwordField.setText("");
								capcha.setText("");
								comboBox.setSelectedIndex(0);
								char[] pass=MyCapcha.geek_Password(6);
								s="";
								for(char c:pass)
								{
									s=s+c;
								}
								recapcha.setText(s);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Plese! Enter correct Capcha");
					capcha.setText("");
				}
			}
		});
		btnLogin.setEnabled(false);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 128, 0));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(358, 282, 130, 29);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Welcome().show();
				dispose();
			}
		});
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(218, 282, 130, 29);
		contentPane.add(btnCancel);
		
		btnRegisterWithUs = new JButton("Register With Us");
		btnRegisterWithUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration().setVisible(true);
				dispose();
			}
		});
		btnRegisterWithUs.setEnabled(false);
		btnRegisterWithUs.setForeground(Color.WHITE);
		btnRegisterWithUs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegisterWithUs.setBackground(new Color(210, 105, 30));
		btnRegisterWithUs.setBounds(217, 322, 273, 29);
		contentPane.add(btnRegisterWithUs);
		
		comboBox=new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String str=(String)comboBox.getSelectedItem();
				if(str.equals("Admin"))
				{
					btnLogin.setEnabled(true);
					btnRegisterWithUs.setEnabled(false);
				}
				else if(str.equals("User"))
				{
					btnLogin.setEnabled(true);
					btnRegisterWithUs.setEnabled(true);
				}
				else
				{
					btnLogin.setEnabled(false);
					btnRegisterWithUs.setEnabled(false);
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(348, 154, 142, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addItem("User");
		comboBox.addItem("Admin");
		
		JLabel lblLoginAs = new JLabel("Login As");
		lblLoginAs.setForeground(new Color(0, 0, 0));
		lblLoginAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginAs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoginAs.setBounds(208, 151, 84, 22);
		contentPane.add(lblLoginAs);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(348, 118, 142, 22);
		contentPane.add(passwordField);
		
		JLabel lblCapcha = new JLabel("Capcha");
		lblCapcha.setForeground(new Color(0, 0, 0));
		lblCapcha.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapcha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCapcha.setBounds(214, 198, 63, 22);
		contentPane.add(lblCapcha);
		
		recapcha = new JTextField();
		recapcha.setHorizontalAlignment(SwingConstants.CENTER);
		recapcha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recapcha.setEditable(false);
		recapcha.setBounds(348, 197, 142, 32);
		contentPane.add(recapcha);
		recapcha.setColumns(10);

		capcha = new JTextField();
		capcha.setBounds(348, 240, 142, 22);
		contentPane.add(capcha);
		capcha.setColumns(10);
		
		JLabel lblEnterAboveCapcha = new JLabel("Enter Capcha");
		lblEnterAboveCapcha.setForeground(new Color(0, 0, 0));
		lblEnterAboveCapcha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAboveCapcha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterAboveCapcha.setBounds(218, 244, 105, 22);
		contentPane.add(lblEnterAboveCapcha);
		
		char[] pass=MyCapcha.geek_Password(6);
		s="";
		for(char c:pass)
		{
			s=s+c;
		}
		recapcha.setText(s);
		
		progressBar = new JProgressBar(0,20);
		progressBar.setStringPainted(true);
		progressBar.setBounds(217, 402, 273, 33);
		contentPane.add(progressBar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 553, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginHere = new JLabel("Login Here");
		lblLoginHere.setBounds(181, 11, 216, 37);
		panel.add(lblLoginHere);
		lblLoginHere.setForeground(new Color(255, 255, 255));
		lblLoginHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginHere.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PasswordRecover().setVisible(true);
				dispose();
			}
		});
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnForgotPassword.setBackground(new Color(0, 206, 209));
		btnForgotPassword.setBounds(217, 362, 273, 29);
		contentPane.add(btnForgotPassword);
		progressBar.setVisible(false);
		t=new Timer(100,this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(i==20)
		{
			if(comboBox.getSelectedItem().equals("User"))
			{
				new User().setVisible(true);
				dispose();
			}
			else
			{
				new Admin().setVisible(true);
				dispose();
			}
		}
		i++;
		progressBar.setValue(i);
	}
	public static boolean testInternetConnection()
	{
		Socket socket=new Socket();
		InetSocketAddress addr=new InetSocketAddress("www.google.com",80);
		try
		{
			socket.connect(addr,3000);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (Exception ee)
			{
			}
		}
	}
}
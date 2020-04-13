import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
@SuppressWarnings("serial")
public class Registration extends JFrame {
	private JPanel contentPane;
	private JTextField usertextField;
	private JTextField fnametextField;
	private JTextField lastnametextField;
	private JTextField mobiletextfield;
	private JTextField college;
	@SuppressWarnings("rawtypes")
	private JComboBox date,month,year,semester,coursename;
	JRadioButton rdbtnMale,rdbtnFemale;
	private JPasswordField passwordField;
	private JTextField recapcha;
	private JTextField capchatext;
	private JTextField emailtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	@SuppressWarnings("rawtypes")
	public Registration() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Login().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setTitle("Student Registration Form");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 736, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(42, 86, 77, 28);
		contentPane.add(lblUserName);
		
		usertextField = new JTextField();
		usertextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usertextField.setBounds(136, 86, 201, 28);
		contentPane.add(usertextField);
		usertextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(42, 130, 77, 28);
		contentPane.add(lblFirstName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(42, 174, 77, 24);
		contentPane.add(lblGender);
		
		fnametextField = new JTextField();
		fnametextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fnametextField.setColumns(10);
		fnametextField.setBounds(136, 131, 201, 28);
		contentPane.add(fnametextField);
		
		ButtonGroup g=new ButtonGroup();
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		rdbtnMale.setBackground(new Color(224, 255, 255));
		rdbtnMale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMale.setBounds(136, 175, 57, 23);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(new Color(224, 255, 255));
		rdbtnFemale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFemale.setBounds(201, 175, 70, 23);
		contentPane.add(rdbtnFemale);
		g.add(rdbtnMale);
		g.add(rdbtnFemale);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDob.setBounds(42, 213, 77, 21);
		contentPane.add(lblDob);
		
		date = new JComboBox();
		date.setBackground(new Color(224, 255, 255));
		date.setBounds(136, 215, 47, 20);
		contentPane.add(date);
		int dd[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		for(int d:dd)
		{
			String s;
			if(d<10)
				s="0"+d;
			else
				s=""+d;
			date.addItem(s);
		}
		
		month = new JComboBox();
		month.setBackground(new Color(224, 255, 255));
		month.setBounds(201, 215, 57, 20);
		contentPane.add(month);
		String mm[]= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(String m:mm)
			month.addItem(m);
		
		year = new JComboBox();
		year.setBackground(new Color(224, 255, 255));
		year.setBounds(268, 215, 69, 20);
		contentPane.add(year);
		int yyyy[]= {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997};
		for(int y:yyyy)
			year.addItem(y);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(42, 245, 77, 21);
		contentPane.add(lblAddress);
		
		JTextArea address = new JTextArea();
		address.setFont(new Font("Monospaced", Font.PLAIN, 16));
		address.setBounds(136, 245, 554, 60);
		contentPane.add(address);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(415, 85, 70, 30);
		contentPane.add(lblPassword);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(415, 130, 70, 28);
		contentPane.add(lblLastName);
		
		lastnametextField = new JTextField();
		lastnametextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastnametextField.setColumns(10);
		lastnametextField.setBounds(495, 132, 195, 26);
		contentPane.add(lastnametextField);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobile.setBounds(415, 174, 70, 24);
		contentPane.add(lblMobile);
		
		mobiletextfield = new JTextField();
		mobiletextfield.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mobiletextfield.setColumns(10);
		mobiletextfield.setBounds(495, 176, 195, 26);
		contentPane.add(mobiletextfield);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSemester.setBounds(553, 213, 57, 21);
		contentPane.add(lblSemester);
		
		semester = new JComboBox();
		semester.setBackground(new Color(224, 255, 255));
		semester.setBounds(613, 215, 77, 20);
		contentPane.add(semester);
		String[] sem= {"Select","First","Second","Third","Fourth","Fifth","Sixth"};
		for(String s:sem)
			semester.addItem(s);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourse.setBounds(415, 213, 57, 21);
		contentPane.add(lblCourse);
		
		coursename = new JComboBox();
		coursename.setBackground(new Color(224, 255, 255));
		coursename.setBounds(479, 215, 64, 20);
		contentPane.add(coursename);
		
		JLabel lblCollege = new JLabel("College");
		lblCollege.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCollege.setBounds(42, 322, 77, 32);
		contentPane.add(lblCollege);
		
		college = new JTextField();
		college.setFont(new Font("Tahoma", Font.PLAIN, 16));
		college.setColumns(10);
		college.setBounds(136, 324, 554, 30);
		contentPane.add(college);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username=usertextField.getText();
				String password=passwordField.getText();
				String firstName=fnametextField.getText();
				String lastName=lastnametextField.getText();
				String email=emailtextField.getText();
				JRadioButton select=null;
				if(rdbtnMale.isSelected())
					select=rdbtnMale;
				if(rdbtnFemale.isSelected())
					select=rdbtnFemale;
				String gen=select.getLabel();
				String mobile=mobiletextfield.getText();
				String d=date.getSelectedItem().toString();
				String m=month.getSelectedItem().toString();
				String y=year.getSelectedItem().toString();
				String dob=d+"/"+m+"/"+y;
				String course=coursename.getSelectedItem().toString();
				String sem=semester.getSelectedItem().toString();
				String addr=address.getText();
				String clg=college.getText();
				if(username.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter username","Error Message",JOptionPane.ERROR_MESSAGE);
					usertextField.requestFocus();
					return;
				}
				Pattern p=Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
				Matcher mt=p.matcher(username);
				if(!(mt.find()&&mt.group().equals(username)))
				{
					JOptionPane.showMessageDialog(null, "Username should be alphanumeric and start with an alphabet","Error Message",JOptionPane.ERROR_MESSAGE);
					usertextField.requestFocus();
					return;
				}
				if(password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter password","Error Message",JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}
				p=Pattern.compile(".{6}");
				mt=p.matcher(password);
				if(!(mt.find()&&mt.group().equals(password)))
				{
					JOptionPane.showMessageDialog(null, "Password should be six character long","Error Message",JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}
				if(firstName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter first name","Error Message",JOptionPane.ERROR_MESSAGE);
					fnametextField.requestFocus();
					return;
				}
				p=Pattern.compile("[a-zA-Z]*");
				mt=p.matcher(firstName);
				if(!(mt.find()&&mt.group().equals(firstName)))
				{
					JOptionPane.showMessageDialog(null, "Firstname contains alphabet only","Error Message",JOptionPane.ERROR_MESSAGE);
					fnametextField.requestFocus();
					return;
				}
				if(lastName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter last name","Error Message",JOptionPane.ERROR_MESSAGE);
					lastnametextField.requestFocus();
					return;
				}
				p=Pattern.compile("[a-zA-Z]*");
				mt=p.matcher(lastName);
				if(!(mt.find()&&mt.group().equals(lastName)))
				{
					JOptionPane.showMessageDialog(null, "Lastname contains alphabet only","Error Message",JOptionPane.ERROR_MESSAGE);
					lastnametextField.requestFocus();
					return;
				}
				if(mobile.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter mobile number","Error Message",JOptionPane.ERROR_MESSAGE);
					mobiletextfield.requestFocus();
					return;
				}
				p=Pattern.compile("(0|91)?[7-9][0-9]{9}");
				mt=p.matcher(mobile);
				if(!(mt.find()&&mt.group().equals(mobile)))
				{
					JOptionPane.showMessageDialog(null, "Not a valid mobile number","Error Message",JOptionPane.ERROR_MESSAGE);
					mobiletextfield.requestFocus();
					return;
				}
				if(course.equals("Select"))
				{
					JOptionPane.showMessageDialog(null, "Please select a course","Error Message",JOptionPane.ERROR_MESSAGE);
					coursename.requestFocus();
					return;
				}
				if(sem.equals("Select"))
				{
					JOptionPane.showMessageDialog(null, "Please select semester","Error Message",JOptionPane.ERROR_MESSAGE);
					semester.requestFocus();
					return;
				}
				if(addr.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter address","Error Message",JOptionPane.ERROR_MESSAGE);
					address.requestFocus();
					return;
				}
				p=Pattern.compile("[a-zA-Z0-9-+/ ,]*");
				mt=p.matcher(addr);
				if(!(mt.find()&&mt.group().equals(addr)))
				{
					JOptionPane.showMessageDialog(null, "Please enter correct address","Error Message",JOptionPane.ERROR_MESSAGE);
					address.requestFocus();
					return;
				}
				if(clg.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter college name","Error Message",JOptionPane.ERROR_MESSAGE);
					college.requestFocus();
					return;
				}
				p=Pattern.compile("[a-zA-Z ]*");
				mt=p.matcher(clg);
				if(!(mt.find()&&mt.group().equals(clg)))
				{
					JOptionPane.showMessageDialog(null, "College contains alphabet only","Error Message",JOptionPane.ERROR_MESSAGE);
					college.requestFocus();
					return;
				}
				if(email.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter college name","Error Message",JOptionPane.ERROR_MESSAGE);
					emailtextField.requestFocus();
					return;
				}
				p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
				mt=p.matcher(email);
				if(!(mt.find()&&mt.group().equals(email)))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid email","Error Message",JOptionPane.ERROR_MESSAGE);
					emailtextField.requestFocus();
					return;
				}
				if(capchatext.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter capcha","Error Message",JOptionPane.ERROR_MESSAGE);
					capchatext.requestFocus();
					return;
				}
				if(recapcha.getText().equals(capchatext.getText()))
				{
					int res=JOptionPane.showConfirmDialog(null, "Are you checked availability of username?","Confirmation",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION)
					{
						Connection con=OracleConnection.getConnection();
						try {
							PreparedStatement ps=con.prepareStatement("insert into userinfo values (?,?,?,?,?,?,?,?,?,?,?,?)");
							ps.setString(1, MyCrypto.encrypt(username));
							ps.setString(2, MyCrypto.encrypt(password));
							ps.setString(3, MyCrypto.encrypt(firstName));
							ps.setString(4, MyCrypto.encrypt(lastName));
							ps.setString(5, MyCrypto.encrypt(gen));
							ps.setString(6, MyCrypto.encrypt(mobile));
							ps.setString(7, MyCrypto.encrypt(dob));
							ps.setString(8, MyCrypto.encrypt(course));
							ps.setString(9, MyCrypto.encrypt(sem));
							ps.setString(10, MyCrypto.encrypt(addr));
							ps.setString(11, MyCrypto.encrypt(clg));
							ps.setString(12, MyCrypto.encrypt(email));
							int row=ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "You have succefully registered. You will be able to login after verification Username="+username+" Password="+password);
							usertextField.setText("");
							passwordField.setText("");
							fnametextField.setText("");
							lastnametextField.setText("");
							g.clearSelection();
							mobiletextfield.setText("");
							date.setSelectedIndex(-1);
							month.setSelectedIndex(-1);
							year.setSelectedIndex(-1);
							coursename.setSelectedIndex(-1);
							semester.setSelectedIndex(-1);
							address.setText("");
							college.setText("");
							emailtextField.setText("");
							capchatext.setText("");
							char[] pass=MyCapcha.geek_Password(6);
							String s="";
							for(char c:pass)
							{
								s=s+c;
							}
							recapcha.setText(s);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Check availability before register.");
						}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please enter correct capcha","Error Message",JOptionPane.ERROR_MESSAGE);
					capchatext.setText("");
				}
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(581, 451, 110, 34);
		contentPane.add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(495, 86, 195, 28);
		contentPane.add(passwordField);
		
		recapcha = new JTextField();
		recapcha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recapcha.setHorizontalAlignment(SwingConstants.CENTER);
		recapcha.setBackground(new Color(189, 183, 107));
		recapcha.setEditable(false);
		recapcha.setBounds(136, 406, 146, 34);
		contentPane.add(recapcha);
		recapcha.setColumns(10);
		
		JLabel capcha = new JLabel("Capcha");
		capcha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		capcha.setBounds(42, 408, 77, 32);
		contentPane.add(capcha);
		
		capchatext = new JTextField();
		capchatext.setToolTipText("Enter Capcha");
		capchatext.setBounds(307, 406, 216, 34);
		contentPane.add(capchatext);
		capchatext.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBounds(439, 451, 110, 34);
		contentPane.add(btnCancel);
		String[] course= {"Select","B.Sc","MCA","M.Sc."};
		for(String c:course)
			coursename.addItem(c);
		char[] pass=MyCapcha.geek_Password(6);
		String s="";
		for(char c:pass)
		{
			s=s+c;
		}
		recapcha.setText(s);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=usertextField.getText();
				if(user.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter username","Error Message",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Pattern p=Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
				Matcher mt=p.matcher(user);
				if(!(mt.find()&&mt.group().equals(user)))
				{
					JOptionPane.showMessageDialog(null, "Username should be alphanumeric and start with an alphabet","Error Message",JOptionPane.ERROR_MESSAGE);
					usertextField.requestFocus();
					return;
				}
				Connection con=OracleConnection.getConnection();
				try 
				{
					PreparedStatement ps=con.prepareStatement("select * from verifieduser where username=?");
					ps.setString(1, MyCrypto.encrypt(user));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Username "+ user +" already exists.");
						usertextField.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username "+user+" available.");
					}
						
				} 
				catch (SQLException e) 
				{
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCheck.setForeground(new Color(255, 255, 255));
		btnCheck.setBackground(new Color(100, 149, 237));
		btnCheck.setBounds(340, 86, 70, 28);
		contentPane.add(btnCheck);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 736, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRegistrationForm = new JLabel("User Registration Form");
		lblRegistrationForm.setForeground(new Color(255, 255, 255));
		lblRegistrationForm.setBounds(95, 0, 534, 68);
		panel.add(lblRegistrationForm);
		lblRegistrationForm.setFont(new Font("Tekton Pro Ext", Font.BOLD, 40));
		lblRegistrationForm.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(42, 365, 77, 32);
		contentPane.add(lblEmail);
		
		emailtextField = new JTextField();
		emailtextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailtextField.setColumns(10);
		emailtextField.setBounds(136, 367, 554, 30);
		contentPane.add(emailtextField);
	}
}
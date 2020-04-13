import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Verification extends JFrame {
	private JPanel contentPane;
	private JTextField firstnametextField;
	private JTextField lastnametextField;
	private JTextField mobiletextField;
	private JTextField collegetextField;
	private JComboBox dd,mm,yyyy,course,semester,usercomboBox;
	private String pass;
	private JRadioButton rbtnmale,rbtnfemale;
	private JTextArea textArea;
	private JTextField emailtextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verification frame = new Verification();
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
	public Verification() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Admin().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 736, 462);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 736, 462);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("User Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(42, 86, 77, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("First Name");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(42, 130, 77, 28);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Gender");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(42, 174, 77, 24);
		panel.add(label_3);
		
		firstnametextField = new JTextField();
		firstnametextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstnametextField.setColumns(10);
		firstnametextField.setBounds(136, 131, 201, 28);
		panel.add(firstnametextField);
		
		ButtonGroup g=new ButtonGroup();
		rbtnmale = new JRadioButton("Male");
		rbtnmale.setHorizontalAlignment(SwingConstants.CENTER);
		rbtnmale.setBackground(new Color(224, 255, 255));
		rbtnmale.setBounds(136, 175, 57, 23);
		panel.add(rbtnmale);
		
		rbtnfemale = new JRadioButton("Female");
		rbtnfemale.setHorizontalAlignment(SwingConstants.CENTER);
		rbtnfemale.setBackground(new Color(224, 255, 255));
		rbtnfemale.setBounds(201, 175, 70, 23);
		panel.add(rbtnfemale);
		g.add(rbtnmale);
		g.add(rbtnfemale);
		
		JLabel label_4 = new JLabel("DOB");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(42, 213, 77, 21);
		panel.add(label_4);
		
		dd = new JComboBox();
		dd.setBackground(new Color(224, 255, 255));
		dd.setBounds(136, 215, 47, 20);
		panel.add(dd);
		int date[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		for(int d:date)
		{
			String s;
			if(d<10)
				s="0"+d;
			else
				s=""+d;
			dd.addItem(s);
		}
		
		mm = new JComboBox();
		mm.setBackground(new Color(224, 255, 255));
		mm.setBounds(201, 215, 57, 20);
		panel.add(mm);
		String month[]= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(String m:month)
			mm.addItem(m);
		
		yyyy = new JComboBox();
		yyyy.setBackground(new Color(224, 255, 255));
		yyyy.setBounds(268, 215, 69, 20);
		panel.add(yyyy);
		int year[]= {1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997};
		for(int y:year)
			yyyy.addItem(y);
		
		JLabel label_5 = new JLabel("Address");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(42, 245, 77, 21);
		panel.add(label_5);
		
		JLabel label_7 = new JLabel("Last Name");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(400, 130, 85, 28);
		panel.add(label_7);
		
		lastnametextField = new JTextField();
		lastnametextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastnametextField.setColumns(10);
		lastnametextField.setBounds(495, 132, 195, 26);
		panel.add(lastnametextField);
		
		JLabel label_8 = new JLabel("Mobile");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(400, 174, 85, 24);
		panel.add(label_8);
		
		mobiletextField = new JTextField();
		mobiletextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mobiletextField.setColumns(10);
		mobiletextField.setBounds(495, 176, 195, 26);
		panel.add(mobiletextField);
		
		JLabel label_9 = new JLabel("Semester");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_9.setBounds(553, 213, 70, 21);
		panel.add(label_9);
		
		semester = new JComboBox();
		semester.setBackground(new Color(224, 255, 255));
		semester.setBounds(620, 215, 70, 20);
		panel.add(semester);
		String[] sem= {"Select","First","Second","Third","Fourth","Fifth","Sixth"};
		for(String s:sem)
			semester.addItem(s);
		
		JLabel label_10 = new JLabel("Course");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_10.setBounds(400, 213, 72, 21);
		panel.add(label_10);
		
		course = new JComboBox();
		course.setBackground(new Color(224, 255, 255));
		course.setBounds(473, 215, 70, 20);
		panel.add(course);
		String[] cou= {"Select","B.Sc","MCA","M.Sc."};
		for(String c:cou)
			course.addItem(c);
		
		JLabel label_11 = new JLabel("College");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_11.setBounds(42, 322, 77, 32);
		panel.add(label_11);
		
		collegetextField = new JTextField();
		collegetextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		collegetextField.setColumns(10);
		collegetextField.setBounds(136, 324, 554, 30);
		panel.add(collegetextField);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=usercomboBox.getSelectedItem().toString();
				String password=pass;
				String first=firstnametextField.getText();
				String last=lastnametextField.getText();
				JRadioButton select=null;
				if(rbtnmale.isSelected())
					select=rbtnmale;
				if(rbtnfemale.isSelected())
					select=rbtnfemale;
				@SuppressWarnings("deprecation")
				String gen=select.getLabel();
				String mobile=mobiletextField.getText();
				String d=dd.getSelectedItem().toString();
				String m=mm.getSelectedItem().toString();
				String y=yyyy.getSelectedItem().toString();
				String dob=d+"/"+m+"/"+y;
				String coursename=course.getSelectedItem().toString();
				String sem=semester.getSelectedItem().toString();
				String addr=textArea.getText();
				String clg=collegetextField.getText();
				String email=emailtextField.getText();
				Connection con=OracleConnection.getConnection();
				Connection conn=OracleConnection.getConnection();
				try {
					PreparedStatement ps=con.prepareStatement("insert into verifieduser values(?,?,?,?,?,?,?,?,?,?,?,?)");
					PreparedStatement pss=conn.prepareStatement("delete from userinfo where username=?");
					pss.setString(1,MyCrypto.encrypt(user));
					ps.setString(1, MyCrypto.encrypt(user));
					ps.setString(2, MyCrypto.encrypt(password));
					ps.setString(3, MyCrypto.encrypt(first));
					ps.setString(4, MyCrypto.encrypt(last));
					ps.setString(5, MyCrypto.encrypt(gen));
					ps.setString(6, MyCrypto.encrypt(mobile));
					ps.setString(7, MyCrypto.encrypt(dob));
					ps.setString(8, MyCrypto.encrypt(coursename));
					ps.setString(9, MyCrypto.encrypt(sem));
					ps.setString(10,MyCrypto.encrypt(addr));
					ps.setString(11,MyCrypto.encrypt(clg));
					ps.setString(12,MyCrypto.encrypt(email));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Information Verified Successfully");
					pss.executeUpdate();
					firstnametextField.setText("");
					lastnametextField.setText("");
					mobiletextField.setText("");
					dd.setSelectedIndex(-1);
					mm.setSelectedIndex(-1);
					yyyy.setSelectedIndex(-1);
					course.setSelectedIndex(-1);
					semester.setSelectedIndex(-1);
					textArea.setText("");
					collegetextField.setText("");
					emailtextField.setText("");
					g.clearSelection();
					usercomboBox.removeAllItems();
					loadData();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
		});
		btnVerify.setBackground(new Color(139, 0, 0));
		btnVerify.setForeground(new Color(255, 255, 255));
		btnVerify.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVerify.setBounds(580, 406, 110, 34);
		panel.add(btnVerify);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Admin().setVisible(true);
			}
		});
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(255, 0, 0));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_1.setBounds(433, 406, 110, 34);
		panel.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(135, 249, 555, 64);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		scrollPane.setViewportView(textArea);
		
		usercomboBox = new JComboBox();
		usercomboBox.setBounds(136, 86, 225, 28);
		panel.add(usercomboBox);
		loadData();
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=usercomboBox.getSelectedItem().toString();
				Connection con=OracleConnection.getConnection();
				try {
					PreparedStatement ps=con.prepareStatement("select * from userinfo where username=?");
					ps.setString(1, MyCrypto.encrypt(user));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						pass=MyCrypto.decrypt(rs.getString(2));
						firstnametextField.setText(MyCrypto.decrypt(rs.getString(3)));
						lastnametextField.setText(MyCrypto.decrypt(rs.getString(4)));
						if(MyCrypto.decrypt(rs.getString(5)).equals("Male"))
							rbtnmale.setSelected(true);
						else
							rbtnfemale.setSelected(true);
						mobiletextField.setText(MyCrypto.decrypt(rs.getString(6)));
						String dob=MyCrypto.decrypt(rs.getString(7));
						String s[]=dob.split("/");
						dd.setSelectedItem(s[0]);
						mm.setSelectedItem(s[1]);
						yyyy.setSelectedItem(Integer.parseInt(s[2]));
						course.setSelectedItem(MyCrypto.decrypt(rs.getString(8)));
						semester.setSelectedItem(MyCrypto.decrypt(rs.getString(9)));
						textArea.setText(MyCrypto.decrypt(rs.getString(10)));
						collegetextField.setText(MyCrypto.decrypt(rs.getString(11)));
						emailtextField.setText(MyCrypto.decrypt(rs.getString(12)));
								
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(139, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(396, 86, 294, 28);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBounds(0, 0, 738, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUserVerificationForm = new JLabel("Registration Verification Form");
		lblUserVerificationForm.setForeground(new Color(255, 255, 255));
		lblUserVerificationForm.setBounds(28, 0, 659, 60);
		panel_1.add(lblUserVerificationForm);
		lblUserVerificationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserVerificationForm.setFont(new Font("Tekton Pro Ext", Font.BOLD, 40));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(42, 363, 77, 32);
		panel.add(lblEmail);
		
		emailtextField = new JTextField();
		emailtextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailtextField.setColumns(10);
		emailtextField.setBounds(136, 365, 554, 30);
		panel.add(emailtextField);
		
		
	}
	public void loadData()
	{
		Connection con=OracleConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select username from userinfo");
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					usercomboBox.addItem(MyCrypto.decrypt(rs.getString(1)));
				}
			}
			else
			{
				usercomboBox.addItem("Not Available");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
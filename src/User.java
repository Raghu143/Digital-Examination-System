import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class User extends JFrame {
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox courseCombo;
	public static String coursename;
	public static int time;
	private Connection con;
	private PreparedStatement ps;
	private String s=Login.user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public User() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Logout().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setIconImage(img);
		setIconImage(img);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 616, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=JOptionPane.showInputDialog(null,"Enter new password");
				if(str==null)
				{
					return;
				}
				if(str.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please provide a new password");
					return;
				}
				Connection con=OracleConnection.getConnection();
				try {
					PreparedStatement ps=con.prepareStatement("update verifieduser set password=? where username=?");
					ps.setString(1,MyCrypto.encrypt(str));
					ps.setString(2,MyCrypto.encrypt(s));
					ps.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Password Succefully Changed","Password",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				finally {
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
				}	
			}
		});
		btnChangePassword.setBackground(new Color(255, 165, 0));
		btnChangePassword.setForeground(new Color(255, 255, 255));
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChangePassword.setBounds(10, 11, 162, 31);
		contentPane.add(btnChangePassword);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Logout().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBackground(new Color(139, 0, 0));
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.setBounds(501, 11, 89, 31);
		contentPane.add(btnLogout);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(197, 11, 198, 31);
		contentPane.add(lblWelcome);
		lblWelcome.setText("Welcome, "+Login.user);
		
		JLabel lblSelectCourse = new JLabel("Select Course");
		lblSelectCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectCourse.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 25));
		lblSelectCourse.setBounds(197, 69, 223, 41);
		contentPane.add(lblSelectCourse);
		
		courseCombo = new JComboBox();
		courseCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseCombo.setBounds(207, 116, 223, 31);
		contentPane.add(courseCombo);
		
		
		JButton btnStartTest = new JButton("Start Test");
		btnStartTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(courseCombo.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Select a course");
					return;
				}
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("select * from result where username=? and coursename=?");
					ps.setString(1,MyCrypto.encrypt(s));
					ps.setString(2,MyCrypto.encrypt(courseCombo.getSelectedItem().toString()));
					ResultSet rs=ps.executeQuery();
					//System.out.println(rs.getString(1)+"........."+rs.getString(2));
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "You have already done this subject test");
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String course=courseCombo.getSelectedItem().toString();
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("select * from course where coursename=?");
					ps.setString(1, MyCrypto.encrypt(course));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						coursename=MyCrypto.decrypt(rs.getString(1));
						time=Integer.parseInt(MyCrypto.decrypt(rs.getString(2)));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
				}
				dispose();
				new Exam().setVisible(true);	
			}
		});
		btnStartTest.setForeground(Color.WHITE);
		btnStartTest.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStartTest.setBackground(new Color(255, 165, 0));
		btnStartTest.setBounds(233, 158, 162, 31);
		contentPane.add(btnStartTest);
		
		JLabel lblThereAre = new JLabel("1. There are maximum 10 questions in each course.");
		lblThereAre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThereAre.setBounds(25, 231, 370, 31);
		contentPane.add(lblThereAre);
		
		JLabel lblMaximumTime = new JLabel("2. Maximum time is shown above in minutes.");
		lblMaximumTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaximumTime.setBounds(25, 260, 370, 31);
		contentPane.add(lblMaximumTime);
		
		JLabel lblAfterTime = new JLabel("3. After time out, You will be automatically logout.");
		lblAfterTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAfterTime.setBounds(25, 291, 370, 31);
		contentPane.add(lblAfterTime);
		
		Connection con=OracleConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from course");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				courseCombo.addItem(MyCrypto.decrypt(rs.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {
	private JPanel contentPane;
	public static JLabel lblTimeDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Logout().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 480, 394);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Logout().setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.RED);
		btnLogout.setFont(new Font("Tekton Pro Ext", Font.BOLD, 14));
		btnLogout.setBounds(319, 12, 109, 31);
		contentPane.add(btnLogout);
		
		JButton Verification = new JButton("Verification");
		Verification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Verification().setVisible(true);
			}
		});
		Verification.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		Verification.setBackground(new Color(139, 0, 0));
		Verification.setForeground(new Color(255, 255, 255));
		Verification.setBounds(49, 84, 369, 35);
		contentPane.add(Verification);
		
		JButton btnCourse = new JButton("Course");
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Course().setVisible(true);
				dispose();
			}
		});
		btnCourse.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnCourse.setBackground(new Color(139, 0, 0));
		btnCourse.setForeground(new Color(255, 255, 255));
		btnCourse.setBounds(49, 130, 175, 35);
		contentPane.add(btnCourse);
		
		JButton btnQuestion = new JButton("Question");
		btnQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Question().setVisible(true);
				dispose();
			}
		});
		btnQuestion.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnQuestion.setBackground(new Color(139, 0, 0));
		btnQuestion.setForeground(new Color(255, 255, 255));
		btnQuestion.setBounds(243, 130, 175, 35);
		contentPane.add(btnQuestion);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				String s=Login.user;
				Connection con=OracleConnection.getConnection();
				try {
					PreparedStatement ps=con.prepareStatement("update admin set password=? where username=?");
					ps.setString(1,MyCrypto.encrypt(str));
					ps.setString(2,MyCrypto.encrypt(s));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Password Succefully Changed","Password",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
				finally {
					try {
						con.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,e1);
					}
				}
			}
		});
		btnChangePassword.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnChangePassword.setBackground(new Color(139, 0, 0));
		btnChangePassword.setForeground(new Color(255, 255, 255));
		btnChangePassword.setBounds(49, 219, 369, 35);
		contentPane.add(btnChangePassword);
		
		JButton btnResult = new JButton("Result");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResultData().setVisible(true);
				dispose();
			}
		});
		btnResult.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnResult.setBackground(new Color(139, 0, 0));
		btnResult.setForeground(new Color(255, 255, 255));
		btnResult.setBounds(49, 309, 369, 35);
		contentPane.add(btnResult);
		
		JButton btnNewButton = new JButton("Total User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TotalUser().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(139, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(49, 173, 175, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAddAdmin = new JButton("Total Admin");
		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TotalAdmin().setVisible(true);
				dispose();
			}
		});
		btnAddAdmin.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnAddAdmin.setForeground(Color.WHITE);
		btnAddAdmin.setBackground(new Color(139, 0, 0));
		btnAddAdmin.setBounds(243, 173, 175, 35);
		contentPane.add(btnAddAdmin);
		
		lblTimeDate = new JLabel("Time & Date");
		lblTimeDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeDate.setBounds(10, 12, 252, 23);
		contentPane.add(lblTimeDate);
		
		JLabel lblWelcomeraghu = new JLabel("Welcome, ");
		lblWelcomeraghu.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeraghu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeraghu.setBounds(10, 45, 252, 15);
		contentPane.add(lblWelcomeraghu);
		lblWelcomeraghu.setText("Welcome, "+Login.user);
		
		JButton btnAddAdmin_1 = new JButton("Add Admin");
		btnAddAdmin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminDialog().setVisible(true);
			}
		});
		btnAddAdmin_1.setForeground(Color.WHITE);
		btnAddAdmin_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnAddAdmin_1.setBackground(new Color(139, 0, 0));
		btnAddAdmin_1.setBounds(49, 263, 369, 35);
		contentPane.add(btnAddAdmin_1);
		TimeDate.getTime();
	}
}
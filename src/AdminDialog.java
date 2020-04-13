import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
public class AdminDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField nametextField;
	private JTextField contacttextField;
	private JTextField usertextField;
	private JPasswordField passwordField;
	private JTextField emailtextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminDialog dialog = new AdminDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	/**
	 * Create the dialog.
	 */
	public AdminDialog() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      dispose();
		    }
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		//setUndecorated(true);
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setTitle("Add New Admin");
		setBounds(100, 100, 584, 342);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Name");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(30, 73, 95, 27);
			contentPanel.add(label);
		}
		{
			nametextField = new JTextField();
			nametextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			nametextField.setColumns(10);
			nametextField.setBounds(155, 73, 217, 27);
			contentPanel.add(nametextField);
		}
		{
			JLabel label = new JLabel("Contact");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(45, 152, 85, 27);
			contentPanel.add(label);
		}
		{
			contacttextField = new JTextField();
			contacttextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contacttextField.setColumns(10);
			contacttextField.setBounds(160, 152, 212, 27);
			contentPanel.add(contacttextField);
		}
		{
			JLabel label = new JLabel("Username");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(30, 190, 100, 27);
			contentPanel.add(label);
		}
		{
			usertextField = new JTextField();
			usertextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			usertextField.setColumns(10);
			usertextField.setBounds(160, 190, 212, 27);
			contentPanel.add(usertextField);
		}
		{
			JLabel label = new JLabel("Password");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(35, 229, 95, 27);
			contentPanel.add(label);
		}
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=nametextField.getText();
				String contact=contacttextField.getText();
				String user=usertextField.getText();
				String password=passwordField.getText();
				String email=emailtextField.getText();
				if(name.equals("") | contact.equals("") | user.equals("") | password.equals("")|email.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill all details");
				}
				else
				{
					int res=JOptionPane.showConfirmDialog(null, "Are you check availability for user name?","Confirmation",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION)
					{
						Connection con=OracleConnection.getConnection();
						try {
							PreparedStatement ps=con.prepareStatement("insert into admin values(?,?,?,?,?)");
							ps.setString(1, MyCrypto.encrypt(name));
							ps.setString(2, MyCrypto.encrypt(email));
							ps.setString(3, MyCrypto.encrypt(contact));
							ps.setString(4, MyCrypto.encrypt(user));
							ps.setString(5, MyCrypto.encrypt(password));
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Successfully Added");
							nametextField.setText("");
							emailtextField.setText("");
							contacttextField.setText("");
							usertextField.setText("");
							passwordField.setText("");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You first availability before adding");
					}
				}
			}
		});
		btnAdd.setBackground(new Color(139, 0, 0));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(162, 275, 89, 27);
		contentPanel.add(btnAdd);
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.setBackground(new Color(255, 0, 0));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnCancel.setBounds(261, 275, 89, 27);
			contentPanel.add(btnCancel);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(71, 58, 1, 2);
		contentPanel.add(separator);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(160, 228, 212, 27);
		contentPanel.add(passwordField);
		
		JButton btnCheckAvailability = new JButton("Check Availability");
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=usertextField.getText();
				Connection con=OracleConnection.getConnection();
				if(user.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill the user name");
				}
				else
				{
					try {
						PreparedStatement ps=con.prepareStatement("select * from admin where username=?");
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
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);
					}
				}
			}
		});
		btnCheckAvailability.setForeground(new Color(255, 255, 255));
		btnCheckAvailability.setBackground(new Color(100, 149, 237));
		btnCheckAvailability.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCheckAvailability.setBounds(387, 191, 152, 27);
		contentPanel.add(btnCheckAvailability);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(128, 0, 0));
			panel.setBounds(0, 0, 584, 62);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Add New Admin Here");
				lblNewLabel.setForeground(new Color(255, 255, 255));
				lblNewLabel.setBounds(116, 11, 320, 36);
				panel.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(30, 114, 95, 27);
		contentPanel.add(lblEmail);
		
		emailtextField = new JTextField();
		emailtextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailtextField.setColumns(10);
		emailtextField.setBounds(155, 114, 217, 27);
		contentPanel.add(emailtextField);	
	}
}
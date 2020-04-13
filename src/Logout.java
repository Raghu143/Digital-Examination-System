import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
public class Logout extends JFrame {
	private JPanel contentPane;
	private JLabel clickLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logout frame = new Logout();
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
	public Logout() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      System.exit(0);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setBackground(new Color(128, 0, 0));
		setTitle("Logout ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 585, 252);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("You have successfully logged out of Digital Examination");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 87, 549, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblThankYouFor = new JLabel("Thank you for using Digital Examination for evaluating yourself");
		lblThankYouFor.setForeground(new Color(0, 0, 0));
		lblThankYouFor.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblThankYouFor.setBounds(10, 123, 549, 25);
		contentPane.add(lblThankYouFor);
		
		JLabel lblToLoginTo = new JLabel("to Login to Digital Examination");
		lblToLoginTo.setForeground(new Color(0, 0, 0));
		lblToLoginTo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblToLoginTo.setBounds(111, 159, 405, 25);
		contentPane.add(lblToLoginTo);
		
		clickLabel = new JLabel("Click Here");
		clickLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clickLabel.setForeground(new Color(0, 255, 0));
		clickLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new Login().setVisible(true);
			}
		});
		clickLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		clickLabel.setBounds(10, 161, 93, 21);
		contentPane.add(clickLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 585, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Digital Examination System");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tekton Pro", Font.BOLD, 31));
		lblNewLabel_1.setBounds(147, 19, 377, 54);
		panel.add(lblNewLabel_1);
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo.png")));
		logoLabel.setBounds(62, 9, 75, 64);
		panel.add(logoLabel);
		
		JLabel lblExit = new JLabel("Click Here");
		lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblExit.setForeground(Color.GREEN);
		lblExit.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		lblExit.setBounds(10, 196, 93, 21);
		contentPane.add(lblExit);
		
		JLabel lblToExitFrom = new JLabel("to exit from Digital Examination");
		lblToExitFrom.setForeground(Color.BLACK);
		lblToExitFrom.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblToExitFrom.setBounds(111, 192, 405, 25);
		contentPane.add(lblToExitFrom);
	}
}
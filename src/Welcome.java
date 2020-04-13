import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Welcome extends JFrame {
	int f;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
					frame.setVisible(true);
					UserSelection.welcomeMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Welcome() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      System.exit(0);
		      dispose();
		    }
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setBounds(100, 100, 700, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digital Examination");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("Tiranti Solid LET", Font.BOLD, 75));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(111, 27, 573, 117);
		contentPane.add(lblNewLabel);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(new Color(128, 0, 0));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Tiranti Solid LET", Font.BOLD, 75));
		lblSystem.setBounds(244, 122, 290, 104);
		contentPane.add(lblSystem);
		
		JButton btnNext = new JButton("Exit");
		btnNext.setBackground(new Color(128, 0, 0));
		btnNext.setForeground(new Color(255, 255, 255));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNext.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		btnNext.setBounds(557, 305, 112, 37);
		contentPane.add(btnNext);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About().setVisible(true);
				dispose();
			}
		});
		btnAbout.setBackground(new Color(128, 0, 0));
		btnAbout.setForeground(new Color(255, 255, 255));
		btnAbout.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		btnAbout.setBounds(20, 305, 112, 37);
		contentPane.add(btnAbout);
		
		JButton button = new JButton("Login");
		button.setBackground(new Color(128, 0, 0));
		button.setForeground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		button.setBounds(374, 305, 112, 37);
		contentPane.add(button);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(128, 0, 0));
		separator.setForeground(new Color(128, 0, 0));
		separator.setBounds(10, 202, 674, 24);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(128, 0, 0));
		separator_1.setBackground(new Color(128, 0, 0));
		separator_1.setBounds(10, 283, 674, 11);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(128, 0, 0));
		separator_2.setBackground(new Color(128, 0, 0));
		separator_2.setBounds(10, 360, 674, 11);
		contentPane.add(separator_2);
		
		JLabel lblDevelopedBy = new JLabel("Developed By : - Raghuvansh Narayan Gupta");
		lblDevelopedBy.setForeground(new Color(0, 0, 128));
		lblDevelopedBy.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblDevelopedBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedBy.setBounds(130, 215, 415, 24);
		contentPane.add(lblDevelopedBy);
		
		JLabel lblMcathSem = new JLabel("MCA 4th Sem., Roll No. : - 16606334");
		lblMcathSem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMcathSem.setForeground(new Color(0, 0, 128));
		lblMcathSem.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblMcathSem.setBounds(130, 250, 415, 24);
		contentPane.add(lblMcathSem);
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(img));
		logoLabel.setBounds(20, 40, 75, 64);
		contentPane.add(logoLabel);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Help().setVisible(true);
				dispose();
			}
		});
		btnHelp.setForeground(Color.WHITE);
		btnHelp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		btnHelp.setBackground(new Color(128, 0, 0));
		btnHelp.setBounds(209, 305, 112, 37);
		contentPane.add(btnHelp);		
	}
}
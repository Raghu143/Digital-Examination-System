import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Help extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
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
	public Help() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Welcome().setVisible(true);
		      dispose();
		    }
		});
		setResizable(false);
		setTitle("Digital Examination");
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 617, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 617, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Help Window");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 617, 45);
		panel.add(lblNewLabel);
		
		JLabel desLabel = new JLabel("<html><p style=\"text-align:justify;\">\r\nYou can choose a course then see each and every question carefully. You will see time duration of particular subject test on the top of the window. Before time elapsed, you can visit each and every question as many times you want and also you can modify your answer but when time elapsed you will automatically logged out. So, before time out you must have to submit their test otherwise you will not appear in the test.\r\n</p></html>");
		desLabel.setForeground(new Color(128, 128, 0));
		desLabel.setVerticalAlignment(SwingConstants.TOP);
		desLabel.setHorizontalAlignment(SwingConstants.CENTER);
		desLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		desLabel.setBounds(20, 61, 571, 151);
		contentPane.add(desLabel);
		
		JLabel lblThanksALot = new JLabel("Good Luck");
		lblThanksALot.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanksALot.setForeground(new Color(128, 0, 0));
		lblThanksALot.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
		lblThanksALot.setBounds(246, 227, 99, 24);
		contentPane.add(lblThanksALot);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Welcome().setVisible(true);
				dispose();
			}
		});
		btnBack.setBackground(new Color(255, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBounds(502, 257, 89, 24);
		contentPane.add(btnBack);
	}
}
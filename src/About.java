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

public class About extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setResizable(false);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      new Welcome().setVisible(true);
		      dispose();
		    }
		});
		Image img=new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 617, 416);
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
		
		JLabel lblNewLabel = new JLabel("About Digital Examination System");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 617, 45);
		panel.add(lblNewLabel);
		
		JLabel lblHello = new JLabel("Hello !!! Everyone");
		lblHello.setForeground(new Color(128, 0, 0));
		lblHello.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
		lblHello.setBounds(10, 61, 180, 24);
		contentPane.add(lblHello);
		
		JLabel desLabel = new JLabel("<html><p style=\"text-align:justify;\">This is a 'Digital Examination System' project coded in \"J2SE\". \r\nI had used many components of java.awt and javax.swing package. I also used some concept of java.net package and javax.mail package.\r\nHere, there is two login option one for student and other for admin. Admin can controls courses and question based on that course. There is also time limit for each course.</p></html>");
		desLabel.setForeground(new Color(128, 128, 0));
		desLabel.setVerticalAlignment(SwingConstants.TOP);
		desLabel.setHorizontalAlignment(SwingConstants.CENTER);
		desLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		desLabel.setBounds(20, 86, 571, 151);
		contentPane.add(desLabel);
		
		JLabel lblThanksALot = new JLabel("Thanks A Lot");
		lblThanksALot.setForeground(new Color(128, 0, 0));
		lblThanksALot.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
		lblThanksALot.setBounds(266, 248, 99, 24);
		contentPane.add(lblThanksALot);
		
		JLabel lblGuidedBy = new JLabel("Guided by");
		lblGuidedBy.setForeground(new Color(128, 128, 0));
		lblGuidedBy.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblGuidedBy.setBounds(17, 268, 99, 24);
		contentPane.add(lblGuidedBy);
		
		JLabel lblDevelopedBy = new JLabel("Developed by");
		lblDevelopedBy.setForeground(new Color(128, 128, 0));
		lblDevelopedBy.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblDevelopedBy.setBounds(17, 292, 116, 24);
		contentPane.add(lblDevelopedBy);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setForeground(new Color(128, 128, 0));
		lblContact.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblContact.setBounds(17, 316, 99, 24);
		contentPane.add(lblContact);
		
		JLabel label = new JLabel(":");
		label.setForeground(new Color(128, 128, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD, 15));
		label.setBounds(133, 268, 14, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setForeground(new Color(128, 128, 0));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_1.setBounds(133, 292, 14, 24);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(":");
		label_2.setForeground(new Color(128, 128, 0));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_2.setBounds(133, 316, 14, 24);
		contentPane.add(label_2);
		
		JLabel lblPraveenSinghYadav = new JLabel("Mr. Praveen Yadav, Asst. Professor, CSIT, GGV");
		lblPraveenSinghYadav.setForeground(new Color(128, 128, 0));
		lblPraveenSinghYadav.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblPraveenSinghYadav.setBounds(157, 268, 434, 24);
		contentPane.add(lblPraveenSinghYadav);
		
		JLabel lblRaghuvashNarayanGupta = new JLabel("Raghuvash Narayan Gupta, MCA IVth");
		lblRaghuvashNarayanGupta.setForeground(new Color(128, 128, 0));
		lblRaghuvashNarayanGupta.setFont(new Font("Monospaced", Font.BOLD, 15));
		lblRaghuvashNarayanGupta.setBounds(157, 292, 364, 24);
		contentPane.add(lblRaghuvashNarayanGupta);
		
		JLabel label_3 = new JLabel("+91-9570017896");
		label_3.setForeground(new Color(128, 128, 0));
		label_3.setFont(new Font("Monospaced", Font.BOLD, 15));
		label_3.setBounds(157, 316, 389, 24);
		contentPane.add(label_3);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Welcome().setVisible(true);
				dispose();
			}
		});
		btnBack.setBackground(new Color(255, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBounds(502, 339, 89, 24);
		contentPane.add(btnBack);
	}
}
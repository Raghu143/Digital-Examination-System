import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class Result extends JFrame {
	private JPanel contentPane;
	private JTextField totalQuestion;
	private JTextField attempQuestion;
	private JTextField correct;
	private JTextField wrong;
	private JTextField percentage;
	private JTextField marks;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
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
	public Result() {
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
		setBounds(100, 100, 542, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total Questions");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 79, 171, 31);
		contentPane.add(lblNewLabel);
		
		totalQuestion = new JTextField();
		totalQuestion.setEditable(false);
		totalQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totalQuestion.setBounds(274, 83, 205, 27);
		contentPane.add(totalQuestion);
		totalQuestion.setColumns(10);
		
		JLabel lblAttempedQuestions = new JLabel("Attemped Questions");
		lblAttempedQuestions.setForeground(Color.WHITE);
		lblAttempedQuestions.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAttempedQuestions.setBounds(54, 129, 171, 31);
		contentPane.add(lblAttempedQuestions);
		
		attempQuestion = new JTextField();
		attempQuestion.setEditable(false);
		attempQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		attempQuestion.setColumns(10);
		attempQuestion.setBounds(274, 133, 205, 27);
		contentPane.add(attempQuestion);
		
		JLabel lblCorrect = new JLabel("Correct");
		lblCorrect.setForeground(Color.WHITE);
		lblCorrect.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCorrect.setBounds(54, 181, 171, 31);
		contentPane.add(lblCorrect);
		
		correct = new JTextField();
		correct.setEditable(false);
		correct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		correct.setColumns(10);
		correct.setBounds(274, 185, 205, 27);
		contentPane.add(correct);
		
		JLabel lblWrong = new JLabel("Wrong");
		lblWrong.setForeground(Color.WHITE);
		lblWrong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWrong.setBounds(54, 233, 171, 31);
		contentPane.add(lblWrong);
		
		wrong = new JTextField();
		wrong.setEditable(false);
		wrong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wrong.setColumns(10);
		wrong.setBounds(274, 237, 205, 27);
		contentPane.add(wrong);
		
		JLabel lblPercentage = new JLabel("Percentage");
		lblPercentage.setForeground(Color.WHITE);
		lblPercentage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPercentage.setBounds(54, 281, 171, 31);
		contentPane.add(lblPercentage);
		
		percentage = new JTextField();
		percentage.setEditable(false);
		percentage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		percentage.setColumns(10);
		percentage.setBounds(274, 285, 205, 27);
		contentPane.add(percentage);
		
		JLabel lblMarksCredit = new JLabel("Marks Credit");
		lblMarksCredit.setForeground(Color.WHITE);
		lblMarksCredit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarksCredit.setBounds(54, 327, 171, 31);
		contentPane.add(lblMarksCredit);
		
		marks = new JTextField();
		marks.setEditable(false);
		marks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		marks.setColumns(10);
		marks.setBounds(274, 331, 205, 27);
		contentPane.add(marks);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=OracleConnection.getConnection();
				try {
					int c=0;
					PreparedStatement ps=con.prepareStatement("insert into result (sno,username,fullname,coursename,totalq,attemptq,correctans,wrongans,date) values(?,?,?,?,?,?,?,?,?)");
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from result");
					while(rs.next())
						c++;
					ps.setString(1,MyCrypto.encrypt(Integer.toString(c+1)));
					ps.setString(2,MyCrypto.encrypt(Login.user));
					ps.setString(3,MyCrypto.encrypt(Login.name));
					ps.setString(4,MyCrypto.encrypt(User.coursename));
					ps.setString(5,MyCrypto.encrypt(Integer.toString(10)));
					ps.setString(6,MyCrypto.encrypt(attempQuestion.getText()));
					ps.setString(7,MyCrypto.encrypt(correct.getText()));
					ps.setString(8,MyCrypto.encrypt(wrong.getText()));
				    String s=java.time.LocalDate.now().toString()+" "+java.time.LocalTime.now().toString();
				    ps.setString(9,MyCrypto.encrypt(s));
				    int status=ps.executeUpdate();
				    if(status==1)
				    JOptionPane.showMessageDialog(null, "Data Saved...");
				    dispose();
				    new Logout().setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSave.setBackground(new Color(128, 0, 0));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBounds(362, 386, 117, 38);
		contentPane.add(btnSave);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 542, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(217, 0, 117, 66);
		panel.add(lblResult);
		lblResult.setForeground(new Color(255, 255, 255));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
	}
	public void setData(String totalQ,String attempQ,String crt,String wrg,String per,String mark)
	{
		totalQuestion.setText(totalQ);
		attempQuestion.setText(attempQ);
		correct.setText(crt);
		wrong.setText(wrg);
		percentage.setText(per);
		marks.setText(mark);	
	}
}
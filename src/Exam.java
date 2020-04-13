import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
public class Exam extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JLabel lblTime;
	private JLabel lblSno,lblQuestionId;
	private ButtonGroup g;
	private JRadioButton opt1,opt2,opt3,opt4;
	private JButton btnNext,btnPrevious,btnFirst,btnLast;
	private JTextArea textArea;
	private int sn=1;
	MyTime t;
	private int i;
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private LinkedList<String> qno;
	private HashMap<String, String> userans,correctans;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exam frame = new Exam();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Exam() {
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
		qno=new LinkedList<String>();
		g=new ButtonGroup();
		userans=new HashMap<String, String>();
		correctans=new HashMap<String, String>();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 652, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblSubject.setBounds(24, 11, 200, 27);
		contentPane.add(lblSubject);
		lblSubject.setText("Subject: "+User.coursename);
		
		lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblTime.setBounds(267, 11, 159, 27);
		contentPane.add(lblTime);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result r=new Result();
				getResult(r);
				dispose();
				r.setVisible(true);
				MyTime.flag=false;
			}
		});
		btnSubmit.setBackground(new Color(139, 0, 0));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		btnSubmit.setBounds(486, 11, 108, 27);
		contentPane.add(btnSubmit);
		
		lblSno = new JLabel("S.No");
		lblSno.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblSno.setBounds(19, 91, 50, 27);
		contentPane.add(lblSno);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 49, 604, 9);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 91, 515, 154);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		opt1 = new JRadioButton("Option - 1");
		opt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=lblQuestionId.getText();
				String[] str=s.split(" ");
				userans.put(str[3], opt1.getText());
			}
		});
		opt1.setBackground(new Color(255, 248, 220));
		opt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt1.setBounds(79, 260, 515, 23);
		contentPane.add(opt1);
		
		opt2 = new JRadioButton("Option - 2");
		opt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=lblQuestionId.getText();
				String[] str=s.split(" ");
				userans.put(str[3], opt2.getText());
			}
		});
		opt2.setBackground(new Color(255, 248, 220));
		opt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt2.setBounds(79, 287, 516, 23);
		contentPane.add(opt2);
		
		opt3 = new JRadioButton("Option - 3");
		opt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=lblQuestionId.getText();
				String[] str=s.split(" ");
				userans.put(str[3], opt3.getText());
			}
		});
		opt3.setBackground(new Color(255, 248, 220));
		opt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt3.setBounds(79, 313, 516, 23);
		contentPane.add(opt3);
		
		opt4 = new JRadioButton("Option - 4");
		opt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=lblQuestionId.getText();
				String[] str=s.split(" ");
				userans.put(str[3], opt4.getText());
			}
		});
		opt4.setBackground(new Color(255, 248, 220));
		opt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt4.setBounds(79, 339, 515, 23);
		contentPane.add(opt4);
		g.add(opt1);
		g.add(opt2);
		g.add(opt3);
		g.add(opt4);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(Color.WHITE);
		btnPrevious.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		btnPrevious.setBackground(new Color(139, 0, 0));
		btnPrevious.setBounds(193, 385, 121, 40);
		btnPrevious.setVisible(false);
		contentPane.add(btnPrevious);
		
		btnLast = new JButton("Last");
		btnLast.setForeground(Color.WHITE);
		btnLast.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		btnLast.setBackground(new Color(139, 0, 0));
		btnLast.setBounds(193, 385, 121, 40);
		contentPane.add(btnLast);
		
		
		btnNext = new JButton("Next");
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		btnNext.setBackground(new Color(139, 0, 0));
		btnNext.setBounds(339, 385, 121, 40);
		contentPane.add(btnNext);
		
		btnFirst = new JButton("First");
		btnFirst.setForeground(Color.WHITE);
		btnFirst.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		btnFirst.setBackground(new Color(139, 0, 0));
		btnFirst.setBounds(339, 385, 121, 40);
		btnFirst.setVisible(false);
		contentPane.add(btnFirst);
		
		lblQuestionId = new JLabel("Question ID");
		lblQuestionId.setFont(new Font("Sylfaen", Font.BOLD, 20));
		lblQuestionId.setBounds(24, 53, 226, 27);
		contentPane.add(lblQuestionId);
		
		// Time Loaded from database Starts
		
		con=OracleConnection.getConnection();
		try {
			ps=con.prepareStatement("select coursetime from course where coursename=?");
			ps.setString(1, MyCrypto.encrypt(User.coursename));
			rs=ps.executeQuery();
			if(rs.next())
			{
			String time=MyCrypto.decrypt(rs.getString(1));
			t=new MyTime(Integer.parseInt(time));
			t.start();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		// Time Loaded from database Ends
		
		// Adding 10 random question id into LinkedList from database
		
		try {
			con=OracleConnection.getConnection();
			Statement stmt=con.createStatement();
			String query="select qid from $ order by rand() limit 10";
			String query1=query.replace("$",User.coursename);
			rs=stmt.executeQuery(query1);
			while(rs.next())
			{
				qno.add(MyCrypto.decrypt(rs.getString(1)));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e1);
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		// Adding 10 random question id into LinkedList from database
		
		i=sn-1;
		con=OracleConnection.getConnection();
		String query="select * from $ where qid=?";
		String query1=query.replace("$", User.coursename);
		try {
			ps=con.prepareStatement(query1);
			ps.setString(1, MyCrypto.encrypt(qno.get(i)));
			lblQuestionId.setText("Question ID : "+qno.get(i));
			rs=ps.executeQuery();
			if(rs.next())
			{
				if(sn<10)
					lblSno.setText("0"+sn+"");
				else
					lblSno.setText(sn+"");
				textArea.setText(MyCrypto.decrypt(rs.getString(2)));
				opt1.setText(MyCrypto.decrypt(rs.getString(3)));
				opt2.setText(MyCrypto.decrypt(rs.getString(4)));
				opt3.setText(MyCrypto.decrypt(rs.getString(5)));
				opt4.setText(MyCrypto.decrypt(rs.getString(6)));
				correctans.put(MyCrypto.decrypt(rs.getString(1)),MyCrypto.decrypt(rs.getString(7)));
				g.clearSelection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnLast.addActionListener(this);
		btnFirst.addActionListener(this);
	}
	public void getResult(Result r)
	{
		int count=0,correct=0,wrong=0;
		double percentage=0,marks=0;
		Vector<String> key=new Vector<String>();
		Map<String, String> map = userans;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if(entry.getValue()!=null)
			{
				count++;
				key.addElement(entry.getKey());
			}
		}
		for(int i=0;i<key.size();i++)
		{
			if(userans.get(key.get(i)).equals(correctans.get(key.get(i))))
				correct++;
			else
				wrong++;
		}
		percentage=(correct*100)/10;
		marks=correct*1;
		r.setData(Integer.toString(10),Integer.toString(count),Integer.toString(correct),Integer.toString(wrong),Double.toString(percentage),Double.toString(marks));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNext)
		{
			btnLast.setVisible(false);
			btnPrevious.setVisible(true);
			con=OracleConnection.getConnection();
			String query="select * from $ where qid=?";
			String query1=query.replace("$", User.coursename);
			try {
				ps=con.prepareStatement(query1);
				sn++;
				i=sn-1;
				if(i+1==10)
				{
					btnNext.setVisible(false);
					btnFirst.setVisible(true);
				}
				if(btnPrevious.isEnabled()==false)
				{
					btnLast.setVisible(false);
					btnPrevious.setVisible(true);
			    }
				ps.setString(1, MyCrypto.encrypt(qno.get(i)));
				if(sn<10)
					lblSno.setText("0"+sn+"");
				else
					lblSno.setText(sn+"");
				lblQuestionId.setText("Question ID : "+qno.get(i));
				rs=ps.executeQuery();
				if(rs.next())
				{
					textArea.setText(MyCrypto.decrypt(rs.getString(2)));
					opt1.setText(MyCrypto.decrypt(rs.getString(3)));
					opt2.setText(MyCrypto.decrypt(rs.getString(4)));
					opt3.setText(MyCrypto.decrypt(rs.getString(5)));
					opt4.setText(MyCrypto.decrypt(rs.getString(6)));
					correctans.put(MyCrypto.decrypt(rs.getString(1)),MyCrypto.decrypt(rs.getString(7)));
					String val=userans.get(qno.get(i));
					if(val==null)
					{
						g.clearSelection();
					}
					else
					{
						UserSelection.setUserSelection(val, opt1, opt2, opt3, opt4);
					}
				}
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,ee);
			}
			finally {
				try {
					con.close();
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		}
		else if(e.getSource()==btnPrevious)
		{
			btnFirst.setVisible(false);
			btnNext.setVisible(true);
			sn--;
			i=sn-1;
			con=OracleConnection.getConnection();
			String query="select * from $ where qid=?";
			String query1=query.replace("$", User.coursename);
			try {
				ps=con.prepareStatement(query1);
				if(i-1==-1)
				{
						btnPrevious.setVisible(false);
						btnLast.setVisible(true);
				}
				if(btnNext.isEnabled()==false)
				{
						btnFirst.setVisible(false);
						btnNext.setVisible(true);
				}
				ps.setString(1, MyCrypto.encrypt(qno.get(i)));
				lblQuestionId.setText("Question ID : "+qno.get(i));
				if(sn<10)
					lblSno.setText("0"+sn+"");
				else
					lblSno.setText(sn+"");
				rs=ps.executeQuery();
				if(rs.next())
				{
					textArea.setText(MyCrypto.decrypt(rs.getString(2)));
					opt1.setText(MyCrypto.decrypt(rs.getString(3)));
					opt2.setText(MyCrypto.decrypt(rs.getString(4)));
					opt3.setText(MyCrypto.decrypt(rs.getString(5)));
					opt4.setText(MyCrypto.decrypt(rs.getString(6)));
					String val=userans.get(qno.get(i));
					if(val==null)
					{
						g.clearSelection();
					}
					else
					{
						UserSelection.setUserSelection(val, opt1, opt2, opt3, opt4);
					}
					correctans.put(MyCrypto.decrypt(rs.getString(1)),MyCrypto.decrypt(rs.getString(7)));	
				}
			} catch (SQLException ee) {
				JOptionPane.showMessageDialog(null,ee);
			}
			finally {
				try {
					con.close();
					ps.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		}
		else if(e.getSource()==btnLast)
		{
			 sn=10;
			 con=OracleConnection.getConnection();
			 String query="select * from $ where qid=?";
			 String query1=query.replace("$", User.coursename);
			 try {
				ps=con.prepareStatement(query1);
				ps.setString(1, MyCrypto.encrypt(qno.getLast()));
				rs=ps.executeQuery();
				if(rs.next())
				{
					textArea.setText(MyCrypto.decrypt(rs.getString(2)));
					opt1.setText(MyCrypto.decrypt(rs.getString(3)));
					opt2.setText(MyCrypto.decrypt(rs.getString(4)));
					opt3.setText(MyCrypto.decrypt(rs.getString(5)));
					opt4.setText(MyCrypto.decrypt(rs.getString(6)));
					correctans.put(MyCrypto.decrypt(rs.getString(1)),MyCrypto.decrypt(rs.getString(7)));
					String val=userans.get(qno.get(9));
					if(val==null)
					{
						g.clearSelection();
					}
					else
					{
						UserSelection.setUserSelection(val, opt1, opt2, opt3, opt4);
					}
				}
				lblQuestionId.setText("Question ID : "+qno.getLast());
				if(sn<10)
					lblSno.setText("0"+sn+"");
				else
					lblSno.setText(sn+"");
				btnLast.setVisible(false);
				btnPrevious.setVisible(true);
				btnNext.setVisible(false);
				btnFirst.setVisible(true);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,e1);
			}
		}
		else if(e.getSource()==btnFirst)
		{
			 sn=1;
			 con=OracleConnection.getConnection();
			 String query="select * from $ where qid=?";
			 String query1=query.replace("$", User.coursename);
			 try {
				    ps=con.prepareStatement(query1);
					ps.setString(1, MyCrypto.encrypt(qno.getFirst()));
					rs=ps.executeQuery();
					if(rs.next())
					{
						textArea.setText(MyCrypto.decrypt(rs.getString(2)));
						opt1.setText(MyCrypto.decrypt(rs.getString(3)));
						opt2.setText(MyCrypto.decrypt(rs.getString(4)));
						opt3.setText(MyCrypto.decrypt(rs.getString(5)));
						opt4.setText(MyCrypto.decrypt(rs.getString(6)));
						correctans.put(MyCrypto.decrypt(rs.getString(1)),MyCrypto.decrypt(rs.getString(7)));
						String val=userans.get(qno.get(0));
						if(val==null)
						{
							g.clearSelection();
						}
						else
						{
							UserSelection.setUserSelection(val, opt1, opt2, opt3, opt4);
						}
					}
					lblQuestionId.setText("Question ID : "+qno.getFirst());
					if(sn<10)
						lblSno.setText("0"+sn+"");
					else
						lblSno.setText(sn+"");
					 btnFirst.setVisible(false);
					 btnNext.setVisible(true);
					 btnPrevious.setVisible(false);
					 btnLast.setVisible(true);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,e2);
			} 
		}
	}
}
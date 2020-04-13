import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
public class Course extends JFrame {
	private JPanel contentPane;
	private JTextField coursetextField;
	private JTextField timetextField;
	private JTable table;
	Vector<Vector> data;
	Vector<String> row;
	Vector<String> cols;
	JComboBox ucomboBox,rcomboBox;
	JScrollPane scrollPane;
	Connection con;
	PreparedStatement ps;
	private JTextField utextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
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
		setBounds(100, 100, 577, 291);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        
		        if(sourceTabbedPane.getTitleAt(index).equals("View")){
		        	data=new Vector<Vector>();
		        	con=OracleConnection.getConnection();
		    		try {
		    			Statement stmt=con.createStatement();
		    			ResultSet rs=stmt.executeQuery("select * from course");
		    			while(rs.next())
		    			{
		    				row=new Vector<String>();
		    				row.add(MyCrypto.decrypt(rs.getString(1)));
		    				row.add(MyCrypto.decrypt(rs.getString(2)));
		    				data.add(row);
		    			}
		    		} catch (SQLException e) {
		    			// TODO Auto-generated catch block
		    			JOptionPane.showMessageDialog(null,e);
		    		}
		    		finally {
		    			try {
		    				con.close();
		    			} catch (SQLException e) {
		    				// TODO Auto-generated catch block
		    				JOptionPane.showMessageDialog(null,e);
		    			}
		    		}
		    		cols=new Vector<String>();
		    		cols.add("Course Name");
		    		cols.add("Course Time");
		    		table = new JTable(data,cols);
		    		scrollPane.setViewportView(table);
		    		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		    		table.getTableHeader().setFont(new Font("SansSerif",Font.BOLD, 16));
		    		table.getTableHeader().setBackground(Color.BLACK);
		    		table.getTableHeader().setForeground(Color.WHITE);
		    		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		    		table.setRowHeight(25);
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Update")){
		        	ucomboBox.removeAllItems();
		        	con=OracleConnection.getConnection();
		        	try {
		        		Statement stmt=con.createStatement();
		        		ResultSet rs=stmt.executeQuery("select * from course");
		        		while(rs.next())
		        		{
		        			ucomboBox.addItem(MyCrypto.decrypt(rs.getString(1)));
		        		}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);
					}
		        	finally {
						try {
							ps.close();
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e);
						}
					}
					
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Remove")){
		        	rcomboBox.removeAllItems();
		        	con=OracleConnection.getConnection();
		        	try {
		        		Statement stmt=con.createStatement();
		        		ResultSet rs=stmt.executeQuery("select * from course");
		        		while(rs.next())
		        		{
		        			rcomboBox.addItem(MyCrypto.decrypt(rs.getString(1)));
		        		}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);
					}
		        	finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e);
						}
					}
		        }
			}
		});
		
						        
		tabbedPane.setBackground(new Color(255, 250, 240));
		tabbedPane.setBounds(10, 68, 544, 179);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Add New", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Time (Minute)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(35, 90, 115, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Course Name");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(24, 36, 126, 25);
		panel.add(label_1);
		
		coursetextField = new JTextField();
		coursetextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		coursetextField.setColumns(10);
		coursetextField.setBackground(Color.WHITE);
		coursetextField.setBounds(184, 32, 232, 36);
		panel.add(coursetextField);
		
		timetextField = new JTextField();
		timetextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timetextField.setColumns(10);
		timetextField.setBackground(Color.WHITE);
		timetextField.setBounds(184, 79, 232, 36);
		panel.add(timetextField);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String coursename=coursetextField.getText();
				String time=timetextField.getText();
				if(coursename.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter course name","Error Message",JOptionPane.ERROR_MESSAGE);
					coursetextField.requestFocus();
					return;
				}
				Pattern p=Pattern.compile("[a-zA-Z]*");
				Matcher mt=p.matcher(coursename);
				if(!(mt.find()&&mt.group().equals(coursename)))
				{
					JOptionPane.showMessageDialog(null, "Course name contains alphabet only","Error Message",JOptionPane.ERROR_MESSAGE);
					coursetextField.requestFocus();
					return;
				}
				if(time.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter course time","Error Message",JOptionPane.ERROR_MESSAGE);
					timetextField.requestFocus();
					return;
				}
				p=Pattern.compile("[0-9]*");
				mt=p.matcher(time);
				if(!(mt.find()&&mt.group().equals(time)))
				{
					JOptionPane.showMessageDialog(null, "Enter a valid time","Error Message",JOptionPane.ERROR_MESSAGE);
					timetextField.requestFocus();
					return;
				}
				if(validateCourse(coursename))
				{
					JOptionPane.showMessageDialog(null, "Course already exists","Error Message",JOptionPane.ERROR_MESSAGE);
					coursetextField.setText("");
					timetextField.setText("");
					coursetextField.requestFocus();
					return;
				}
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("insert into course values(?,?)");
					ps.setString(1, MyCrypto.encrypt(coursename));
					ps.setString(2, MyCrypto.encrypt(time));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "New course added successfully");
					coursetextField.setText("");
					timetextField.setText("");
					coursetextField.requestFocus();
					Connection conn=OracleConnection.getConnection();
					String query="create table $ (qid varchar(100),qdesc varchar(3500),opt1 varchar(500),opt2 varchar(500),opt3 varchar(500),opt4 varchar(500),correctans varchar(500))";
					String query1=query.replace("$", coursename);
					PreparedStatement pss=conn.prepareStatement(query1);
					pss.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1);
				}
				finally {
					try {
						ps.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1);
					}
				}
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBackground(new Color(139, 0, 0));
		button.setBounds(426, 32, 89, 83);
		panel.add(button);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("View", null, panel_1, null);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 519, 129);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Update", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(255, 245, 238));
		panel_4.setBounds(0, 0, 539, 151);
		panel_2.add(panel_4);
		
		ucomboBox = new JComboBox();
		ucomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(ucomboBox.getSelectedIndex()==-1)
				{
					return;
				}
				String coursename=ucomboBox.getSelectedItem().toString();
				loadCourse(coursename);
			}
		});
		ucomboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		ucomboBox.setBounds(184, 11, 182, 30);
		panel_4.add(ucomboBox);
		
		JLabel lblNewLabel = new JLabel("Select Course");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(32, 11, 116, 30);
		panel_4.add(lblNewLabel);
		
		JLabel lblTime = new JLabel("Time (Minute)");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTime.setBounds(32, 56, 116, 30);
		panel_4.add(lblTime);
		
		utextField = new JTextField();
		utextField.setFont(new Font("Tahoma", Font.BOLD, 15));
		utextField.setBounds(184, 52, 182, 34);
		panel_4.add(utextField);
		utextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String coursename=ucomboBox.getSelectedItem().toString();
				String time=utextField.getText();
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("update course set coursetime=? where coursename=?");
					ps.setString(1, MyCrypto.encrypt(time));
					ps.setString(2, MyCrypto.encrypt(coursename));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Course Updated");
					loadCourse(coursename);
					ucomboBox.setSelectedIndex(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1);
				}
				finally {
					try {
						ps.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(184, 97, 182, 32);
		panel_4.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Remove", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label_2 = new JLabel("Select Course");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(220, 11, 116, 30);
		panel_3.add(label_2);
		
		rcomboBox = new JComboBox();
		rcomboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		rcomboBox.setBounds(187, 52, 182, 30);
		panel_3.add(rcomboBox);
		
		JButton btnDeleteCourse = new JButton("Delete Course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rcomboBox.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "No course to be deleted");
					return;
				}
				String coursename=rcomboBox.getSelectedItem().toString();
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("delete from course where coursename=?");
					ps.setString(1, MyCrypto.encrypt(coursename));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Course Deleted");
					rcomboBox.removeAllItems();
					Connection conn=OracleConnection.getConnection();
					String query="drop table $";
					String query1=query.replace("$", coursename);
					PreparedStatement pss=conn.prepareStatement(query1);
					pss.executeUpdate();
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from course");
					while(rs.next())
					{
						rcomboBox.addItem(MyCrypto.decrypt(rs.getString(1)));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1);
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
		});
		btnDeleteCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeleteCourse.setBounds(187, 93, 182, 32);
		panel_3.add(btnDeleteCourse);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(128, 0, 0));
		panel_5.setBounds(0, 0, 577, 57);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblQuestion = new JLabel("Course");
		lblQuestion.setForeground(new Color(255, 255, 255));
		lblQuestion.setBounds(211, 0, 165, 57);
		panel_5.add(lblQuestion);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 89, 29);
		panel_5.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(255, 0, 0));
	}
	void loadCourse(String course)
	{
		con=OracleConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from course where coursename=?");
			ps.setString(1, MyCrypto.encrypt(course));
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				utextField.setText(MyCrypto.decrypt(rs.getString(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
	}
	boolean validateCourse(String course)
	{
		ResultSet rs;
		con=OracleConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from course");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(course.equals(MyCrypto.decrypt(rs.getString(1))))
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
		return false;
	}
}
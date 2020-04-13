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
import javax.swing.JTextArea;
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
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Question extends JFrame {
	private JPanel contentPane;
	private JTextField id;
	private JTextField opt1;
	private JTextField opt2;
	private JTextField opt3;
	private JTextField opt4;
	private JTextField vopt1;
	private JTextField vopt2;
	private JTextField vopt3;
	private JTextField vopt4;
	private JTextField uopt1;
	private JTextField uopt2;
	private JTextField uopt3;
	private JTextField uopt4;
	private JTextField ropt1;
	private JTextField ropt2;
	private JTextField ropt3;
	private JTextField ropt4;
	private JComboBox correctans;
	private JComboBox vcorrectans,vid,vcourse;
	private JComboBox uid,ucourse,ucorrectans;
	private JComboBox rid,rcourse,rcorrectans;
	private JTextArea vdescription,udescription,rdescription;
	PreparedStatement ps;
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question frame = new Question();
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
	public Question() {
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
		setBounds(100, 100, 654, 580);
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
		        if(index<0)
		        {
		        	return;
		        }
		        if(sourceTabbedPane.getTitleAt(index).equals("View")){
		        	vid.removeAllItems();
		        	vcorrectans.removeAllItems();
		        	if(vcourse.getSelectedIndex()==-1)
		        	{
		        		return;
		        	}
		        	String coursename=vcourse.getSelectedItem().toString();
					Connection con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", coursename);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							vid.addItem(MyCrypto.decrypt(rs.getString(1)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null,e);
						}
					}
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Update")){
		        	uid.removeAllItems();
		        	if(ucourse.getSelectedIndex()==-1)
		        	{
		        		return;
		        	}
		        	String coursename=ucourse.getSelectedItem().toString();
					Connection con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", coursename);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							uid.addItem(MyCrypto.decrypt(rs.getString(1)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e);
					}
					finally
					{
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e);
						}
					}
					
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Remove")){
		        	rid.removeAllItems();
		        	if(rcourse.getSelectedIndex()==-1)
		        	{
		        		return;
		        	}
		        	String coursename=rcourse.getSelectedItem().toString();
					Connection con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", coursename);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							rid.addItem(MyCrypto.decrypt(rs.getString(1)));
							
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
		tabbedPane.setBounds(10, 68, 618, 462);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Add New", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setBounds(10, 11, 76, 24);
		lblCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCourse);
		
		JComboBox course = new JComboBox();
		course.setBounds(96, 11, 127, 24);
		course.setBackground(new Color(255, 245, 238));
		panel.add(course);
		
		JLabel lblQuestionId = new JLabel("Question ID");
		lblQuestionId.setBounds(342, 11, 114, 24);
		lblQuestionId.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionId.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblQuestionId);
		
		id = new JTextField();
		id.setBounds(466, 11, 127, 24);
		panel.add(id);
		id.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 53, 572, 165);
		panel.add(scrollPane);
		
		JTextArea description = new JTextArea();
		description.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane.setViewportView(description);
		
		JLabel lblAnswer = new JLabel("Option - 1");
		lblAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnswer.setBounds(31, 237, 76, 24);
		panel.add(lblAnswer);
		
		JLabel lblAnswer_1 = new JLabel("Option - 2");
		lblAnswer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswer_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnswer_1.setBounds(31, 272, 76, 24);
		panel.add(lblAnswer_1);
		
		JLabel lblOption = new JLabel("Option - 3");
		lblOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOption.setBounds(31, 305, 76, 24);
		panel.add(lblOption);
		
		JLabel lblOption_1 = new JLabel("Option - 4");
		lblOption_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOption_1.setBounds(31, 341, 76, 24);
		panel.add(lblOption_1);
		
		opt1 = new JTextField();
		opt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt1.setBounds(186, 239, 407, 24);
		panel.add(opt1);
		opt1.setColumns(10);
		
		opt2 = new JTextField();
		opt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt2.setColumns(10);
		opt2.setBounds(186, 274, 407, 24);
		panel.add(opt2);
		
		opt3 = new JTextField();
		opt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt3.setColumns(10);
		opt3.setBounds(186, 308, 407, 24);
		panel.add(opt3);
		
		opt4 = new JTextField();
		opt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		opt4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				correctans.removeAllItems();
				correctans.addItem(opt1.getText());
				correctans.addItem(opt2.getText());
				correctans.addItem(opt3.getText());
				correctans.addItem(opt4.getText());
			}
		});
		opt4.setColumns(10);
		opt4.setBounds(186, 343, 407, 24);
		panel.add(opt4);
		
		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCorrectAnswer.setBounds(31, 393, 127, 24);
		panel.add(lblCorrectAnswer);
		
		correctans = new JComboBox();
		correctans.setBackground(new Color(255, 250, 250));
		correctans.setBounds(186, 393, 200, 30);
		panel.add(correctans);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBackground(new Color(139, 0, 0));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(course.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select a course");
					course.requestFocus();
					return;
				}
				if(id.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter course ID");
					id.requestFocus();
					return;
				}
				if(description.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter course ID");
					description.requestFocus();
					return;
				}
				if(opt1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter option 1");
					opt1.requestFocus();
					return;
				}
				if(opt2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter option 2");
					opt2.requestFocus();
					return;
				}
				if(opt3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter option 3");
					opt3.requestFocus();
					return;
				}
				if(opt4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter option 4");
					opt4.requestFocus();
					return;
				}
				/*if(validateQID(id.getText(),course.getSelectedItem().toString()))
				{
					JOptionPane.showMessageDialog(null, "This Question ID already exists");
					return;
				}*/
				String coursename=course.getSelectedItem().toString();
				Vector<String> str=new Vector<String>();
				str.addElement(id.getText());
				str.addElement(description.getText());
				str.addElement(opt1.getText());
				str.addElement(opt2.getText());
				str.addElement(opt3.getText());
				str.addElement(opt4.getText());
				str.addElement(correctans.getSelectedItem().toString());
				con=OracleConnection.getConnection();
				String query="insert into $table values(?,?,?,?,?,?,?)";
				String query1=query.replace("$table", coursename);
				try {
					ps=con.prepareStatement(query1);
					for(int i=0;i<str.size();i++)
					{
						ps.setString(i+1, MyCrypto.encrypt(str.get(i)));
					}
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Question Succefully Saved");
					course.setSelectedIndex(-1);
					id.setText("");
					description.setText("");
					opt1.setText("");
					opt2.setText("");
					opt3.setText("");
					opt4.setText("");
					correctans.removeAllItems();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error in insertion"+e);
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
		});
		btnSave.setBounds(447, 393, 146, 30);
		panel.add(btnSave);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("View", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Course");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 11, 76, 24);
		panel_1.add(label);
		
		vcourse = new JComboBox();
		vcourse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				vid.removeAllItems();
				JComboBox cb = (JComboBox) evt.getSource();
			    String item=evt.getItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
					con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", item);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							vid.addItem(MyCrypto.decrypt(rs.getString(1)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    }
			}
		});
		vcourse.setBackground(new Color(255, 245, 238));
		vcourse.setBounds(96, 11, 127, 24);
		panel_1.add(vcourse);
		
		JLabel label_1 = new JLabel("Question ID");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(342, 11, 114, 24);
		panel_1.add(label_1);
		
		vopt1 = new JTextField();
		vopt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vopt1.setEditable(false);
		vopt1.setColumns(10);
		vopt1.setBounds(186, 239, 407, 24);
		panel_1.add(vopt1);
		
		JLabel label_2 = new JLabel("Option - 1");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(31, 237, 76, 24);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Option - 2");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(31, 272, 76, 24);
		panel_1.add(label_3);
		
		vopt2 = new JTextField();
		vopt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vopt2.setEditable(false);
		vopt2.setColumns(10);
		vopt2.setBounds(186, 274, 407, 24);
		panel_1.add(vopt2);
		
		JLabel label_4 = new JLabel("Option - 3");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(31, 305, 76, 24);
		panel_1.add(label_4);
		
		vopt3 = new JTextField();
		vopt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vopt3.setEditable(false);
		vopt3.setColumns(10);
		vopt3.setBounds(186, 308, 407, 24);
		panel_1.add(vopt3);
		
		JLabel label_5 = new JLabel("Option - 4");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(31, 341, 76, 24);
		panel_1.add(label_5);
		
		vopt4 = new JTextField();
		vopt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vopt4.setEditable(false);
		vopt4.setColumns(10);
		vopt4.setBounds(186, 343, 407, 24);
		panel_1.add(vopt4);
		
		JLabel label_6 = new JLabel("Correct Answer");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(31, 393, 127, 24);
		panel_1.add(label_6);
		
		vcorrectans = new JComboBox();
		vcorrectans.setBackground(new Color(255, 250, 250));
		vcorrectans.setBounds(186, 393, 200, 30);
		panel_1.add(vcorrectans);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 51, 573, 164);
		panel_1.add(scrollPane_1);
		
		vdescription = new JTextArea();
		vdescription.setFont(new Font("Monospaced", Font.PLAIN, 14));
		vdescription.setEditable(false);
		scrollPane_1.setViewportView(vdescription);
		
		vid = new JComboBox();
		vid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				String item=evt.getItem().toString();
				String tab=vcourse.getSelectedItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
			    	vcorrectans.removeAllItems();
					con=OracleConnection.getConnection();
					String query="select * from $table where qid=?";
					String query1=query.replace("$table", tab);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, MyCrypto.encrypt(item));
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							vdescription.setText(MyCrypto.decrypt(rs.getString(2)));
							vopt1.setText(MyCrypto.decrypt(rs.getString(3)));
							vopt2.setText(MyCrypto.decrypt(rs.getString(4)));
							vopt3.setText(MyCrypto.decrypt(rs.getString(5)));
							vopt4.setText(MyCrypto.decrypt(rs.getString(6)));
							vcorrectans.addItem(MyCrypto.decrypt(rs.getString(3)));
							vcorrectans.addItem(MyCrypto.decrypt(rs.getString(4)));
							vcorrectans.addItem(MyCrypto.decrypt(rs.getString(5)));
							vcorrectans.addItem(MyCrypto.decrypt(rs.getString(6)));
							vcorrectans.setSelectedItem(MyCrypto.decrypt(rs.getString(7)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    }
			}
		});
		vid.setBackground(new Color(255, 250, 250));
		vid.setBounds(457, 11, 136, 24);
		panel_1.add(vid);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Update", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(255, 245, 238));
		panel_4.setBounds(0, 0, 613, 434);
		panel_2.add(panel_4);
		
		JLabel label_7 = new JLabel("Course");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(10, 11, 76, 24);
		panel_4.add(label_7);
		
		ucourse = new JComboBox();
		ucourse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				uid.removeAllItems();
				JComboBox cb = (JComboBox) evt.getSource();
			    String item=evt.getItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
			    	con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", item);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							uid.addItem(MyCrypto.decrypt(rs.getString(1)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}
		});
		ucourse.setBackground(new Color(255, 245, 238));
		ucourse.setBounds(96, 11, 127, 24);
		panel_4.add(ucourse);
		
		JLabel label_8 = new JLabel("Question ID");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(342, 11, 114, 24);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel("Option - 1");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_9.setBounds(31, 237, 76, 24);
		panel_4.add(label_9);
		
		JLabel label_10 = new JLabel("Option - 2");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_10.setBounds(31, 272, 76, 24);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel("Option - 3");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_11.setBounds(31, 305, 76, 24);
		panel_4.add(label_11);
		
		JLabel label_12 = new JLabel("Option - 4");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_12.setBounds(31, 341, 76, 24);
		panel_4.add(label_12);
		
		uopt1 = new JTextField();
		uopt1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ucorrectans.removeAllItems();
				ucorrectans.addItem(uopt1.getText());
				ucorrectans.addItem(uopt2.getText());
				ucorrectans.addItem(uopt3.getText());
				ucorrectans.addItem(uopt4.getText());
			}
		});
		uopt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uopt1.setColumns(10);
		uopt1.setBounds(186, 239, 407, 24);
		panel_4.add(uopt1);
		
		uopt2 = new JTextField();
		uopt2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ucorrectans.removeAllItems();
				ucorrectans.addItem(uopt1.getText());
				ucorrectans.addItem(uopt2.getText());
				ucorrectans.addItem(uopt3.getText());
				ucorrectans.addItem(uopt4.getText());
			}
		});
		uopt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uopt2.setColumns(10);
		uopt2.setBounds(186, 274, 407, 24);
		panel_4.add(uopt2);
		
		uopt3 = new JTextField();
		uopt3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ucorrectans.removeAllItems();
				ucorrectans.addItem(uopt1.getText());
				ucorrectans.addItem(uopt2.getText());
				ucorrectans.addItem(uopt3.getText());
				ucorrectans.addItem(uopt4.getText());
			}
		});
		uopt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uopt3.setColumns(10);
		uopt3.setBounds(186, 308, 407, 24);
		panel_4.add(uopt3);
		
		uopt4 = new JTextField();
		uopt4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ucorrectans.removeAllItems();
				ucorrectans.addItem(uopt1.getText());
				ucorrectans.addItem(uopt2.getText());
				ucorrectans.addItem(uopt3.getText());
				ucorrectans.addItem(uopt4.getText());
			}
		});
		uopt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uopt4.setColumns(10);
		uopt4.setBounds(186, 343, 407, 24);
		panel_4.add(uopt4);
		
		JLabel label_13 = new JLabel("Correct Answer");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_13.setBounds(31, 393, 127, 24);
		panel_4.add(label_13);
		
		ucorrectans = new JComboBox();
		ucorrectans.setBackground(new Color(255, 250, 250));
		ucorrectans.setBounds(186, 393, 200, 30);
		panel_4.add(ucorrectans);
		
		JButton usave = new JButton("Save");
		usave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String id=uid.getSelectedItem().toString();
				String tab=ucourse.getSelectedItem().toString();
				con=OracleConnection.getConnection();
				String query="update $table set qdesc=?,opt1=?,opt2=?,opt3=?,opt4=?,correctans=? where qid=?";
				String query1=query.replace("$table", tab);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, MyCrypto.encrypt(udescription.getText()));
						ps.setString(2, MyCrypto.encrypt(uopt1.getText()));
						ps.setString(3, MyCrypto.encrypt(uopt2.getText()));
						ps.setString(4, MyCrypto.encrypt(uopt3.getText()));
						ps.setString(5, MyCrypto.encrypt(uopt4.getText()));
						ps.setString(6, MyCrypto.encrypt(ucorrectans.getSelectedItem().toString()));
						ps.setString(7, MyCrypto.encrypt(id));
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Question Successfully Updated");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		});
		usave.setForeground(Color.WHITE);
		usave.setFont(new Font("Tahoma", Font.BOLD, 18));
		usave.setBackground(new Color(139, 0, 0));
		usave.setBounds(447, 393, 146, 30);
		panel_4.add(usave);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 46, 568, 180);
		panel_4.add(scrollPane_2);
		
		udescription = new JTextArea();
		udescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				correctans.removeAllItems();
				correctans.addItem(opt1.getText());
				correctans.addItem(opt2.getText());
				correctans.addItem(opt3.getText());
				correctans.addItem(opt4.getText());
			}
		});
		udescription.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane_2.setViewportView(udescription);
		
		uid = new JComboBox();
		uid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				String item=evt.getItem().toString();
				String tab=ucourse.getSelectedItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
			    	ucorrectans.removeAllItems();
			    	con=OracleConnection.getConnection();
					String query="select * from $table where qid=?";
					String query1=query.replace("$table", tab);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, MyCrypto.encrypt(item));
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							udescription.setText(MyCrypto.decrypt(rs.getString(2)));
							uopt1.setText(MyCrypto.decrypt(rs.getString(3)));
							uopt2.setText(MyCrypto.decrypt(rs.getString(4)));
							uopt3.setText(MyCrypto.decrypt(rs.getString(5)));
							uopt4.setText(MyCrypto.decrypt(rs.getString(6)));
							ucorrectans.addItem(MyCrypto.decrypt(rs.getString(3)));
							ucorrectans.addItem(MyCrypto.decrypt(rs.getString(4)));
							ucorrectans.addItem(MyCrypto.decrypt(rs.getString(5)));
							ucorrectans.addItem(MyCrypto.decrypt(rs.getString(6)));
							ucorrectans.setSelectedItem(MyCrypto.decrypt(rs.getString(7)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    }
			}
		});
		uid.setBackground(new Color(255, 250, 250));
		uid.setBounds(466, 13, 136, 24);
		panel_4.add(uid);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 245, 238));
		tabbedPane.addTab("Remove", null, panel_3, null);
		panel_3.setLayout(null);
		
		rid = new JComboBox();
		rid.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				String item=evt.getItem().toString();
				String tab=rcourse.getSelectedItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
			    	rcorrectans.removeAllItems();
			    	con=OracleConnection.getConnection();
					String query="select * from $table where qid=?";
					String query1=query.replace("$table", tab);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, MyCrypto.encrypt(item));
						ResultSet rs=ps.executeQuery();
						while(rs.next())
						{
							rdescription.setText(MyCrypto.decrypt(rs.getString(2)));
							ropt1.setText(MyCrypto.decrypt(rs.getString(3)));
							ropt2.setText(MyCrypto.decrypt(rs.getString(4)));
							ropt3.setText(MyCrypto.decrypt(rs.getString(5)));
							ropt4.setText(MyCrypto.decrypt(rs.getString(6)));
							rcorrectans.addItem(MyCrypto.decrypt(rs.getString(3)));
							rcorrectans.addItem(MyCrypto.decrypt(rs.getString(4)));
							rcorrectans.addItem(MyCrypto.decrypt(rs.getString(5)));
							rcorrectans.addItem(MyCrypto.decrypt(rs.getString(6)));
							rcorrectans.setSelectedItem(MyCrypto.decrypt(rs.getString(7)));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    }
			}
			
		});
		rid.setBackground(new Color(255, 250, 250));
		rid.setBounds(466, 12, 136, 24);
		panel_3.add(rid);
		
		JLabel label_14 = new JLabel("Question ID");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_14.setBounds(342, 10, 114, 24);
		panel_3.add(label_14);
		
		rcourse = new JComboBox();
		rcourse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				rid.removeAllItems();
				JComboBox cb = (JComboBox) evt.getSource();
			    String item=evt.getItem().toString();
			    if(evt.getStateChange() == ItemEvent.SELECTED)
			    {
					con=OracleConnection.getConnection();
					String query="select * from $table";
					String query1=query.replace("$table", item);
					try {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query1);
						while(rs.next())
						{
							rid.addItem(MyCrypto.decrypt(rs.getString(1)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}
		});
		rcourse.setBackground(new Color(255, 245, 238));
		rcourse.setBounds(96, 10, 127, 24);
		panel_3.add(rcourse);
		
		JLabel label_15 = new JLabel("Course");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_15.setBounds(10, 10, 76, 24);
		panel_3.add(label_15);
		
		JLabel label_16 = new JLabel("Option - 1");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_16.setBounds(31, 226, 76, 24);
		panel_3.add(label_16);
		
		ropt1 = new JTextField();
		ropt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ropt1.setForeground(new Color(0, 0, 0));
		ropt1.setEditable(false);
		ropt1.setColumns(10);
		ropt1.setBounds(186, 228, 407, 24);
		panel_3.add(ropt1);
		
		JLabel label_17 = new JLabel("Option - 2");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_17.setBounds(31, 261, 76, 24);
		panel_3.add(label_17);
		
		ropt2 = new JTextField();
		ropt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ropt2.setForeground(new Color(0, 0, 0));
		ropt2.setEditable(false);
		ropt2.setColumns(10);
		ropt2.setBounds(186, 263, 407, 24);
		panel_3.add(ropt2);
		
		JLabel label_18 = new JLabel("Option - 3");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_18.setBounds(31, 294, 76, 24);
		panel_3.add(label_18);
		
		ropt3 = new JTextField();
		ropt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ropt3.setForeground(new Color(0, 0, 0));
		ropt3.setEditable(false);
		ropt3.setColumns(10);
		ropt3.setBounds(186, 297, 407, 24);
		panel_3.add(ropt3);
		
		JLabel label_19 = new JLabel("Option - 4");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_19.setBounds(31, 330, 76, 24);
		panel_3.add(label_19);
		
		ropt4 = new JTextField();
		ropt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ropt4.setForeground(new Color(0, 0, 0));
		ropt4.setEditable(false);
		ropt4.setColumns(10);
		ropt4.setBounds(186, 332, 407, 24);
		panel_3.add(ropt4);
		
		JLabel label_20 = new JLabel("Correct Answer");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_20.setBounds(31, 382, 127, 24);
		panel_3.add(label_20);
		
		rcorrectans = new JComboBox();
		rcorrectans.setForeground(new Color(0, 0, 0));
		rcorrectans.setBackground(new Color(255, 255, 255));
		rcorrectans.setBounds(186, 382, 200, 30);
		panel_3.add(rcorrectans);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=rid.getSelectedItem().toString();
				int i=rid.getSelectedIndex();
				String tab=rcourse.getSelectedItem().toString();
				con=OracleConnection.getConnection();
				String query="delete from $table where qid=?";
				String query1=query.replace("$table", tab);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, MyCrypto.encrypt(id));
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Question Successfully Removed");
						rid.setSelectedIndex(0);
						rid.removeItemAt(i);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		});
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.setBackground(new Color(139, 0, 0));
		btnRemove.setBounds(447, 382, 146, 30);
		panel_3.add(btnRemove);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 48, 583, 167);
		panel_3.add(scrollPane_3);
		
		rdescription = new JTextArea();
		rdescription.setFont(new Font("Monospaced", Font.PLAIN, 14));
		rdescription.setEditable(false);
		scrollPane_3.setViewportView(rdescription);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(128, 0, 0));
		panel_5.setBounds(0, 0, 654, 57);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setForeground(new Color(255, 255, 255));
		lblQuestion.setBounds(238, 2, 165, 55);
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
		
		
		
		con=OracleConnection.getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from course");
			while(rs.next())
			{
				course.addItem(MyCrypto.decrypt(rs.getString(1)));
				vcourse.addItem(MyCrypto.decrypt(rs.getString(1)));
				ucourse.addItem(MyCrypto.decrypt(rs.getString(1)));
				rcourse.addItem(MyCrypto.decrypt(rs.getString(1)));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	/*boolean validateQID(String id,String table)
	{
		con=OracleConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from table");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				if(rs.getString(1).equals(id))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/
}
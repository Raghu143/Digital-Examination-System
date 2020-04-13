import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableColumn;
public class TotalAdmin extends JFrame {
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField searchtextField;
	Vector<Vector> data;
	Vector<String> row;
	Vector<String> userList;
	Connection con;
	PreparedStatement ps;
	private JButton btnBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TotalAdmin frame = new TotalAdmin();
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
	public TotalAdmin() {
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
		setBounds(100, 100, 624, 429);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		data=new Vector<Vector>();
		userList=new Vector<String>();
		con=OracleConnection.getConnection();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from admin");
			while(rs.next())
			{
				row=new Vector<String>();
				row.add(MyCrypto.decrypt(rs.getString(1)));
				row.add(MyCrypto.decrypt(rs.getString(2)));
				row.add(MyCrypto.decrypt(rs.getString(3)));
				row.add(MyCrypto.decrypt(rs.getString(4)));
				userList.addElement(MyCrypto.decrypt(rs.getString(4)));
				row.add(MyCrypto.decrypt(rs.getString(5)));
				data.add(row);
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
		
		Vector<String> cols=new Vector<String>();
		cols.add("Name");
		cols.add("Email");
		cols.add("Contact");
		cols.add("Username");
		cols.add("Password");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 122, 559, 245);
		contentPane.add(scrollPane);
		
		table = new JTable(data,cols){
			   public boolean isCellEditable(int row, int column){
			        return false;
			   }
			   public boolean isCellResizable(int row, int column){
			        return false;
			   }
			}; 
		table.getTableHeader().setResizingAllowed(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.getTableHeader().setFont(new Font("SansSerif",Font.BOLD, 16));
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setRowHeight(25);
		
		TableColumn col0=table.getColumnModel().getColumn(0);
		col0.setPreferredWidth(200);
		TableColumn col1=table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(200);
		TableColumn col2=table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(200);
		TableColumn col3=table.getColumnModel().getColumn(3);
		col3.setPreferredWidth(200);
		TableColumn col4=table.getColumnModel().getColumn(4);
		col4.setPreferredWidth(200);
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JLabel lblNewLabel = new JLabel("Total Registered Admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 11, 565, 31);
		contentPane.add(lblNewLabel);
		
		searchtextField = new JTextField();
		searchtextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchtextField.setToolTipText("Enter Username and search");
		searchtextField.setBounds(128, 72, 150, 31);
		contentPane.add(searchtextField);
		searchtextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchData=searchtextField.getText();
				if(searchData.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please provide username");
					searchtextField.requestFocus();
					return;
				}
				if(!userList.contains(searchData))
				{
					JOptionPane.showMessageDialog(null, "This username doesn't exist");
					searchtextField.setText("");
					searchtextField.requestFocus();
					return;
				}
				for(int i=0;i<table.getRowCount();i++)
				{
					if(table.getValueAt(i,2).equals(searchData))
					{
						table.setRowSelectionAllowed(true);
					    table.setRowSelectionInterval(i, i);
					}
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(288, 72, 89, 31);
		contentPane.add(btnSearch);
		
		JButton btnDeleteSelectedData = new JButton("Delete Selected Data");
		btnDeleteSelectedData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				int index=table.getSelectedRow();
				if(index<0)
				{
					JOptionPane.showMessageDialog(null, "Please select a row");
					return;
				}
				String username=model.getValueAt(index,3).toString();
				JOptionPane.showMessageDialog(null, username);
				con=OracleConnection.getConnection();
				try {
					ps=con.prepareStatement("delete from admin where username=?");
					ps.setString(1, MyCrypto.encrypt(username));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					model.removeRow(index);
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
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
		btnDeleteSelectedData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteSelectedData.setBounds(387, 72, 205, 31);
		contentPane.add(btnDeleteSelectedData);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(33, 72, 89, 31);
		contentPane.add(btnBack);
	}
}
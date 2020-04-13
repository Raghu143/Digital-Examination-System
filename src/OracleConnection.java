import java.sql.*;

import javax.swing.JOptionPane;
public class OracleConnection {
	static Connection getConnection()
	{
		try
		{
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			//Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://100.64.67.237/myprojectt","ALPHA","LKGw9cAJpUPBRfWV"); 
			Class.forName("com.mysql.jdbc.Driver");  
			//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12232663","sql12232663","WV2DcRUVPv");
			return con;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e);
		} catch (ClassNotFoundException ce) {
			JOptionPane.showMessageDialog(null,ce);
		}
		return null;
	}
}
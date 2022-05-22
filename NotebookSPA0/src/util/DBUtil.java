package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public DBUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection getConnection(){
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Notebook";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, "sa", "ht3789517@");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeAll(ResultSet rs, Statement st, Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

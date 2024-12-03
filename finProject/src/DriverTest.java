import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {

	public static void main(String[] args) {
		
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","1122");
			System.out.println("Success");
		}catch(SQLException ex) {
			System.out.println("SQLExceptin" + ex);
		}catch(Exception e) {
			System.out.println("Exception"+e);
		}

	}

}
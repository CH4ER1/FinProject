package RoomKiosk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlarmDatabase {
   Connection con = null;
    static Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/kiosk_DB";
    String user = "root";
    String passwd = "1234";
    
    public AlarmDatabase() {
       try {
          Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement();
            System.out.println("success");
       }catch (Exception e) {
            System.out.println("failed " + e.toString());
        }
    }
   
    public void insertalarmlist(String ampm, int hour, int minute, boolean everyday, String selected_days) {
       
       String sql = "INSERT INTO alarmlist (ampm, hour, minute, everyday, selected_days) VALUES ('"
                + ampm + "', " + hour + ", " + minute + ", '" + everyday + "', '" + selected_days  + "')";
       
       try {
          stmt.executeUpdate(sql);
            System.out.println("Alarm inserted successfully.");
       }catch (SQLException e) {
            System.out.println("Insertion failed: " + e.toString());
        }
    }
    public void close() {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.toString());
        }
    }
    
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      new AlarmDatabase();
   }

}

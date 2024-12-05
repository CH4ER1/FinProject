package RoomKiosk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import RoomKiosk.Alarm; //HotelKiosk패키지에서 Alarm클래스 임포트

public class AlarmDatabase {
   Connection con = null;
    static Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/Hotel";
    String user = "root";
    String passwd = "1122";
    
    
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
       String sql = "INSERT INTO alarm (ampm, hour, minute, everyday, selected_days) VALUES ('"
                + ampm + "', " + hour + ", " + minute + ", '" + everyday+ "', '" + selected_days  + "')";
       
       try {
          stmt.executeUpdate(sql);
            System.out.println("Alarm inserted successfully.");
       }catch (SQLException e) {
            System.out.println("Insertion failed: " + e.toString());
        }
    }
    
 
    
    //데이터 조회
    public List<Alarm> getAlarms(){
       List<Alarm> alarms = new ArrayList<>();
       String sql = "SELECT * FROM alarm";
       try(ResultSet rs = stmt.executeQuery(sql)){
          while(rs.next()) {
             String ampm = rs.getString("ampm");
             int hour = rs.getInt("hour");
             int minute = rs.getInt("minute");
             boolean everyday = rs.getBoolean("everyday");
             String selectedDays = rs.getString("selected_days");
             
             Alarm alarm = new Alarm(ampm, hour, minute, everyday, selectedDays);
             alarms.add(alarm);
             
          }
       }
       catch(SQLException e) {
          System.out.println("Failed to fetch alarms: "+ e.getMessage());
       }
       return alarms;
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
      new AlarmDatabase() ;

      }

}

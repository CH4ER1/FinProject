package RoomKiosk;

/*
 * 합칠 때 table명 확인 : 현재 dbkioskfood로 작성되어 있음
 * id/pw 확인 필요
 * dbkioskfood 테이블 아래 order_food 테이블에 sql문 삽입하는 형태로 작성되어 있음
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class FoodDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/dbkioskfood?serverTimezone=Asia/Seoul";
    private static final String USERID = "root";
    private static final String PASSWORD = "0623";

    // 데이터 삽입 메서드 (선택적인 choice 값을 맵으로 받기)
    public static void insertFood(int room_number, int price, Map<String, Integer> choices) {
        Connection connect = null;
        PreparedStatement Statement = null;

        try {
            // 데이터베이스 연결
            connect = DriverManager.getConnection(URL, USERID, PASSWORD);
            
            // 기본 쿼리
            StringBuilder sql = new StringBuilder("INSERT INTO order_food (room_number, price");
            
         // 선택적 컬럼 추가
            for (String key : choices.keySet()) {
                sql.append(", ").append(key);
            }
            
            sql.append(") VALUES (?, ?");
            
         // 선택적 값 추가
            for (String key : choices.keySet()) {
                sql.append(", ?");
            }
            sql.append(")");

            Statement = connect.prepareStatement(sql.toString());
            
            // ?에 값 설정
            Statement.setInt(1, room_number);
            Statement.setInt(2, price);

            // 선택적 값 설정 (ex. 스파게티를 넣으면 스파게티 항목 추가)
            int index = 3;
            for (Integer value : choices.values()) {
                Statement.setInt(index++, value); // Map에서 value를 가져와서 설정
            }
            
            // 쿼리 실행
            int rowsAffected = Statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (Statement != null) {
                    Statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

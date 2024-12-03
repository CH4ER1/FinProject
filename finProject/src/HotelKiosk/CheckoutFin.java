package HotelKiosk;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.*;
//호텔 키오스크 체크아웃완료
public class CheckoutFin extends JFrame{
	   private String reservationNum;

   public CheckoutFin(String name, String reservationNumber) {
	    super("Hotel Kiosk");
	    this.reservationNum = reservationNumber;
	    setSize(700, 850);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container contentPane = getContentPane();
	    contentPane.setLayout(new BorderLayout());

	    contentPane.add(new NorthPanel(), BorderLayout.NORTH);
	    contentPane.add(new CenterPanel(name, reservationNumber), BorderLayout.CENTER); // 방 번호 전달
	    contentPane.add(new WestPanel(), BorderLayout.WEST);
	    contentPane.add(new EastPanel(), BorderLayout.EAST);
	    contentPane.add(new SouthPanel(), BorderLayout.SOUTH);

	    setVisible(true);
	}

    

   public static void main(String[] args) {
	   new CheckoutFin("이지원", "RES1122");
   }
   
	class NorthPanel extends JPanel {
		public NorthPanel() {
			setBackground(new Color(74, 69, 66));
			setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1.0;			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0.1;
			
			JLabel emptyLabel = new JLabel();
			emptyLabel.setOpaque(true);
			emptyLabel.setBackground(new Color(74, 69, 66));
			emptyLabel.setPreferredSize(new Dimension(700, 10));
			add(emptyLabel, gbc);
			
            gbc.gridy = 1; // 두 번째 행
            gbc.weighty = 0.0; 
                                                
            JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            logoPanel.setBackground(new Color(74, 69, 66));
            JLabel logo = new JLabel("");
            ImageIcon icon = new ImageIcon("images/logo.png");
            logo.setIcon(icon);
            logoPanel.add(logo);
            logoPanel.setPreferredSize(new Dimension(700,50));
            add(logoPanel, gbc); 

            gbc.gridy = 2; 
            gbc.weighty = 0.0; 
            gbc.insets = new Insets(0, 0, 30, 0); 
            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            textPanel.setBackground(new Color(74, 69, 66));
            
            JLabel leftBar = new JLabel();
            leftBar.setOpaque(true);
            leftBar.setBackground(new Color(255, 178, 165));
            leftBar.setPreferredSize(new Dimension(200, 3));
            
            JLabel rightBar = new JLabel();
            rightBar.setOpaque(true);
            rightBar.setBackground(new Color(255, 178, 165));
            rightBar.setPreferredSize(new Dimension(200, 3));
            
            JLabel textLabel = new JLabel("   체크아웃   ");
            textLabel.setForeground(Color.WHITE);
            textLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 18)); 
            
            textPanel.add(leftBar, BorderLayout.WEST);
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(rightBar, BorderLayout.EAST);
           
            textPanel.setPreferredSize(new Dimension(700, 90));
            add(textPanel, gbc);
			
		}
	}
   
 
   class CenterPanel extends JPanel {
       public CenterPanel(String name, String reservationNumber) {
	        setBackground(new Color(104, 90, 90));
	        setLayout(new GridBagLayout()); // GridBagLayout 사용

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(10, 10, 10, 10); // 컴포넌트 간 간격
	        gbc.fill = GridBagConstraints.CENTER;
	        gbc.anchor = GridBagConstraints.CENTER;

	        // 방 번호 제목 ("방 번호")를 담을 패널
	        JPanel titlePanel = new JPanel();
	        titlePanel.setPreferredSize(new Dimension(80, 40)); // 직사각형 크기 설정
	        titlePanel.setBackground(new Color(255, 178, 165)); // 직사각형 배경색
	        titlePanel.setLayout(new GridBagLayout()); // 중앙 정렬

	        // 방 번호 제목 ("방 번호")
	        JLabel roomLabel = new JLabel("방 번호");
	        roomLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 14));
	        roomLabel.setForeground(Color.WHITE); // 텍스트 색상
	        titlePanel.add(roomLabel); // 텍스트를 패널에 추가
	        gbc.gridy = 0; // 첫 번째 행
	        add(titlePanel, gbc);
	        
	        // 데이터베이스에서 방 번호 가져오기
	        String roomNumber = getRoomNumber(reservationNumber);
	        
	        // 방 번호 레이블
	        JLabel roomNumberLabel = new JLabel(roomNumber != null ? roomNumber : "정보 없음");
	        roomNumberLabel.setFont(new Font("Elephant", Font.PLAIN, 36)); // 글씨 크기 조정
	        roomNumberLabel.setForeground(Color.WHITE);
	        gbc.gridy = 1; // 두 번째 행
	        add(roomNumberLabel, gbc);

	        // 체크인 완료 메시지 ("체크아웃이 완료되었습니다.")
	        JLabel completeMessage = new JLabel("체크아웃이 완료되었습니다.");
	        completeMessage.setFont(new Font("KoPubDotum Bold", Font.BOLD, 20));
	        completeMessage.setForeground(new Color(255, 178, 165));
	        gbc.gridy = 2; // 세 번째 행
	        add(completeMessage, gbc);

	        // 안내 메시지 ("프론트에 룸 키를 반납하세요.")
	        JLabel subMessage = new JLabel("프론트에 룸 키를 반납하세요.");
	        subMessage.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        subMessage.setForeground(Color.WHITE);
	        gbc.gridy = 3; // 네 번째 행
	        add(subMessage, gbc);

	        // 중앙에 패널 배치 정렬
	        setPreferredSize(new Dimension(700, 500));
       }
       
	    public String getRoomNumber(String reservationNumber) {
	        String roomNumber = null;
	        String query = "SELECT room_number FROM SelfCheckinInfo WHERE reservation_num = ?";
	        
	        try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(query)) {
	            
	            pstmt.setString(1, reservationNumber);  // 예약 번호로 조회
	            
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                roomNumber = rs.getString("room_number");  // 방 번호를 가져옴
	            }
	            
	        } catch (SQLException e) {
	            System.out.println("데이터베이스 오류: " + e.getMessage());
	        }
	        
	        return roomNumber;
	    }

   }
   class WestPanel extends JPanel {
      public WestPanel() {
         setBackground(new Color(74, 69, 66));
         add(new JLabel("       "));
         setPreferredSize(new Dimension(100, 300));
      }
   }
   
   class EastPanel extends JPanel {
      public EastPanel() {
         setBackground(new Color(74, 69, 66));
         add(new JLabel("       "));
         setPreferredSize(new Dimension(100, 300));
      }
   }
   
	class SouthPanel extends JPanel {
	    private int remainingTime = 10; // 초기 카운트다운 시간

	    public SouthPanel() {
	        setBackground(new Color(74, 69, 66));
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬
	        setPreferredSize(new Dimension(700, 300));

	        // 상단 여백
	        add(Box.createVerticalGlue());

	        // 중앙에 배치할 JLabel
	        JLabel infoLabel = new JLabel("10초 뒤 메인 화면으로 돌아갑니다.");
	        infoLabel.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        infoLabel.setForeground(Color.GRAY);
	        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
	        add(infoLabel);

	        // 하단 여백
	        add(Box.createVerticalGlue());

	        // Timer로 카운트다운 기능 구현
	        Timer timer = new Timer(1000, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (remainingTime > 0) {
	                    remainingTime--;
	                    infoLabel.setText(remainingTime + "초 뒤 메인 화면으로 돌아갑니다.");
	                } else {
	                    ((Timer) e.getSource()).stop();
	                    new HotelKioskMain();
	                    ((JFrame) SwingUtilities.getWindowAncestor(SouthPanel.this)).dispose();
	                    
	                }
	            }
	        });
	        timer.start();
	    }
	}
   
   // 모시러 20만큼 깎인 레이블
   class RoundedLabel extends JLabel {
       private int arcWidth;
       private int arcHeight;
       
   public RoundedLabel(String label, int arcWidth, int arcHeight) {
        super(label);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 버튼 배경 색상
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.dispose();
        super.paintComponent(g);
    }
   }
}

package RoomKiosk;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// 룸서비스 결제 완료
public class RoomKioskFoodFin extends JFrame {
   public RoomKioskFoodFin() {
      super("Room Kiosk");
      setSize(700, 850);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(new NPanel(), BorderLayout.NORTH);
      contentPane.add(new CPanel(), BorderLayout.CENTER);
      contentPane.add(new WPanel(), BorderLayout.WEST);
      contentPane.add(new EPanel(), BorderLayout.EAST);
      contentPane.add(new SPanel(), BorderLayout.SOUTH);
      
      setVisible(true);
      
   }
   
   public static void main(String[] args) {
      new RoomKioskFoodFin();
   }
   
   class NPanel extends JPanel {
      public NPanel() {
         setBackground(new Color(255, 201, 169));
         setLayout(new GridBagLayout());
         
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.weightx = 1.0;         
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.weighty = 0.1;
         
         JLabel emptyLabel = new JLabel();
         emptyLabel.setOpaque(true);
         emptyLabel.setBackground(new Color(255, 201, 169));
         emptyLabel.setPreferredSize(new Dimension(700, 10));
         add(emptyLabel, gbc);
         
            gbc.gridy = 1; // 두 번째 행
            gbc.weighty = 0.0; // 로고 패널의 세로 비율
            
            JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            logoPanel.setBackground(new Color(255, 201, 169));
            JLabel logo = new JLabel("");
            ImageIcon icon = new ImageIcon("images/BlackLogo.png");
            logo.setIcon(icon);
            logoPanel.add(logo);
            logoPanel.setPreferredSize(new Dimension(700,50));
            add(logoPanel, gbc); // 두 번째 셀에 로고 패널 추가

            // 텍스트 패널
            gbc.gridy = 2; // 세 번째 행
            gbc.weighty = 0.0; // 텍스트 패널의 세로 비율
            gbc.insets = new Insets(0, 0, 30, 0); // 아래쪽 여백 30px 추가
            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            textPanel.setBackground(new Color(255, 201, 169));
            
            JLabel leftBar = new JLabel();
            leftBar.setOpaque(true);
            leftBar.setBackground(Color.WHITE);
            leftBar.setPreferredSize(new Dimension(250, 3));
            
            JLabel rightBar = new JLabel();
            rightBar.setOpaque(true);
            rightBar.setBackground(Color.WHITE);
            rightBar.setPreferredSize(new Dimension(250, 3));
            
            JLabel textLabel = new JLabel("   룸 서비스   ");
            textLabel.setForeground(new Color(132, 107, 100));
            textLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 18)); // 글꼴 및 크기 설정
            
            textPanel.add(leftBar, BorderLayout.WEST);
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(rightBar, BorderLayout.EAST);
           
            textPanel.setPreferredSize(new Dimension(700, 90));
            add(textPanel, gbc);
      }
   }
   
   class CPanel extends JPanel {
       public CPanel() {
           // 배경색 설정
           setBackground(new Color(255, 236, 236));
           setLayout(new GridBagLayout()); // GridBagLayout 사용

           GridBagConstraints gbc = new GridBagConstraints();
           gbc.insets = new Insets(10, 10, 10, 10); // 컴포넌트 간 간격
           gbc.fill = GridBagConstraints.CENTER;
           gbc.anchor = GridBagConstraints.CENTER;

           // 주문 번호 제목 ("주문 번호")를 담을 패널
           JPanel titlePanel = new JPanel();
           titlePanel.setPreferredSize(new Dimension(80, 40)); // 직사각형 크기 설정
           titlePanel.setBackground(new Color(229, 158, 138)); // 직사각형 배경색
           titlePanel.setLayout(new GridBagLayout()); // 중앙 정렬

           // 주문 번호 제목 ("주문 번호")
           JLabel roomLabel = new JLabel("주문 번호");
           roomLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 14));
           roomLabel.setForeground(Color.WHITE); // 텍스트 색상
           titlePanel.add(roomLabel); // 텍스트를 패널에 추가
           gbc.gridy = 0; // 첫 번째 행
           add(titlePanel, gbc);

           // 주문 번호 ("146")
           JLabel roomNumber = new JLabel("146");
           roomNumber.setFont(new Font("Elephant", Font.PLAIN, 36)); // 글씨 크기 조정
           roomNumber.setForeground(new Color(223, 122, 118));
           gbc.gridy = 1; // 두 번째 행
           add(roomNumber, gbc);

           JLabel completeMessage = new JLabel("결제가 완료되었습니다.");
           completeMessage.setFont(new Font("KoPubDotum Bold", Font.BOLD, 20));
           completeMessage.setForeground(new Color(132, 107, 100));
           gbc.gridy = 2; // 세 번째 행
           add(completeMessage, gbc);

           JLabel subMessage = new JLabel("식사가 준비되면 객실로 가져다 드리겠습니다.");
           subMessage.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
           subMessage.setForeground(new Color(212, 159, 159));
           gbc.gridy = 3; // 네 번째 행
           add(subMessage, gbc);

           // 중앙에 패널 배치 정렬
           setPreferredSize(new Dimension(700, 500));
       }
   }
  
   class WPanel extends JPanel {
      public WPanel() {
         setBackground(new Color(255, 201, 169));
         add(new JLabel("       "));
         setPreferredSize(new Dimension(100, 300));
      }
   }
   
   class EPanel extends JPanel {
      public EPanel() {
         setBackground(new Color(255, 201, 169));
         add(new JLabel("       "));
         setPreferredSize(new Dimension(100, 300));
      }
   }
   
   class SPanel extends JPanel {
       private int remainingTime = 10; // 초기 카운트다운 시간

       public SPanel() {
           setBackground(new Color(255, 201, 169));
           setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬
           setPreferredSize(new Dimension(700, 300));

           // 상단 여백
           add(Box.createVerticalGlue());

           // 중앙에 배치할 JLabel
           JLabel infoLabel = new JLabel("10초 뒤 메인 화면으로 돌아갑니다.");
           infoLabel.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
           infoLabel.setForeground(new Color(132, 107, 100));
           infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
           add(infoLabel);

           add(Box.createVerticalGlue());

           Timer timer = new Timer(1000, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if (remainingTime > 0) {
                       infoLabel.setText(remainingTime + "초 뒤 메인 화면으로 돌아갑니다.");
                       remainingTime--;
                   } else {
                       ((Timer) e.getSource()).stop(); 
                       new RoomKioskMain();
                       dispose(); // 현재 창 닫기
                   }
               }
           });
           timer.start();
       }
   }
}

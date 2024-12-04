package RoomKiosk;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomKioskMealFin extends JFrame {
    public RoomKioskMealFin() {
        super("Hotel Kiosk");
        setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 동작 설정
        Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new NorthPanel(), BorderLayout.NORTH); // 북쪽 패널 추가
        contentPane.add(new CenterPanel(), BorderLayout.CENTER); // 가운데 패널 추가
        contentPane.add(new SouthPanel(), BorderLayout.SOUTH); // 하단 패널 추가
        contentPane.add(new EastPanel(), BorderLayout.EAST);
        contentPane.add(new WestPanel(), BorderLayout.WEST);

        setVisible(true); // 프레임을 화면에 표시
    }

    public static void main(String[] args) {
        new RoomKioskMealFin();
    }

    class NorthPanel extends JPanel {
        public NorthPanel() {
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
    class CenterPanel extends JPanel {
        public CenterPanel() {
            setBackground(new Color(255, 220, 200));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 세로 방향으로 배치

            add(OrderFinPanel(new Color(255, 236, 236)));
        }
        
        // 조식권 구매 패널
        private JPanel OrderFinPanel(Color bgColor) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout()); // GridBagLayout 사용
            panel.setBackground(bgColor);
            panel.setAlignmentX(CENTER_ALIGNMENT);
            panel.setPreferredSize(new Dimension(680, 250)); // 패널 크기 조절

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15); // 여백 설정
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            // 설명 배치용 패널
            JPanel finOrderPanel = new JPanel();
            finOrderPanel.setBackground(bgColor);
            finOrderPanel.setLayout(new BoxLayout(finOrderPanel, BoxLayout.Y_AXIS));  // 수평 배치
            finOrderPanel.setPreferredSize(new Dimension(170, 220));
            finOrderPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            
            // '방 번호'
            RoundedButtonTen roomNumButton = new RoundedButtonTen("방 번호");
            roomNumButton.setPreferredSize(new Dimension(200, 25)); // 버튼 크기 조정
            roomNumButton.setFont(new Font("KoPubDotum Bold", Font.BOLD, 15));
            roomNumButton.setBackground(new Color(229,158,138)); // 버튼 배경색
            roomNumButton.setForeground(Color.WHITE);
            roomNumButton.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
            gbc.gridy = 0; 
            finOrderPanel.add(roomNumButton);
            finOrderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
             
            // 방 번호 Label (숫자)
            JLabel roomNumLabel = new JLabel("801");
            roomNumLabel.setForeground(new Color(223, 122, 118));
            roomNumLabel.setFont(new Font("SoukouMincho", Font.BOLD, 80)); // 글꼴 및 크기 설정
            roomNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
            roomNumLabel.setPreferredSize(new Dimension(110, 35));

            finOrderPanel.add(Box.createRigidArea(new Dimension(0,10)));
            finOrderPanel.add(roomNumLabel);
            
            // 구매 완료 안내 Label
            JLabel finOrderLabel = new JLabel("구매가 완료되었습니다.");
            finOrderLabel.setForeground(new Color(114, 89, 80));
            finOrderLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 24)); // 글꼴 및 크기 설정
            finOrderLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
            
            finOrderPanel.add(Box.createRigidArea(new Dimension(0,20)));
            finOrderPanel.add(finOrderLabel);
            
         // 부가 설명 Label
            JLabel finDescLabel = new JLabel("구매하신 조식권은 체크아웃 전까지 사용 가능합니다.");
            finDescLabel.setForeground(new Color(186, 114, 114));
            finDescLabel.setFont(new Font("KoPubDotum Medium", Font.PLAIN, 16)); // 글꼴 및 크기 설정
            finDescLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
            finOrderPanel.add(Box.createRigidArea(new Dimension(0,10)));
            finOrderPanel.add(finDescLabel);
            
            gbc.weightx = 1; // 공간 완전히 차지
          
            panel.add(finOrderPanel, gbc);
            return panel;
        }
    }
    
    class SouthPanel extends JPanel {
    	private int remainingTime = 10;
    	
        public SouthPanel() {
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
    
    // 모시러 10만큼 깎인 버튼
    class RoundedButtonTen extends JButton {
        public RoundedButtonTen(String label) {
            super(label);
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // 모서리를 둥글게
            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 40); // 기본 크기 설정
        }
    }
    
    class WestPanel extends JPanel {
        //왼쪽 패널 빈공간 설정
        public WestPanel() {
            setBackground(new Color(255, 201, 169));
            add(new JLabel("       "));
            setPreferredSize(new Dimension(80, 300));
        }
    }
    class EastPanel extends JPanel {
        //왼쪽 패널 빈공간 설정
        public EastPanel() {
            setBackground(new Color(255, 201, 169));
            add(new JLabel("       "));
            setPreferredSize(new Dimension(80, 300));
        }
    }
}

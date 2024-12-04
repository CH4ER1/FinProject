package RoomKiosk;

import javax.swing.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomKioskAlarmMain extends JFrame {
    public RoomKioskAlarmMain() {
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
        new RoomKioskAlarmMain();
    }

    class NorthPanel extends JPanel {
        public NorthPanel() {
            setBackground(new Color(255, 220, 200));
            setLayout(new GridBagLayout()); // GridBagLayout 사용

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0; // 가로 방향으로 확대

            // 빈 레이블 추가
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.1; // 빈 레이블의 세로 비율
            JLabel emptyLabel = new JLabel();
            emptyLabel.setOpaque(true);
            emptyLabel.setBackground(new Color(255, 220, 200));
            emptyLabel.setPreferredSize(new Dimension(700, 40)); // 세로 크기 조절
            add(emptyLabel, gbc); // 첫 번째 셀에 빈 레이블 추가

            // 로고 패널
            gbc.gridy = 1; // 두 번째 행
            gbc.weighty = 0.0; // 로고 패널의 세로 비율
            JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            logoPanel.setBackground(new Color(255, 220, 200));
            JLabel logo = new JLabel("");
            ImageIcon icon = new ImageIcon("images/room_logo.png");
            logo.setIcon(icon);
            logoPanel.add(logo);
            logoPanel.setPreferredSize(new Dimension(700, 100));
            add(logoPanel, gbc); // 두 번째 셀에 로고 패널 추가

            // 텍스트 패널
            gbc.gridy = 2; // 세 번째 행
            gbc.weighty = 0.0; // 텍스트 패널의 세로 비율
            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            textPanel.setBackground(new Color(255, 220, 200));

            JLabel leftBar = new JLabel();
            leftBar.setOpaque(true);
            leftBar.setBackground(Color.WHITE);
            leftBar.setPreferredSize(new Dimension(200, 3));

            JLabel rightBar = new JLabel();
            rightBar.setOpaque(true);
            rightBar.setBackground(Color.WHITE);
            rightBar.setPreferredSize(new Dimension(200, 3));

            JLabel textLabel = new JLabel("알람 목록");
            textLabel.setForeground(new Color(95, 70, 70));
            textLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 24)); // 글꼴 및 크기 설정
            textPanel.setPreferredSize(new Dimension(700, 70));

            textPanel.add(leftBar, BorderLayout.WEST);
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(rightBar, BorderLayout.EAST);

            add(textPanel, gbc); // 세 번째 셀에 텍스트 패널 추가
        }
    }

    class CenterPanel extends JPanel {
        public CenterPanel() {
            setBackground(new Color(255, 220, 200));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 세로 방향으로 배치

            add(createAlarmList(8, 45, true, false, new Color(251,173,151)));
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(createAlarmList(12, 40, false, true, new Color(251,173,151)));
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(createAlarmList(10, 10, true, false, new Color(251,173,151)));
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(createAlarmList(5, 28, true, true, new Color(251,173,151)));
            add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        // 알람 목록 Panel
        private JPanel createAlarmList(int hour, int minute, boolean isDay, boolean onAlarm, Color bgColor) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout()); // GridBagLayout 사용
            panel.setBackground(bgColor);

            LineBorder outerBorder = new LineBorder(new Color(255,236,236), 5); // 외부 테두리
            EmptyBorder innerPadding = new EmptyBorder(5, 5, 5, 5); // 내부 여백
            LineBorder innerBorder = new LineBorder(new Color(255,236,236), 2); // 내부 테두리

            // CompoundBorder를 사용하여 두 테두리 결합
            panel.setBorder(new CompoundBorder(outerBorder, new CompoundBorder(innerPadding, innerBorder)));
            panel.setPreferredSize(new Dimension(680, 100)); // 패널 크기 조절

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15); // 여백 설정
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // 알람이 적용되는 요일이 적힌 레이블
            JLabel dayAlarm = createLabel("매일");
            dayAlarm.setForeground(new Color(255,236,236));
            dayAlarm.setFont(new Font("KoPubDotum Bold", Font.BOLD, 20));
            dayAlarm.setPreferredSize(new Dimension(5, 40)); // 크기 조정
            dayAlarm.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
            gbc.gridx = 0;
            panel.add(dayAlarm, gbc);

            // 알람 적용 시간이 적힌 레이블
            JLabel time_alarm = createLabel(hour + ":" + minute);
            time_alarm.setForeground(new Color(255,236,231));
            time_alarm.setFont(new Font("KoPubDotum Bold", Font.BOLD, 60));
            time_alarm.setVerticalAlignment(SwingConstants.CENTER);  // 세로로 중앙 정렬
            time_alarm.setPreferredSize(new Dimension(150, 70)); // 크기 조정
            gbc.gridx = 1;
            panel.add(time_alarm, gbc);

            // 오전/오후 여부가 적힌 레이블
            JLabel dayNight = createLabel("");
            if (isDay == true) {
                dayNight.setText("오전");
            } else {
                dayNight.setText("오후");
            }
            dayNight.setForeground(new Color(255,236,231));
            dayNight.setFont(new Font("SeoulHangang CBL", Font.BOLD, 30));
            dayNight.setPreferredSize(new Dimension(70, 40)); // 크기 조정
            dayNight.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
            gbc.gridx = 2;
            panel.add(dayNight, gbc);

            // 알람 스위치 
            AlarmSwitchButton alarmOnButton = new AlarmSwitchButton("", onAlarm);
            alarmOnButton.setPreferredSize(new Dimension(100, 40)); // 버튼 크기 조정
            alarmOnButton.setFont(new Font("KoPubDotum Bold", Font.BOLD, 15));
            alarmOnButton.setBackground(new Color(255,236,231)); // 버튼 배경색
            alarmOnButton.setForeground(Color.WHITE);
            gbc.gridx = 3;
            panel.add(alarmOnButton, gbc);

            return panel;
        }

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("KoPubDotum Bold", Font.BOLD, 18)); // 글자 크기 조정
            return label;
        }
    }
    
    class SouthPanel extends JPanel {
        public SouthPanel() {
            setBackground(new Color(255, 220, 200));
            RoundedButton paymentButton = new RoundedButton("알람 추가");
            paymentButton.setPreferredSize(new Dimension(200, 25)); // 버튼 크기 조정
            paymentButton.setFont(new Font("KoPubDotum Bold", Font.BOLD, 22));
            paymentButton.setBackground(new Color(190, 107, 104)); // 버튼 배경색
            paymentButton.setForeground(Color.WHITE);
            add(paymentButton);
            add(new JLabel("       "));
            setPreferredSize(new Dimension(0, 100));
        }
    }
    
    // 모시러 20만큼 깎인 버튼
    class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 모서리를 둥글게
            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(270, 60); // 기본 크기 설정
        }
    }
    
    // 알람 ON/OFF 스위치 버튼
    class AlarmSwitchButton extends JButton {
       private boolean on;
        public AlarmSwitchButton(String label, boolean on) {
            super(label);
            this.on = on;
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
           Graphics2D g2d = (Graphics2D) g;
           
           g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           
            Color shadowColor = new Color(0, 0, 0, 50); // 가장 오른쪽은 투명도
            int shadowOffsetX = 4; // X축 오프셋
            int shadowOffsetY = 4; // Y축 오프셋
           
         // 그림자 그리기
            g2d.setColor(shadowColor);
            g2d.fillRoundRect(5 + shadowOffsetX, 4 + shadowOffsetY, 75, 25, 25, 25);
            
            g.setColor(getBackground());
            g.fillRoundRect(5, 4, 75, 25, 25, 25); // 모서리를 둥글게
            
            if (on == true) {
                g2d.setColor(shadowColor);
                g2d.fillOval(57, 2, 35, 35);
                
                // 원 (버튼)
                g.setColor(new Color(241, 132, 128));
                g.fillOval(55, 0, 35, 35);
            } else {
               
                g2d.setColor(shadowColor);
                g2d.fillOval(2, 2, 35, 35);
                
                // 원 (버튼)
                g.setColor(new Color(190, 107, 104));
                g.fillOval(0, 0, 35, 35);
            }

            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 40); // 기본 크기 설정
        }
        
        @Override
        public Dimension getMinimumSize() {
            return new Dimension(100, 40); // 최소 크기 지정
        }
    }
    
    class WestPanel extends JPanel {
        //왼쪽 패널 빈공간 설정
        public WestPanel() {
            setBackground(new Color(255, 220, 200));
            add(new JLabel("       "));
            setPreferredSize(new Dimension(70, 300));
        }
    }
    class EastPanel extends JPanel {
        //왼쪽 패널 빈공간 설정
        public EastPanel() {
            setBackground(new Color(255, 220, 200));
            add(new JLabel("       "));
            setPreferredSize(new Dimension(70, 300));
        }
    }
}
package RoomKiosk;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomKioskTempLightFin extends JFrame {
	public RoomKioskTempLightFin() {
		super("Room Kiosk");
		setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 동작 설정
        Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(new NPanel(), BorderLayout.NORTH); // 북쪽 패널 추가
        contentPane.add(new CPanel(), BorderLayout.CENTER); // 가운데 패널 추가
        contentPane.add(new SPanel(), BorderLayout.SOUTH); // 하단 패널 추가
        contentPane.add(new EPanel(), BorderLayout.EAST);
        contentPane.add(new WPanel(), BorderLayout.WEST);

        setVisible(true); // 프레임을 화면에 표시
	}
	
	public static void main(String[] args) {
		new RoomKioskTempLightFin();
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
	
	class SPanel extends JPanel {
		private int remainingTime = 10;
		public SPanel() {
	           setBackground(new Color(255, 201, 169));
	           setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬
	           setPreferredSize(new Dimension(700, 180));

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
		            public void actionPerformed(ActionEvent e) {
		                if (remainingTime > 0) {
		                    remainingTime--;
		                    infoLabel.setText(remainingTime + "초 뒤 메인 화면으로 돌아갑니다.");
		                } else {
		                    ((Timer) e.getSource()).stop();
		                    new RoomKioskMain();
		                    ((JFrame) SwingUtilities.getWindowAncestor(SPanel.this)).dispose();
		                    
		                }
		            }
		        });
		        timer.start();
		}
	}
	
	class EPanel extends JPanel {
		public EPanel() {
	         setBackground(new Color(255, 201, 169));
	         add(new JLabel("       "));
	         setPreferredSize(new Dimension(100, 300));
		}
	}
	
	class WPanel extends JPanel {
		public WPanel() {
	         setBackground(new Color(255, 201, 169));
	         add(new JLabel("       "));
	         setPreferredSize(new Dimension(100, 300));
		}
	}
	class CPanel extends JPanel {
	    public CPanel() {
	        Integer temperature = RoomKioskTempLight.getNowTemp();
	        Boolean light1 = RoomKioskTempLight.getNowLight1();
	        Boolean light2 = RoomKioskTempLight.getNowLight2();
	        Boolean light3 = RoomKioskTempLight.getNowLight3();

	        setBackground(new Color(255, 236, 236));
	        setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.fill = GridBagConstraints.CENTER;
	        gbc.anchor = GridBagConstraints.CENTER;

	        // Title Panel
	        JPanel titlePanel = new JPanel();
	        titlePanel.setPreferredSize(new Dimension(80, 40));
	        titlePanel.setBackground(new Color(229, 158, 138));
	        titlePanel.setLayout(new GridBagLayout());

	        JLabel roomLabel = new JLabel("설정 완료");
	        roomLabel.setFont(new Font("KoPubDotum Bold", Font.BOLD, 14));
	        roomLabel.setForeground(Color.WHITE);
	        titlePanel.add(roomLabel);
	        gbc.gridy = 0; // 첫 번째 행
	        gbc.gridx = 0; // 중앙 정렬
	        gbc.gridwidth = 4; // 전체 열 사용
	        add(titlePanel, gbc);

	        // Image
	        ImageIcon icon = new ImageIcon("images/temperature.png");
	        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(img);
	        JLabel tempserviceLabel = new JLabel(scaledIcon);
	        gbc.gridy = 1;
	        gbc.gridwidth = 4; // 전체 열 사용
	        add(tempserviceLabel, gbc);

	        // Complete Message
	        JLabel completeMessage = new JLabel("온도 및 조명 설정이 완료되었습니다");
	        completeMessage.setFont(new Font("KoPubDotum Bold", Font.BOLD, 20));
	        completeMessage.setForeground(new Color(132, 107, 100));
	        gbc.gridy = 2;
	        gbc.gridwidth = 4; // 전체 열 사용
	        add(completeMessage, gbc);

	        // Temperature
	        JLabel tempMessage = new JLabel("설정 온도");
	        tempMessage.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        tempMessage.setForeground(new Color(132, 107, 100));
	        gbc.gridy = 3;
	        gbc.gridwidth = 4; // 전체 열 사용
	        add(tempMessage, gbc);

	        JLabel tempLabel = new JLabel(temperature + "℃");
	        tempLabel.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 14));
	        tempLabel.setForeground(new Color(132, 107, 100));
	        gbc.gridy = 4;
	        add(tempLabel, gbc);

	        // Lights
	        JLabel subMessage = new JLabel("설정 조명");
	        subMessage.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        subMessage.setForeground(new Color(132, 107, 100));
	        gbc.gridy = 5;
	        add(subMessage, gbc);

	        JPanel lightPanel = new JPanel();
	        lightPanel.setLayout(new GridBagLayout());
	        lightPanel.setBackground(new Color(255, 236, 236));
	        GridBagConstraints lightGbc = new GridBagConstraints();
	        lightGbc.insets = new Insets(5, 5, 5, 5);

	        JLabel lightLabel1 = new JLabel(light1 ? "ON" : "OFF");
	        lightLabel1.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        lightLabel1.setForeground(new Color(132, 107, 100));
	        lightGbc.gridx = 0;
	        lightPanel.add(lightLabel1, lightGbc);

	        JLabel lightLabel2 = new JLabel(light2 ? "ON" : "OFF");
	        lightLabel2.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        lightLabel2.setForeground(new Color(132, 107, 100));
	        lightGbc.gridx = 1;
	        lightPanel.add(lightLabel2, lightGbc);

	        JLabel lightLabel3 = new JLabel(light3 ? "ON" : "OFF");
	        lightLabel3.setFont(new Font("KoPubDotum Bold", Font.PLAIN, 16));
	        lightLabel3.setForeground(new Color(132, 107, 100));
	        lightGbc.gridx = 2;
	        lightPanel.add(lightLabel3, lightGbc);

	        gbc.gridy = 6;
	        gbc.gridwidth = 4; // 전체 열 사용
	        add(lightPanel, gbc);

	        setPreferredSize(new Dimension(700, 600));
	    }
	}


}

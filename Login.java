import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
   SixTeam team = new SixTeam();
         
   JFrame f1 = new JFrame();
   JButton b1 = new JButton();
   JLabel lbl = new JLabel("CHATTING");
   JLabel lbl1 = new JLabel("NickName");
   JLabel lbl2 = new JLabel("IP              ");
   JLabel lbl3 = new JLabel("PORT       ");
   JTextField tf1 = new JTextField("", 15);
   JTextField tf2 = new JTextField("", 15);
   JTextField tf3 = new JTextField("", 15);
   JPanel p1 = new JPanel(); // nick name
   JPanel p2 = new JPanel(); // ip
   JPanel p3 = new JPanel(); // port
   String nickName;
   static String ip;
   String port;

   // 에러 처리 변수
   JFrame f2 = new JFrame();
   JLabel errorLbl = new JLabel("다시 입력하세요");
   JButton errorButton = new JButton("확인");

   // 글씨들
   public void label() {
      lbl.setBounds(150, 100, 180, 100);
      lbl.setHorizontalAlignment(lbl.CENTER);
      lbl.setFont(lbl.getFont().deriveFont(30.0f));
      f1.add(lbl);
   }

   // 택스트 박스 , 글씨 panel
   public void textBox() {
      // 닉네임 panel
      lbl1.setBounds(100, 150, 50, 30);
      lbl1.setFont(lbl2.getFont().deriveFont(14.0f));
      lbl1.setHorizontalAlignment(JLabel.RIGHT);
      p1.add(lbl1);
      p1.add(tf1);
      p1.setBounds(100, 200, 250, 30);
      f1.add(p1);

      // ip panel
      lbl2.setBounds(100, 150, 50, 30);
      lbl2.setSize(80, 30);
      lbl2.setFont(lbl2.getFont().deriveFont(14.0f));
      lbl2.setHorizontalAlignment(JLabel.RIGHT);
      p2.add(lbl2);
      p2.add(tf2);
      p2.setBounds(100, 250, 250, 30);
      f1.add(p2);

      lbl3.setFont(lbl3.getFont().deriveFont(14.0f));
      lbl3.setHorizontalAlignment(JLabel.RIGHT);
      p3.add(lbl3);
      p3.add(tf3);
      p3.setBounds(100, 300, 250, 30);
      f1.add(p3);
      // port panel
   }

   // Frame 설정
   public void frame() {
      f1.setTitle("CHATTING");
      f1.setSize(500, 600);
      f1.setLayout(null);
      f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

   // 접속 버튼 박스
   public void button() {
      b1.setBounds(200, 400, 100, 40);
      // setBounds ( 가로 , 세로 , 가로길이 , 세로길이 )
      b1.setText("접속");
      f1.add(b1);

   }

   // 버튼 클릭 이벤트 처리
   public void clickEvent() {
      b1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            // textField 의 값을 가져온다.
            nickName = tf1.getText(); // 닉네임
            ip = tf2.getText(); // ip
            port = tf3.getText(); // port번호

            // 빈 값을 넣었을 경우, 오류 출력
            if (nickName.equals("") || ip.equals("") || port.equals("")) {
               f2.setTitle("오류");
               f2.setSize(400, 300);
               f2.setLayout(null);
               errorLbl.setBounds(150, 50, 100, 100);
               errorLbl.setHorizontalAlignment(lbl.CENTER);
               errorLbl.setFont(errorLbl.getFont().deriveFont(13.0f));
               f2.add(errorLbl);
               errorButton.setBounds(150, 130, 100, 40);
               f2.add(errorButton);
               f2.setVisible(true);
               f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

               // 에러 버튼 클릭 이벤트 처리
               errorLbl.addMouseListener(new MouseAdapter() {
                  public void mouseClicked(MouseEvent e) {
                     f2.dispose();
                  }
               });

            } else {
               // 제대로 작성했을 경우
               Socket socket = null;
               int inputPort = Integer.parseInt(port);
               try {
                  socket = new Socket("192.168.3.18", inputPort);

                  new ClientExample4(nickName, socket);
                  new PerClinetThread(nickName, socket);   //nickName을 perClekdkd에 전달
               } catch (Exception e1) {
                  System.out.println(e1.getMessage());
               }
               team.sixteammain(nickName, socket);
               f1.dispose();
            }
         }

      });
   }

   
   public static void main(String[] args) {
      Login lo = new Login();

      lo.label();
      lo.textBox();
      lo.button();
      lo.clickEvent();
      lo.frame();
      lo.f1.setVisible(true);

      // setBounds ( 가로 , 세로 , 가로길이 , 세로길이 )
      // setLocation .: 컴포넌트 위치 지정
      // setSize : 컴포넌트 크기 지정
      // setBounds : 컴포넌트 위치 , 크기 지정
      // setPriffersize : 컴포넌트 최적 크기 지정
   }
}
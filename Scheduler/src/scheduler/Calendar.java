package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.ibm.icu.util.ChineseCalendar;

public class Calendar {
	private ChineseCalendar lunar;
	private java.util.Calendar solar;
	private final JFrame jf = new JFrame("Schedule");
	
	ScheduleInform si3;
	
	private int K;
	private int yy;
	private int mm;
	private int MMM;
   private int cYear;
   private int cMonth;
   private int cDate;
   private int lunarYear;
   private int lunarMonth;
   private int lunarDate;
   
   private static int endDates[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   private static int endDate;
   private static int gap;
   private static int cc;
   private static int dd = 0;
   private static int count = 0;
   
   private static String s[] = {"             January", "            February", "               March"
		   				, "               April", "                 May", "                June"
		   				, "                July", "              August", "           September"
		   				, "             October", "            November", "            December"};
   
   private static String cs;
   
   public static JPanel calendarPanel = new JPanel();
   private static JPanel topBar = new JPanel();
   private static JPanel datePanel = new JPanel();
   private static JPanel calendarBarA = new JPanel();
   private static JPanel calendarBarB = new JPanel();
   
   static JLabel todayL = new JLabel("      Calendar");
   static JLabel selectY = new JLabel();
   static JLabel selectM = new JLabel();
   static JLabel selectD = new JLabel();
   
   private static JButton calendarB[][] = new JButton[6][7];
   private static JButton bb;
   private static JButton BB;
   private static JButton leftButton = new JButton("               <");
   private static JButton rightButton = new JButton(">               ");
   private static JButton enterButton = new JButton("ENTER");
   
   JTextArea jta = new JTextArea();
   
   //주어진 연도, 월에 해당하는 달력을 그리는 함수
   private void drawCalendar(int Y, int M, int D) {
	   yy = Y;
	   mm = M;
	   int d = D;
	   cc = 1;
      endDate = endDates[M-1];
      
      //윤년일 때 2월은 29일까지
      if(isLeap(Y)&&(M==2)) {
         endDate++;
      }
      
      //인자로 주어진 날짜에 해당하는 캘린더 객체 
      java.util.Calendar monthFrame = java.util.Calendar.getInstance();
      monthFrame.set(Y, M-1, 1);
      
      //1일이 나타나기 전까지의 calendarB 갯수
      gap = monthFrame.get(java.util.Calendar.DAY_OF_WEEK)-1;

      for(int i = 0; i < 6; i++) {
         for(int j = 0; j < 7; j++) {
            calendarB[i][j].setText("");
            calendarB[i][j].setBackground(new Color(246, 238,199));
            calendarB[i][j].setHorizontalAlignment(SwingConstants.LEFT);
            calendarB[i][j].setVerticalAlignment(SwingConstants.TOP);
         }
      }
      
      for(int i = 0; i < gap; i++) {
    	  int aa = M-2;
    	  if(aa==-1) {
    		  aa = 11;
    	  }
    	  int a = endDates[aa];
    	  bb = calendarB[0][i];
    	  bb.setText(Integer.toString(a - gap + i + 1));
    	  bb.setForeground(Color.GRAY);
      }
      
      for(int i = 1; i<=endDate; i++) {
    	 int k = i;
         bb = calendarB[(gap + i - 1) / 7][(gap + i - 1) % 7];
         bb.setText(Integer.toString(i));
         bb.setForeground(Color.BLACK);
         bb.setBackground(new Color(246, 238,199));
         bb.setFocusPainted(false);
         if((i == D) && (cMonth == MMM)) {
        	BB = bb;
            bb.setForeground(Color.MAGENTA);
         }
         if(((gap + i - 1) % 7) == 6) {
            bb.setForeground(Color.BLUE);
         }
         if(((gap + i - 1) % 7) == 0) {
            bb.setForeground(Color.RED);
         }
		 bb.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseEntered(MouseEvent e) {
	        	 bb.setCursor(new Cursor(Cursor.HAND_CURSOR));
	         }
	         public void mouseExited(MouseEvent e) {
	        	 bb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	         }
	         public void mousePressed(MouseEvent e) {
	        	K = k;
	        	selectD.setText(Integer.toString(k));
	 			jta.setText("");
	 			jf.setLayout(new BorderLayout());
	 			jf.setSize(300, 200);
	 			jta.setSize(250, 150);
	 			jf.add(jta, BorderLayout.CENTER);
	 			enterButton.setSize(250, 50);
	 			jf.add(enterButton, BorderLayout.SOUTH);
	 			if(Schedule.readS(Y, M, K)) {
	 				String contants = Schedule.si.sss;
	 				jta.setText(contants);
	 			}
	 			jf.setVisible(true);
	 			 //BackButton 이벤트
	 		      enterButton.addActionListener(new ActionListener() {
	 					public void actionPerformed(ActionEvent e) {
	 						if(e.getSource() == enterButton) {
	 							Schedule.fs = jta.getText();
	 					        Schedule.writeS(yy, mm, K, Schedule.fs);
	 						}
	 					}
	 				});
	         }
	      });
					
         //양력 공휴일 및 기념일 설정
         switch(M) {
         case 1:
        	 if(i == 1) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;신정</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 16) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;레몬 생일</html>");
        	 }
        	 if(i == 20) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;대한</html>");
        	 }
        	 break;
         case 2:
        	 if(i == 4) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;입춘</html>");
        	 }
        	 if(i == 15) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;예킹 생일</html>");
        	 }
        	 if(i == 19) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;우수</html>");
        	 }
        	 break;
         case 3:
        	 if(i == 1) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;삼일절</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 6) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;경칩</html>");
        	 }
        	 if(i == 21) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;춘분</html>");
        	 }
        	 break;
         case 4:
        	 if(i == 5) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;청명</html>");
        	 }
        	 if(i == 20) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;곡우</html>");
        	 }
        	 break;
         case 5:
        	 if(i == 5) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;어린이날</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 6) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;입하</html>");
        		 if(((gap + i - 1) % 7)==1) {
        			 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;입하&emsp&emsp&emsp&emsp;대체공휴일</html>");
        			 bb.setForeground(Color.RED);
        		 }
        	 }
        	 if(i == 21) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;소만</html>");
        	 }
        	 break;
         case 6:
        	 if(i == 6) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;현충일&emsp&emsp&ensp;망종</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 21) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;하지</html>");
        	 }
        	 break;
         case 7:
        	 if(i == 7) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;소서</html>");
        	 }
        	 if(i == 21) bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;내 생일♡</html>");
        	 if(i == 23) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;대서</html>");
        	 }
        	 break;
         case 8:
        	 if(i == 8) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;입추</html>");
        	 }
        	 if(i == 15) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;광복절</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 23) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;처서</html>");
        	 }
        	 break;
         case 9:
        	 if(i == 8) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;백로</html>");
        	 }
        	 if(i == 23) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;추분</html>");
        	 }
        	 break;
         case 10:
        	 if(i == 3) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;개천절</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 7) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;한로</html>");
        	 }
        	 if(i == 9) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;한글날</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 if(i == 24) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;상강</html>");
        	 }
        	 break;
         case 11:
        	 if(i == 8) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;입동</html>");
        	 }
        	 if(i == 22) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;소설</html>");
        	 }
        	 if(i == 29) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;두부 생일</html>");
        	 }
        	 break;
         case 12:
        	 if(i == 7) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;대설</html>");
        	 }
        	 if(i == 22) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;동지</html>");
        	 }
        	 if(i == 25) {
        		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;크리스마스</html>");
        		 bb.setForeground(Color.RED);
        	 }
        	 break;
         default:
        	System.out.println("잘못된 달");
         }
      }
      
      solar.set(java.util.Calendar.YEAR, Y);
      solar.set(java.util.Calendar.MONTH,  M-1);
      
      for(int i = 1; i<=endDate; i++) {
          bb = calendarB[(gap + i - 1) / 7][(gap + i - 1) % 7];
          
          solar.set(java.util.Calendar.DAY_OF_MONTH, i);
          lunar.setTimeInMillis(solar.getTimeInMillis());
          
          //ChinessCalendar.EXTENDED_YEAR 는 Calendar.YEAR 값과 2637 만큼의 차이를 가짐.
          lunarYear = lunar.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
          lunarMonth = lunar.get(ChineseCalendar.MONTH)+1;
          lunarDate = lunar.get(ChineseCalendar.DAY_OF_MONTH);

          //음력 공휴일 설정
          switch(lunarMonth) {
          case 1:
        	  if(lunarDate == 1) {
          		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;설날</html>");
          		 bb.setForeground(Color.RED);
          		 if(i > 1) {
          			if((((gap + i - 1) % 7)-1) < 0) {
          				calendarB[((gap + i - 1) / 7)-1][6].setForeground(Color.RED);
          			}
          			else {
          				calendarB[(gap + i - 1) / 7][((gap + i - 1) % 7)-1].setForeground(Color.RED);
          			}
          		 }
          		if(((gap + i - 1) % 7) == 0) {
          		  if((i+2)<=endDate) {
          			calendarB[(gap + i - 1) / 7][2].setText("<html>"+(i+2)+"&emsp&emsp&emsp&emsp&emsp;대체공휴일</html>");
            		  calendarB[(gap + i - 1) / 7][2].setForeground(Color.RED);
          		  }
          		}
          	 }
        	  if(lunarDate == 2) {
         		 bb.setForeground(Color.RED);
         	 }
         	 break;
          case 2:
         	 break;
          case 3:
         	 break;
          case 4:
        	  if((lunarDate == 8) && (M != 5)) {
        			  bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;석가탄신일</html>");
              		 bb.setForeground(Color.RED);
        	  }
         	 break;
          case 5:
         	 break;
          case 6:
         	 break;
          case 7:
         	 break;
          case 8:
         	 if(lunarDate == 15) {
         		 bb.setText("<html>"+i+"&emsp&emsp&emsp&emsp&emsp;추석</html>");
         		 bb.setForeground(Color.RED);
         	 }
         	 if((lunarDate == 14) || lunarDate == 16) {
        		 bb.setForeground(Color.RED);
        	 }
         	 break;
          case 9:
         	 break;
          case 10:
         	 break;
          case 11:
         	 break;
          case 12:
         	 break;
          default:
         	 System.out.println("잘못된 음력 달");
         	 break;
          }
       }
     
      for(int i = gap + endDate; i < 42; i++) {
    	  bb = calendarB[i/7][i%7];
    	  bb.setText(Integer.toString(cc++));
    	  bb.setForeground(Color.GRAY);
      }

   }
   
   //메인 화면에서 Calendar버튼 클릭 시 calendar화면 샛팅
   public Calendar() {
	   jta.setText("");
	   
      //각 패널의 배치관리자
      calendarPanel.setLayout(null);
      topBar.setLayout(new BorderLayout());
      calendarBarA.setLayout(new GridLayout(7, 7));
      calendarBarB.setLayout(new BorderLayout());
      
      topBar.setSize(400, 200);
      topBar.setLocation(0, 80);
      calendarBarB.setSize(800, 590);
      calendarBarB.setLocation(400, 0);
      datePanel.setSize(400, 400);
      datePanel.setLocation(0, 300);
      
      calendarPanel.add(datePanel);
      
      //각 패널의 배경 색
      topBar.setBackground(Color.BLACK);
      calendarBarA.setBackground(Color.ORANGE);
      calendarBarB.setBackground(Color.ORANGE);
      datePanel.setBackground(Color.BLACK);
      calendarPanel.setBackground(Color.BLACK);
      
      todayL.setFont(new Font("", Font.BOLD, 50));
      todayL.setForeground(Color.ORANGE);
      topBar.add(todayL, BorderLayout.NORTH);
      
      //leftButton, rightButton, enterButton 속성
      leftButton.setBackground(Color.BLACK);
      leftButton.setForeground(Color.orange);
      leftButton.setFont(new Font("", Font.PLAIN, 20));
      leftButton.setFocusPainted(false);
      leftButton.setBorderPainted(false);
      rightButton.setBackground(Color.BLACK);
      rightButton.setForeground(Color.orange);
      rightButton.setFont(new Font("", Font.PLAIN, 20));
      rightButton.setFocusPainted(false);
      rightButton.setBorderPainted(false);
      
      //연도, 달 라벨 속성
      selectY.setForeground(Color.ORANGE);
      selectM.setForeground(Color.ORANGE);
      
      //현재 연도, 달, 일, 달의 끝 설정
      java.util.Calendar now = java.util.Calendar.getInstance();
      cYear = now.get(java.util.Calendar.YEAR);
      cMonth = now.get(java.util.Calendar.MONTH)+1;
      MMM = cMonth;
      cDate = now.get(java.util.Calendar.DAY_OF_MONTH);
      endDate = now.getMaximum(java.util.Calendar.DATE);
      
      //윤년이면 2월이 29일
      if(isLeap(cYear)&&(cMonth==2)) {
         endDate++;
      }
      
      //현재 달을 영어 문자열로 표현
      cs = s[cMonth-1];
      
      //레이블에 현재 년도와 달 표시
      selectY.setText("        " + Integer.toString(cYear));
      selectY.setFont(new Font("", Font.CENTER_BASELINE, 20));
      selectM.setText(cs);
      selectM.setFont(new Font("", Font.ITALIC, 35));
      selectD.setText(Integer.toString(cDate));
      selectD.setFont(new Font("", Font.ITALIC, 100));
      selectD.setForeground(Color.ORANGE);
      datePanel.add(selectD);
      
      String days[] = {"Sun", "Mon", "Thu", "Wed", "Thi", "Fri", "Sat"};
      
      if(count == 0) {
         for(int i = 0; i < 7; i++) {
            bb = new JButton(days[i]);
            bb.setFocusPainted(false);
            bb.setBorderPainted(false);
            if(i==0) {
               bb.setForeground(Color.RED);
            }
            if(i==6) {
               bb.setForeground(Color.BLUE);
            }
            bb.setBackground(Color.ORANGE);
            calendarBarA.add(bb);
         }
      }
      
      topBar.add(leftButton, BorderLayout.WEST);
      topBar.add(selectY, BorderLayout.CENTER);
      topBar.add(rightButton, BorderLayout.EAST);
      topBar.add(selectM, BorderLayout.SOUTH);
      
      if(count == 0) {
         for (int i = 0; i < 6; i++) {
               for (int j = 0; j < 7; j++) {
                   calendarBarA.add(calendarB[i][j] = new JButton(""));
                   calendarB[i][j].setBackground(Color.ORANGE);
               }
         }
      }
      
      count++;
      
      calendarBarB.add(calendarBarA, BorderLayout.CENTER);
      calendarPanel.add(topBar);
      calendarPanel.add(calendarBarB);
      
      lunar = new ChineseCalendar();
      solar = java.util.Calendar.getInstance();
      
      drawCalendar(cYear, cMonth, cDate);
      
    //leftButton 이벤트
      leftButton.addMouseListener(new MouseAdapter() {
         
         @Override
         public void mouseEntered(MouseEvent e) {
            leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
         public void mouseExited(MouseEvent e) {
            leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
         public void mousePressed(MouseEvent e) {
            cMonth--;
            if(cMonth==0) {
               cMonth = 12;
               cYear--;
            }
            
            cs = s[cMonth-1];
            
            selectY.setText("        " +Integer.toString(cYear));
            selectM.setText(cs);
            selectM.setFont(new Font("", Font.ITALIC, 35));
            
            System.out.println(cYear + "년 " + cMonth + "월 " + cDate +"일");
            
            drawCalendar(cYear, cMonth, cDate);
         }
      });
      
      //rightButton 이벤트
      rightButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
         public void mouseExited(MouseEvent e) {
            rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
         public void mousePressed(MouseEvent e) {
            cMonth++;
            if(cMonth==13) {
               cMonth = 1;
               cYear++;
            }
            cs = s[cMonth-1];
            
            selectY.setText("        " + Integer.toString(cYear));
            selectM.setText(cs);
            selectM.setFont(new Font("", Font.ITALIC, 35));
           
            drawCalendar(cYear, cMonth, cDate);
         }
      });
   }
  
   public static boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }
}
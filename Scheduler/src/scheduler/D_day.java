package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class D_day{
   String yearList[];
   static String monthList[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
   
   public static JComboBox<String>monthChoice = new JComboBox<String>(monthList);
   public static JComboBox yearChoice = new JComboBox();
   public static JComboBox dateChoice = new JComboBox();
   
   public static int currentY2;
   public static int currentM2;
   public static int currentD2;
   
   public static int y;
   public static int m;
   public static int d;
   
   private int interestedY;
   private int interestedM;
   private static int interestedD;

   public static JPanel D_dayPanel1 = new JPanel();
   private static JPanel D_dayPanel1_A = new JPanel();
   private static JPanel D_dayPanel1_B = new JPanel();
   private static JPanel D_dayPanel1_C = new JPanel();
   private static JPanel D_dayPanel1_D = new JPanel();
   private static JPanel D_dayPanel1_E = new JPanel();
   private static JPanel D_dayPanel1_F = new JPanel();
   private static JPanel D_dayPanel1_a = new JPanel();
   private static JPanel D_dayPanel1_b = new JPanel();
   private static JPanel D_dayPanel1_c = new JPanel();
   
   private final JLabel D_dayLabel = new JLabel("D-day");
   private final JLabel yearLabel = new JLabel("YEAR");
   private final JLabel monthLabel = new JLabel("MONTH");
   private final JLabel dateLabel = new JLabel("DATE");
   private final JLabel textLabel = new JLabel("내용");
   private final JLabel text2Label = new JLabel("Check Your D-day List");
   private final JLabel text3Label = new JLabel();
   private final JLabel text4Label = new JLabel("Today!"); //D_day count 라벨
   private final JLabel text5Label = new JLabel(""); //D_day 내용 라벨
   
   private JTextField text = new JTextField(40);
   
   private static JButton enterButton = new JButton("ENTER");
   
   //객체 입출력 객체
   ObjectOutputStream oos2;
   ObjectInputStream ois2;
   DdayInform si2;
   
   public String fn2;
   public static String fs2;
   
   public D_day() {   
      D_dayPanel1.setLayout(null);
      
      //각 패널의 배경 색
      D_dayPanel1.setBackground(Color.black);
      D_dayPanel1_A.setBackground(Color.BLACK);
      D_dayPanel1_B.setBackground(Color.black);
      D_dayPanel1_C.setBackground(Color.black);
      D_dayPanel1_D.setBackground(Color.black);
      D_dayPanel1_E.setBackground(Color.black);
      D_dayPanel1_F.setBackground(Color.black);
      text.setBackground(Color.ORANGE);
      text.setForeground(Color.BLACK);
      D_dayPanel1_a.setBackground(Color.BLACK);
      D_dayPanel1_b.setBackground(Color.BLACK);
      D_dayPanel1_c.setBackground(Color.BLACK);

      //각 컴포넌트의 속성
      D_dayLabel.setSize(500, 150);
      D_dayLabel.setForeground(Color.ORANGE);
      D_dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      D_dayLabel.setFont(new Font("", Font.ITALIC, 60));
      D_dayLabel.setLocation(0, 20);
      D_dayPanel1_B.setSize(500, 80);
      D_dayPanel1_B.setLayout(new FlowLayout());
      D_dayPanel1_B.setLocation(0, 170);
      yearLabel.setForeground(Color.ORANGE);
      yearLabel.setFont(new Font("", Font.ITALIC, 30));
      D_dayPanel1_C.setSize(500, 80);
      D_dayPanel1_C.setLayout(new FlowLayout());
      D_dayPanel1_C.setLocation(0, 250);
      monthLabel.setForeground(Color.ORANGE);
      monthLabel.setFont(new Font("", Font.ITALIC, 30));
      D_dayPanel1_D.setSize(500, 80);
      D_dayPanel1_D.setLayout(new FlowLayout());
      D_dayPanel1_D.setLocation(0, 330);
      dateLabel.setForeground(Color.ORANGE);
      dateLabel.setFont(new Font("", Font.ITALIC, 30));
      D_dayPanel1_E.setLayout(new FlowLayout());
      D_dayPanel1_E.setSize(500, 50);
      D_dayPanel1_E.setLocation(0, 410);
      enterButton.setSize(350, 150);
      enterButton.setLocation(0, 470);
      enterButton.setForeground(Color.ORANGE);
      enterButton.setBackground(Color.BLACK);
      enterButton.setFont(new Font("", Font.ITALIC, 35));
      enterButton.setVerticalAlignment(SwingConstants.TOP);
      enterButton.setBorderPainted(false);
      enterButton.setFocusPainted(false);
      enterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
               enterButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(MouseEvent e) {
            	System.out.println("D_day = " + computeD_day());
            	fs2 = text.getText();
            	writeS(interestedY, interestedM, interestedD, fs2);
            }
         });
      
      D_dayPanel1_F.setLocation(0, 460);
      D_dayPanel1_F.setSize(500, 160);
      D_dayPanel1_A.setSize(700, 150);
      D_dayPanel1_A.setLocation(500, 0);
      D_dayPanel1_b.setSize(700, 100);
      D_dayPanel1_b.setLocation(500, 150);
      D_dayPanel1_c.setLayout(new BorderLayout());
      D_dayPanel1_c.setSize(700, 300);
      D_dayPanel1_c.setLocation(500, 250);
      textLabel.setForeground(Color.WHITE);
      textLabel.setFont(new Font("", Font.BOLD, 15));
      text2Label.setForeground(Color.ORANGE);
      text2Label.setFont(new Font("", Font.ITALIC, 60));
      text2Label.setSize(700, 150);
      text3Label.setFont(new Font("", Font.PLAIN, 40));
      text3Label.setForeground(Color.ORANGE);
      text4Label.setFont(new Font("", Font.ITALIC, 100));
      text4Label.setForeground(Color.ORANGE);
      text4Label.setHorizontalAlignment(SwingConstants.CENTER);
      text5Label.setFont(new Font("", Font.PLAIN, 40));
      text5Label.setForeground(Color.ORANGE);
      text5Label.setHorizontalAlignment(SwingConstants.CENTER);
      D_dayPanel1_a.setSize(700, 200);
      D_dayPanel1_a.setLocation(500, 0);
      
      //컴포넌트들을 add
      D_dayPanel1.add(D_dayLabel);
      D_dayPanel1_B.add(yearLabel);
      D_dayPanel1_B.add(yearChoice);
      D_dayPanel1.add(D_dayPanel1_B);
      D_dayPanel1_C.add(monthLabel);
      D_dayPanel1_C.add(monthChoice);
      D_dayPanel1.add(D_dayPanel1_C);
      D_dayPanel1_D.add(dateLabel);
      D_dayPanel1_D.add(dateChoice);
      D_dayPanel1.add(D_dayPanel1_D);
      D_dayPanel1_E.add(textLabel);
      D_dayPanel1_E.add(text);
      D_dayPanel1.add(D_dayPanel1_E);
      D_dayPanel1_F.add(enterButton);
      D_dayPanel1.add(D_dayPanel1_F);
      D_dayPanel1.add(D_dayPanel1_A);
      D_dayPanel1_a.add(text2Label);
      D_dayPanel1_b.add(text3Label);
      D_dayPanel1_c.add(text4Label, BorderLayout.CENTER);
      D_dayPanel1_c.add(text5Label, BorderLayout.SOUTH);
      D_dayPanel1_A.add(text2Label);
      D_dayPanel1_b.add(text3Label);
      D_dayPanel1_c.add(text4Label, BorderLayout.CENTER);
      D_dayPanel1_c.add(text5Label, BorderLayout.SOUTH);
      D_dayPanel1.add(D_dayPanel1_A);
      D_dayPanel1.add(D_dayPanel1_b);
      D_dayPanel1.add(D_dayPanel1_c);
      
      //캘린더 객체를 통한 현재 날짜 추출
      java.util.Calendar interested = java.util.Calendar.getInstance();
      
      //오늘 날짜를 담고있는 변수
      currentY2 = interested.get(java.util.Calendar.YEAR);
      currentM2 = interested.get(java.util.Calendar.MONTH)+1;
      currentD2 = interested.get(java.util.Calendar.DAY_OF_MONTH);
      
      //현재 관심을 두는 날짜를 담고있는 변수
      interestedY = currentY2;
      interestedM = currentM2;
      interestedD = currentD2;
      
      si2 = new DdayInform(interestedY, interestedM, interestedD, null);
      
      //오늘 날짜의 디데이 파일이 존재하는지 확인->존재하면 내용 출력
      drawDdayData(interestedY, interestedM, interestedD);
      D_dayPanel1_E.add(text);
      
      text3Label.setText(currentY2 + "년 " + currentM2 + "월 " + currentD2 +"일 까지");
      text5Label.setText(si2.sss);
      
      //yearChoice 콤보박스 속성
      yearChoice.setEditable(true);
        for (int i = interestedY - 50; i < interestedY + 50; i++) {
           yearChoice.addItem(Integer.toString(i));
        }
        yearChoice.setSelectedItem(Integer.toString(y));
        yearChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
                int i = yearChoice.getSelectedIndex();
                if (i >= 0)
                {
                   interestedY = Integer.parseInt(yearChoice.getSelectedItem().toString());
                   yearChoice.setSelectedItem(Integer.toString(interestedY));
                   System.out.println("interestedY = " + interestedY);
                }
            }
        });
        
        //dateChoice 콤보박스 속성
        dateChoice.setEditable(true);
        System.out.println("currentM = " + currentM2);
        
       if((currentM2 == 1) || (currentM2 == 3) || (currentM2 == 5) || 
             (currentM2 == 7) ||(currentM2 == 8) ||(currentM2 == 10) || (currentM2 == 12)) {
          for(int i = 0; i < 31; i++) {
             dateChoice.addItem(Integer.toString(i+1));
          }
       }
       else if(currentM2 == 2) {
          if(!isLeap(currentY2)) {
             for(int i = 0; i < 28; i++) {
                dateChoice.addItem(Integer.toString(i+1));
             }
          }
          else {
             for(int i = 0; i < 29; i++) {
                dateChoice.addItem(Integer.toString(i+1));
             }
          }
       }
       else {
          for(int i = 0; i < 30; i++) {
             dateChoice.addItem(Integer.toString(i+1));
          }
       }

       dateChoice.setSelectedItem(Integer.toString(interestedD));
       dateChoice.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae)
             {
                 int i = dateChoice.getSelectedIndex();
                 if (i >= 0)
                 {
                    interestedD = Integer.parseInt(dateChoice.getSelectedItem().toString());
                    dateChoice.setSelectedItem(Integer.toString(interestedD));
                    System.out.println("interestedD = " + interestedD);
                    D_dayPanel1_E.add(text);
                    text.setText("");
                    text5Label.setText("");
                    drawDdayData(interestedY, interestedM, interestedD);
                    
                 }
             }
         });
       
       //yearChoice 콤보박스 속성
       yearChoice.setEditable(true);
         for (int i = interestedY - 50; i < interestedY + 50; i++) {
            yearChoice.addItem(Integer.toString(i));
         }
         yearChoice.setSelectedItem(Integer.toString(currentY2));
         yearChoice.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae)
             {
                 int i = yearChoice.getSelectedIndex();
                 if (i >= 0)
                 {
                    interestedY = Integer.parseInt(yearChoice.getSelectedItem().toString());
                    yearChoice.setSelectedItem(Integer.toString(interestedY));
                    drawDateCombo(interestedY, interestedM);
                    System.out.println("interestedY = " + interestedY);
                    text.setText(" ");
                    drawDdayData(interestedY, interestedM, interestedD);
                 }
             }
         });
         
         //monthChoice 콤보박스 속성
         monthChoice.setEditable(true);
         monthChoice.setSelectedItem(Integer.toString(currentM2));
         monthChoice.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae)
             {
                 int i = monthChoice.getSelectedIndex();
                 if (i >= 0)
                 {
                    interestedM = Integer.parseInt(monthChoice.getSelectedItem().toString());
                    monthChoice.setSelectedItem(Integer.toString(interestedM));
                 }
                 drawDateCombo(interestedY, interestedM);
                 System.out.println("interestedM = " + interestedM);
                 drawDdayData(interestedY, interestedM, interestedD);
                 D_dayPanel1_E.add(text);
                 text.setText("");
                 text5Label.setText("");
             }
         });
                 
    }
    
   //해당 년도와 달에 해당하는 일을 출력
    public void drawDateCombo(int year ,int month) {
       if((month == 1) || (month == 3) || (month == 5) || 
             (month == 7) ||(month == 8) ||(month == 10) || (month == 12)) {
          dateChoice.removeAllItems();
          for(int i = 0; i < 31; i++) {
             dateChoice.addItem(Integer.toString(i+1));
          }
       }
       else if(month == 2) {
          if(!isLeap(year)) {
             dateChoice.removeAllItems();
             for(int i = 0; i < 28; i++) {
                dateChoice.addItem(Integer.toString(i+1));
             }
          }
          else {
             dateChoice.removeAllItems();
             for(int i = 0; i < 29; i++) {
                dateChoice.addItem(Integer.toString(i+1));
             }
          }
       }
       else {
          dateChoice.removeAllItems();
          for(int i = 0; i < 30; i++) 
          {
             dateChoice.addItem(Integer.toString(i+1));
          }
       }
       
       dateChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae)
                {
                    int i = dateChoice.getSelectedIndex();
                    if (i >= 0)
                    {
                       interestedD = Integer.parseInt(dateChoice.getSelectedItem().toString());
                       dateChoice.setSelectedItem(Integer.toString(interestedD));
                       System.out.println("interestedD = " + interestedD);
                       drawDdayData(interestedY, interestedM, interestedD);
                       D_dayPanel1_E.add(text);
                       text.setText("");
                       text5Label.setText("");
                    }
                }
            });
    
   }
    
    //선택된 날짜의 디데이를 출력하고 파일을 읽어서 해당 날짜의 파일이 존재하면 내용 출력
    public void drawDdayData(int yy, int mm, int dd) {
    	System.out.println("D_day = " + computeD_day());
    	text3Label.setText(yy + "년 " + mm + "월 " + dd +"일 까지");
 	   if(readS(yy, mm, dd) == 1) {
 		   text.setText(si2.sss);
 		  text5Label.setText(si2.sss);
 	   }
 	   else {
 		   System.out.println(" si2.interestedY = " +  si2.interestedY + " si2.interestedM = " + si2.interestedM 
 				   + " si2.interestedD = " + si2.interestedD);
 	   }
    }
   
    //디데이 파일을 읽는 함수
   public int readS(int a, int b, int c) {
	   try {
		   File f;
		   fn2 = "D" + a + b + c;
		   f = new File(fn2);
		   
		   //객체를 읽어옴
		   ois2 = new ObjectInputStream(new FileInputStream(f));
		   
		   if(f.exists()) {
			   si2 = (DdayInform)ois2.readObject();
			   System.out.println("읽기 성공");
			  // D_dayPanel1_E.add(text);
			   ois2.close();
			   return 1;
		   }
	   }
	   catch(Exception e) {
		   System.out.println("reading Failed");
	   }
	   
	   return 0;
	   
   }
   
   //디데이 파일을 쓰는 함수
   public void writeS(int a, int b, int c, String s) {
	   try {
		   File ff;
		   String name =  "D" + a + b + c;
		   
		   //선택된 날짜를 파일명으로 하는 파일 생성
		   ff = new File(name);
		   
			oos2 = new ObjectOutputStream(new FileOutputStream(name));
		
			System.out.println(fs2);
			si2 = new DdayInform(a, b, c, fs2);
		
			oos2.writeObject(si2);
			
			System.out.println("si 저장 성공");
			System.out.println("si.interested = " + si2.interestedY + si2.interestedM + si2.interestedD);
			oos2.close();
		} catch (IOException e1) {
			System.out.println("failed");
		}
   }
   
   private long computeD_day() {
	   java.util.Calendar today = java.util.Calendar.getInstance(); //오늘 날짜의 캘린더 객체
	   java.util.Calendar cal = java.util.Calendar.getInstance(); //디데이로 설정한 날짜의 캘린더 객체

	   cal.set(interestedY, interestedM-1, interestedD);
	   
	   long days = cal.getTimeInMillis()-today.getTimeInMillis();

	   long Dday = (days/86400000L)*(-1);
	   
	   if(Dday == 0) {
		   text4Label.setText("Today!");
	   }else if (Dday > 0){
		   text4Label.setText("D+" + Dday);
	   }else {
		   text4Label.setText("D" + Dday);
	   }
	   
	   return Dday;
   }
  
   //윤년인지 검사하여 true, false를 리턴하는 함수
   public static boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }
}
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Schedule{

   String yearList[];
   static String monthList[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
   String dateList1[] = new String[30];
   String dateList2[] = new String[31];
   String dateList3[] = new String[28];
   
   public static JComboBox<String>monthChoice = new JComboBox<String>(monthList);
   public static JComboBox yearChoice = new JComboBox();
   public static JComboBox dateChoice = new JComboBox();
   
   public static int currentY;
   public static int currentM;
   public static int currentD;
   
   public static int interestedY;
   public static int interestedM;
   public static int interestedD;
   
   private static JTextArea ta;

   public static JPanel SchedulePanel1 = new JPanel();
   private static JPanel SchedulePanel1_A = new JPanel();
   private static JPanel SchedulePanel1_B = new JPanel();
   private static JPanel SchedulePanel1_C = new JPanel();
   private static JPanel SchedulePanel1_D = new JPanel();
   private static JPanel SchedulePanel1_E = new JPanel();
   private static JPanel SchedulePanel1_F = new JPanel();
   private static JPanel SchedulePanel1_a = new JPanel();
   private static JPanel SchedulePanel1_b = new JPanel();
   private static JPanel SchedulePanel1_c = new JPanel();
   private static JPanel east = new JPanel();
   private static JPanel west = new JPanel();

   private final JLabel D_dayLabel = new JLabel("Schedule");
   private final JLabel yearLabel = new JLabel("YEAR");
   private final JLabel monthLabel = new JLabel("MONTH");
   private final JLabel dateLabel = new JLabel("DATE");
   private final JLabel todayLabel = new JLabel();

   private final JLabel text2Label = new JLabel("record Your Schedule");
   
   private JButton enterButton = new JButton("ENTER");
      
   static ObjectOutputStream oos;
   static ScheduleInform si;
   
   public static String fn;
   public static String fs;
   
   public Schedule() {
      SchedulePanel1.setLayout(null);
      
      //각 패널의 배경 색
      SchedulePanel1.setBackground(Color.black);
      
      SchedulePanel1_a.setBackground(Color.ORANGE);
      SchedulePanel1_b.setBackground(Color.ORANGE);
      SchedulePanel1_c.setBackground(Color.ORANGE);
      
      SchedulePanel1_B.setBackground(Color.black);
      SchedulePanel1_C.setBackground(Color.black);
      SchedulePanel1_D.setBackground(Color.black);
      SchedulePanel1_E.setBackground(Color.black);
      SchedulePanel1_F.setBackground(Color.black);
      east.setBackground(Color.ORANGE);
      west.setBackground(Color.ORANGE);

      //각 컴포넌트의 속성
      D_dayLabel.setSize(500, 150);
      D_dayLabel.setForeground(Color.ORANGE);
      D_dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      D_dayLabel.setFont(new Font("", Font.ITALIC, 60));
      D_dayLabel.setLocation(0, 20);
      SchedulePanel1_B.setSize(500, 80);
      SchedulePanel1_B.setLayout(new FlowLayout());
      SchedulePanel1_B.setLocation(0, 170);
      yearLabel.setForeground(Color.ORANGE);
      yearLabel.setFont(new Font("", Font.ITALIC, 30));
      SchedulePanel1_C.setSize(500, 80);
      SchedulePanel1_C.setLayout(new FlowLayout());
      SchedulePanel1_C.setLocation(0, 250);
      monthLabel.setForeground(Color.ORANGE);
      monthLabel.setFont(new Font("", Font.ITALIC, 30));
      SchedulePanel1_D.setSize(500, 80);
      SchedulePanel1_D.setLayout(new FlowLayout());
      SchedulePanel1_D.setLocation(0, 330);
      dateLabel.setForeground(Color.ORANGE);
      dateLabel.setFont(new Font("", Font.ITALIC, 30));
      SchedulePanel1_E.setLayout(new FlowLayout());
      SchedulePanel1_E.setSize(500, 50);
      SchedulePanel1_E.setLocation(0, 410);
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
            	 fs = ta.getText();
            	writeS(interestedY, interestedM, interestedD, fs);
        		
            }
         });
      SchedulePanel1_F.setLocation(0, 460);
      SchedulePanel1_F.setSize(500, 160);
      SchedulePanel1_a.setSize(700, 80);
      SchedulePanel1_a.setLocation(500, 0);
      SchedulePanel1_b.setSize(700, 400);
      SchedulePanel1_b.setLayout(new BorderLayout());
      SchedulePanel1_b.setLocation(500, 80);
      SchedulePanel1_c.setSize(700, 105);
      SchedulePanel1_c.setLocation(500, 480);
      SchedulePanel1_c.setLayout(new FlowLayout());

      text2Label.setForeground(Color.BLACK);
      text2Label.setFont(new Font("", Font.ITALIC, 60));
   
      //컴포넌트들을 add
      SchedulePanel1.add(D_dayLabel);
      SchedulePanel1_B.add(yearLabel);
      SchedulePanel1_B.add(yearChoice);
      SchedulePanel1.add(SchedulePanel1_B);
      SchedulePanel1_C.add(monthLabel);
      SchedulePanel1_C.add(monthChoice);
      SchedulePanel1.add(SchedulePanel1_C);
      SchedulePanel1_D.add(dateLabel);
      SchedulePanel1_D.add(dateChoice);
      SchedulePanel1.add(SchedulePanel1_D);

      SchedulePanel1.add(SchedulePanel1_E);
      SchedulePanel1_c.add(enterButton);
      SchedulePanel1.add(SchedulePanel1_F);
      SchedulePanel1.add(SchedulePanel1_A);
      SchedulePanel1_a.add(text2Label);
 

      SchedulePanel1.add(SchedulePanel1_a);
      SchedulePanel1.add(SchedulePanel1_b);
      SchedulePanel1.add(SchedulePanel1_c);
      
      
      //캘린더 객체를 통한 현재 날짜 추출
      java.util.Calendar interested = java.util.Calendar.getInstance();
    
      currentY = interested.get(java.util.Calendar.YEAR);
      currentM = interested.get(java.util.Calendar.MONTH)+1;
      currentD = interested.get(java.util.Calendar.DAY_OF_MONTH);
      interestedY = currentY;
      interestedM = currentM;
      interestedD = currentD;
      
      si = new ScheduleInform(interestedY, interestedM, interestedD, null);
      drawScheduleData(interestedY, interestedM, interestedD);
      ta = new JTextArea();
      SchedulePanel1_b.add(ta, BorderLayout.CENTER);
      readS(currentY, currentM, currentD);
      ta.setText(si.sss);
      
        //dateChoice 콤보박스 속성
       dateChoice.setEditable(true);
       System.out.println("currentM = " + currentM);
       
      if((currentM == 1) || (currentM == 3) || (currentM == 5) || 
            (currentM == 7) ||(currentM == 8) ||(currentM == 10) || (currentM == 12)) {
         for(int i = 0; i < 31; i++) {
            dateChoice.addItem(Integer.toString(i+1));
         }
      }
      else if(currentM == 2) {
         if(!isLeap(currentY)) {
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
                   SchedulePanel1_b.add(ta);
                   ta.setText("");
                   drawScheduleData(interestedY, interestedM, interestedD);
                   
                }
            }
        });
      
      //yearChoice 콤보박스 속성
      yearChoice.setEditable(true);
        for (int i = interestedY - 50; i < interestedY + 50; i++) {
           yearChoice.addItem(Integer.toString(i));
        }
        yearChoice.setSelectedItem(Integer.toString(currentY));
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
                   ta.setText(" ");
                   drawScheduleData(interestedY, interestedM, interestedD);
                }
            }
        });
        
        //monthChoice 콤보박스 속성
        monthChoice.setEditable(true);
        monthChoice.setSelectedItem(Integer.toString(currentM));
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
                drawScheduleData(interestedY, interestedM, interestedD);
                SchedulePanel1_b.add(ta);
                ta.setText("");
            }
        });
                
   }
   
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
                      drawScheduleData(interestedY, interestedM, interestedD);
                      SchedulePanel1_b.add(ta);
                      ta.setText("");
                   }
               }
           });
      
   
   }
   
   public void drawScheduleData(int yy, int mm, int dd) {
	   if(readS(yy, mm, dd)) {
		   ta.setText(si.sss);
	   }
	   else {
		   //ScheduleInform s = new ScheduleInform(yy, mm, dd);
		   //if 파일을 읽어서 interested변수가 각각 일치하면 textArea값 출력
		   	//else 일치하는 것이 없으면 새로운  si객체 생성 후 파일에 쓰기
		   //fs = si.textArea.getText();
		   
		   //si = new ScheduleInform(yy, mm, dd);
		   
		   System.out.println(" si.interestedY = " +  si.interestedY + " si.interestedM = " + si.interestedM 
				   + " si.interestedD = " + si.interestedD);
	   }
   }
   
   public static boolean readS(int a, int b, int c) {
	   try {
		   File f;
		   fn = "" + a + b + c;
		   f = new File(fn);
		   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		   
		   if(f.exists()) {
			   si = (ScheduleInform)ois.readObject();
			   System.out.println("읽기 성공");
			   SchedulePanel1_b.add(ta);
			   
			   ois.close();
			   return true;
		   }
	   }
	   catch(Exception e) {
		   System.out.println("reading Failed");
	   }
	   
	   return false;
	   
   }
   
   public static void writeS(int a, int b, int c, String s) {
	   try {
		   File ff;
		   String name =  "" + a + b + c;
		   ff = new File(name);
		   
			oos = new ObjectOutputStream(new FileOutputStream(name));
		
			System.out.println(fs);
			si = new ScheduleInform(a, b, c, fs);
		
			oos.writeObject(si);
			
			System.out.println("si 저장 성공");
			System.out.println("si.interested = " + si.interestedY + si.interestedM + si.interestedD);
			oos.close();
		} catch (IOException e1) {
			System.out.println("failed");
		}
   }
   public static boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }
}
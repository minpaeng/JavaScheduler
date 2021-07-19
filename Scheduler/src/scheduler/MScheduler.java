package scheduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MScheduler extends JFrame{
	Icon icon = new ImageIcon("bee.png");
	
	private JButton calendarButton = new JButton("Calendar");
	private JButton scheduleButton = new JButton("Schedule");
	private JButton DdayButton = new JButton("D-day");
	private JButton backButton = new JButton("��BACK");
	
	private JPanel backgroundPanel = new JPanel();
	private JPanel calendarPanel = new JPanel();
	private JPanel calendarPanelA = new JPanel();
	private JPanel calendarPanelB= new JPanel();
	private JLabel schedulePanel = new JLabel(icon);
	
	public static JLabel scheduleLabel[] = new JLabel[6];
	private JLabel todaySchedule = new JLabel("Today's Schedule");
	
	public static JPanel clockPanel = new JPanel();
	
	public static int count = 0;
	public static int count1 = 0;

	
	public boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }
	
	public MScheduler() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("calendar.png");
		setIconImage(img);
		
		//������ �Ӽ�
		setSize(1200, 620);
		setTitle("mScheduler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		//�ڷΰ��� ��ư �Ӽ�
		backButton.setSize(110,50);
		backButton.setLocation(2, 5);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backButton.setVisible(false);
		add(backButton);
		
		backgroundPanel.setLayout(null);

		//�⺻ȭ�� ��ư ��ư �Ӽ�
		calendarButton.setSize(400, 300);
		calendarButton.setLocation(0, 0);
		scheduleButton.setSize(400, 300);
		scheduleButton.setLocation(400, 300);
		DdayButton.setSize(400, 300);
		DdayButton.setLocation(800, 0);
		
		backgroundPanel.add(calendarButton);
		backgroundPanel.add(scheduleButton);
		backgroundPanel.add(DdayButton);
		backgroundPanel.add(calendarPanel);
		add(backgroundPanel);
		
		//�⺻ȭ�鿡 �� �ǳ� �Ӽ�
		calendarPanel.setSize(400, 300);
		calendarPanel.setBackground(Color.ORANGE);
		calendarPanel.setLocation(0, 300);
		backgroundPanel.add(calendarPanel);
		schedulePanel.setBackground(Color.ORANGE);
		schedulePanel.setSize(400, 300);
		schedulePanel.setLocation(400, 0);
		backgroundPanel.add(schedulePanel);
		clockPanel.setSize(400, 300);
		clockPanel.setLocation(800,  300);
		clockPanel.setBackground(Color.ORANGE);
		backgroundPanel.add(clockPanel);

		//calendar ��ư �Ӽ�
		calendarButton.setFocusPainted(false);
		calendarButton.setBackground(Color.BLACK);
		calendarButton.setFont(new Font("",Font.TYPE1_FONT, 40));
		calendarButton.setForeground(Color.ORANGE);
		calendarButton.addMouseListener(new MouseAdapter() {
			@Override
			//Ŀ���� ��ư�� ���� �� �̺�Ʈ
			public void mouseEntered(MouseEvent e) {
				calendarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			//Ŀ���� ��ư���� ���� �� �̺�Ʈ
			public void mouseExited(MouseEvent e) {
				calendarButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//��ư�� ������ �� �̺�Ʈ
			public void mousePressed(MouseEvent e) {
				backgroundPanel.setVisible(false);
				add(scheduler.Calendar.calendarPanel);
				scheduler.Calendar.calendarPanel.setVisible(true);
				backButton.setVisible(true);
			}
			
		});
		
		//schedule��ư �Ӽ�
		scheduleButton.setBackground(Color.BLACK);
		scheduleButton.setFont(new Font("",Font.TYPE1_FONT, 40));
		scheduleButton.setForeground(Color.ORANGE);
		scheduleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				scheduleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				scheduleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				backgroundPanel.setVisible(false);
				backButton.setVisible(true);
				count1++;
				if(count1 == 1) {
					add(Schedule.SchedulePanel1);
				}
				Schedule.SchedulePanel1.setVisible(true);
			}
			
		});
		
		//D-day ��ư �Ӽ�
		DdayButton.setBackground(Color.BLACK);
		DdayButton.setFont(new Font("D-day",Font.TYPE1_FONT, 40));
		DdayButton.setForeground(Color.ORANGE);
		DdayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DdayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				DdayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				backgroundPanel.setVisible(false);
				backButton.setVisible(true);
				//add�� �ѹ��� ���ֱ� ���� count����
				count++;
				if(count == 1) {
					add(D_day.D_dayPanel1);	
				}
				D_day.D_dayPanel1.setVisible(true);
			}
			
		});
		
		//Back��ư �Ӽ�
		backButton.setBorderPainted(false);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.ORANGE);
		backButton.setFont(new Font("", Font.PLAIN, 20));
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				backgroundPanel.setVisible(true);
				backButton.setVisible(false);
				scheduler.Calendar.calendarPanel.setVisible(false);
				Schedule.SchedulePanel1.setVisible(false);
				D_day.D_dayPanel1.setVisible(false);
			}
		});
		
		//calendarPanel�� �̹��� Ķ���� ����
		java.util.Calendar monthF = java.util.Calendar.getInstance();
		calendarPanelA.setLayout(new GridLayout(2,0));
		calendarPanel.setLayout(new BorderLayout());
		java.util.Calendar now = java.util.Calendar.getInstance();
		
		//���� ��¥�� ����, ��, ���� ��´�
		int currentYear = now.get(java.util.Calendar.YEAR);
		int currentMonth = now.get(java.util.Calendar.MONTH)+1;
		int currentDate = now.get(java.util.Calendar.DATE);
		int end_of_month = now.getActualMaximum(java.util.Calendar.DATE);
		
		//gap�� ���ϱ� ���� Calendar ��ü(date = 1�� ����)
		monthF.set(currentYear, currentMonth-1, 1);
		
		System.out.println("����: " + monthF.get(java.util.Calendar.DAY_OF_WEEK));
		
		JLabel currentA = new JLabel(currentYear+"��");
		currentA.setBackground(Color.ORANGE);
		currentA.setHorizontalAlignment(SwingConstants.LEFT);
		currentA.setFont(new Font("", Font.CENTER_BASELINE, 12));
		calendarPanelA.add(currentA);
		JLabel currentB = new JLabel(currentMonth+"�� "+currentDate+"��");
		currentB.setBackground(Color.ORANGE);
		currentB.setHorizontalAlignment(SwingConstants.CENTER);
		currentB.setFont(new Font("", Font.CENTER_BASELINE, 15));
		calendarPanelA.add(currentB);
		
		calendarPanelA.setBackground(Color.ORANGE);
		calendarPanelB.setBackground(Color.ORANGE);
		calendarPanel.add(calendarPanelA, BorderLayout.NORTH);
		
		calendarPanelB.setLayout(new GridLayout(0,7));
		
		String days[] = {"Sun", "Mon", "Thu", "Wed", "Thi", "Fri", "Sat"};
		
		JLabel currentL[][] = new JLabel[6][7];
		JLabel b;
		
		//�������� �˻����� �� true�̰� ���� 2���̸� ���� ���� 28�� 1�� ������
		if(isLeap(currentYear)&&(currentMonth==2)) {
			end_of_month++;
		}
		
		//�Ͽ����� ������, ������� �Ķ�������
		for(int i = 0; i < 7; i++) {
			b = new JLabel(days[i]);
			b.setHorizontalAlignment(SwingConstants.CENTER);
			if(i==0) {
				b.setForeground(Color.RED);
			}
			if(i==6) {
				b.setForeground(Color.BLUE);
			}
			b.setBackground(Color.ORANGE);
			calendarPanelB.add(b);
		}
		
		//
		int gap = monthF.get(java.util.Calendar.DAY_OF_WEEK)-1;
		
		System.out.println(gap);
		
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++)
            {
                calendarPanelB.add(currentL[i][j] = new JLabel(""));
            }
		}
		
		for(int i = 1; i<=end_of_month; i++) {
			b = currentL[(gap + i - 1) / 7][(gap + i - 1) % 7];
			b.setHorizontalAlignment(SwingConstants.CENTER);
			b.setText(Integer.toString(i));
			if(currentDate == i) {
				b.setForeground(Color.magenta);
			}
			if(((gap + i - 1) % 7) == 6) {
				b.setForeground(Color.BLUE);
			}
			if(((gap + i - 1) % 7) == 0) {
				b.setForeground(Color.RED);
			}
		}
		
		calendarPanel.add(calendarPanelB, BorderLayout.CENTER);
		
		setVisible(true);
	}	
	
	//mainȭ���� �� ��ư�� ���� ����� �����ϰ� ���ִ� ������ ȣ��
	public static void main(String[] args) throws FileNotFoundException, IOException {
		new MScheduler();
		new Schedule();
		new Calendar();
		new Clock();
		new D_day();
	}

}

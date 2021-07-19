package scheduler;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Clock extends ClockLabel{
	private ActionListener timerListener;
	private Timer t;
	
	public Clock() {
		//main화면의 시계를 표현해주는 레이블들을 add
		scheduler.MScheduler.clockPanel.add(currentTime);
		scheduler.MScheduler.clockPanel.add(apLabel);
		scheduler.MScheduler.clockPanel.add(timeLabel);
	}

}

class ClockLabel implements ActionListener {
	SimpleDateFormat sdf1; // am/pm 출력 포멧
	SimpleDateFormat sdf2; // 시간 출력 포멧
	
	//상속될 것이므로 protected
	protected JLabel currentTime = new JLabel("| Current Time |");
	protected JLabel apLabel = new JLabel();
	protected JLabel timeLabel = new JLabel();
	
	public ClockLabel() {
		//레이블들의 속성
		currentTime.setForeground(Color.WHITE);
		apLabel.setForeground(Color.WHITE);
		timeLabel.setForeground(Color.WHITE);
		currentTime.setFont(new Font("", Font.ITALIC, 40));
		apLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		//시간 포멧 설정
		sdf1 = new SimpleDateFormat("    a    ", Locale.US);
		sdf2 = new SimpleDateFormat("hh:mm:ss ", Locale.US);
		
		//레이블 속성2
        apLabel.setFont(new Font("", Font.ITALIC, 50));		
        timeLabel.setFont(new Font("", Font.ITALIC, 90));

        //1초 단위로 시간 표시
		Timer t = new Timer(1000, this);
		t.start();
	}
	
	//ActionListener 이벤트
	public void actionPerformed(ActionEvent e) {
	  	Date d = new Date();
	  	apLabel.setText(sdf1.format(d));
	  	timeLabel.setText(sdf2.format(d));
	}
}

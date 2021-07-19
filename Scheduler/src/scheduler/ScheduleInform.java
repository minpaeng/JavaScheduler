package scheduler;

import java.awt.Font;
import java.io.Serializable;

import javax.swing.JTextArea;

public class ScheduleInform implements Serializable{

	public int interestedY;
	public int interestedM;
	public int interestedD;
	public String sss;
	
	//JTextArea textArea = new JTextArea();
	
	ScheduleInform(int a, int b, int c, String s) {
		interestedY = a;
		interestedM = b;
		interestedD = c;
		sss = s;
		//textArea.setFont(new Font("", Font.PLAIN, 15));
	}
}

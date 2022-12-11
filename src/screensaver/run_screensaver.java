package screensaver;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*Screensaver class. This is a digital clock that displays the current time with a standard deviation of 60 seconds.
The expected time is correct.
Since it is a screensaver the code has to detect mouse movement and automatically exit.*/
public class run_screensaver {
	
	public static void main(String[] args) {
		String aboutTime = getOffset();
		//not necessary in the screensaver version because the headline won't be visible
		JFrame frame = new JFrame("current time with expected deviation of 60 seconds");
		JLabel label = new JLabel(aboutTime);
		label.setFont(new Font("Serif", Font.BOLD, 200));
		JPanel panel = new JPanel();
		
		//one panel that is centered
		panel.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);		
		panel.add(label);
		frame.add(panel);
		//activate fullscreen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//remove header
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		//get the location of your cursor at the start
		PointerInfo start = MouseInfo.getPointerInfo();
		Point p = start.getLocation();
		int[] coordinates = new int[] {(int) p.getX(), (int) p.getY()};
		
		//updates current time every second
		while(true) {
			//get the location of your cursor again.
			PointerInfo process = MouseInfo.getPointerInfo();
			Point q = process.getLocation();
			int[] coordinates_2 = new int[] {(int) q.getX(), (int) q.getY()};
			//check if coordinates have changed
			if(!equals(coordinates, coordinates_2)) {
				System.exit(0);
			}
			try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			aboutTime = getOffset();
			label.setText(aboutTime);
			label.setFont(new Font("Serif", Font.BOLD, 200));
		}
		
	}
	public static String getOffset() {
		Random rand = new Random();
		double std = rand.nextGaussian()*60;
		Time time = new Time();
		LocalDateTime t = time.getTime();
		time.setTime(t.plusSeconds((long) std));
		return time.getDTF().format(time.getTime());
	}
	
	public static boolean equals(int[] a, int[] b) {
		if(a.length != b.length) {
			return false;
		}
		for(int i=1; i< a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
		
}
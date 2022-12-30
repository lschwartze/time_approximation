package screensaver;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*Screensaver class. This is a digital clock that displays the current time with a standard deviation of 60 seconds.
The expected time is correct.
Since it is a screensaver the code has to detect mouse movement and automatically exit.*/
public class run_screensaver {
	
	public static void main(String[] args) {
		//offset by this number of seconds
		double std = getOffset();
		Time time = new Time();
		LocalDateTime t = time.getTime();
		//add to current time
		time.setTime(t.plusSeconds((long) std));
		String aboutTime = time.getDTF().format(time.getTime());
		
		//print time in label as string
		JFrame frame = new JFrame("current time with expected deviation of 60 seconds");
		JLabel label = new JLabel(aboutTime);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setFont(new Font("Serif", Font.BOLD, 200));
		JPanel panel = new JPanel();
		//align multiple panels in frame vertically
		panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//save offset to show normal distribution approximation
		int[] offset = new int[300];
		for(int i=0; i<offset.length; i++) {
			offset[i] = 0;
		}
		int marg = 60;
		SeedPlot plot = new SeedPlot(offset, marg);
		
		//one panel that is centered
		frame.setLocationRelativeTo(null);		
		panel.add(label);
		panel.add(plot);
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
	            Thread.sleep(990);
	        } 
			catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			//new offset
			std = getOffset();
			time = new Time();
			t = time.getTime();
			time.setTime(t.plusSeconds((long) std));
			aboutTime = time.getDTF().format(time.getTime());
			//update plot
			if((int) std < 150 && (int) std >= -150) {
				offset[(int) std + 150]++;
			}
			panel.remove(plot);
			plot = new SeedPlot(offset, marg);
			panel.add(plot);
			
			//update time
			label.setText(aboutTime);
			label.setFont(new Font("Serif", Font.BOLD, 200));
			panel.revalidate();
			panel.repaint();
		}
		
	}
	public static double getOffset() {
		Random rand = new Random();
		double std = rand.nextGaussian()*60;
		return std;
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
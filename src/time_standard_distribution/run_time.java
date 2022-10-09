package time_standard_distribution;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class run_time {
	
	public static void main(String[] args) {
		String aboutTime = getOffset();
		JFrame frame = new JFrame("current time with expected deviation of 60 seconds");
		JLabel label = new JLabel(aboutTime);
		label.setFont(new Font("Serif", Font.BOLD, 200));
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);		
		
		panel.add(label);
		frame.add(panel);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.setVisible(true);
		
		while(true) {
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
	
		
}

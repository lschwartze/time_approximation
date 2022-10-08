package time_standard_distribution;

import java.time.LocalDateTime;
import java.util.Random;

public class run_time {
	
	public static void main(String[] args) {
		Random rand = new Random();
		double std = rand.nextGaussian();
		Time time = new Time();
		LocalDateTime t = time.getTime();
		time.setTime(time.getTime().plusMinutes((long) std));
		String aboutTime = time.getDTF().format(time.getTime());
		System.out.println(aboutTime);
	}
	
		
}

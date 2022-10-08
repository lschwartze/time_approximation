package time_standard_distribution;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class run_time {
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime time = LocalDateTime.now();
		System.out.println(dtf.format(time));
	}
	
		
}

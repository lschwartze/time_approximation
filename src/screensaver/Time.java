package screensaver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
	private DateTimeFormatter dtf; 
	private LocalDateTime time;
	
	public Time() {
		dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		time = LocalDateTime.now();
	}
	
	public DateTimeFormatter getDTF() {
		return this.dtf;
	}
	public LocalDateTime getTime() {
		return this.time;
	}
	public void setTime(LocalDateTime t) {
		this.time = t;
	}
}

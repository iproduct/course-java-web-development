package datetimeapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeApiDemo {

	public static void main(String[] args) {
		String str = "2019-05-17 12:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
//		LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
		String formattedDateTime = dateTime.format(formatter2); // "1986-04-08 12:30"
		System.out.println(formattedDateTime);
	}

}

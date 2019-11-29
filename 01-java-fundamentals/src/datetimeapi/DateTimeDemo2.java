package datetimeapi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateTimeDemo2 {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate dateOfBirth = LocalDate.of(1982, Month.MAY, 14);
		LocalDateTime now = LocalDateTime.now();
		LocalTime timeOfBirth = LocalTime.of(14, 50);
		LocalDateTime dateTimeOfBirth = LocalDateTime.of(dateOfBirth, timeOfBirth);
		Period howOld = Period.between(dateOfBirth, today);
		Duration age = Duration.between(dateTimeOfBirth, now);
		long daysOld = ChronoUnit.DAYS.between(dateOfBirth, today);
		System.out.println("Your age are: " + howOld.getYears() + " years, " 
		     + howOld.getMonths() + " months, and " + howOld.getDays() 
		     + " days. (" + age.toDays() + " /" + daysOld + "/ days total)");

	}

}

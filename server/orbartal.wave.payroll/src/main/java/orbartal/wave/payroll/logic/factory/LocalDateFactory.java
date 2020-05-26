package orbartal.wave.payroll.logic.factory;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class LocalDateFactory {
	
	public LocalDate buidWithMonthLastDay(LocalDate input) {
		return input.withDayOfMonth(input.lengthOfMonth());
	}

	public LocalDate buildWithMonthFirstDay(LocalDate input) {
		return input.withDayOfMonth(1);
	}

	public LocalDate buildNextDate(LocalDate input) {
		if (input.getDayOfMonth() == 1)
			return input.withDayOfMonth(15);
		else if (input.getDayOfMonth() == 16) {
			return input.withDayOfMonth(input.lengthOfMonth());
		}
		throw new RuntimeException("Invalid date in getNextDate");
	}
}

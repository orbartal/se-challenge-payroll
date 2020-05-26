package orbartal.wave.payroll.logic.factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.PayPeriodDto;

@Service
public class PayPeriodsFactory {

	private PayPeriodFactory payPeriodFactory;

	private LocalDateFactory localDateFactory;

	@Autowired
	public PayPeriodsFactory(PayPeriodFactory payPeriodFactory, LocalDateFactory localDateFactory) {
		this.payPeriodFactory = payPeriodFactory;
		this.localDateFactory = localDateFactory;
	}

	public List<PayPeriodDto> buildPeriodTwoWeeksPeriods(PayPeriodDto globalPeriod) {
		LocalDate start = localDateFactory.buildWithMonthFirstDay(globalPeriod.getStart());
		LocalDate end = localDateFactory.buidWithMonthLastDay(globalPeriod.getEnd());
		LocalDate prev = start;
		LocalDate current = localDateFactory.buildNextDate(prev);
		List<PayPeriodDto> results = new ArrayList<>();
		while (prev.isBefore(end)) {
			PayPeriodDto period = payPeriodFactory.buildPeriod(prev, current);
			results.add(period);
			prev = current.plusDays(1);
			current = localDateFactory.buildNextDate(prev);
		}
		return results;
	}

}

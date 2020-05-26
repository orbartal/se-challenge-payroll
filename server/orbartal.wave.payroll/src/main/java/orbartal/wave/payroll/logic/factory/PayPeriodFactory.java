package orbartal.wave.payroll.logic.factory;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.PayPeriodDto;

@Service
public class PayPeriodFactory {

	public PayPeriodDto buildPeriod(LocalDate start, LocalDate end) {
		PayPeriodDto dto = new PayPeriodDto();
		dto.setStartDate(start);
		dto.setEndDate(end);
		return dto;
	}

}

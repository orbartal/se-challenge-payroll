package orbartal.wave.payroll.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.application.dto.PayPeriodDto;
import orbartal.wave.payroll.data.TimeReportItemDataReader;
import orbartal.wave.payroll.logic.factory.PayPeriodsFactory;

@Service
public class EmployeeReportReader {

	@Autowired
	private TimeReportItemDataReader timeReportItemDataReader;

	@Autowired
	private PayPeriodsFactory payPeriodsFactory;

	public List<EmployeeReportDto> readPayrollReportByUid(Long uid) {
		PayPeriodDto globalPeriod = timeReportItemDataReader.readGlobalPayPeriod();
		List<PayPeriodDto> periods = payPeriodsFactory.buildPeriodTwoWeeksPeriods(globalPeriod);
		return periods.stream()
				.map(p -> timeReportItemDataReader.readEmployeeReportByEmployeeUidAndPeriod(uid, p))
				.filter(p -> p.isPresent())
				.map(p -> p.get())
				.collect(Collectors.toList());
	}

}

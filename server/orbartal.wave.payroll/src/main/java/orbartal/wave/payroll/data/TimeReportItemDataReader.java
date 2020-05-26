package orbartal.wave.payroll.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.EmployeeReportDto;
import orbartal.wave.payroll.application.domain.PayPeriodDto;
import orbartal.wave.payroll.data.domain.TimeReportItemEntity;
import orbartal.wave.payroll.data.repository.TimeReportItemRepository;
import orbartal.wave.payroll.logic.factory.EmployeeReportFactory;
import orbartal.wave.payroll.logic.factory.PayPeriodFactory;

@Service
public class TimeReportItemDataReader {

	@Autowired
	private TimeReportItemRepository timeReportItemRepository;

	@Autowired
	private PayPeriodFactory payPeriodFactory;

	@Autowired
	private EmployeeReportFactory employeeReportFactory;

	public PayPeriodDto readGlobalPayPeriod() {
		LocalDate start = timeReportItemRepository.readGlobalStartDate();
		LocalDate end = timeReportItemRepository.readGlobalEndDate();
		return payPeriodFactory.buildPeriod(start, end);
	}
	
	public Optional<EmployeeReportDto> readEmployeeReportByEmployeeUidAndPeriod(Long uid, PayPeriodDto period) {
		List<TimeReportItemEntity> items = timeReportItemRepository.readPayAmountByEmployeeUidAndPeriod(uid, period.getStart(), period.getEnd());
		double amount = employeeReportFactory.calculateAmount(items);
		return employeeReportFactory.buildOptionalEmployeeReport(uid, period, amount);
	}

}

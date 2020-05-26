package orbartal.wave.payroll.logic.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.application.dto.PayPeriodDto;
import orbartal.wave.payroll.data.entity.TimeReportItemEntity;
import orbartal.wave.payroll.info.JobGroup;

@Service
public class EmployeeReportFactory {
	
	public double calculateAmount(List<TimeReportItemEntity> items) {
		List<JobGroup> groups = Arrays.asList(JobGroup.values());
		Map<String, Double> payByGroup = groups.stream().collect(Collectors.toMap(j->j.name(), j->j.getHourlyPay()));
		return items.stream().mapToDouble(i->i.getHours()*payByGroup.get(i.getJobGroup().getName())).sum();
	}

	public Optional<EmployeeReportDto> buildOptionalEmployeeReport(Long uid, PayPeriodDto period, double amount) {
		if (amount == 0) {
			return Optional.empty();
		}
		EmployeeReportDto dto = new EmployeeReportDto ();
		dto.setAmountPaid(amount+"");
		dto.setEmployeeId(uid);
		dto.setPayPeriod(period);
		return Optional.of(dto);
	}

}

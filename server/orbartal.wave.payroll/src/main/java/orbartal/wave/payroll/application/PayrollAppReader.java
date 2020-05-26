package orbartal.wave.payroll.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.EmployeeReportDto;
import orbartal.wave.payroll.application.domain.PayrollReportDto;
import orbartal.wave.payroll.logic.EmployeesLogicReader;

@Service
public class PayrollAppReader {

	@Autowired
	private EmployeesLogicReader employeesLogicReader;

	public PayrollReportDto readPayrollReport() {
		List<Long> uids = employeesLogicReader.readAllEmployeesUids();
		List<EmployeeReportDto> employeesReports = employeesLogicReader.readEmployeesReport(uids);
		return employeesLogicReader.buildPayrollReport(employeesReports);
	}

}

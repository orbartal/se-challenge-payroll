package orbartal.wave.payroll.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.EmployeeReportDto;
import orbartal.wave.payroll.application.domain.PayrollReportDto;
import orbartal.wave.payroll.data.EmployeeDataReader;

@Service
public class EmployeesLogicReader {

	@Autowired
	private EmployeeDataReader employeeDataReader;

	@Autowired
	private EmployeeReportReader employeeReportReader;

	public List<Long> readAllEmployeesUids() {
		return employeeDataReader.readAllEmployeesUids();
	}

	public List<EmployeeReportDto> readEmployeesReport(List<Long> uids) {
		List<EmployeeReportDto> employeesReports =  new ArrayList<>();
		for (Long uid : uids) {
			List<EmployeeReportDto> reports =  employeeReportReader.readPayrollReportByUid(uid);
			employeesReports.addAll(reports);
		}
		return employeesReports;
	}

	public PayrollReportDto buildPayrollReport(List<EmployeeReportDto> employeesReports) {
		PayrollReportDto dto = new PayrollReportDto();
		dto.setEmployeeReports(employeesReports);
		return dto;
	}

}

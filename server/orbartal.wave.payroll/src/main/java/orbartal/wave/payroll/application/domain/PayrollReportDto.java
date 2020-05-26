package orbartal.wave.payroll.application.domain;

import java.util.List;

public class PayrollReportDto {

	private List<EmployeeReportDto> employeeReports;

	public List<EmployeeReportDto> getEmployeeReports() {
		return employeeReports;
	}

	public void setEmployeeReports(List<EmployeeReportDto> employeeReports) {
		this.employeeReports = employeeReports;
	}

}

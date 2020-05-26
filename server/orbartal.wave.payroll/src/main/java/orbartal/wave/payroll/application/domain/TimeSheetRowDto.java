package orbartal.wave.payroll.application.domain;


public class TimeSheetRowDto {
	
	private String date;
	private String hoursWorked;	
	private String employeeId;
	private String jobGroup;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(String hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

}

package orbartal.wave.payroll.logic.domain;

import java.time.LocalDate;

public class TimeSheetRowInfo {
	
	private final LocalDate date;
	private final double hoursWorked;	
	private final long employeeId;
	private final JobGroupEnum jobGroup;

	public TimeSheetRowInfo(LocalDate date, double hoursWorked, long employeeId, JobGroupEnum jobGroup) {
		this.date = date;
		this.hoursWorked = hoursWorked;
		this.employeeId = employeeId;
		this.jobGroup = jobGroup;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public JobGroupEnum getJobGroup() {
		return jobGroup;
	}

}

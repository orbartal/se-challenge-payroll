package orbartal.wave.payroll.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="time_report_item")
public class TimeReportItemEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "hours", nullable = false)
	private double hours;

	@JoinColumn(name = "employee_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private EmployeeEntity employee;

	@ManyToOne
	@JoinColumn(name = "job_group_id", nullable = false)
	private JobGroupEntity jobGroup;

	@JoinColumn(name = "time_sheet_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private TimeReportEntity timeReport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public JobGroupEntity getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(JobGroupEntity jobGroup) {
		this.jobGroup = jobGroup;
	}

	public TimeReportEntity getTimeReport() {
		return timeReport;
	}

	public void setTimeReport(TimeReportEntity timeReport) {
		this.timeReport = timeReport;
	}

}

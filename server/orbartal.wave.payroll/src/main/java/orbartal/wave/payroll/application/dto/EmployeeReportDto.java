package orbartal.wave.payroll.application.dto;

public class EmployeeReportDto {
	private Long employeeId;
	private PayPeriodDto payPeriod;
	private String amountPaid;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public PayPeriodDto getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriodDto payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

}

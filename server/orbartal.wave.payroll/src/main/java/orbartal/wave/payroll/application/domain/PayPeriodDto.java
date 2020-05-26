package orbartal.wave.payroll.application.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayPeriodDto {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate start) {
		this.startDate = start;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate end) {
		this.endDate = end;
	}

}

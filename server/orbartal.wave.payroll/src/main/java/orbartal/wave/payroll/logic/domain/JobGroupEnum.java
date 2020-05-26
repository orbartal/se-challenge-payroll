package orbartal.wave.payroll.logic.domain;

public enum JobGroupEnum {
	A(20.0), 
	B(30.0);
	
	private final Double hourlyPay;

	private JobGroupEnum(Double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public Double getHourlyPay() {
		return hourlyPay;
	}
	
	 
	
	
	
}

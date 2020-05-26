package orbartal.wave.payroll.info;

public enum JobGroup {
	A(20.0), 
	B(30.0);
	
	private final Double hourlyPay;

	private JobGroup(Double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public Double getHourlyPay() {
		return hourlyPay;
	}
	
	 
	
	
	
}

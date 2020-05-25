package orbartal.wave.payroll.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("orbartal.wave.payroll")
public class MainPayroll {
	public static void main(String[] args) {
		SpringApplication.run(MainPayroll.class, args); 
	}
}


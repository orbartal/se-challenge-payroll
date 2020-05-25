package orbartal.wave.payroll.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("orbartal.wave.payroll")
@EnableJpaRepositories("orbartal.wave.payroll.data.repository")
@EntityScan("orbartal.wave.payroll.data.entity")   
public class MainPayroll {
	public static void main(String[] args) {
		SpringApplication.run(MainPayroll.class, args); 
	}
}


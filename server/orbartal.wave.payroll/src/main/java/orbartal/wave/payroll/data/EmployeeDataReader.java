package orbartal.wave.payroll.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeDataReader {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Long> readAllEmployeesUids() {
		return employeeRepository.readAllEmployeesUids();
	}

}

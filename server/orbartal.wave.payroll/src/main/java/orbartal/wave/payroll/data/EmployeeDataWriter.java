package orbartal.wave.payroll.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.data.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeDataWriter {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeEntity createIfNotExists(Long uid) {
		EmployeeEntity employee = employeeRepository.findByUid(uid);
		return (employee != null) ? employee : saveNewEmployeeEntity(uid);
	}

	private EmployeeEntity saveNewEmployeeEntity(Long uid) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setUid(uid);
		return employeeRepository.save(entity);
	}

}

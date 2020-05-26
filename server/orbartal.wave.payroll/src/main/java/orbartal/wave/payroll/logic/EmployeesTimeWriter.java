package orbartal.wave.payroll.logic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.EmployeeDataWriter;
import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@Service
public class EmployeesTimeWriter {

	@Autowired
	private EmployeeDataWriter employeeData;

	public Map<Long, EmployeeEntity> saveAndReadEmployeesByUids(TimeSheetTableInfo timeSheet) {
		Set<Long> uids = timeSheet.getRows().stream().map(r -> r.getEmployeeId()).collect(Collectors.toSet());
		List<EmployeeEntity> employees = uids.stream().map(u -> employeeData.createIfNotExists(u)).collect(Collectors.toList());
		//List<EmployeeEntity> employees2 = employeeRepository.readByUids(uids);
		return employees.stream().collect(Collectors.toMap(e -> e.getUid(), e -> e));
	}

}

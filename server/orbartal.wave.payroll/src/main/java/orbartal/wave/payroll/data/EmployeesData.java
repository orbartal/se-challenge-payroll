package orbartal.wave.payroll.data;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.entity.EmployeeEntity;
import orbartal.wave.payroll.info.TimeSheetTableInfo;

@Service
public class EmployeesData {

	@Autowired
	private EmployeeData employeeData;

	public Map<Long, EmployeeEntity> saveAndReadEmployeesByUids(TimeSheetTableInfo timeSheet) {
		Set<Long> uids = timeSheet.getRows().stream().map(r -> r.getEmployeeId()).collect(Collectors.toSet());
		List<EmployeeEntity> employees = uids.stream().map(u -> employeeData.createIfNotExists(u)).collect(Collectors.toList());
		//List<EmployeeEntity> employees2 = employeeRepository.readByUids(uids);
		return employees.stream().collect(Collectors.toMap(e -> e.getUid(), e -> e));
	}



}

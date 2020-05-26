package orbartal.wave.payroll.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.domain.TimeReportEntity;
import orbartal.wave.payroll.data.domain.TimeReportItemEntity;
import orbartal.wave.payroll.data.repository.TimeReportItemRepository;
import orbartal.wave.payroll.logic.domain.TimeSheetRowInfo;

@Service
@Transactional
public class TimeReportItemDataWriter {

	@Autowired
	private TimeReportItemRepository timeReportItemRepository;
	
	public List<TimeReportItemEntity> saveNewTimeReportItems(
			List<TimeSheetRowInfo> rows,
			TimeReportEntity timeReport,
			Map<String, JobGroupEntity> jobGroupByName,
			Map<Long, EmployeeEntity> employeeByUid) {
		return rows.stream().map(r->saveNewTimeReportItem(r, timeReport, jobGroupByName, employeeByUid)).collect(Collectors.toList());
	}

	private TimeReportItemEntity saveNewTimeReportItem(
			TimeSheetRowInfo info, 
			TimeReportEntity timeReport,
			Map<String, JobGroupEntity> jobGroupByName, 
			Map<Long, EmployeeEntity> employeeByUid) {
		JobGroupEntity jobGroup = jobGroupByName.get(info.getJobGroup().name());
		EmployeeEntity employee = employeeByUid.get(info.getEmployeeId());
		TimeReportItemEntity entity = new TimeReportItemEntity();
		entity.setDate(info.getDate());
		entity.setEmployee(employee);
		entity.setHours(info.getHoursWorked());
		entity.setJobGroup(jobGroup);
		entity.setTimeReport(timeReport);
		return timeReportItemRepository.save(entity);
	}

}

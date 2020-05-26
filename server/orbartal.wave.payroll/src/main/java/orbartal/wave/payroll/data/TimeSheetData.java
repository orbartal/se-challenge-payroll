package orbartal.wave.payroll.data;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.entity.EmployeeEntity;
import orbartal.wave.payroll.data.entity.JobGroupEntity;
import orbartal.wave.payroll.data.entity.TimeReportEntity;
import orbartal.wave.payroll.info.JobGroup;
import orbartal.wave.payroll.info.TimeSheetTableInfo;

@Service
public class TimeSheetData {

	@Autowired
	private TimeReportData timeReportData;

	@Autowired
	private JobGroupData jobGroupData;

	@Autowired
	private EmployeesData employeesData;

	@Autowired
	private TimeReportItemData timeReportItemData;
	
	@PostConstruct
    public void doLog() {
		for (JobGroup jg : JobGroup.values()) {
			jobGroupData.createIfNotExists(jg.name());
		}
    }

	public void save(TimeSheetTableInfo timeSheet) throws RuntimeException {
		TimeReportEntity timeReport = timeReportData.saveNewTimeReport(timeSheet.getId());
		Map<String, JobGroupEntity> jobGroupByName = jobGroupData.readAllJobGroups();
		Map<Long, EmployeeEntity> employeeByUid = employeesData.saveAndReadEmployeesByUids(timeSheet);
		timeReportItemData.saveNewTimeReportItems(timeSheet.getRows(), timeReport, jobGroupByName, employeeByUid);
	}

	public List<Long> readAllEmployeesUids() {
		return employeesData.readAllEmployeesUids();
	}

}

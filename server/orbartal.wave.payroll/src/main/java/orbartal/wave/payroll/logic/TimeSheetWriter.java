package orbartal.wave.payroll.logic;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.JobGroupDataWriter;
import orbartal.wave.payroll.data.JobGroupDataReader;
import orbartal.wave.payroll.data.TimeReportDataWriter;
import orbartal.wave.payroll.data.TimeReportItemDataWriter;
import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.domain.TimeReportEntity;
import orbartal.wave.payroll.logic.domain.JobGroupEnum;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@Service
public class TimeSheetWriter {

	@Autowired
	private TimeReportDataWriter timeReportDataWriter;

	@Autowired
	private JobGroupDataWriter jobGroupDataWriter;

	@Autowired
	private JobGroupDataReader jobGroupDataReader;

	@Autowired
	private EmployeesTimeWriter employeesDataWriter;

	@Autowired
	private TimeReportItemDataWriter timeReportItemDataWriter;

	@PostConstruct
    public void doLog() {
		for (JobGroupEnum jg : JobGroupEnum.values()) {
			jobGroupDataWriter.createIfNotExists(jg.name());
		}
    }

	public void save(TimeSheetTableInfo timeSheet) throws RuntimeException {
		TimeReportEntity timeReport = timeReportDataWriter.saveNewTimeReport(timeSheet.getId());
		Map<String, JobGroupEntity> jobGroupByName = jobGroupDataReader.readAllJobGroups();
		Map<Long, EmployeeEntity> employeeByUid = employeesDataWriter.saveAndReadEmployeesByUids(timeSheet);
		timeReportItemDataWriter.saveNewTimeReportItems(timeSheet.getRows(), timeReport, jobGroupByName, employeeByUid);
	}

}

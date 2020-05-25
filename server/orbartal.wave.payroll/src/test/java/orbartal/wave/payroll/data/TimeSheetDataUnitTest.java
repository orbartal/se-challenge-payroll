package orbartal.wave.payroll.data;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.data.entity.EmployeeEntity;
import orbartal.wave.payroll.data.entity.JobGroupEntity;
import orbartal.wave.payroll.data.entity.TimeReportEntity;
import orbartal.wave.payroll.info.TimeSheetRowInfo;
import orbartal.wave.payroll.info.TimeSheetTableInfo;

@RunWith(MockitoJUnitRunner.class)
public class TimeSheetDataUnitTest {

	@Mock
	private TimeReportData timeReportData;

	@Mock
	private JobGroupData jobGroupData;

	@Mock
	private EmployeesData employeesData;

	@Mock
	private TimeReportItemData timeReportItemData;

	@InjectMocks
	private TimeSheetData fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(timeReportData);
		verifyNoMoreInteractions(jobGroupData);
		verifyNoMoreInteractions(employeesData);
		verifyNoMoreInteractions(timeReportItemData);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSave() throws Exception {
		String reportId = "43";
		List<TimeSheetRowInfo> rows = mock(List.class);
		TimeReportEntity timeReport = mock(TimeReportEntity.class);
		Map<String, JobGroupEntity> jobGroupByName = mock(Map.class);
		Map<Long, EmployeeEntity> employeeByUid = mock(Map.class);
		TimeSheetTableInfo timeSheet = new TimeSheetTableInfo(reportId, rows);

		when(timeReportData.saveNewTimeReport(reportId)).thenReturn(timeReport);
		when(jobGroupData.readAllJobGroups()).thenReturn(jobGroupByName);
		when(employeesData.saveAndReadEmployeesByUids(timeSheet)).thenReturn(employeeByUid);
		when(timeReportItemData.saveNewTimeReportItems(rows, timeReport, jobGroupByName, employeeByUid)).thenReturn(null);

		fixture.save(timeSheet);

		verify(timeReportData).saveNewTimeReport(reportId);
		verify(jobGroupData).readAllJobGroups();
		verify(employeesData).saveAndReadEmployeesByUids(timeSheet);
		verify(timeReportItemData).saveNewTimeReportItems(rows, timeReport, jobGroupByName, employeeByUid);
	}

}

package orbartal.wave.payroll.logic;

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

import orbartal.wave.payroll.data.JobGroupDataWriter;
import orbartal.wave.payroll.data.JobGroupDataReader;
import orbartal.wave.payroll.data.TimeReportDataWriter;
import orbartal.wave.payroll.data.TimeReportItemDataWriter;
import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.domain.TimeReportEntity;
import orbartal.wave.payroll.logic.EmployeesTimeWriter;
import orbartal.wave.payroll.logic.TimeSheetWriter;
import orbartal.wave.payroll.logic.domain.TimeSheetRowInfo;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@RunWith(MockitoJUnitRunner.class)
public class TimeSheetWriterUnitTest {

	@Mock
	private TimeReportDataWriter timeReportData;

	@Mock
	private JobGroupDataWriter jobGroupData;

	@Mock
	private JobGroupDataReader jobGroupDataReader;

	@Mock
	private EmployeesTimeWriter employeesData;

	@Mock
	private TimeReportItemDataWriter timeReportItemData;

	@InjectMocks
	private TimeSheetWriter fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(timeReportData);
		verifyNoMoreInteractions(jobGroupData);
		verifyNoMoreInteractions(jobGroupDataReader);
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
		when(jobGroupDataReader.readAllJobGroups()).thenReturn(jobGroupByName);
		when(employeesData.saveAndReadEmployeesByUids(timeSheet)).thenReturn(employeeByUid);
		when(timeReportItemData.saveNewTimeReportItems(rows, timeReport, jobGroupByName, employeeByUid)).thenReturn(null);

		fixture.save(timeSheet);

		verify(timeReportData).saveNewTimeReport(reportId);
		verify(jobGroupDataReader).readAllJobGroups();
		verify(employeesData).saveAndReadEmployeesByUids(timeSheet);
		verify(timeReportItemData).saveNewTimeReportItems(rows, timeReport, jobGroupByName, employeeByUid);
	}

}

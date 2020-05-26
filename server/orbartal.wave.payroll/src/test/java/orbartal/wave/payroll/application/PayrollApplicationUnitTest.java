package orbartal.wave.payroll.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.application.dto.PayrollReportDto;
import orbartal.wave.payroll.application.mapper.CsvTimeSheetInfoMapper;
import orbartal.wave.payroll.data.TimeSheetData;
import orbartal.wave.payroll.info.TimeSheetTableInfo;
import orbartal.wave.payroll.logic.EmployeesLogicReader;

@RunWith(MockitoJUnitRunner.class)
public class PayrollApplicationUnitTest {

	@Mock
	private CsvTimeSheetInfoMapper csvTimeSheetRowInfoMapper;
	
	@Mock
	private TimeSheetData timeSheetData;

	@Mock
	private EmployeesLogicReader employeesLogicReader;

	@InjectMocks
	private PayrollApplication fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(csvTimeSheetRowInfoMapper);
		verifyNoMoreInteractions(timeSheetData);
		verifyNoMoreInteractions(employeesLogicReader);
	}

	@Test
	public void testUploadCsv() throws Exception {
		MultipartFile file = mock(MultipartFile.class);
		TimeSheetTableInfo table = mock(TimeSheetTableInfo.class);

		when(csvTimeSheetRowInfoMapper.readInfo(file)).thenReturn(table);

		fixture.uploadCsv(file);

		verify(csvTimeSheetRowInfoMapper).readInfo(file);
		verify(timeSheetData).save(table);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReadPayrollReport() throws Exception {
		List<Long> uids = mock(List.class);
		List<EmployeeReportDto> employeesReports = mock(List.class);
		PayrollReportDto expectd = mock(PayrollReportDto.class);

		when(employeesLogicReader.readAllEmployeesUids()).thenReturn(uids);
		when(employeesLogicReader.readEmployeesReport(uids)).thenReturn(employeesReports);
		when(employeesLogicReader.buildPayrollReport(employeesReports)).thenReturn(expectd);

		PayrollReportDto actual = fixture.readPayrollReport();
		
		assertEquals(expectd, actual);

		verify(employeesLogicReader).readAllEmployeesUids();
		verify(employeesLogicReader).readEmployeesReport(uids);
		verify(employeesLogicReader).buildPayrollReport(employeesReports);
	}

}

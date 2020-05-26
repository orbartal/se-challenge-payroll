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

import orbartal.wave.payroll.application.domain.EmployeeReportDto;
import orbartal.wave.payroll.application.domain.PayrollReportDto;
import orbartal.wave.payroll.logic.EmployeesLogicReader;

@RunWith(MockitoJUnitRunner.class)
public class PayrollAppReaderUnitTest {

	@Mock
	private EmployeesLogicReader employeesLogicReader;

	@InjectMocks
	private PayrollAppReader fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(employeesLogicReader);
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

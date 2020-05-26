package orbartal.wave.payroll.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.data.EmployeeDataReader;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesLogicReaderUnitTest {

	@Mock
	private EmployeeDataReader employeeDataReader;

	@Mock
	private EmployeeReportReader employeeReportReader;

	@InjectMocks
	private EmployeesLogicReader fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(employeeDataReader);
		verifyNoMoreInteractions(employeeReportReader);
	}

	@Test
	public void testReadPayrollReport() throws Exception {
		List<Long> uids = Arrays.asList(1L, 4L, 6L);
		Map<Long, List<EmployeeReportDto>> dtoByUid = uids.stream().collect(Collectors.toMap(i->i, i->Arrays.asList(mock(EmployeeReportDto.class), mock(EmployeeReportDto.class))));
		
		uids.forEach(i->when(employeeReportReader.readPayrollReportByUid(i)).thenReturn(dtoByUid.get(i)));

		List<EmployeeReportDto> actual = fixture.readEmployeesReport(uids);
		
		assertEquals(6, actual.size());
		uids.forEach(i->assertTrue(actual.containsAll(dtoByUid.get(i))));

		uids.forEach(i->verify(employeeReportReader).readPayrollReportByUid(i));
	}

}

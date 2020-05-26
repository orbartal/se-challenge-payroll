package orbartal.wave.payroll.logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.application.dto.PayPeriodDto;
import orbartal.wave.payroll.data.TimeReportItemDataReader;
import orbartal.wave.payroll.logic.factory.PayPeriodsFactory;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeReportReaderUnitTest {

	@Mock
	private TimeReportItemDataReader timeReportItemDataReader;

	@Mock
	private PayPeriodsFactory payPeriodsFactory;

	@InjectMocks
	private EmployeeReportReader fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(timeReportItemDataReader);
		verifyNoMoreInteractions(payPeriodsFactory);
	}

	@Test
	public void testReadPayrollReportWithNonEmpty() throws Exception {
		Long uid = 4L;
		PayPeriodDto globalPeriod = mock(PayPeriodDto.class);
		List<PayPeriodDto> periods = Arrays.asList(mock(PayPeriodDto.class), mock(PayPeriodDto.class), mock(PayPeriodDto.class));
		Map<PayPeriodDto, Optional<EmployeeReportDto>> reportPerPeriod = periods.stream().collect(Collectors.toMap(p->p, p->Optional.of(mock(EmployeeReportDto.class))));

		when(timeReportItemDataReader.readGlobalPayPeriod()).thenReturn(globalPeriod);
		when(payPeriodsFactory.buildPeriodTwoWeeksPeriods(globalPeriod)).thenReturn(periods);
		periods.forEach(p->when(timeReportItemDataReader.readEmployeeReportByEmployeeUidAndPeriod(uid, p)).thenReturn(reportPerPeriod.get(p)));

		List<EmployeeReportDto> actual = fixture.readPayrollReportByUid(uid);
		
		assertEquals(3, actual.size());
		assertEquals(reportPerPeriod.values().stream().map(p->p.get()).collect(Collectors.toSet()), actual.stream().collect(Collectors.toSet()));

		verify(timeReportItemDataReader).readGlobalPayPeriod();
		verify(payPeriodsFactory).buildPeriodTwoWeeksPeriods(globalPeriod);
		periods.forEach(p->verify(timeReportItemDataReader).readEmployeeReportByEmployeeUidAndPeriod(uid, p));
	}
	
	@Test
	public void testReadPayrollReportWithEmpty() throws Exception {
		Long uid = 4L;
		PayPeriodDto globalPeriod = mock(PayPeriodDto.class);
		List<PayPeriodDto> periods = Arrays.asList(mock(PayPeriodDto.class), mock(PayPeriodDto.class), mock(PayPeriodDto.class));
		Map<PayPeriodDto, Optional<EmployeeReportDto>> reportPerPeriod = periods.stream().collect(Collectors.toMap(p->p, p->Optional.empty()));

		when(timeReportItemDataReader.readGlobalPayPeriod()).thenReturn(globalPeriod);
		when(payPeriodsFactory.buildPeriodTwoWeeksPeriods(globalPeriod)).thenReturn(periods);
		periods.forEach(p->when(timeReportItemDataReader.readEmployeeReportByEmployeeUidAndPeriod(uid, p)).thenReturn(reportPerPeriod.get(p)));

		List<EmployeeReportDto> actual = fixture.readPayrollReportByUid(uid);
		
		assertEquals(0, actual.size());

		verify(timeReportItemDataReader).readGlobalPayPeriod();
		verify(payPeriodsFactory).buildPeriodTwoWeeksPeriods(globalPeriod);
		periods.forEach(p->verify(timeReportItemDataReader).readEmployeeReportByEmployeeUidAndPeriod(uid, p));
	}

}

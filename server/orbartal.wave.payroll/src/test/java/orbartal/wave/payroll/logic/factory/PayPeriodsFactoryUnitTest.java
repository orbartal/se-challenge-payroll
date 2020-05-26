package orbartal.wave.payroll.logic.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.application.dto.PayPeriodDto;
import orbartal.wave.payroll.data.TimeReportItemDataReader;

@RunWith(MockitoJUnitRunner.class)
public class PayPeriodsFactoryUnitTest {

	@Mock
	private TimeReportItemDataReader timeReportItemDataReader;

	@Mock
	private PayPeriodsFactory payPeriodsFactory;

	@InjectMocks
	private PayPeriodsFactory fixture;

	@Before
	public void executedBeforeEach() {
		fixture = new PayPeriodsFactory(new PayPeriodFactory(), new LocalDateFactory());
	}

	@Test
	public void testBuildPeriodTwoWeeksPeriods() throws Exception {
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusYears(1);
		PayPeriodDto globalPeriod = new PayPeriodDto();
		globalPeriod.setStart(start);
		globalPeriod.setEnd(end);

		List<PayPeriodDto> actual = fixture.buildPeriodTwoWeeksPeriods(globalPeriod);

		assertEquals(26, actual.size());
		for (int i=0; i<25; i++) {
			LocalDate d1 = actual.get(i).getStart();
			LocalDate d2 = actual.get(i).getEnd();
			LocalDate d3 = actual.get(i+1).getStart();

	        Period period1 = Period.between(d1, d2);
			assertEquals(0, period1.getYears());
			assertEquals(0, period1.getMonths());
			assertTrue(10<period1.getDays());
			assertTrue(20>period1.getDays());
			
	        Period period2 = Period.between(d2, d3);
	        assertEquals(0, period2.getYears());
			assertEquals(0, period2.getMonths());
			assertEquals(1, period2.getDays());
		}
	}

}

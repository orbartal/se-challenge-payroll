package orbartal.wave.payroll.logic.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.application.domain.EmployeeReportDto;
import orbartal.wave.payroll.application.domain.PayPeriodDto;
import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.domain.TimeReportItemEntity;
import orbartal.wave.payroll.logic.domain.JobGroupEnum;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeReportFactoryUnitTest {

	@InjectMocks
	private EmployeeReportFactory fixture;

	@Test
	public void testCalculateAmount() throws Exception {
		TimeReportItemEntity e1 = buildItem(5.0, JobGroupEnum.A);
		TimeReportItemEntity e2 = buildItem(20.0, JobGroupEnum.B);
		TimeReportItemEntity e3 = buildItem(10.0, JobGroupEnum.A);

		List<TimeReportItemEntity> items = Arrays.asList(e1, e2, e3);
		
		double actual = fixture.calculateAmount(items);
		
		assertEquals(900, actual, 0.1);
	}
	
	@Test
	public void testBuildOptionalEmployeeReportWithAmountZero() throws Exception {
		Long uid = 7L;
		PayPeriodDto period = mock(PayPeriodDto.class);
		double amount = 0;
		
		Optional<EmployeeReportDto> actual = fixture.buildOptionalEmployeeReport(uid, period, amount);
		
		assertFalse(actual.isPresent());
	}
	
	@Test
	public void testBuildOptionalEmployeeReportWithAmountNonZero() throws Exception {
		Long uid = 7L;
		PayPeriodDto period = mock(PayPeriodDto.class);
		double amount = 18.4;
		
		Optional<EmployeeReportDto> actual = fixture.buildOptionalEmployeeReport(uid, period, amount);
		
		assertTrue(actual.isPresent());
		EmployeeReportDto dto = actual.get();
		assertEquals(amount+"", dto.getAmountPaid());
		assertEquals(uid, dto.getEmployeeId());
		assertEquals(period, dto.getPayPeriod());
	}

	private TimeReportItemEntity buildItem(double h, JobGroupEnum g) {
		TimeReportItemEntity e = new TimeReportItemEntity();
		e.setHours(h);
		JobGroupEntity jobGroupEntity = new JobGroupEntity();
		jobGroupEntity.setName(g.name());
		e.setJobGroup(jobGroupEntity);
		return e;
	}
	
	public double calculateAmount(List<TimeReportItemEntity> items) {
		List<JobGroupEnum> groups = Arrays.asList(JobGroupEnum.values());
		Map<String, Double> payByGroup = groups.stream().collect(Collectors.toMap(j->j.name(), j->j.getHourlyPay()));
		return items.stream().mapToDouble(i->i.getHours()*payByGroup.get(i.getJobGroup().getName())).sum();
	}

}

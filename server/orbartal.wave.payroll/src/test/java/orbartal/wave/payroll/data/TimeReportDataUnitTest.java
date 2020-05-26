package orbartal.wave.payroll.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.data.domain.TimeReportEntity;
import orbartal.wave.payroll.data.repository.TimeReportRepository;

@RunWith(MockitoJUnitRunner.class)
public class TimeReportDataUnitTest {

	@Mock
	private TimeReportRepository timeReportRepository;

	@InjectMocks
	private TimeReportDataWriter fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(timeReportRepository);
	}

	@Test
	public void saveNewTimeReport() throws Exception {
		String reportId = "47";
		ArgumentCaptor<TimeReportEntity> captor = ArgumentCaptor.forClass(TimeReportEntity.class);

		fixture.saveNewTimeReport(reportId);

		verify(timeReportRepository).save(captor.capture());
		TimeReportEntity actual = captor.getValue();
		assertNotNull(actual);
		assertEquals(reportId, actual.getUid());
	}

}

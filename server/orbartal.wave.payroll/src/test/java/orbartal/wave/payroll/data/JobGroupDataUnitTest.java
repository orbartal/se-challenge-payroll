package orbartal.wave.payroll.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.repository.JobGroupRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobGroupDataUnitTest {

	@Mock
	private JobGroupRepository jobGroupRepository;

	@InjectMocks
	private JobGroupDataWriter fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(jobGroupRepository);
	}

	@Test
	public void testSaveNonExistsJobGroup() throws Exception {
		String name = "C";
		ArgumentCaptor<JobGroupEntity> captor = ArgumentCaptor.forClass(JobGroupEntity.class);

		when(jobGroupRepository.existsByName(name)).thenReturn(false);

		fixture.createIfNotExists(name);

		verify(jobGroupRepository).existsByName(name);
		verify(jobGroupRepository).save(captor.capture());
		JobGroupEntity actual = captor.getValue();
		assertNotNull(actual);
		assertEquals(name, actual.getName());
	}

	@Test
	public void testSaveAlreadyExistsJobGroup() throws Exception {
		String name = "C";
		when(jobGroupRepository.existsByName(name)).thenReturn(true);

		fixture.createIfNotExists(name);

		verify(jobGroupRepository).existsByName(name);
	}

}

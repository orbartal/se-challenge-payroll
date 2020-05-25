package orbartal.wave.payroll.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.data.entity.JobGroupEntity;
import orbartal.wave.payroll.data.repository.JobGroupRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobGroupDataUnitTest {

	@Mock
	private JobGroupRepository jobGroupRepository;

	@InjectMocks
	private JobGroupData fixture;

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
	
	@Test
	public void testReadAllJobGroups() throws Exception {
		List<JobGroupEntity> list = new ArrayList<>();
		list.add(toEntity(1L, "A"));
		list.add(toEntity(2L, "B"));

		when(jobGroupRepository.findAll()).thenReturn(list);

		Map<String, JobGroupEntity> actual = fixture.readAllJobGroups();

		verify(jobGroupRepository).findAll();

		assertNotNull(actual);
		assertEquals(2, actual.size());
		assertSame(list.get(0), actual.get("A"));
		assertSame(list.get(1), actual.get("B"));
	}
	
	private JobGroupEntity toEntity(long id, String name) {
		JobGroupEntity e = new JobGroupEntity();
		e.setId(id);
		e.setName(name);
		return e;
	}

}

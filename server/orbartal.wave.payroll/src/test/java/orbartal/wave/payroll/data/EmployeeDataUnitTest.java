package orbartal.wave.payroll.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
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

import orbartal.wave.payroll.data.entity.EmployeeEntity;
import orbartal.wave.payroll.data.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDataUnitTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeData fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(employeeRepository);
	}

	@Test
	public void testSaveNonExistsEmployee() throws Exception {
		Long uid = 1L;
		ArgumentCaptor<EmployeeEntity> captor = ArgumentCaptor.forClass(EmployeeEntity.class);

		when(employeeRepository.findByUid(uid)).thenReturn(null);

		fixture.createIfNotExists(uid);

		verify(employeeRepository).findByUid(uid);
		verify(employeeRepository).save(captor.capture());
		EmployeeEntity actual = captor.getValue();
		assertNotNull(actual);
		assertEquals(uid, actual.getUid());
	}

	@Test
	public void testSaveAlreadyExistsEmployee() throws Exception {
		Long uid = 1L;
		when(employeeRepository.findByUid(uid)).thenReturn(mock(EmployeeEntity.class));

		fixture.createIfNotExists(uid);

		verify(employeeRepository).findByUid(uid);
	}
	

}

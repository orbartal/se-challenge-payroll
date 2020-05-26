package orbartal.wave.payroll.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import orbartal.wave.payroll.data.domain.EmployeeEntity;
import orbartal.wave.payroll.logic.EmployeesTimeWriter;
import orbartal.wave.payroll.logic.domain.TimeSheetRowInfo;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesDataUnitTest {

	@Mock
	private EmployeeDataWriter employeeData;

	@InjectMocks
	private EmployeesTimeWriter fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(employeeData);
	}

	@Test
	public void testSaveAndReadEmployeesByUids() throws Exception {
		Random random = new Random();
		List<TimeSheetRowInfo> rows = IntStream.range(0, 3).boxed().map(i->mock(TimeSheetRowInfo.class)).collect(Collectors.toList());
		Map<TimeSheetRowInfo, Long> employeeUidByRow = rows.stream().collect(Collectors.toMap(r->r, r->(long)random.nextInt(1000)));
		Set<Long> uids = employeeUidByRow.values().stream().collect(Collectors.toSet());
		Map<Long, EmployeeEntity> employeeByUid = uids.stream().collect(Collectors.toMap(i->i, i->mock(EmployeeEntity.class)));
		TimeSheetTableInfo timeSheet = new TimeSheetTableInfo("", rows);

		rows.forEach(r->when(r.getEmployeeId()).thenReturn(employeeUidByRow.get(r)));
		uids.forEach(i->when(employeeData.createIfNotExists(i)).thenReturn(employeeByUid.get(i)));
		uids.forEach(i->when(employeeByUid.get(i).getUid()).thenReturn(i));

		Map<Long, EmployeeEntity> actual = fixture.saveAndReadEmployeesByUids(timeSheet);

		uids.forEach(i->verify(employeeData).createIfNotExists(i));

		assertNotNull(actual);
		assertEquals(rows.size(), actual.size());
		for (Long uid : uids) {
			assertEquals(employeeByUid.get(uid), actual.get(uid));
		}
	}

}

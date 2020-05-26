package orbartal.wave.payroll.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.mapper.CsvTimeSheetInfoMapper;
import orbartal.wave.payroll.logic.TimeSheetWriter;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@RunWith(MockitoJUnitRunner.class)
public class PayrollAppWriterUnitTest {

	@Mock
	private CsvTimeSheetInfoMapper csvTimeSheetRowInfoMapper;
	
	@Mock
	private TimeSheetWriter timeSheetData;

	@InjectMocks
	private PayrollAppWriter fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(csvTimeSheetRowInfoMapper);
		verifyNoMoreInteractions(timeSheetData);
	}

	@Test
	public void testUploadCsv() throws Exception {
		MultipartFile file = mock(MultipartFile.class);
		TimeSheetTableInfo table = mock(TimeSheetTableInfo.class);

		when(csvTimeSheetRowInfoMapper.readInfo(file)).thenReturn(table);

		fixture.uploadCsv(file);

		verify(csvTimeSheetRowInfoMapper).readInfo(file);
		verify(timeSheetData).save(table);
	}

}

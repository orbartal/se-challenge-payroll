package orbartal.wave.payroll.application.mapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.logic.domain.TimeSheetRowInfo;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;
import orbartal.wave.payroll.utils.CsvExampleReader;

public class CsvTimeSheetInfoMapperIntTest {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###.#");

	static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

	private MultipartFileStreamMapper multipartFileStreamMapper;

	private TimeSheetRowCsvDtoMapper timeSheetRowCsvDtoMapper;

	private TimeSheetRowDtoInfoMapper timeSheetRowDtoInfoMapper;

	private CsvTimeSheetInfoMapper fixture;

	@Before
	public void executedBeforeEach() {
		multipartFileStreamMapper = new MultipartFileStreamMapper();
		timeSheetRowCsvDtoMapper = new TimeSheetRowCsvDtoMapper();
		timeSheetRowDtoInfoMapper = new TimeSheetRowDtoInfoMapper();
		fixture = new CsvTimeSheetInfoMapper(multipartFileStreamMapper, timeSheetRowCsvDtoMapper,
				timeSheetRowDtoInfoMapper);
	}

	@Test
	public void testUploadCsv() throws Exception {
		CsvExampleReader csvExampleReader = new CsvExampleReader();
		String fileName = csvExampleReader.getCsvExampleInputFileName();
		File csv = csvExampleReader.getCsvExampleInputFile();
		String content = new String(Files.readAllBytes(Paths.get(csv.getAbsolutePath())));
		InputStream inputStream = new ByteArrayInputStream(content.getBytes());
		MultipartFile file = mock(MultipartFile.class);

		when(file.getInputStream()).thenReturn(inputStream);
		when(file.getOriginalFilename()).thenReturn(fileName);

		TimeSheetTableInfo actual = fixture.readInfo(file);

		Assertions.assertNotNull(actual);
		validateRows(content, actual.getRows());
		Assertions.assertEquals(42+"", actual.getId());
	}

	private void validateRows(String content, List<TimeSheetRowInfo> actual) {
		String lines[] = content.split("\\r?\\n");
		Assertions.assertEquals(lines.length - 1, actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEqualsText(lines[i + 1], actual.get(i));
		}
	}

	private void assertEqualsText(String expected, TimeSheetRowInfo row) {
		String date = DATE_FORMAT.format(row.getDate());
		String h = DECIMAL_FORMAT.format(row.getHoursWorked());
		String id = row.getEmployeeId() + "";
		String jg = row.getJobGroup().name();
		String actual = date + "," + h + "," + id + "," + jg;
		Assertions.assertEquals(expected, actual);
	}

}

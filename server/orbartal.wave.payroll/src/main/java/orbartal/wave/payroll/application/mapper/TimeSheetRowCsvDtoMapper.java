package orbartal.wave.payroll.application.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import orbartal.wave.payroll.application.domain.TimeSheetRowDto;

@Service
public class TimeSheetRowCsvDtoMapper {
	
	public List<TimeSheetRowDto> readTimeSheetRowDtosFromStream(InputStream inputStream) {
		try {
			List<String[]> rows = readTimeSheetRowsFromStream(inputStream);
	        return rows.stream().map(r->toDto(r)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<String[]> readTimeSheetRowsFromStream(InputStream inputStream) throws IOException, CsvException {
		Reader reader = new InputStreamReader(inputStream);
        @SuppressWarnings("resource")
		CSVReader csvReader = new CSVReader(reader);
        csvReader.skip(1);
        return csvReader.readAll();
	}

	private TimeSheetRowDto toDto(String[] input) {
		TimeSheetRowDto dto = new TimeSheetRowDto();
		dto.setDate(input[0]);
		dto.setHoursWorked(input[1]);
		dto.setEmployeeId(input[2]);
		dto.setJobGroup(input[3]);
		return dto;
	}

}

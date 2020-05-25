package orbartal.wave.payroll.application.mapper;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.dto.TimeSheetRowDto;
import orbartal.wave.payroll.info.TimeSheetRowInfo;
import orbartal.wave.payroll.info.TimeSheetTableInfo;

@Service
public class CsvTimeSheetInfoMapper {

	private MultipartFileStreamMapper multipartFileStreamMapper;

	private TimeSheetRowCsvDtoMapper timeSheetRowCsvDtoMapper;

	private TimeSheetRowDtoInfoMapper timeSheetRowDtoInfoMapper;

	@Autowired
	public CsvTimeSheetInfoMapper(
			MultipartFileStreamMapper multipartFileStreamMapper,
			TimeSheetRowCsvDtoMapper timeSheetRowCsvDtoMapper, 
			TimeSheetRowDtoInfoMapper timeSheetRowDtoInfoMapper) {
		this.multipartFileStreamMapper = multipartFileStreamMapper;
		this.timeSheetRowCsvDtoMapper = timeSheetRowCsvDtoMapper;
		this.timeSheetRowDtoInfoMapper = timeSheetRowDtoInfoMapper;
	}

	public TimeSheetTableInfo readInfo(MultipartFile file) {
		List<TimeSheetRowInfo> rows = readRows(file);
		String id = readId(file);
		return new TimeSheetTableInfo(id, rows);
	}

	private String readId(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String fileName2 = fileName.split("\\.")[0];
		return fileName2.split("-")[2];
	}

	private List<TimeSheetRowInfo> readRows(MultipartFile file) {
		InputStream stream = multipartFileStreamMapper.readInputStream(file);
		List<TimeSheetRowDto> dtos = timeSheetRowCsvDtoMapper.readTimeSheetRowDtosFromStream(stream);
		return timeSheetRowDtoInfoMapper.readInfosFromDtos(dtos);
	}

}

package orbartal.wave.payroll.application.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import orbartal.wave.payroll.application.domain.TimeSheetRowDto;
import orbartal.wave.payroll.logic.domain.JobGroupEnum;
import orbartal.wave.payroll.logic.domain.TimeSheetRowInfo;

@Service
public class TimeSheetRowDtoInfoMapper {
	
	static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

	public List<TimeSheetRowInfo> readInfosFromDtos(List<TimeSheetRowDto> dtos) {
		return dtos.stream().map(d -> toInfo(d)).collect(Collectors.toList());
	}

	private TimeSheetRowInfo toInfo(TimeSheetRowDto dto) {
		try {
			LocalDate date = LocalDate.parse(dto.getDate(), DATE_FORMAT);
			double hoursWorked = Double.parseDouble(dto.getHoursWorked());
			long employeeId = Long.parseLong(dto.getEmployeeId());
			JobGroupEnum jobGroup = JobGroupEnum.valueOf(dto.getJobGroup());
			return new TimeSheetRowInfo(date, hoursWorked, employeeId, jobGroup);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

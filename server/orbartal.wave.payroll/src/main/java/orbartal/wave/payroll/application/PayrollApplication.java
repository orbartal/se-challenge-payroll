package orbartal.wave.payroll.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.dto.EmployeeReportDto;
import orbartal.wave.payroll.application.dto.PayrollReportDto;
import orbartal.wave.payroll.application.mapper.CsvTimeSheetInfoMapper;
import orbartal.wave.payroll.data.TimeSheetData;
import orbartal.wave.payroll.info.TimeSheetTableInfo;
import orbartal.wave.payroll.logic.EmployeesLogicReader;

@Service
public class PayrollApplication {

	@Autowired
	private CsvTimeSheetInfoMapper csvTimeSheetRowInfoMapper;

	@Autowired
	private TimeSheetData timeSheetData;

	@Autowired
	private EmployeesLogicReader employeesLogicReader;

	public void uploadCsv(MultipartFile file) throws RuntimeException {
		TimeSheetTableInfo timeSheet = csvTimeSheetRowInfoMapper.readInfo(file);
		timeSheetData.save(timeSheet);
	}

	public PayrollReportDto readPayrollReport() {
		List<Long> uids = employeesLogicReader.readAllEmployeesUids();
		List<EmployeeReportDto> employeesReports = employeesLogicReader.readEmployeesReport(uids);
		return employeesLogicReader.buildPayrollReport(employeesReports);
	}

}

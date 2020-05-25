package orbartal.wave.payroll.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.mapper.CsvTimeSheetInfoMapper;
import orbartal.wave.payroll.data.TimeSheetData;
import orbartal.wave.payroll.info.TimeSheetTableInfo;

@Service
public class PayrollApplication {

	@Autowired
	private CsvTimeSheetInfoMapper csvTimeSheetRowInfoMapper;

	@Autowired
	private TimeSheetData timeSheetData;

	public void uploadCsv(MultipartFile file) throws RuntimeException {
		TimeSheetTableInfo timeSheet = csvTimeSheetRowInfoMapper.readInfo(file);
		timeSheetData.save(timeSheet);
	}

}

package orbartal.wave.payroll.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.mapper.CsvTimeSheetInfoMapper;
import orbartal.wave.payroll.logic.TimeSheetWriter;
import orbartal.wave.payroll.logic.domain.TimeSheetTableInfo;

@Service
@Transactional
public class PayrollAppWriter {

	@Autowired
	private CsvTimeSheetInfoMapper csvTimeSheetRowInfoMapper;

	@Autowired
	private TimeSheetWriter timeSheetData;

	public void uploadCsv(MultipartFile file) throws RuntimeException {
		TimeSheetTableInfo timeSheet = csvTimeSheetRowInfoMapper.readInfo(file);
		timeSheetData.save(timeSheet);
	}

}

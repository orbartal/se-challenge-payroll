package orbartal.wave.payroll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.PayrollApplication;
import orbartal.wave.payroll.application.dto.PayrollReportDto;

@RestController
@RequestMapping("/v1/payroll")
public class PayrollController {

	@Autowired
	private PayrollApplication payrollApplication;

	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws RuntimeException {
		String originalFilename = file.getOriginalFilename();
		payrollApplication.uploadCsv(file);
		return new ResponseEntity<String>(originalFilename, HttpStatus.OK);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ResponseEntity<PayrollReportDto> getReport() {
		PayrollReportDto dto = payrollApplication.readPayrollReport();
		return new ResponseEntity<PayrollReportDto>(dto, HttpStatus.OK);
	}

}

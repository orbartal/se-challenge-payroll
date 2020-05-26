package orbartal.wave.payroll.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.application.PayrollAppWriter;
import orbartal.wave.payroll.application.PayrollAppReader;
import orbartal.wave.payroll.application.domain.PayrollReportDto;
import orbartal.wave.payroll.application.domain.PayrollReportResponse;

@RestController
@RequestMapping("/v1/payroll")
public class PayrollController {

	@Autowired
	private PayrollAppReader payrollAppReader;

	@Autowired
	private PayrollAppWriter payrollAppWriter;

	@RequestMapping(value = "/csv", method = RequestMethod.POST)
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws RuntimeException {
		String originalFilename = file.getOriginalFilename();
		payrollAppWriter.uploadCsv(file);
		return new ResponseEntity<String>(originalFilename, HttpStatus.OK);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ResponseEntity<PayrollReportResponse> getReport() {
		PayrollReportDto dto = payrollAppReader.readPayrollReport();
		PayrollReportResponse response = new PayrollReportResponse();
		response.setPayrollReport(dto);
		return new ResponseEntity<PayrollReportResponse>(response, HttpStatus.OK);
	}

}

package orbartal.wave.payroll.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1/payroll")
public class PayrollController {	

	@RequestMapping(value="/csv", method=RequestMethod.POST)
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws RuntimeException {
		String originalFilename = file.getOriginalFilename();
		return new ResponseEntity<String>(originalFilename, HttpStatus.OK);
	}

	@RequestMapping(value="/report", method=RequestMethod.GET)
	public ResponseEntity<String> getReport() {
		return new ResponseEntity<String>("report", HttpStatus.OK);
	}

}

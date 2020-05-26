package orbartal.wave.payroll.api.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import orbartal.wave.payroll.api.rest.PayrollController;
import orbartal.wave.payroll.application.PayrollAppReader;
import orbartal.wave.payroll.application.PayrollAppWriter;
import orbartal.wave.payroll.application.domain.PayrollReportDto;
import orbartal.wave.payroll.application.domain.PayrollReportResponse;

@RunWith(MockitoJUnitRunner.class)
public class PayrollControllerUnitTest {

	@Mock
	private PayrollAppReader payrollAppReader;
	
	@Mock
	private PayrollAppWriter payrollAppWriter;

	@InjectMocks
	private PayrollController fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(payrollAppReader);
		verifyNoMoreInteractions(payrollAppWriter);
	}

	@Test
	public void testUploadCsv() throws Exception {
		String originalFilename = "input.csv";
		MultipartFile file = mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn(originalFilename);

		ResponseEntity<String> actual = fixture.uploadCsv(file);

		assertNotNull(actual);
		assertEquals(200, actual.getStatusCode().value());
		assertEquals(originalFilename, actual.getBody());

		verify(payrollAppWriter).uploadCsv(file);
	}

	@Test
	public void testGetReport() throws Exception {
		PayrollReportDto dto = mock(PayrollReportDto.class);

		when(payrollAppReader.readPayrollReport()).thenReturn(dto);

		ResponseEntity<PayrollReportResponse> actual = fixture.getReport();

		assertNotNull(actual);
		assertEquals(200, actual.getStatusCode().value());
		assertEquals(dto, actual.getBody().getPayrollReport());

		verify(payrollAppReader).readPayrollReport();
	}

}

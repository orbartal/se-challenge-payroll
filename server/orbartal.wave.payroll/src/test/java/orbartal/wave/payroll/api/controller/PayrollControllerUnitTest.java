package orbartal.wave.payroll.api.controller;

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

import orbartal.wave.payroll.application.PayrollApplication;
import orbartal.wave.payroll.application.dto.PayrollReportDto;

@RunWith(MockitoJUnitRunner.class)
public class PayrollControllerUnitTest {

	@Mock
	private PayrollApplication payrollApplication;

	@InjectMocks
	private PayrollController fixture;

	@After
	public void runAfterTestMethod() {
		verifyNoMoreInteractions(payrollApplication);
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

		verify(payrollApplication).uploadCsv(file);
	}

	@Test
	public void testGetReport() throws Exception {
		PayrollReportDto dto = mock(PayrollReportDto.class);

		when(payrollApplication.readPayrollReport()).thenReturn(dto);

		ResponseEntity<PayrollReportDto> actual = fixture.getReport();

		assertNotNull(actual);
		assertEquals(200, actual.getStatusCode().value());
		assertEquals(dto, actual.getBody());

		verify(payrollApplication).readPayrollReport();
	}

}

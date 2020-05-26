package orbartal.wave.payroll.e2e;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import orbartal.wave.payroll.utils.CsvExampleReader;

public class PayrollControllerTester {

	private static final String API_PATH = "http://localhost:8081/v1/payroll";
	private static final String API_PATH_CSV = API_PATH+"/"+"csv";
	private static final String API_PATH_REPORT = API_PATH+"/"+"report";
	private static final String CSV_FILE_NAME = "time-report-42.csv";
	
	private CsvExampleReader filesReader = new CsvExampleReader();
	
	//Note: We must restart the server before each run of this test because we cannot delete data that was inserted to the server
	@Test
	public void testAll() throws IOException {
		testReadExampleCsv();
		testReadExampleJson();
		testGetReportWithEmptyData();
		testUploadCsv();
		testGetReportWithNonEmptyData();
	}


	public void testReadExampleCsv() throws IOException {
		File csv = filesReader.getCsvExampleInputFile();
	    String content = filesReader.readFileContent(csv);
		Assertions.assertNotNull(content);
	}
	
	public void testReadExampleJson() throws IOException {
		File json = filesReader.getJsonExampleOutputFile();
	    String content = filesReader.readFileContent(json);
		Assertions.assertNotNull(content);
	}

	public void testUploadCsv() throws IOException {
		File csv = filesReader.getCsvExampleInputFile();

		Response actual = RestAssured.given()
				.param("timestamp", new Date().getTime())
				.multiPart("file", csv)
				.accept(ContentType.JSON)
				.when()
				.post(API_PATH_CSV)
				.andReturn();

		Assertions.assertNotNull(actual);
		Assertions.assertEquals(HttpStatus.OK.value(), actual.statusCode());
		Assertions.assertEquals(CSV_FILE_NAME, actual.getBody().asString());
	}

	public void testGetReportWithEmptyData() {
		Object actual = RestAssured.given().accept(ContentType.JSON).when().get(API_PATH_REPORT).andReturn();

		Assertions.assertNotNull(actual);
		Assertions.assertEquals(RestAssuredResponseImpl.class, actual.getClass());
		RestAssuredResponseImpl response = (RestAssuredResponseImpl)actual;
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	public void testGetReportWithNonEmptyData() throws IOException {
		Object actual = RestAssured.given().accept(ContentType.JSON).when().get(API_PATH_REPORT).andReturn();

		Assertions.assertNotNull(actual);
		Assertions.assertEquals(RestAssuredResponseImpl.class, actual.getClass());
		RestAssuredResponseImpl response = (RestAssuredResponseImpl)actual;
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		
		File outputPath = filesReader.getJsonExampleOutputFile();
		String expectedJsonOutput = filesReader.readFileContent(outputPath);
		String actualJsonOutput = response.getBody().asString();
		Assertions.assertEquals(expectedJsonOutput, actualJsonOutput);
	}

}

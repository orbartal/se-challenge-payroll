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
import orbartal.wave.payroll.utils.TestFilesReader;

public class PayrollControllerTester {

	private static final String API_PATH = "http://localhost:8081/v1/payroll";
	private static final String API_PATH_CSV = API_PATH+"/"+"csv";
	private static final String API_PATH_REPORT = API_PATH+"/"+"report";
	//private static final String CSV_FILE_41 = "time-report-41.csv";
	//private static final String CSV_FILE_42 = "time-report-42.csv";
	
	private TestFilesReader filesReader = new TestFilesReader();
	
	//Note: We must restart the server before each run of this test because we cannot delete data that was inserted to the server
	@Test
	public void testAll() throws IOException {
		testReadExampleCsv();
		testReadExampleJson();
		testUploadCsv41();
		testGetReportWithEmptyData();
		testUploadCsv42();
		testUploadCsv42Again();
		testGetReportWithNonEmptyData();
	}


	public void testReadExampleCsv() throws IOException {
		File csv = filesReader.readCsvInputFile42();
	    String content = filesReader.readFileContent(csv);
		Assertions.assertNotNull(content);
	}
	
	public void testReadExampleJson() throws IOException {
		File json = filesReader.getJsonExampleOutputFile();
	    String content = filesReader.readFileContent(json);
		Assertions.assertNotNull(content);
	}
	
	public void testUploadCsv41() throws IOException {
		File csv = filesReader.readCsvInputFile41();
		uploadCsvAndAssert(csv);
	}

	public void testUploadCsv42() throws IOException {
		File csv = filesReader.readCsvInputFile42();
		uploadCsvAndAssert(csv);
	}
	
	private void testUploadCsv42Again() throws IOException {
		File csv = filesReader.readCsvInputFile42();
		Response actual = uploadCsv(csv);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.statusCode());
	}


	private void uploadCsvAndAssert(File csv) throws IOException {
		Response actual = uploadCsv(csv);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(HttpStatus.OK.value(), actual.statusCode());
		Assertions.assertEquals(csv.getName(), actual.getBody().asString());
	}

	private Response uploadCsv(File csv) {
		Response actual = RestAssured.given()
				.param("timestamp", new Date().getTime())
				.multiPart("file", csv)
				.accept(ContentType.JSON)
				.when()
				.post(API_PATH_CSV)
				.andReturn();
		return actual;
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

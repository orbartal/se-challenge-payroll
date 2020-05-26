package orbartal.wave.payroll.e2e;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;

public class PayrollControllerTester {

	private static final String API_PATH = "http://localhost:8081/v1/payroll";
	private static final String API_PATH_CSV = API_PATH+"/"+"csv";
	private static final String API_PATH_REPORT = API_PATH+"/"+"report";
	private static final String CSV_FILE_NAME = "time-report-42.csv";

	@Test
	public void testExampleCsv() throws IOException {
		File csv = getCsvExampleInputFile();
	    String content = new String(Files.readAllBytes(Paths.get(csv.getAbsolutePath())));
		Assertions.assertNotNull(content);
	}

	@Test
	public void testUploadCsv() throws IOException {
		File csv = getCsvExampleInputFile();

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

	@Test
	public void testGetReportWithEmptyData() {
		Object actual = RestAssured.given().accept(ContentType.JSON).when().get(API_PATH_REPORT).andReturn();

		Assertions.assertNotNull(actual);
		Assertions.assertEquals(RestAssuredResponseImpl.class, actual.getClass());
		RestAssuredResponseImpl response = (RestAssuredResponseImpl)actual;
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	
	@Test
	public void testGetReportWithNonEmptyData() throws IOException {
		testUploadCsv();
		Object actual = RestAssured.given().accept(ContentType.JSON).when().get(API_PATH_REPORT).andReturn();

		Assertions.assertNotNull(actual);
		Assertions.assertEquals(RestAssuredResponseImpl.class, actual.getClass());
		RestAssuredResponseImpl response = (RestAssuredResponseImpl)actual;
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		//Assertions.assertEquals("report", response.getBody().asString());
	}

	private static File getCsvExampleInputFile() throws IOException {
		File f = new File (new File(".").getCanonicalPath());
		for (int i=0; i<2; i++) {
			f = new File (f.getParent());
		}
		String pathFull = f.getAbsolutePath() + "/" + CSV_FILE_NAME;
		return new File(pathFull);
	}

}

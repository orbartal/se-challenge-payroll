package orbartal.wave.payroll.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFilesReader {

	private static final String CSV_FILE_41 = "time-report-41.csv";
	private static final String CSV_FILE_42 = "time-report-42.csv";

	public String getCsvExampleInputFileName() throws IOException {
		return CSV_FILE_42;
	}

	public File readCsvInputFile41() throws IOException {
		return readCsvInputFile(CSV_FILE_41);
	}

	public File readCsvInputFile42() throws IOException {
		return readCsvInputFile(CSV_FILE_42);
	}

	private File readCsvInputFile(String fileName) throws IOException {
		File f = new File (new File(".").getCanonicalPath());
		for (int i=0; i<2; i++) {
			f = new File (f.getParent());
		}
		String pathFull = f.getAbsolutePath() + "/" + fileName;
		return new File(pathFull);
	}

	public File getJsonExampleOutputFile() throws IOException {
		File f = new File (new File(".").getCanonicalPath());
		String relativePath = "\\src\\test\\resources\\employees-payroll-report-42.json";
		String pathFull = f.getAbsolutePath() + relativePath;
		return new File(pathFull);
	}

	public String readFileContent(File csv) throws IOException {
		return new String(Files.readAllBytes(Paths.get(csv.getAbsolutePath())));
	}

}

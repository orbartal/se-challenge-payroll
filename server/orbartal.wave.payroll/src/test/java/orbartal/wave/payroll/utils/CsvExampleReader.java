package orbartal.wave.payroll.utils;

import java.io.File;
import java.io.IOException;

public class CsvExampleReader {

	private static final String CSV_FILE_NAME = "time-report-42.csv";

	public String getCsvExampleInputFileName() throws IOException {
		return CSV_FILE_NAME;
	}

	public File getCsvExampleInputFile() throws IOException {
		File f = new File (new File(".").getCanonicalPath());
		for (int i=0; i<2; i++) {
			f = new File (f.getParent());
		}
		String pathFull = f.getAbsolutePath() + "/" + CSV_FILE_NAME;
		return new File(pathFull);
	}


}

package orbartal.wave.payroll.info;

import java.util.List;

public class TimeSheetTableInfo {

	private final String id;
	private final List<TimeSheetRowInfo> rows;

	public TimeSheetTableInfo(String id, List<TimeSheetRowInfo> rows) {
		super();
		this.id = id;
		this.rows = rows;
	}

	public String getId() {
		return id;
	}

	public List<TimeSheetRowInfo> getRows() {
		return rows;
	}

}
 
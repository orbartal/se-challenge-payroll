package orbartal.wave.payroll.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.entity.TimeReportEntity;
import orbartal.wave.payroll.data.repository.TimeReportRepository;

@Service
public class TimeReportData {

	@Autowired
	private TimeReportRepository timeReportRepository;

	public TimeReportEntity saveNewTimeReport(String uid) {
		TimeReportEntity entity = new TimeReportEntity();
		entity.setUid(uid);
		return timeReportRepository.save(entity);
	}

}

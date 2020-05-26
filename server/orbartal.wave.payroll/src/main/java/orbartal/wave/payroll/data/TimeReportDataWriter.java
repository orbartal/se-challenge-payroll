package orbartal.wave.payroll.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.domain.TimeReportEntity;
import orbartal.wave.payroll.data.repository.TimeReportRepository;

@Service
@Transactional
public class TimeReportDataWriter {

	@Autowired
	private TimeReportRepository timeReportRepository;

	public TimeReportEntity saveNewTimeReport(String uid) {
		TimeReportEntity entity = new TimeReportEntity();
		entity.setUid(uid);
		return timeReportRepository.save(entity);
	}

}

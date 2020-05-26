package orbartal.wave.payroll.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.repository.JobGroupRepository;

@Service
@Transactional(readOnly = true)
public class JobGroupDataReader {

	@Autowired
	private JobGroupRepository jobGroupRepository;

	public Map<String, JobGroupEntity> readAllJobGroups() {
		Iterable<JobGroupEntity> it = jobGroupRepository.findAll();
		List<JobGroupEntity> list = StreamSupport.stream(it.spliterator(), false).collect(Collectors.toList());
		return list.stream().collect(Collectors.toMap(j->j.getName(), j->j));
	}

}

package orbartal.wave.payroll.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orbartal.wave.payroll.data.entity.JobGroupEntity;
import orbartal.wave.payroll.data.repository.JobGroupRepository;

@Service
public class JobGroupData {

	@Autowired
	private JobGroupRepository jobGroupRepository;

	public void createIfNotExists(String name) {
		if (!jobGroupRepository.existsByName(name)) {
			JobGroupEntity entity = new JobGroupEntity();
			entity.setName(name);
			jobGroupRepository.save(entity);
		}
	}
	
	public Map<String, JobGroupEntity> readAllJobGroups() {
		Iterable<JobGroupEntity> it = jobGroupRepository.findAll();
		List<JobGroupEntity> list = StreamSupport.stream(it.spliterator(), false).collect(Collectors.toList());
		return list.stream().collect(Collectors.toMap(j->j.getName(), j->j));
	}

}

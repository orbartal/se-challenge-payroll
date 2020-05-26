package orbartal.wave.payroll.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.wave.payroll.data.domain.JobGroupEntity;
import orbartal.wave.payroll.data.repository.JobGroupRepository;

@Service
@Transactional
public class JobGroupDataWriter {

	@Autowired
	private JobGroupRepository jobGroupRepository;

	public void createIfNotExists(String name) {
		if (!jobGroupRepository.existsByName(name)) {
			JobGroupEntity entity = new JobGroupEntity();
			entity.setName(name);
			jobGroupRepository.save(entity);
		}
	}

}

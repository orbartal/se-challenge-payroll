package orbartal.wave.payroll.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import orbartal.wave.payroll.data.entity.JobGroupEntity;

@Repository
public interface JobGroupRepository extends PagingAndSortingRepository<JobGroupEntity, Long>{
	
	@Query("SELECT CASE WHEN COUNT(j)>0 THEN true ELSE false END FROM JobGroupEntity j WHERE j.name = :name")
	boolean existsByName(@Param("name") String name);
}


package orbartal.wave.payroll.data.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import orbartal.wave.payroll.data.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {

	@Query(value = "SELECT DISTINCT e FROM EmployeeEntity e WHERE e.uid in :uids ORDER BY e.uid")
	List<EmployeeEntity> readByUids(@Param("uids") Set<Long> uids);

	@Query(value = "SELECT e FROM EmployeeEntity e  WHERE e.uid = :uid")
	EmployeeEntity findByUid(@Param("uid") Long uid);

	@Query(value = "SELECT DISTINCT e.uid FROM EmployeeEntity e ORDER BY e.uid")
	List<Long> readAllEmployeesUids();
}

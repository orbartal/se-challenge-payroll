
package orbartal.wave.payroll.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import orbartal.wave.payroll.data.entity.TimeReportEntity;

@Repository
public interface TimeReportRepository extends PagingAndSortingRepository<TimeReportEntity, Long>{
}


package orbartal.wave.payroll.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import orbartal.wave.payroll.data.entity.TimeReportItemEntity;

@Repository
public interface TimeReportItemRepository extends PagingAndSortingRepository<TimeReportItemEntity, Long>{
}

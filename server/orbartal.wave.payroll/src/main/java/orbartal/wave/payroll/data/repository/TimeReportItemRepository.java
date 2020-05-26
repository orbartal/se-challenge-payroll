
package orbartal.wave.payroll.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import orbartal.wave.payroll.data.entity.TimeReportItemEntity;

@Repository
public interface TimeReportItemRepository extends PagingAndSortingRepository<TimeReportItemEntity, Long>{

	@Query(value = "SELECT t FROM TimeReportItemEntity t  WHERE t.employee.uid = :uid AND t.date >= :start AND t.date <= :end  ORDER BY t.date")
	List<TimeReportItemEntity> readPayAmountByEmployeeUidAndPeriod(
			@Param("uid") Long uid, 
			@Param("start") LocalDate start, 
			@Param("end") LocalDate end);

	@Query(value = "SELECT MIN(t.date) FROM TimeReportItemEntity t")
	LocalDate readGlobalStartDate();

	@Query(value = "SELECT MAX(t.date) FROM TimeReportItemEntity t")
	LocalDate readGlobalEndDate();
}

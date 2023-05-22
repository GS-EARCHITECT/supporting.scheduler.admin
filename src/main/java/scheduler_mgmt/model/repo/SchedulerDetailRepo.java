package scheduler_mgmt.model.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scheduler_mgmt.model.master.SchedulerDetail;
import scheduler_mgmt.model.master.SchedulerDetailPK;

@Repository("schedulerDetailRepo")
public interface SchedulerDetailRepo extends CrudRepository<SchedulerDetail, SchedulerDetailPK> 
{ 
@Query(value = "SELECT * FROM SCHEDULER_DETAILS a WHERE a.rule_line_seq_no in :ids order by rule_seq_no", nativeQuery = true)
ArrayList<SchedulerDetail> getSelectSchedules(@Param("id") ArrayList<Long> ids);

@Query(value = "SELECT * FROM SCHEDULER_DETAILS a WHERE (a.company_seq_no = :cSeqNo and a.rule_seq_no = :rSeqNo and a.target_seq_no = :tSeqNo) order by rule_seq_no", nativeQuery = true)
ArrayList<SchedulerDetail> getSelectSchedulesForCompanyTargetRule(@Param("cSeqNo") Long cSeqNo, @Param("rSeqNo") Long rSeqNo, @Param("tSeqNo") Long tSeqNo);

@Query(value = "SELECT * FROM SCHEDULER_DETAILS a WHERE a.rule_line_seq_no = :rSeqNo order by rule_line_seq_no", nativeQuery = true)
ArrayList<SchedulerDetail> getSelectSchedulesForRuleLine(@Param("rSeqNo") Long rSeqNo);

@Modifying
@Query(value = "DELETE FROM SCHEDULER_DETAILS WHERE a.rule_line_seq_no in :ids", nativeQuery = true)
void delSelectSchedules(@Param("ids") ArrayList<Long> ids);

@Query(value = "select coalesce(count(*),0) FROM SCHEDULER_DETAILS a WHERE a.rule_line_seq_no = :id", nativeQuery = true)
Float getCountOfSchedules(@Param("id") Long id);

@Modifying
@Query(value = "DELETE FROM SCHEDULER_DETAILS a WHERE a.rule_line_seq_no = :id", nativeQuery = true)
void delSchedulesForRuleLine(@Param("id") Long id);
} 


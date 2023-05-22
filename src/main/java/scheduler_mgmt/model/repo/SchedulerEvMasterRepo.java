package scheduler_mgmt.model.repo;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scheduler_mgmt.model.master.SchedulerMaster;


@Repository("schedulerEventMasterRepo")
public interface SchedulerEvMasterRepo extends CrudRepository<SchedulerMaster, Long> 
{ 
@Query(value = "SELECT * FROM SCHEDULER_MASTER b where upper(trim(SCHEDULED_FLAG))<>'Y' order by rule_line_seq_no", nativeQuery = true)
ArrayList<SchedulerMaster> getSchedules();

@Query(value = "SELECT * FROM SCHEDULER_MASTER b where "
		+ "((upper(trim(SCHEDULED_FLAG))<>'Y' and b.company_seq_no= :compSeqNo and b.rule_seq_no= :ruleSeqNo "
		+ " and b.target_seq_no= :targetSeqNo) and" + 
		" ((:frDtTm BETWEEN b.FROM_DTTM and b.TO_DTTM) " + 
		"  or (:toDtTm BETWEEN b.FROM_DTTM and b.TO_DTTM)))  order by rule_line_seq_no", nativeQuery = true)
Float checkIfSExists(@Param("compSeqNo") Long compSeqNo, @Param("targetSeqNo") Long targetSeqNo, @Param("ruleSeqNo") Long ruleSeqNo, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);


@Query(value = "SELECT * FROM SCHEDULER_MASTER where dow > 0 order by target_type", nativeQuery = true)
ArrayList<SchedulerMaster> getSchedulesDOW();

@Query(value = "SELECT * FROM SCHEDULER_MASTER where DAY_PLUS_BASIS > 0 order by target_type", nativeQuery = true)
ArrayList<SchedulerMaster> getSchedulesDaysPlus();

@Query(value = "SELECT * FROM SCHEDULER_MASTER a WHERE a.rule_seq_no in :ids order by rule_seq_no", nativeQuery = true)
SchedulerMaster getSelectSchedules(@Param("id") ArrayList<Long> ids);

@Query(value = "DELETE FROM SCHEDULER_MASTER WHERE a.rule_seq_no in :ids", nativeQuery = true)
void delSelectSchedules(@Param("ids") ArrayList<Long> ids);

@Modifying
@Query(value="update SCHEDULER_MASTER set scheduled_flag = :st WHERE rule_line_seq_no = :rlSeqNo", nativeQuery = true)
void updateScheduleStatus(@Param("st") Character st, @Param("rlSeqNo") Long rlSeqNo);
} 


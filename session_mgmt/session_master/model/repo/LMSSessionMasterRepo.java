package session_mgmt.session_master.model.repo;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import session_mgmt.session_master.model.master.LMSSessionMaster;

@Repository("lmsSessionMasterRepo")
public interface LMSSessionMasterRepo extends CrudRepository<LMSSessionMaster, Long> 
{ 
	@Query(value = "SELECT * FROM LMS_SESSION_MASTER a WHERE a.session_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionMaster> getSelectSessions(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM LMS_SESSION_MASTER a WHERE a.course_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionMaster> getSelectSessionsByCourses(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM LMS_SESSION_MASTER a WHERE a.INSTITUTE_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionMaster> getSelectSessionsByInst(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM LMS_SESSION_MASTER a WHERE a.BATCH_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionMaster> getSelectSessionsByBatches(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "select * FROM LMS_SESSION_MASTER b where ((b.session_SEQ_NO in :sessioncSeqNos) and ((:frDtTm BETWEEN b.FROM_DTTM and b.TO_DTTM)) and ((:toDtTm BETWEEN b.FROM_DTTM and b.TO_DTTM)))",nativeQuery = true) 
	ArrayList<LMSSessionMaster> getSelectSessionsBetweenTimes(@Param("sessioncSeqNos") ArrayList<Long> sessioncSeqNos, @Param("frDtTm") Timestamp frDtTm, @Param("frDtTm") Timestamp toDtTm);
		
	@Query(value = "DELETE FROM LMS_SESSION_MASTER WHERE a.course_seq_no in :ids", nativeQuery = true)
	void delSelectSessions(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "DELETE FROM LMS_SESSION_MASTER WHERE a.INSTITUTE_SEQ_NO in :ids", nativeQuery = true)
	void delSelectSessionsByInst(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "DELETE FROM LMS_SESSION_MASTER WHERE a.course_SEQ_NO in :ids", nativeQuery = true)
	void delSelectSessionsByCourses(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "delete FROM LMS_SESSION_MASTER a WHERE a.BATCH_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionMaster> delSelectSessionsByBatches(@Param("ids") ArrayList<Long> ids);
	
} 


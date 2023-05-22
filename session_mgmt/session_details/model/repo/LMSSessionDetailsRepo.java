package session_mgmt.session_details.model.repo;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import session_mgmt.session_details.model.details.LMSSessionDetails;
import session_mgmt.session_details.model.details.LMSSessionDetailsPK;

@Repository("sessionDetailsRepo")
public interface LMSSessionDetailsRepo extends JpaRepository<LMSSessionDetails, LMSSessionDetailsPK> {

	@Query(value = "SELECT * FROM LMS_SESSION_DETAILS a WHERE a.SESSION_SEQ_NO in :ids order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionDetails> getSelectSessions(@Param("ids") ArrayList<Long> ids);

	@Query(value = "SELECT * FROM LMS_SESSION_DETAILS a WHERE a.parent_session_SEQ_NO in :ids order by subject_seq_no", nativeQuery = true)
	ArrayList<LMSSessionDetails> getSelectSessionsByParents(@Param("ids") ArrayList<Long> ids);
	
	@Query(value = "DELETE FROM LMS_SESSION_DETAILS WHERE a.subject_seq_no in :ids", nativeQuery = true)
	void delSelectLMSSession(@Param("ids") ArrayList<Long> ids);
		
	
}

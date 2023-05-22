package session_mgmt.session_details.model.repo;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import session_mgmt.session_details.model.master.LMSSessionScheduleMaster;
import session_mgmt.session_details.model.master.LMSSessionScheduleMasterPK;

@Repository("sessionScheduleMasterRepo")
public interface LMSSessionScheduleMasterRepo extends JpaRepository<LMSSessionScheduleMaster, LMSSessionScheduleMasterPK> {

	@Query(value = "SELECT * FROM LMS_SESSION_SCHEDULE_MASTER a WHERE a.SESSION_SEQ_NO = :id and a.dow_no = :dow order by session_seq_no", nativeQuery = true)
	ArrayList<LMSSessionScheduleMaster> getSessionsByDOW(@Param("id") Long id, @Param("dow") Integer dow);

}

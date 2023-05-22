package session_mgmt.session_details.model.master;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LMS_SESSION_SCHEDULE_MASTER database table.
 * 
 */
@Entity
@Table(name="LMS_SESSION_SCHEDULE_MASTER")
public class LMSSessionScheduleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LMSSessionScheduleMasterPK id;

	public LMSSessionScheduleMaster() {
	}

	public LMSSessionScheduleMasterPK getId() {
		return this.id;
	}

	public void setId(LMSSessionScheduleMasterPK id) {
		this.id = id;
	}

}
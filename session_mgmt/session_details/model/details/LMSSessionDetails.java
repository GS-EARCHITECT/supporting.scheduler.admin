package session_mgmt.session_details.model.details;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LMS_SESSION_DETAILS database table.
 * 
 */
@Entity
@Table(name="LMS_SESSION_DETAILS")
public class LMSSessionDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LMSSessionDetailsPK id;

	public LMSSessionDetails() {
	}

	public LMSSessionDetailsPK getId() {
		return this.id;
	}

	public void setId(LMSSessionDetailsPK id) {
		this.id = id;
	}

	public LMSSessionDetails(LMSSessionDetailsPK id) {
		super();
		this.id = id;
	}

	
}
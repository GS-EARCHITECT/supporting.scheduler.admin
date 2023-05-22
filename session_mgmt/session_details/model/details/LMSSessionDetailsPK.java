package session_mgmt.session_details.model.details;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LMS_SESSION_DETAILS database table.
 * 
 */
@Embeddable
public class LMSSessionDetailsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SESSION_SEQ_NO")
	private long sessionSeqNo;

	@Column(name="PARENT_SESSION_SEQ_NO")
	private long parentSessionSeqNo;

	public LMSSessionDetailsPK() {
	}
	public long getSessionSeqNo() {
		return this.sessionSeqNo;
	}
	public void setSessionSeqNo(long sessionSeqNo) {
		this.sessionSeqNo = sessionSeqNo;
	}
	public long getParentSessionSeqNo() {
		return this.parentSessionSeqNo;
	}
	public void setParentSessionSeqNo(long parentSessionSeqNo) {
		this.parentSessionSeqNo = parentSessionSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LMSSessionDetailsPK)) {
			return false;
		}
		LMSSessionDetailsPK castOther = (LMSSessionDetailsPK)other;
		return 
			(this.sessionSeqNo == castOther.sessionSeqNo)
			&& (this.parentSessionSeqNo == castOther.parentSessionSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sessionSeqNo ^ (this.sessionSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.parentSessionSeqNo ^ (this.parentSessionSeqNo >>> 32)));
		
		return hash;
	}
	public LMSSessionDetailsPK(long sessionSeqNo, long parentSessionSeqNo) {
		super();
		this.sessionSeqNo = sessionSeqNo;
		this.parentSessionSeqNo = parentSessionSeqNo;
	}
		
}
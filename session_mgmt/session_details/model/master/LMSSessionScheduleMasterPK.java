package session_mgmt.session_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LMS_SESSION_SCHEDULE_MASTER database table.
 * 
 */
@Embeddable
public class LMSSessionScheduleMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SESSION_SEQ_NO")
	private long sessionSeqNo;

	@Column(name="DOW_NO")
	private long dowNo;

	@Column(name="SUBJECT_SEQ_NO")
	private long subjectSeqNo;

	@Column(name="FR_DTTM")
	private String frDttm;

	@Column(name="TO_DTTM")
	private String toDttm;

	public LMSSessionScheduleMasterPK() {
	}
	public long getSessionSeqNo() {
		return this.sessionSeqNo;
	}
	public void setSessionSeqNo(long sessionSeqNo) {
		this.sessionSeqNo = sessionSeqNo;
	}
	public long getDowNo() {
		return this.dowNo;
	}
	public void setDowNo(long dowNo) {
		this.dowNo = dowNo;
	}
	public long getSubjectSeqNo() {
		return this.subjectSeqNo;
	}
	public void setSubjectSeqNo(long subjectSeqNo) {
		this.subjectSeqNo = subjectSeqNo;
	}
	public String getFrDttm() {
		return this.frDttm;
	}
	public void setFrDttm(String frDttm) {
		this.frDttm = frDttm;
	}
	public String getToDttm() {
		return this.toDttm;
	}
	public void setToDttm(String toDttm) {
		this.toDttm = toDttm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LMSSessionScheduleMasterPK)) {
			return false;
		}
		LMSSessionScheduleMasterPK castOther = (LMSSessionScheduleMasterPK)other;
		return 
			(this.sessionSeqNo == castOther.sessionSeqNo)
			&& (this.dowNo == castOther.dowNo)
			&& (this.subjectSeqNo == castOther.subjectSeqNo)
			&& this.frDttm.equals(castOther.frDttm)
			&& this.toDttm.equals(castOther.toDttm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sessionSeqNo ^ (this.sessionSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.dowNo ^ (this.dowNo >>> 32)));
		hash = hash * prime + ((int) (this.subjectSeqNo ^ (this.subjectSeqNo >>> 32)));
		hash = hash * prime + this.frDttm.hashCode();
		hash = hash * prime + this.toDttm.hashCode();
		
		return hash;
	}
}
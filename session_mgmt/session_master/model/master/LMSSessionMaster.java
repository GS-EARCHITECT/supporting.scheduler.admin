package session_mgmt.session_master.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="LMS_SESSION_MASTER")
public class LMSSessionMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4892378997098622040L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="lms_session_seq_no")
	@SequenceGenerator(name="lms_session_seq_no", sequenceName="lms_session_seq_no", allocationSize = 1)
	@Column(name="SESSION_SEQ_NO")
	private Long sessionSeqNo;

	@Column(name="COURSE_SEQ_NO")
	private Long courseSeqNo;

	@Column(name="BATCH_SEQ_NO")
	private Long batchSeqNo;
	
	@Column(name="FROM_DTTIM")
	private Timestamp fromDttim;

	@Column(name="INSTITUTE_SEQ_NO")
	private Long instituteSeqNo;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="SESSION_SUMMARY")
	private String sessionSummary;

	@Column(name="TO_DTTM")
	private Timestamp toDttm;

	public LMSSessionMaster() {
	}

	public Long getSessionSeqNo() {
		return this.sessionSeqNo;
	}

	public void setSessionSeqNo(Long sessionSeqNo) {
		this.sessionSeqNo = sessionSeqNo;
	}

	public Long getCourseSeqNo() {
		return this.courseSeqNo;
	}

	public void setCourseSeqNo(Long courseSeqNo) {
		this.courseSeqNo = courseSeqNo;
	}

	public Timestamp getFromDttim() {
		return this.fromDttim;
	}

	public void setFromDttim(Timestamp fromDttim) {
		this.fromDttim = fromDttim;
	}

	public Long getInstituteSeqNo() {
		return this.instituteSeqNo;
	}

	public void setInstituteSeqNo(Long instituteSeqNo) {
		this.instituteSeqNo = instituteSeqNo;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionSummary() {
		return this.sessionSummary;
	}

	public void setSessionSummary(String sessionSummary) {
		this.sessionSummary = sessionSummary;
	}

	public Timestamp getToDttm() {
		return this.toDttm;
	}

	public void setToDttm(Timestamp toDttm) {
		this.toDttm = toDttm;
	}

	public Long getBatchSeqNo() {
		return batchSeqNo;
	}

	public void setBatchSeqNo(Long batchSeqNo) {
		this.batchSeqNo = batchSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchSeqNo == null) ? 0 : batchSeqNo.hashCode());
		result = prime * result + ((courseSeqNo == null) ? 0 : courseSeqNo.hashCode());
		result = prime * result + ((fromDttim == null) ? 0 : fromDttim.hashCode());
		result = prime * result + ((instituteSeqNo == null) ? 0 : instituteSeqNo.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((sessionSeqNo == null) ? 0 : sessionSeqNo.hashCode());
		result = prime * result + ((sessionSummary == null) ? 0 : sessionSummary.hashCode());
		result = prime * result + ((toDttm == null) ? 0 : toDttm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LMSSessionMaster other = (LMSSessionMaster) obj;
		if (batchSeqNo == null) {
			if (other.batchSeqNo != null)
				return false;
		} else if (!batchSeqNo.equals(other.batchSeqNo))
			return false;
		if (courseSeqNo == null) {
			if (other.courseSeqNo != null)
				return false;
		} else if (!courseSeqNo.equals(other.courseSeqNo))
			return false;
		if (fromDttim == null) {
			if (other.fromDttim != null)
				return false;
		} else if (!fromDttim.equals(other.fromDttim))
			return false;
		if (instituteSeqNo == null) {
			if (other.instituteSeqNo != null)
				return false;
		} else if (!instituteSeqNo.equals(other.instituteSeqNo))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (sessionSeqNo == null) {
			if (other.sessionSeqNo != null)
				return false;
		} else if (!sessionSeqNo.equals(other.sessionSeqNo))
			return false;
		if (sessionSummary == null) {
			if (other.sessionSummary != null)
				return false;
		} else if (!sessionSummary.equals(other.sessionSummary))
			return false;
		if (toDttm == null) {
			if (other.toDttm != null)
				return false;
		} else if (!toDttm.equals(other.toDttm))
			return false;
		return true;
	}

	public LMSSessionMaster(Long sessionSeqNo, Long courseSeqNo, Long batchSeqNo, Timestamp fromDttim,
			Long instituteSeqNo, String sessionId, String sessionSummary, Timestamp toDttm) {
		super();
		this.sessionSeqNo = sessionSeqNo;
		this.courseSeqNo = courseSeqNo;
		this.batchSeqNo = batchSeqNo;
		this.fromDttim = fromDttim;
		this.instituteSeqNo = instituteSeqNo;
		this.sessionId = sessionId;
		this.sessionSummary = sessionSummary;
		this.toDttm = toDttm;
	}

}
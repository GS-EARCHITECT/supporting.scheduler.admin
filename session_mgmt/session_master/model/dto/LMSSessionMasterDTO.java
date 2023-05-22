package session_mgmt.session_master.model.dto;

import java.io.Serializable;

public class LMSSessionMasterDTO implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4894958501487735403L;
	private long sessionSeqNo;
	private Long courseSeqNo;
	private Long batchSeqNo;
	private String fromDttim;
	private Long instituteSeqNo;
	private String sessionId;
	private String sessionSummary;
	private String toDttm;
	
	public long getSessionSeqNo() {
		return sessionSeqNo;
	}
	public void setSessionSeqNo(long sessionSeqNo) {
		this.sessionSeqNo = sessionSeqNo;
	}
	public Long getCourseSeqNo() {
		return courseSeqNo;
	}
	public void setCourseSeqNo(Long courseSeqNo) {
		this.courseSeqNo = courseSeqNo;
	}
	public String getFromDttim() {
		return fromDttim;
	}
	public void setFromDttim(String fromDttim) {
		this.fromDttim = fromDttim;
	}
	public Long getInstituteSeqNo() {
		return instituteSeqNo;
	}
	public void setInstituteSeqNo(Long instituteSeqNo) {
		this.instituteSeqNo = instituteSeqNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionSummary() {
		return sessionSummary;
	}
	public void setSessionSummary(String sessionSummary) {
		this.sessionSummary = sessionSummary;
	}
	public String getToDttm() {
		return toDttm;
	}
	public void setToDttm(String toDttm) {
		this.toDttm = toDttm;
	}

	public Long getBatchSeqNo() {
		return batchSeqNo;
	}
	public void setBatchSeqNo(Long batchSeqNo) {
		this.batchSeqNo = batchSeqNo;
	}
	public LMSSessionMasterDTO() {
		super();
	}
	public LMSSessionMasterDTO(long sessionSeqNo, Long courseSeqNo, Long batchSeqNo, String fromDttim,
			Long instituteSeqNo, String sessionId, String sessionSummary, String toDttm) {
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
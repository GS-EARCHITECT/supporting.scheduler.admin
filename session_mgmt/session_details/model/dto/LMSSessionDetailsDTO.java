package session_mgmt.session_details.model.dto;

import java.io.Serializable;


public class LMSSessionDetailsDTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008168869429698715L;
	private long sessionSeqNo;
	private long parentSessionSeqNo;
	public long getSessionSeqNo() {
		return sessionSeqNo;
	}
	public void setSessionSeqNo(long sessionSeqNo) {
		this.sessionSeqNo = sessionSeqNo;
	}
	public long getParentSessionSeqNo() {
		return parentSessionSeqNo;
	}
	public void setParentSessionSeqNo(long parentSessionSeqNo) {
		this.parentSessionSeqNo = parentSessionSeqNo;
	}
	public LMSSessionDetailsDTO(long sessionSeqNo, long parentSessionSeqNo) {
		super();
		this.sessionSeqNo = sessionSeqNo;
		this.parentSessionSeqNo = parentSessionSeqNo;
	}
	public LMSSessionDetailsDTO() {
		super();
	}
	
	
	
		}

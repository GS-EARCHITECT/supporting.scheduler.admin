package scheduler_mgmt.model.dto;

import java.io.Serializable;

public class SchedulerSpecEvMasterDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -590349344105748297L;
	private long specEventSeqNo;
	private String companySeqNo;
	private String fromDttm;

	public long getSpecEventSeqNo() {
		return specEventSeqNo;
	}

	public void setSpecEventSeqNo(long specEventSeqNo) {
		this.specEventSeqNo = specEventSeqNo;
	}

	public String getCompanySeqNo() {
		return companySeqNo;
	}

	public void setCompanySeqNo(String companySeqNo) {
		this.companySeqNo = companySeqNo;
	}

	public String getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(String fromDttm) {
		this.fromDttm = fromDttm;
	}

	public SchedulerSpecEvMasterDTO(long specEventSeqNo, String companySeqNo, String fromDttm) {
		super();
		this.specEventSeqNo = specEventSeqNo;
		this.companySeqNo = companySeqNo;
		this.fromDttm = fromDttm;
	}

	public SchedulerSpecEvMasterDTO() {
		super();
	}
}
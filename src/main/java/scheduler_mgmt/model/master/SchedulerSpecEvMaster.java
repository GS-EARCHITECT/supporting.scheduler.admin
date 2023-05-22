package scheduler_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "SCHEDULER_SPEC_EVENTS_MASTER")
public class SchedulerSpecEvMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4731012908992860044L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPEC_EV_SEQ_NO")
	@SequenceGenerator(name = "SPEC_EV_SEQ_NO", sequenceName = "SPEC_EV_SEQ_NO", allocationSize = 1)
	@Column(name = "SPEC_EV_SEQ_NO")
	private long specEventSeqNo;

	@Column(name = "EVENT_SUMMARY")
	private String companySeqNo;

	@Column(name = "ON_DTTM")
	private Timestamp fromDttm;

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

	public Timestamp getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(Timestamp fromDttm) {
		this.fromDttm = fromDttm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companySeqNo == null) ? 0 : companySeqNo.hashCode());
		result = prime * result + ((fromDttm == null) ? 0 : fromDttm.hashCode());
		result = prime * result + (int) (specEventSeqNo ^ (specEventSeqNo >>> 32));
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
		SchedulerSpecEvMaster other = (SchedulerSpecEvMaster) obj;
		if (companySeqNo == null) {
			if (other.companySeqNo != null)
				return false;
		} else if (!companySeqNo.equals(other.companySeqNo))
			return false;
		if (fromDttm == null) {
			if (other.fromDttm != null)
				return false;
		} else if (!fromDttm.equals(other.fromDttm))
			return false;
		if (specEventSeqNo != other.specEventSeqNo)
			return false;
		return true;
	}

	public SchedulerSpecEvMaster(long specEventSeqNo, String companySeqNo, Timestamp fromDttm) {
		super();
		this.specEventSeqNo = specEventSeqNo;
		this.companySeqNo = companySeqNo;
		this.fromDttm = fromDttm;
	}

	public SchedulerSpecEvMaster() {
		super();
	}

}
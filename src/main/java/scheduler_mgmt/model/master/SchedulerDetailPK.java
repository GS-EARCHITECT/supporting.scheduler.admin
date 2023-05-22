package scheduler_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The primary key class for the SCHEDULER_DETAILS database table.
 * 
 */
@Embeddable
public class SchedulerDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "FR_DTTM")
	private Timestamp frDttm;

	@Column(name = "TO_DTTM")
	private Timestamp toDttm;

	@Column(name = "RULE_LINE_SEQ_NO")
	private long ruleLineSeqNo;

	public SchedulerDetailPK() {
	}

	public Timestamp getFrDttm() {
		return frDttm;
	}

	public void setFrDttm(Timestamp frDttm) {
		this.frDttm = frDttm;
	}

	public Timestamp getToDttm() {
		return toDttm;
	}

	public void setToDttm(Timestamp toDttm) {
		this.toDttm = toDttm;
	}

	public long getRuleLineSeqNo() {
		return ruleLineSeqNo;
	}

	public void setRuleLineSeqNo(long ruleLineSeqNo) {
		this.ruleLineSeqNo = ruleLineSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frDttm == null) ? 0 : frDttm.hashCode());
		result = prime * result + (int) (ruleLineSeqNo ^ (ruleLineSeqNo >>> 32));
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
		SchedulerDetailPK other = (SchedulerDetailPK) obj;
		if (frDttm == null) {
			if (other.frDttm != null)
				return false;
		} else if (!frDttm.equals(other.frDttm))
			return false;
		if (ruleLineSeqNo != other.ruleLineSeqNo)
			return false;
		if (toDttm == null) {
			if (other.toDttm != null)
				return false;
		} else if (!toDttm.equals(other.toDttm))
			return false;
		return true;
	}

}
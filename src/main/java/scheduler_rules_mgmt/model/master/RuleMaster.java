package scheduler_rules_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the CATALOG_MASTER database table.
 * 
 */
@Entity
@Table(name = "RULE_MASTER")
public class RuleMaster implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8053597734518238602L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RULE_SEQ_NO")
	@SequenceGenerator(name = "RULE_SEQ_NO", sequenceName = "RULE_SEQ_NO", allocationSize = 1)
	@Column(name = "RULE_SEQ_NO")
	private long ruleSeqNo;

	@Column(name = "FUNCTION_NAME")
	private String functionName;
	
	@Column(name = "CLASS_NAME")
	private String className;

	@Column(name = "CLASS_PACKAGE")
	private String classPackage;

	public long getRuleSeqNo() {
		return ruleSeqNo;
	}

	public void setRuleSeqNo(long ruleSeqNo) {
		this.ruleSeqNo = ruleSeqNo;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((classPackage == null) ? 0 : classPackage.hashCode());
		result = prime * result + ((functionName == null) ? 0 : functionName.hashCode());
		result = prime * result + (int) (ruleSeqNo ^ (ruleSeqNo >>> 32));
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
		RuleMaster other = (RuleMaster) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (classPackage == null) {
			if (other.classPackage != null)
				return false;
		} else if (!classPackage.equals(other.classPackage))
			return false;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		if (ruleSeqNo != other.ruleSeqNo)
			return false;
		return true;
	}

	public RuleMaster(long ruleSeqNo, String functionName, String className, String classPackage) {
		super();
		this.ruleSeqNo = ruleSeqNo;
		this.functionName = functionName;
		this.className = className;
		this.classPackage = classPackage;
	}

	public RuleMaster() {
		super();
	}
	
}
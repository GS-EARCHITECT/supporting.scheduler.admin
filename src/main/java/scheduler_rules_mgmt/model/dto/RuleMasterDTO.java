package scheduler_rules_mgmt.model.dto;

import java.io.Serializable;

public class RuleMasterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4498051341339346230L;

	private long ruleSeqNo;
	private String functionName;
	private String className;
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

	public RuleMasterDTO(long ruleSeqNo, String functionName, String className, String classPackage) {
		super();
		this.ruleSeqNo = ruleSeqNo;
		this.functionName = functionName;
		this.className = className;
		this.classPackage = classPackage;
	}

	public RuleMasterDTO() {
		super();
	}

}
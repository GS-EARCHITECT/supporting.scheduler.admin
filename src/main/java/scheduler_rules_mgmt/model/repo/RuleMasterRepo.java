package scheduler_rules_mgmt.model.repo;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scheduler_rules_mgmt.model.master.RuleMaster;

@Repository("ruleMasterRepo")
public interface RuleMasterRepo extends CrudRepository<RuleMaster, Long> 
{
	@Query(value = "SELECT * FROM RULE_MASTER a WHERE a.rule_seq_no in :ids order by rule_seq_no", nativeQuery = true)
	RuleMaster getSelectRules(@Param("id") ArrayList<Long> ids);
	
	@Query(value = "DELETE FROM RULE_MASTER a WHERE a.rule_seq_no in :ids", nativeQuery = true)
	void delSelectRules(@Param("ids") ArrayList<Long> ids);
}

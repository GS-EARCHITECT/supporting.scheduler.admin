package scheduler_rules_mgmt.services;

import java.util.ArrayList;

import scheduler_rules_mgmt.model.dto.RuleMasterDTO;

public interface I_RuleMasterService
{
	abstract public RuleMasterDTO newRuleMaster(RuleMasterDTO promoMasterDTO);
    abstract public ArrayList<RuleMasterDTO> getAllRuleMasters();    
    abstract public RuleMasterDTO getRuleMasterById(Long promoSeqNo);
    abstract public void updRuleMaster(RuleMasterDTO RuleMasterDTO);
    abstract public void delRuleMaster(Long DocumentSeqNo);
    abstract public void delAllRuleMasters();    
    abstract public void delSelectRules(ArrayList<Long> ids);    
}
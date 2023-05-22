package scheduler_rules_mgmt.services;

import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import scheduler_rules_mgmt.model.dto.RuleMasterDTO;
import scheduler_rules_mgmt.model.master.RuleMaster;
import scheduler_rules_mgmt.model.repo.RuleMasterRepo;

@Service("ruleMasterServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RuleMasterService implements I_RuleMasterService 
{
//	private static final Logger logger = LoggerFactory.getLogger(RuleMasterService.class);
	
	@Autowired
	private RuleMasterRepo ruleMasterRepo;
	
	public RuleMasterDTO newRuleMaster(RuleMasterDTO lMaster) 
	{
		RuleMaster RuleMaster = ruleMasterRepo.save(this.setRuleMaster(lMaster));
		lMaster = getRuleMasterDTO(RuleMaster);
		return lMaster;
	}

	public ArrayList<RuleMasterDTO> getAllRuleMasters() {
		ArrayList<RuleMaster> resourceList = (ArrayList<RuleMaster>) ruleMasterRepo.findAll();
		ArrayList<RuleMasterDTO> lMasterss = new ArrayList<RuleMasterDTO>();
		lMasterss = resourceList != null ? this.getRuleMasterDTOs(resourceList) : null;
		return lMasterss;
	}

	public RuleMasterDTO getRuleMasterById(Long ruleSeqNo) {
		Optional<RuleMaster> RuleMaster = ruleMasterRepo.findById(ruleSeqNo);
		RuleMasterDTO lMasters = null;
		if (RuleMaster.isPresent()) {
			lMasters = RuleMaster != null ? this.getRuleMasterDTO(RuleMaster.get()) : null;
		}
		return lMasters;
	}

	public void updRuleMaster(RuleMasterDTO lMaster) 
	{
		RuleMaster ruleMaster = this.setRuleMaster(lMaster);
		if (ruleMasterRepo.existsById(lMaster.getRuleSeqNo())) 
				{		
			ruleMaster.setRuleSeqNo(lMaster.getRuleSeqNo());			
			ruleMasterRepo.save(ruleMaster);			
		}
		return;
	}

	public void delRuleMaster(Long resourceSeqNo) {
		if (ruleMasterRepo.existsById(resourceSeqNo)) {
			ruleMasterRepo.deleteById(resourceSeqNo);
		}
		return;
	}

	public void delAllRuleMasters() {
		ruleMasterRepo.deleteAll();
	}

	public void delSelectRules(ArrayList<Long> ids)
	{
		if (ids != null) {
			ruleMasterRepo.delSelectRules(ids);
		}
	}

	
	private ArrayList<RuleMasterDTO> getRuleMasterDTOs(ArrayList<RuleMaster> lMasters) 
	{
		RuleMasterDTO lDTO = null;
		ArrayList<RuleMasterDTO> lMasterDTOs = new ArrayList<RuleMasterDTO>();		
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getRuleMasterDTO(lMasters.get(i));			
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private RuleMasterDTO getRuleMasterDTO(RuleMaster lMaster) 
	{		
		RuleMasterDTO lDTO = new RuleMasterDTO();		
		lDTO.setRuleSeqNo(lMaster.getRuleSeqNo());
		lDTO.setFunctionName(lMaster.getFunctionName());
		lDTO.setClassPackage(lMaster.getClassPackage());
		lDTO.setClassName(lMaster.getClassName());
		return lDTO;
	}

	private RuleMaster setRuleMaster(RuleMasterDTO lDTO) 
	{
		RuleMaster lMaster = new RuleMaster();		
		lMaster.setFunctionName(lDTO.getFunctionName());
		lMaster.setClassPackage(lDTO.getClassPackage());
		lMaster.setClassName(lDTO.getClassName());
		return lMaster;
	}
}
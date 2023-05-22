package scheduler_rules_mgmt.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scheduler_rules_mgmt.model.dto.RuleMasterDTO;
import scheduler_rules_mgmt.services.I_RuleMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ruleManagement")
public class Rule_Master_Controller 
{

	private static final Logger logger = LoggerFactory.getLogger(Rule_Master_Controller.class);

	@Autowired
	private I_RuleMasterService ruleMasterServ;
	
	@PostMapping("/new")
	public ResponseEntity<RuleMasterDTO> newrule(@RequestBody RuleMasterDTO ruleDTO) {
		RuleMasterDTO ruleDTO2 = ruleMasterServ.newRuleMaster(ruleDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(ruleDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllrules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<RuleMasterDTO>> getAllRuleMasters() {
		ArrayList<RuleMasterDTO> ruleDTOs = ruleMasterServ.getAllRuleMasters();
		return new ResponseEntity<>(ruleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{ruleSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })	
	public ResponseEntity<RuleMasterDTO> getRuleMasterById(@PathVariable Long ruleSeqNo) 
	{
		RuleMasterDTO ruleAccNoDTOs = ruleMasterServ.getRuleMasterById(ruleSeqNo);		
		return new ResponseEntity<>(ruleAccNoDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updrule")
	public void updaterule(@RequestBody RuleMasterDTO ruleDTO) 
	{
			ruleMasterServ.updRuleMaster(ruleDTO);	
		return;
	}

	@DeleteMapping("/delrule/{ruleSeqNo}")
	public void deleterule(@PathVariable Long ruleSeqNo) {
		ruleMasterServ.delRuleMaster(ruleSeqNo);
	}

	@DeleteMapping("/delSelectrules")
	public void deleteSelectrules(@RequestBody ArrayList<Long> rulesSeqNoList) {
		ruleMasterServ.delSelectRules(rulesSeqNoList);
		return;
	}
	
	@DeleteMapping("/delAllrule")
	public void deleteAllrules() {
		ruleMasterServ.delAllRuleMasters();;
		return;
	}
	}
package session_mgmt.session_master.controller;

import java.sql.Timestamp;
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
import session_mgmt.session_master.model.dto.LMSSessionMasterDTO;
import session_mgmt.session_master.services.I_LMSSessionMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/sessionManagement")
public class LMSSessionMasterController {

//	private static final Logger logger = LoggerFactory.getLogger(LMSSessionMaster_Controller.class);

	@Autowired
	private I_LMSSessionMasterService lmsSessionMasterServ;
	
	@PostMapping("/new")
	public ResponseEntity<LMSSessionMasterDTO> newsession(@RequestBody LMSSessionMasterDTO sessionDTO) {
		LMSSessionMasterDTO sessionDTO2 = lmsSessionMasterServ.newLMSSessionMaster(sessionDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(sessionDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllSessions", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<LMSSessionMasterDTO>> getAllLMSSessionMasters() {
		ArrayList<LMSSessionMasterDTO> sessionDTOs = lmsSessionMasterServ.getAllSessions();
		return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectSessions", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<LMSSessionMasterDTO>> getSelectLMSMediaBySubjects(@RequestBody ArrayList<Long> subjectDetailsSeqNos) {
		ArrayList<LMSSessionMasterDTO> sessionDTOs = lmsSessionMasterServ.getSelectSessions(subjectDetailsSeqNos);		
		return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectSessionsbyInst", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<LMSSessionMasterDTO>> getSelectSessionsByInst(@RequestBody ArrayList<Long> iSeqNos) {
		ArrayList<LMSSessionMasterDTO> sessionDTOs = lmsSessionMasterServ.getSelectSessionsByInst(iSeqNos);		
		return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectSessionsbyBatches", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<LMSSessionMasterDTO>> getSelectSessionsByBatches(@RequestBody ArrayList<Long> iSeqNos) {
		ArrayList<LMSSessionMasterDTO> sessionDTOs = lmsSessionMasterServ.getSelectSessionsByBatches(iSeqNos);		
		return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectSessionsBetweenTimes/{fr}/{to}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<LMSSessionMasterDTO>> getSelectSessionsBetweenTimes(@PathVariable String fr, @PathVariable String to, @RequestBody ArrayList<Long> sSeqNos) {
		ArrayList<LMSSessionMasterDTO> sessionDTOs = lmsSessionMasterServ.getSelectSessionsBetweenTimes(sSeqNos, fr, to);		
		return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updSession")
	public void updatesession(@RequestBody LMSSessionMasterDTO sessionDTO) 
	{
			lmsSessionMasterServ.updLMSSessionMaster(sessionDTO);	
		return;
	}

	@DeleteMapping("/delSelectsession")
	public void deleteSelectsession(@RequestBody ArrayList<Long> sSeqNoList) {
		lmsSessionMasterServ.delSelectSessions(sSeqNoList);
		return;
	}
	
	@DeleteMapping("/delAllsession")
	public void deleteAllSessions() {
		lmsSessionMasterServ.delAllLMSSessionMasters();;
		return;
	}
	
	@DeleteMapping("/delSelectsessionbyIn")
	public void delSelectSessionsByInst(@RequestBody ArrayList<Long> ids)
	{
		lmsSessionMasterServ.delSelectSessionsByInst(ids);
		return;
	}
	
	@DeleteMapping("/delSelectsessionbyCourses")
	public void delSelectSessionsByCourses(@RequestBody ArrayList<Long> ids)
	{
		lmsSessionMasterServ.delSelectSessionsByCourses(ids);
		return;
	}
	
	@DeleteMapping("/delSelectsessionbyBatches")
	public void delSelectSessionsByBatches(@RequestBody ArrayList<Long> ids)
	{
		lmsSessionMasterServ.delSelectSessionsByBatches(ids);;
		return;
	}
	
}
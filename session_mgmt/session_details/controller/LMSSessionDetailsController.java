package session_mgmt.session_details.controller;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import session_mgmt.session_details.model.dto.LMSSessionDetailsDTO;
import session_mgmt.session_details.services.I_LMSSessionDetailsService;

@RestController
@RequestMapping("/sessionDetails")
public class LMSSessionDetailsController {

//private static final Logger logger = LoggerFactory.getLogger(LMS_SessionDetails_Controller.class);
	
	@Autowired
	private I_LMSSessionDetailsService lmsSessionDetailsServ ;
	

	@PostMapping("/addSubject")
	public ResponseEntity<LMSSessionDetailsDTO> addToSubject(@RequestBody LMSSessionDetailsDTO lms_SessionDetails_DTO) 
	{		
		LMSSessionDetailsDTO lms_SessionDetails_DTO2 = lmsSessionDetailsServ.newLMSSessionDetails(lms_SessionDetails_DTO);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<>(lms_SessionDetails_DTO2, httpHeaders, HttpStatus.CREATED);		
	}

	@GetMapping(value="/getAllSessions", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<LMSSessionDetailsDTO>> getAllSessions() {
		

		ArrayList<LMSSessionDetailsDTO> lms_SessionDetails_DTOs = lmsSessionDetailsServ.getAllLMSSessionDetailss();
		return new ResponseEntity<>(lms_SessionDetails_DTOs, HttpStatus.OK);
			}
	
	@GetMapping(value="/getSessionsByParents", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<LMSSessionDetailsDTO>> getSessionsByParents(@RequestBody ArrayList<Long> subjectSeqNos) 
	{
		ArrayList<LMSSessionDetailsDTO> sessionDetails_DTOs = lmsSessionDetailsServ.getSelectSessionsByParents(subjectSeqNos);
		return new ResponseEntity<>(sessionDetails_DTOs, HttpStatus.OK);
	}
	
	@GetMapping(value="/getSubjects", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<LMSSessionDetailsDTO>> getSelectSessions(@RequestBody ArrayList<Long> sessionSeqNos) 
	{
		ArrayList<LMSSessionDetailsDTO> sessionDetails_DTOs = lmsSessionDetailsServ.getSelectSessions(sessionSeqNos);
		return new ResponseEntity<>(sessionDetails_DTOs, HttpStatus.OK);
	}

	@PutMapping("/sessionUpdate")
	public void updateSubject(@RequestBody LMSSessionDetailsDTO lms_SessionDetails_DTO) {		
		lmsSessionDetailsServ.updLMSSessionDetails(lms_SessionDetails_DTO);
	}

	@DeleteMapping("/deleteSelectSessions")
	public void deleteSession(@RequestBody ArrayList<Long> sessionSeqNos) 
	{	
		lmsSessionDetailsServ.delSelectLMSSessions(sessionSeqNos);
	}

	
	@DeleteMapping("/deleteAllSessions")
	public void deleteAllSessions() 
	{	
		lmsSessionDetailsServ.delAllLMSSessionDetailss();
	}

}
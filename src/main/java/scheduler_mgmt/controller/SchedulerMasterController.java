package scheduler_mgmt.controller;

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
import scheduler_mgmt.model.dto.SchedulerDetailDTO;
import scheduler_mgmt.model.dto.SchedulerMasterDTO;
import scheduler_mgmt.services.I_SchedulerMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/schedulerManagement")
public class SchedulerMasterController 
{
//private static final Logger logger = LoggerFactory.getLogger(Scheduler_Master_Controller.class);

	@Autowired
	private I_SchedulerMasterService schedulerMasterServ;
	
	@GetMapping(value = "/run", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createSchedule()
	{
		String str = schedulerMasterServ.createSchedules();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(str, httpHeaders, HttpStatus.CREATED);
	}
	
	@PostMapping("/new")
	public ResponseEntity<SchedulerMasterDTO> newschedule(@RequestBody SchedulerMasterDTO scheduleDTO) {		
		SchedulerMasterDTO scheduleDTO2 = schedulerMasterServ.newSchedulerMaster(scheduleDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(scheduleDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllSchedules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<SchedulerMasterDTO>> getAllSchedulerMasters() {
		ArrayList<SchedulerMasterDTO> scheduleDTOs = schedulerMasterServ.getAllSchedulerMasters();
		return new ResponseEntity<>(scheduleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectSchedulesForCompanyTargetRule", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<SchedulerDetailDTO>> getSelectSchedulesForCompanyTargetRule(@PathVariable Long cSeqNo, @PathVariable Long rSeqNo, @PathVariable Long tSeqNo) 
	{
		ArrayList<SchedulerDetailDTO> scheduleDetailDTOs = schedulerMasterServ.getSelectSchedulesForCompanyTargetRule(cSeqNo, rSeqNo, tSeqNo);;
		return new ResponseEntity<>(scheduleDetailDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectSchedulesForRuleLine", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<SchedulerDetailDTO>> getSelectSchedulesForRuleLine(@RequestBody Long rLineSeqNo) 
	{
		ArrayList<SchedulerDetailDTO> scheduleDetailDTOs = schedulerMasterServ.getSelectSchedulesForRuleLine(rLineSeqNo);
		return new ResponseEntity<>(scheduleDetailDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{scheduleSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })	
	public ResponseEntity<SchedulerMasterDTO> getSchedulerMasterById(@PathVariable Long scheduleSeqNo) 
	{
		SchedulerMasterDTO scheduleAccNoDTOs = schedulerMasterServ.getSchedulerMasterById(scheduleSeqNo);		
		return new ResponseEntity<>(scheduleAccNoDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updschedule")
	public void updateschedule(@RequestBody SchedulerMasterDTO scheduleDTO) 
	{
			schedulerMasterServ.updSchedulerMaster(scheduleDTO);	
		return;
	}

	@DeleteMapping("/delschedule/{scheduleSeqNo}")
	public void deleteschedule(@PathVariable Long scheduleSeqNo) {
		schedulerMasterServ.delSchedulerMaster(scheduleSeqNo);
	}

	@DeleteMapping("/delSelectschedules")
	public void deleteSelectschedules(@RequestBody ArrayList<Long> schedulersSeqNoList) {
		schedulerMasterServ.delSelectSchedulers(schedulersSeqNoList);
		return;
	}
	
	@DeleteMapping("/delAllschedules")
	public void deleteAllschedules() {
		schedulerMasterServ.delAllSchedulerMasters();;
		return;
	}
	}
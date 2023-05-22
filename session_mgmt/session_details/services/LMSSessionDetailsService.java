package session_mgmt.session_details.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import session_mgmt.session_details.model.details.LMSSessionDetails;
import session_mgmt.session_details.model.details.LMSSessionDetailsPK;
import session_mgmt.session_details.model.dto.LMSSessionDetailsDTO;
import session_mgmt.session_details.model.master.LMSSessionScheduleMaster;
import session_mgmt.session_details.model.repo.LMSSessionDetailsRepo;
import session_mgmt.session_details.model.repo.LMSSessionScheduleMasterRepo;

@Service("sessionDetailsServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LMSSessionDetailsService implements I_LMSSessionDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(LMSSessionDetailsService.class);
	@Autowired
	private LMSSessionDetailsRepo sessionDetailsRepo;

	@Autowired
	private LMSSessionScheduleMasterRepo sessionScheduleMasterRepo;

	@Scheduled(fixedRate = 3000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void scheduleSession() 
	{
		
		logger.info("BEGIN");
		// currDate = session start date
		// lastdate = currDate+number of days in course
		Date currDate = new Date(System.currentTimeMillis());
		Date frts = null;
		Date tots = null;
		Long ss = (long) 0;
		Long cs = (long) 0;
		DateFormat dtOnly = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat tmOnly = new SimpleDateFormat("HH:mm:ss");
		Long days = (long) 10;
		Integer dow =0;
		Long i = (long) 1;		
		String frTime=null;
		String toTime=null;
		String dt = null;		
		java.util.GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");				
		cal.setTime(currDate);
		
		for(long ctr=0; ctr<days; ctr++)
		 {			  		  
		  dow=cal.get(Calendar.DAY_OF_WEEK)-1;
		  logger.info("DOW IS :"+dow);		  
		   ArrayList<LMSSessionScheduleMaster> lms = sessionScheduleMasterRepo.getSessionsByDOW(i, dow);
		  dt = dtOnly.format(currDate);
		  
		  if(lms!=null)
			{ 
			for (int j = 0; j < lms.size(); j++) 
			{
			frTime=dt + " " +lms.get(j).getId().getFrDttm();
			toTime=dt + " " +lms.get(j).getId().getToDttm();
			cs=lms.get(j).getId().getSubjectSeqNo();
			ss=lms.get(j).getId().getSessionSeqNo();
			try {
				frts = formatter.parse(frTime);
				tots = formatter.parse(toTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Session :"+ss);
			logger.info("Subject :"+cs);
			logger.info("Date :"+dt);
			logger.info("From :"+frts);
			logger.info("To :"+tots);			
			}			
		  }
		  cal.add(Calendar.DATE, 1);
		  currDate=cal.getTime();
		  cal.setTime(currDate);	        
		}
		 logger.info("END");
	return;

	}

	public LMSSessionDetailsDTO newLMSSessionDetails(LMSSessionDetailsDTO lMasters) {
		LMSSessionDetailsPK lmsSessionDetailsPK = new LMSSessionDetailsPK();
		lmsSessionDetailsPK.setSessionSeqNo(lMasters.getSessionSeqNo());
		lmsSessionDetailsPK.setParentSessionSeqNo(lMasters.getParentSessionSeqNo());
		LMSSessionDetails lmsSessionDetails = null;

		if (!sessionDetailsRepo.existsById(lmsSessionDetailsPK)) {
			lmsSessionDetails = this.setLMSSessionDetails(lMasters);
			lmsSessionDetails.setId(lmsSessionDetailsPK);
			lMasters = getLMSSessionDetailsDTO(sessionDetailsRepo.save(lmsSessionDetails));
		}

		return lMasters;
	}

	public ArrayList<LMSSessionDetailsDTO> getAllLMSSessionDetailss() {
		ArrayList<LMSSessionDetails> subjectList = (ArrayList<LMSSessionDetails>) sessionDetailsRepo.findAll();
		ArrayList<LMSSessionDetailsDTO> lMasterss = new ArrayList<LMSSessionDetailsDTO>();
		lMasterss = subjectList != null ? this.getLMSSessionDetailsDTOs(subjectList) : null;
		return lMasterss;
	}

	public ArrayList<LMSSessionDetailsDTO> getSelectSessions(ArrayList<Long> ids) {
		ArrayList<LMSSessionDetails> lMasters = sessionDetailsRepo.getSelectSessions(ids);
		ArrayList<LMSSessionDetailsDTO> LMSSessionDetailsDTOs = new ArrayList<LMSSessionDetailsDTO>();
		LMSSessionDetailsDTO LMSSessionDetailsDTO = null;

		if (lMasters != null) {
			for (int i = 0; i < lMasters.size(); i++) {
				LMSSessionDetailsDTO = this.getLMSSessionDetailsDTO(lMasters.get(i));
				LMSSessionDetailsDTOs.add(LMSSessionDetailsDTO);
			}
		}
		return LMSSessionDetailsDTOs;
	}

	public ArrayList<LMSSessionDetailsDTO> getSelectSessionsByParents(ArrayList<Long> ids) {
		ArrayList<LMSSessionDetails> lMasters = sessionDetailsRepo.getSelectSessionsByParents(ids);
		ArrayList<LMSSessionDetailsDTO> LMSSessionDetailsDTOs = new ArrayList<LMSSessionDetailsDTO>();
		LMSSessionDetailsDTO LMSSessionDetailsDTO = null;

		if (lMasters != null) {
			for (int i = 0; i < lMasters.size(); i++) {
				LMSSessionDetailsDTO = this.getLMSSessionDetailsDTO(lMasters.get(i));
				LMSSessionDetailsDTOs.add(LMSSessionDetailsDTO);
			}
		}
		return LMSSessionDetailsDTOs;
	}

	public void updLMSSessionDetails(LMSSessionDetailsDTO lMaster) {
		LMSSessionDetailsPK lmsSessionDetailsPK = new LMSSessionDetailsPK();
		lmsSessionDetailsPK.setSessionSeqNo(lMaster.getSessionSeqNo());
		lmsSessionDetailsPK.setParentSessionSeqNo(lMaster.getParentSessionSeqNo());
		LMSSessionDetails lMSSessionDetails = null;

		if (sessionDetailsRepo.existsById(lmsSessionDetailsPK)) {
			lMSSessionDetails = setLMSSessionDetails(lMaster);
			lMSSessionDetails.setId(lmsSessionDetailsPK);
			sessionDetailsRepo.save(lMSSessionDetails);
		}
		return;
	}

	public void delLMSSessionDetails(LMSSessionDetailsDTO lMaster) {
		LMSSessionDetailsPK lmsSessionDetailsPK = new LMSSessionDetailsPK();
		lmsSessionDetailsPK.setSessionSeqNo(lMaster.getSessionSeqNo());
		lmsSessionDetailsPK.setParentSessionSeqNo(lMaster.getParentSessionSeqNo());

		if (sessionDetailsRepo.existsById(lmsSessionDetailsPK)) {
			sessionDetailsRepo.deleteById(lmsSessionDetailsPK);
		}
		return;
	}

	public void delSelectLMSSessions(ArrayList<Long> DocumentSeqNos) {
		sessionDetailsRepo.delSelectLMSSession(DocumentSeqNos);
		return;
	}

	public void delAllLMSSessionDetailss() {
		sessionDetailsRepo.deleteAll();
	}

	private ArrayList<LMSSessionDetailsDTO> getLMSSessionDetailsDTOs(ArrayList<LMSSessionDetails> lMasters) {
		LMSSessionDetailsDTO lDTO = null;
		ArrayList<LMSSessionDetailsDTO> lMasterDTOs = new ArrayList<LMSSessionDetailsDTO>();
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getLMSSessionDetailsDTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private LMSSessionDetailsDTO getLMSSessionDetailsDTO(LMSSessionDetails lMaster) {
		LMSSessionDetailsDTO lDTO = new LMSSessionDetailsDTO();
		lDTO.setSessionSeqNo(lMaster.getId().getSessionSeqNo());
		lDTO.setParentSessionSeqNo(lMaster.getId().getParentSessionSeqNo());
		return lDTO;
	}

	private LMSSessionDetails setLMSSessionDetails(LMSSessionDetailsDTO lDTO) {
		LMSSessionDetails lMaster = new LMSSessionDetails();
		return lMaster;
	}
}
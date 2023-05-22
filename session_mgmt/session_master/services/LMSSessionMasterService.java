package session_mgmt.session_master.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import session_mgmt.session_master.model.dto.LMSSessionMasterDTO;
import session_mgmt.session_master.model.master.LMSSessionMaster;
import session_mgmt.session_master.model.repo.LMSSessionMasterRepo;

@Service("lmsSessionMasterServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class LMSSessionMasterService implements I_LMSSessionMasterService 
{

	//private static final Logger logger = LoggerFactory.getLogger(LMSSessionMaster_Controller.class);
	
	@Autowired
    private LMSSessionMasterRepo lmsSessionMasterRepo;

	@Override
    public ArrayList<LMSSessionMasterDTO> getAllSessions() 
    {
    	ArrayList<LMSSessionMasterDTO> courseMasterDTOs = new ArrayList<LMSSessionMasterDTO>();
    	ArrayList<LMSSessionMaster> courseMasterOpts =  (ArrayList<LMSSessionMaster>) lmsSessionMasterRepo.findAll();
    	    	
    	if(courseMasterOpts!=null)
    	{
    		courseMasterDTOs = getSessionMasterDtos(courseMasterOpts);
    	}
    	else
    	{
    		courseMasterDTOs= null;    	
    	}
            	
        return courseMasterDTOs;
    }
    
    @Override
    public ArrayList<LMSSessionMasterDTO> getSelectSessions(ArrayList<Long> ids)
	{
		ArrayList<LMSSessionMaster> lMasters = lmsSessionMasterRepo.getSelectSessions(ids);
		ArrayList<LMSSessionMasterDTO> LMSSessionMasterDTOs = new ArrayList<LMSSessionMasterDTO>();
		LMSSessionMasterDTO LMSSessionMasterDTO = null;

		if (lMasters != null) 
		{
		LMSSessionMasterDTOs = getSessionMasterDtos(lMasters);				
		}
		
		return LMSSessionMasterDTOs;
	}

    @Override
    public ArrayList<LMSSessionMasterDTO> getSelectSessionsByInst(ArrayList<Long> ids)
	{
		ArrayList<LMSSessionMaster> lMasters = lmsSessionMasterRepo.getSelectSessionsByInst(ids);
		ArrayList<LMSSessionMasterDTO> LMSSessionMasterDTOs = new ArrayList<LMSSessionMasterDTO>();
		LMSSessionMasterDTO LMSSessionMasterDTO = null;

		if (lMasters != null) 
		{
		LMSSessionMasterDTOs = getSessionMasterDtos(lMasters);				
		}
		
		return LMSSessionMasterDTOs;
	}

    @Override
    public ArrayList<LMSSessionMasterDTO> getSelectSessionsByBatches(ArrayList<Long> ids)
	{
		ArrayList<LMSSessionMaster> lMasters = lmsSessionMasterRepo.getSelectSessionsByBatches(ids);
		ArrayList<LMSSessionMasterDTO> LMSSessionMasterDTOs = new ArrayList<LMSSessionMasterDTO>();
		LMSSessionMasterDTO LMSSessionMasterDTO = null;

		if (lMasters != null) 
		{
		LMSSessionMasterDTOs = getSessionMasterDtos(lMasters);				
		}
		
		return LMSSessionMasterDTOs;
	}
    
    @Override
    public ArrayList<LMSSessionMasterDTO> getSelectSessionsBetweenTimes(ArrayList<Long> sessioncSeqNos, String frDtTm, String toDtTm)
	{
		LMSSessionMaster lMaster = new LMSSessionMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = LocalDateTime.parse(frDtTm, formatter);
		LocalDateTime dateTo = LocalDateTime.parse(toDtTm, formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);
		    	
    	ArrayList<LMSSessionMaster> lMasters = lmsSessionMasterRepo.getSelectSessionsBetweenTimes(sessioncSeqNos, ts_Fr, ts_To);
		ArrayList<LMSSessionMasterDTO> LMSSessionMasterDTOs = new ArrayList<LMSSessionMasterDTO>();
		
		if (lMasters != null) 
		{
		LMSSessionMasterDTOs = getSessionMasterDtos(lMasters);				
		}
		
		return LMSSessionMasterDTOs;
	}
    
    public LMSSessionMasterDTO newLMSSessionMaster(LMSSessionMasterDTO lms_SessionMasterDTO) 
    {    	
    LMSSessionMaster lms_SessionMaster = lmsSessionMasterRepo.save(setCustomerMaster(lms_SessionMasterDTO));
    LMSSessionMasterDTO courseMasterDTO2 = getSessionMasterDto(lms_SessionMaster);
	return courseMasterDTO2;
    }

    public void updLMSSessionMaster(LMSSessionMasterDTO lMSSessionMasterDTO) 
    {
    LMSSessionMaster lms_SessionMaster = setCustomerMaster(lMSSessionMasterDTO);
    lmsSessionMasterRepo.save(lms_SessionMaster);    
    }
    
    public void delSelectSessions(ArrayList<Long> DocumentSeqNos) 
    {
    lmsSessionMasterRepo.delSelectSessions(DocumentSeqNos);
    }

    public void delSelectSessionsByInst(ArrayList<Long> ids) 
    {
    lmsSessionMasterRepo.delSelectSessionsByInst(ids);
    }
    
    public void delSelectSessionsByCourses(ArrayList<Long> ids) 
    {
    lmsSessionMasterRepo.delSelectSessionsByCourses(ids);
    }
    
    public void delSelectSessionsByBatches(ArrayList<Long> ids) 
    {
    lmsSessionMasterRepo.delSelectSessionsByBatches(ids);
    }
   
    public void delAllLMSSessionMasters() 
    {
    lmsSessionMasterRepo.deleteAll();
    }
    
    private ArrayList<LMSSessionMasterDTO> getSessionMasterDtos(ArrayList<LMSSessionMaster> lms_SessionMasters) 
	{
		LMSSessionMasterDTO courseMasterDTO = null;
		ArrayList<LMSSessionMasterDTO> courseMasterDTOs = new ArrayList<LMSSessionMasterDTO>(); 
		
		for(int i=0; i<lms_SessionMasters.size();i++)
		{		
		courseMasterDTO = getSessionMasterDto(lms_SessionMasters.get(i));
		courseMasterDTOs.add(courseMasterDTO);
		}		
		return courseMasterDTOs;
	}
	
	
	private LMSSessionMasterDTO getSessionMasterDto(LMSSessionMaster sessionMaster) 
	{
		LMSSessionMasterDTO lDTO = new LMSSessionMasterDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");				
		lDTO.setFromDttim(formatter.format(sessionMaster.getFromDttim().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(sessionMaster.getToDttm().toLocalDateTime()));
		lDTO.setInstituteSeqNo(sessionMaster.getInstituteSeqNo());
		lDTO.setSessionId(sessionMaster.getSessionId());
		lDTO.setSessionSummary(sessionMaster.getSessionSummary());
		lDTO.setSessionSeqNo(sessionMaster.getSessionSeqNo());
		lDTO.setCourseSeqNo(sessionMaster.getCourseSeqNo());
		lDTO.setBatchSeqNo(sessionMaster.getBatchSeqNo());
		return lDTO;
		}
	
	private LMSSessionMaster setCustomerMaster(LMSSessionMasterDTO sMasterDTO) 
	{
		LMSSessionMaster lms_sLmsSessionMaster	=	new	LMSSessionMaster();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateFr = LocalDateTime.parse(sMasterDTO.getFromDttim(), formatter);
		LocalDateTime dateTo = LocalDateTime.parse(sMasterDTO.getToDttm(), formatter);
		Timestamp ts_Fr = Timestamp.valueOf(dateFr);
		Timestamp ts_To = Timestamp.valueOf(dateTo);		
		lms_sLmsSessionMaster.setSessionSummary(sMasterDTO.getSessionSummary());		
		lms_sLmsSessionMaster.setFromDttim(ts_Fr);
		lms_sLmsSessionMaster.setToDttm(ts_To);
		lms_sLmsSessionMaster.setCourseSeqNo(sMasterDTO.getCourseSeqNo());
		lms_sLmsSessionMaster.setInstituteSeqNo(sMasterDTO.getInstituteSeqNo());
		lms_sLmsSessionMaster.setSessionId(sMasterDTO.getSessionId());
		lms_sLmsSessionMaster.setBatchSeqNo(sMasterDTO.getBatchSeqNo());
		return lms_sLmsSessionMaster;
	}

}

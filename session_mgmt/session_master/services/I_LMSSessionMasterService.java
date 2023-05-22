package session_mgmt.session_master.services;

import java.util.ArrayList;
import session_mgmt.session_master.model.dto.LMSSessionMasterDTO;

public interface I_LMSSessionMasterService 
{
	abstract public LMSSessionMasterDTO newLMSSessionMaster(LMSSessionMasterDTO resourceMediaDetailsDTO);
	abstract public ArrayList<LMSSessionMasterDTO> getAllSessions();
	abstract public ArrayList<LMSSessionMasterDTO> getSelectSessions(ArrayList<Long> ids);	
	abstract public ArrayList<LMSSessionMasterDTO> getSelectSessionsByInst(ArrayList<Long> ids);
	abstract public ArrayList<LMSSessionMasterDTO> getSelectSessionsByBatches(ArrayList<Long> ids);
	abstract public ArrayList<LMSSessionMasterDTO> getSelectSessionsBetweenTimes(ArrayList<Long> sessioncSeqNos, String frDtTm, String toDtTm);
	abstract public void updLMSSessionMaster(LMSSessionMasterDTO LMSSessionMasterDTO);	
	abstract public void delAllLMSSessionMasters();	
	abstract public void delSelectSessions( ArrayList<Long> ids);
	abstract public void delSelectSessionsByInst(ArrayList<Long> ids);
	abstract public void delSelectSessionsByCourses(ArrayList<Long> ids);
	abstract public void delSelectSessionsByBatches(ArrayList<Long> ids);
}
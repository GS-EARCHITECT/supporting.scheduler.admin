package session_mgmt.session_details.services;

import java.util.ArrayList;

import session_mgmt.session_details.model.dto.LMSSessionDetailsDTO;

public interface I_LMSSessionDetailsService
{
    abstract public LMSSessionDetailsDTO newLMSSessionDetails(LMSSessionDetailsDTO sessionDetailsDTO);
    abstract public ArrayList<LMSSessionDetailsDTO> getAllLMSSessionDetailss();    
    abstract public ArrayList<LMSSessionDetailsDTO> getSelectSessions(ArrayList<Long> ids);
    abstract public ArrayList<LMSSessionDetailsDTO> getSelectSessionsByParents(ArrayList<Long> ids);
    abstract public void updLMSSessionDetails(LMSSessionDetailsDTO LMSSessionDetailsDTO); 
    abstract public void delSelectLMSSessions(ArrayList<Long> DocumentSeqNos);
    abstract public void delAllLMSSessionDetailss();
}
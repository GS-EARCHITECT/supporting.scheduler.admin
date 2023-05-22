package scheduler_mgmt.services;

import java.util.ArrayList;
import scheduler_mgmt.model.dto.SchedulerDetailDTO;
import scheduler_mgmt.model.dto.SchedulerMasterDTO;

public interface I_SchedulerMasterService 
{
abstract public String createSchedules();	
abstract public SchedulerMasterDTO newSchedulerMaster(SchedulerMasterDTO promoMasterDTO);
abstract public ArrayList<SchedulerDetailDTO> getSelectSchedulesForCompanyTargetRule(Long cSeqNo, Long rSeqNo, Long tSeqNo);
abstract public ArrayList<SchedulerDetailDTO> getSelectSchedulesForRuleLine(Long rSeqNo);
abstract public ArrayList<SchedulerMasterDTO> getAllSchedulerMasters();    
abstract public SchedulerMasterDTO getSchedulerMasterById(Long promoSeqNo);
abstract public void updSchedulerMaster(SchedulerMasterDTO SchedulerMasterDTO);
abstract public void delSchedulerMaster(Long DocumentSeqNo);
abstract public void delAllSchedulerMasters();    
abstract public void delSelectSchedulers(ArrayList<Long> ids);
}
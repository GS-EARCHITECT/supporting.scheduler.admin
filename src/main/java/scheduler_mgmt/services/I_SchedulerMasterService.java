package scheduler_mgmt.services;

import java.util.ArrayList;
import scheduler_mgmt.model.dto.SchedulerDetail_DTO;
import scheduler_mgmt.model.dto.SchedulerMaster_DTO;

public interface I_SchedulerMasterService 
{
abstract public String createSchedules();	
abstract public SchedulerMaster_DTO newSchedulerMaster(SchedulerMaster_DTO promoMasterDTO);
abstract public ArrayList<SchedulerDetail_DTO> getSelectSchedulesForCompanyTargetRule(Long cSeqNo, Long rSeqNo, Long tSeqNo);
abstract public ArrayList<SchedulerDetail_DTO> getSelectSchedulesForRuleLine(Long rSeqNo);
abstract public ArrayList<SchedulerMaster_DTO> getAllSchedulerMasters();    
abstract public SchedulerMaster_DTO getSchedulerMasterById(Long promoSeqNo);
abstract public void updSchedulerMaster(SchedulerMaster_DTO SchedulerMaster_DTO);
abstract public void delSchedulerMaster(Long DocumentSeqNo);
abstract public void delAllSchedulerMasters();    
abstract public void delSelectSchedulers(ArrayList<Long> ids);
}
package scheduler_mgmt.services;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import scheduler_mgmt.model.dto.SchedulerDetail_DTO;
import scheduler_mgmt.model.dto.SchedulerMaster_DTO;
import scheduler_mgmt.model.master.SchedulerDetail;
import scheduler_mgmt.model.master.SchedulerDetailPK;
import scheduler_mgmt.model.master.SchedulerMaster;
import scheduler_mgmt.model.repo.SchedulerDetailRepo;
import scheduler_mgmt.model.repo.SchedulerMasterRepo;
import scheduler_rules_mgmt.model.master.RuleMaster;
import scheduler_rules_mgmt.model.repo.RuleMasterRepo;

@Service("schedulerMasterServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class SchedulerMasterService implements I_SchedulerMasterService {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerMasterService.class);

	@Autowired
	private SchedulerMasterRepo schedulerMasterRepo;

	@Autowired
	private RuleMasterRepo ruleMasterRepo;

	@Autowired
	private SchedulerDetailRepo schedulerDetailRepo;

	@Scheduled(fixedRate = 10000)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public String createSchedules() 
	{
		ArrayList<SchedulerMaster> schedulerMasters = schedulerMasterRepo.getSchedules();

		if (schedulerMasters != null) {
			SchedulerMaster schedulerMaster = null;
			ArrayList<SchedulerDetail_DTO> schedulerDetailDTOs = null;
			Class<?> classRef = null;
			Method method = null;
			String methodName = null;
			String className = null;
			String packageName = null;
			Optional<RuleMaster> ruleMaster = null;
			ArrayList<SchedulerDetail_DTO> schedulerDetailDTOs2 = null;
			Float cntRecs = (float) 0; 

			for (int ctr = 0; ctr < schedulerMasters.size(); ctr++) 
			{
				schedulerMaster = schedulerMasters.get(ctr);				
				ruleMaster = ruleMasterRepo.findById(schedulerMasters.get(ctr).getRuleSeqNo());

				if (ruleMaster.isPresent() && ruleMaster!=null)
				{					
					methodName = ruleMaster.get().getFunctionName().trim();
					className = ruleMaster.get().getClassName().trim();
					packageName = ruleMaster.get().getClassPackage().trim();
					className = packageName + '.' + className;				
					Object instance = null;

					try {
						classRef = Class.forName(className.trim());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						instance = classRef.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						method = classRef.getDeclaredMethod(methodName, SchedulerMaster.class);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {						
						schedulerDetailDTOs2 = (ArrayList<SchedulerDetail_DTO>) method.invoke(instance, schedulerMaster);						
						
						if (schedulerDetailDTOs2 != null) 
						{
							cntRecs =	schedulerDetailRepo.getCountOfSchedules(schedulerMaster.getRuleLineSeqNo());
							if(cntRecs>0)
							{						
							schedulerDetailRepo.delSchedulesForRuleLine(schedulerMaster.getRuleLineSeqNo());
							}
							for (int i = 0; i < schedulerDetailDTOs2.size(); i++) {								
								schedulerDetailRepo.save(setSchedulerDetail(schedulerDetailDTOs2.get(i)));
							}
						}
						schedulerMasterRepo.updateScheduleStatus('Y', schedulerMaster.getRuleLineSeqNo());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Throwable actualException = e.getCause();
			            actualException.printStackTrace();
					}
				}
			}
		}
		return "";
	}

	public SchedulerMaster_DTO newSchedulerMaster(SchedulerMaster_DTO lMaster) 
	{
		logger.info("creating schedule for");
		logger.info("Comp :"+Long.toString(lMaster.getCompanySeqNo()));
		logger.info("Rule :"+Long.toString(lMaster.getRuleSeqNo()));		
		logger.info("From :"+lMaster.getFromDttm());
		logger.info("To :"+lMaster.getToDttm());
		SchedulerMaster_DTO lMaster2 = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime frdttm = LocalDateTime.parse(lMaster.getFromDttm(), formatter);
		LocalDateTime todttm = LocalDateTime.parse(lMaster.getToDttm(), formatter);
		Timestamp fdttm = Timestamp.valueOf(frdttm);
		Timestamp tdttm = Timestamp.valueOf(todttm);
		
		Long countChk = schedulerMasterRepo.checkIfSExists(lMaster.getCompanySeqNo(), lMaster.getTargetSeqNo(),
				lMaster.getRuleSeqNo(), fdttm, tdttm);

		if (countChk == 0) {			
			SchedulerMaster SchedulerMaster = schedulerMasterRepo.save(this.setSchedulerMaster(lMaster));
			lMaster2 = getSchedulerMaster_DTO(SchedulerMaster);
			logger.info("created schedule");
		}
		return lMaster2;
	}

	public ArrayList<SchedulerMaster_DTO> getAllSchedulerMasters() {
		ArrayList<SchedulerMaster> resourceList = (ArrayList<SchedulerMaster>) schedulerMasterRepo.findAll();
		ArrayList<SchedulerMaster_DTO> lMasterss = new ArrayList<SchedulerMaster_DTO>();
		lMasterss = resourceList != null ? this.getSchedulerMaster_DTOs(resourceList) : null;
		return lMasterss;
	}

	public SchedulerMaster_DTO getSchedulerMasterById(Long scheduleSeqNo) {
		Optional<SchedulerMaster> SchedulerMaster = schedulerMasterRepo.findById(scheduleSeqNo);
		SchedulerMaster_DTO lMasters = null;
		if (SchedulerMaster.isPresent()) {
			lMasters = SchedulerMaster != null ? this.getSchedulerMaster_DTO(SchedulerMaster.get()) : null;
		}
		return lMasters;
	}
	
	
	public ArrayList<SchedulerDetail_DTO> getSelectSchedulesForCompanyTargetRule(Long cSeqNo, Long rSeqNo, Long tSeqNo) 
	{
		ArrayList<SchedulerDetail> schedulerDetails = schedulerDetailRepo.getSelectSchedulesForCompanyTargetRule(cSeqNo, rSeqNo, tSeqNo);
		ArrayList<SchedulerDetail_DTO> lDetailDTOs = schedulerDetails != null ? this.getSchedulerDetailsDTOs(schedulerDetails) : null;		 		
		return lDetailDTOs;
	}
	
	public ArrayList<SchedulerDetail_DTO> getSelectSchedulesForRuleLine(Long rSeqNo) 
	{
		ArrayList<SchedulerDetail> schedulerDetails = schedulerDetailRepo.getSelectSchedulesForRuleLine(rSeqNo);
		ArrayList<SchedulerDetail_DTO> lDetailDTOs = schedulerDetails != null ? this.getSchedulerDetailsDTOs(schedulerDetails) : null;		 		
		return lDetailDTOs;
	}
	
	public void updSchedulerMaster(SchedulerMaster_DTO lMaster) {
		SchedulerMaster scheduleMaster = this.setSchedulerMaster(lMaster);
		if (schedulerMasterRepo.existsById(lMaster.getRuleLineSeqNo())) {
			scheduleMaster.setRuleSeqNo(lMaster.getRuleSeqNo());
			schedulerMasterRepo.save(scheduleMaster);
		}
		return;
	}

	public void delSchedulerMaster(Long resourceSeqNo) {
		if (schedulerMasterRepo.existsById(resourceSeqNo)) {
			schedulerMasterRepo.deleteById(resourceSeqNo);
		}
		return;
	}

	public void delAllSchedulerMasters() {
		schedulerMasterRepo.deleteAll();
	}

	public void delSelectSchedulers(ArrayList<Long> ids) {
		if (ids != null) {
			schedulerMasterRepo.delSelectSchedules(ids);
		}
	}
	
	private ArrayList<SchedulerDetail_DTO> getSchedulerDetailsDTOs(ArrayList<SchedulerDetail> lDetails) {
		SchedulerDetail_DTO lDTO = null;
		ArrayList<SchedulerDetail_DTO> lDetailDTOs2 = new ArrayList<SchedulerDetail_DTO>();
		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = getSchedulerDetailsDTO(lDetails.get(i));
			lDetailDTOs2.add(lDTO);
		}
		return lDetailDTOs2;
	}

	private SchedulerDetail_DTO getSchedulerDetailsDTO(SchedulerDetail lDetail)
	{
		SchedulerDetail_DTO lDTO = new SchedulerDetail_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		lDTO.setFrDttm(formatter.format(lDetail.getId().getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lDetail.getId().getToDttm().toLocalDateTime()));
		lDTO.setRuleLineSeqNo(lDetail.getId().getRuleLineSeqNo());		
		lDTO.setJobSeqNo(lDetail.getJobSeqNo());
		lDTO.setRemarks(lDetail.getRemarks());
		lDTO.setStatus(lDetail.getStatus());		
		return lDTO;
	}

	private ArrayList<SchedulerMaster_DTO> getSchedulerMaster_DTOs(ArrayList<SchedulerMaster> lMasters) {
		SchedulerMaster_DTO lDTO = null;
		ArrayList<SchedulerMaster_DTO> lMasterDTOs = new ArrayList<SchedulerMaster_DTO>();
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getSchedulerMaster_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private SchedulerMaster_DTO getSchedulerMaster_DTO(SchedulerMaster lMaster) {
		SchedulerMaster_DTO lDTO = new SchedulerMaster_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		lDTO.setFromDttm(formatter.format(lMaster.getFrDttm().toLocalDateTime()));
		lDTO.setToDttm(formatter.format(lMaster.getToDttm().toLocalDateTime()));
		lDTO.setRuleSeqNo(lMaster.getRuleSeqNo());
		lDTO.setFrtm(lMaster.getFrtm());
		lDTO.setTotm(lMaster.getTotm());
		lDTO.setCompanySeqNo(lMaster.getCompanySeqNo());
		lDTO.setJobTypeSeqNo(lMaster.getJobTypeSeqNo());
		lDTO.setRuleLineSeqNo(lMaster.getRuleLineSeqNo());
		lDTO.setScheduledFlag(lMaster.getScheduledFlag());
		lDTO.setScheduleData(lMaster.getScheduleData());
		lDTO.setTargetSeqNo(lMaster.getTargetSeqNo());
		return lDTO;
	}

	private SchedulerMaster setSchedulerMaster(SchedulerMaster_DTO lDTO) {
		SchedulerMaster lMaster = new SchedulerMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime frDateTime = LocalDateTime.parse(lDTO.getFromDttm(), formatter);
		LocalDateTime toDateTime = LocalDateTime.parse(lDTO.getToDttm(), formatter);
		lMaster.setFrDttm(Timestamp.valueOf(frDateTime));
		lMaster.setToDttm(Timestamp.valueOf(toDateTime));
		lMaster.setRuleSeqNo(lDTO.getRuleSeqNo());
		lMaster.setFrtm(lDTO.getFrtm());
		lMaster.setTotm(lDTO.getTotm());
		lMaster.setCompanySeqNo(lDTO.getCompanySeqNo());
		lMaster.setJobTypeSeqNo(lDTO.getJobTypeSeqNo());
		lMaster.setScheduledFlag(lDTO.getScheduledFlag());
		lMaster.setScheduleData(lDTO.getScheduleData());
		lMaster.setTargetSeqNo(lDTO.getTargetSeqNo());		
		return lMaster;
	}

	private SchedulerDetail setSchedulerDetail(SchedulerDetail_DTO sDTO) {
		SchedulerDetail sDetail = new SchedulerDetail();
		SchedulerDetailPK schedulerDetailPK = new SchedulerDetailPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime frDateTime = LocalDateTime.parse(sDTO.getFrDttm(), formatter);
		LocalDateTime toDateTime = LocalDateTime.parse(sDTO.getToDttm(), formatter);
		schedulerDetailPK.setFrDttm(Timestamp.valueOf(frDateTime));
		schedulerDetailPK.setToDttm(Timestamp.valueOf(toDateTime));
		schedulerDetailPK.setRuleLineSeqNo(sDTO.getRuleLineSeqNo());
		sDetail.setId(schedulerDetailPK);
		sDetail.setJobSeqNo(sDTO.getJobSeqNo());
		sDetail.setRemarks(sDTO.getRemarks());
		sDetail.setStatus(sDTO.getStatus());
		return sDetail;
	}

}

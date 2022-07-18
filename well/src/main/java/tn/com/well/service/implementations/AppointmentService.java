package tn.com.well.service.implementations;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.com.well.dao.AppointmentRepository;
import tn.com.well.dao.UserDao;
import tn.com.well.entity.Appointment;
import tn.com.well.entity.User;
import tn.com.well.service.IAppointmentService;
import tn.com.well.service.MailService;
import tn.com.well.service.SmsService;





@Slf4j
@Service
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository AppRepo;

	
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private MailService Ms;
	
	@Autowired
	private SmsService Ss;
	
	private  String UsrId = "";
	
	//@Autowired
    //private UtilsUser utilsUser;
	
	@Override
	public void addAppointment(Appointment app, String ExpertId) {
		//Date today = new Date();
		//User logg = utilsUser.getLoggedInUser();
        //app.setSender(logg);
		
		//log.info("today:"+today);
		User user = userRepo.findById(ExpertId).get();
		if(user == null)
		{
            log.info("Doctor not found, please verify chosen expert id.");
		}
		/*else if(app.getAppDate().before(today))
		{
			 log.info("chosen Appointment date is before current date, make sure to pick a valid date.");
		}*/
		else if(app.getAppHour().compareTo(user.getStart_hour())<0 || app.getAppHour().compareTo(user.getEnd_hour())>0)
		{
			log.info("chosen Appointment hour doesn't match Doctor's business hours.");
		}
		else if(user.isBan())
		{

			log.info("The following Doctor: "+user.getUserFirstName()+" "+user.getUserLastName()+" ,Specialized in: "+user.getSpecialty()+" is BANNED by Admin therefore no appointments can be taken.");
		}
		else{
		app.setAppHour(app.getAppHour());
		app.setAppDate(app.getAppDate());
		app.setAppStatus(app.getAppStatus().PENDING);
		app.setAppReport(app.getAppReport().NO_REPORT);
		app.setUser(user);
		AppRepo.save(app);
		log.info("Appointment with Doctor: "+user.getUserFirstName()+" "+user.getUserLastName()+ " of Specialty: " +user.getSpecialty()+ " is taken successfully.");
		
		try {
			Ms.sendAppEmail(user);
			
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		Ss.sendSms(user);
		log.info("Appointment Mail and SMS are sent to Doctor successfully.");
		
		}
	}

	@Override
	public void deleteAppointment(int id) {
		
		AppRepo.deleteById(id);
		log.info("Appointment removed.");
		
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> apps = (List<Appointment>) AppRepo.findAll();
		return apps;
	}

	@Override
	public void updateAppointment(Appointment app, int id) {
		Appointment a = AppRepo.findById(id).get();
		
		//User logg = utilsUser.getLoggedInUser();
        //app.setSender(logg);
		
        User user = app.getUser();
		
		a.setAppHour(app.getAppHour());
		a.setAppDate(app.getAppDate());
		a.setAppStatus(app.getAppStatus());
		if(user.isBan())
		{
			log.info("The following Doctor: " +user.getUserFirstName()+ " "+user.getUserLastName()+" of Specialty: "+user.getSpecialty()+" is BANNED by Admin therefore no appointments can be taken.");
		}
		else{
		a.setUser(user);
		}
		AppRepo.save(a);
		log.info("Appointment edited.");
		
	}

	@Override
	public void getMostAndLeastVisitedExpert() {
		
		
		String Exp_id = AppRepo.getMostVisitedExpertId();
		String Exp_id2= AppRepo.getLeastVisitedExpertId();
		
		int nbr = AppRepo.getMostVisitedExpertAppNbr();
		int nbr2 = AppRepo.getLeastVisitedExpertAppNbr();
		
		User user = userRepo.findById(Exp_id).get();
		User user2 = userRepo.findById(Exp_id2).get();
		
		
		log.info("The Most Visited Doctor is : "+user.getUserFirstName()+" "+user.getUserLastName()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" with a total of "+nbr+" appointments.");
		log.info("The Least Visited Doctor is : "+user2.getUserFirstName()+" "+user2.getUserLastName()+" ,Address: "+user2.getAddress()+" ,Phone: "+user2.getPhone()+" with a total of "+nbr2+" appointments.");
			
	}

	@Override
	public void banReportedExpert() {
		
	
		String UsrId = AppRepo.retrieveUserId();
		User user = userRepo.findById(UsrId).get();
		int count = AppRepo.getAppReportCountbyUserId(UsrId);
		
		if(count==1)
		{
			try {
			
				Ms.sendWarningEmail(user);
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
			Ss.sendWarningSms(user);
			log.info("BAN WARNING Mail and SMS are sent to Doctor successfully.");
			
			}
		else if(count>1)
		{
			AppRepo.updateBan(UsrId);
			log.info("Reported Doctor: "+user.getUserFirstName()+" "+user.getUserLastName()+ " of Specialty: "+user.getSpecialty()+" ,Address: "+user.getAddress()+" ,Phone: "+user.getPhone()+" is BANNED by Admin.");
			
			AppRepo.DeleteAppByUsrId(UsrId);
			
			try {
				
				Ms.sendBanEmail(user);
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
			Ss.sendBanSms(user);
			log.info("BAN Mail and SMS are sent to Doctor successfully.");
			
		}	
		else
		{
			log.info("No Appointment Reports found therefore no Doctors to ban.");
		}
	}
	
	
	@Override
	public void updateAppointmentReport(Appointment ap, Integer id) {
		Appointment app = AppRepo.findById(id).get();
		app.setAppReport(ap.getAppReport());
		app.setAppStatus(ap.getAppStatus().REPORTED);
		AppRepo.save(ap);
		log.info("Appointment Report inserted/edited");
	}

	@Override
	public void removeAppointmentReport(Appointment ap, Integer id) {
		Appointment app = AppRepo.findById(id).get();
		app.setAppReport(ap.getAppReport().NO_REPORT);
		app.setAppStatus(ap.getAppStatus().PENDING);
		AppRepo.save(ap);
		log.info("Appointment Report removed.");
	}

	

}



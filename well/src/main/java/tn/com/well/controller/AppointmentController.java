package tn.com.well.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.com.well.entity.Appointment;
import tn.com.well.service.implementations.AppointmentService;









@CrossOrigin
@RequestMapping("/appointment")
@RestController
public class AppointmentController {

	@Autowired
	AppointmentService as;
	
	//http://localhost:8089/Dahlia/appointment/add-appointment/8
	 @PostMapping("/add-appointment/{expert-id}")
		public void addAppointment(@RequestBody Appointment app,@PathVariable("expert-id") String ExpertId) {
		 as.addAppointment(app, ExpertId);
		}
	 
	//http://localhost:8089/Dahlia/appointment/retrieve-all-appointments
		 @GetMapping("/retrieve-all-appointments")
		 public List <Appointment> getAppointments(){
			 
			 List <Appointment> listApps = as.getAllAppointments();
			 return listApps;
		 }
		 
		//http://localhost:8089/Dahlia/review/retrieve-all-reviews
		 @GetMapping("/retrieve-most&least-visited-expert")
		 public void retrieveMostAndLeastVisitedExpert(){
			 as.getMostAndLeastVisitedExpert();
		 }
		 
		//http://localhost:8089/Dahlia/review/retrieve-all-reviews
		 @GetMapping("/ban-reported-expert")
		 public void banReportedExpert(){
			 as.banReportedExpert();
		 }
		 
	// http://localhost:8089/Dahlia/appointment/remove-appointment/{app-id}
		@DeleteMapping("/remove-appointment/{app-id}")
		public void removeAppointment(@PathVariable("app-id") Integer AppId) {
			as.deleteAppointment(AppId);
			}

	// http://localhost:8089/Dahlia/appointment/edit-appointment/{app-id}
		@PutMapping("/edit-appointment/{app-id}")
		public void editAppointment(@RequestBody Appointment app,@PathVariable("app-id") Integer AppId) {
			 as.updateAppointment(app, AppId);
			}
		
		
		
			 
		// http://localhost:8089/Dahlia/appointmentReport/remove-appointmentReport/{apprp-id}
			@DeleteMapping("/remove-appointmentReport/{app-id}")
			public void removeAppointmentReport(@RequestBody Appointment app,@PathVariable("app-id") Integer AppId) {
				as.removeAppointmentReport(app,AppId);
				}

		// http://localhost:8089/Dahlia/appointmentReport/edit-appointmentReport/{apprp-id}
			@PutMapping("/edit-appointmentReport/{app-id}")
			public void editAppointmentReport(@RequestBody Appointment app,@PathVariable("app-id") Integer AppId) {
				 as.updateAppointmentReport(app, AppId);
				}
		
		
		
}


package tn.com.well.service;

import java.util.List;

import tn.com.well.entity.Appointment;



public interface IAppointmentService {

	public void deleteAppointment(int id);
	public List<Appointment> getAllAppointments();
	public void updateAppointment(Appointment app, int id);
	public void addAppointment(Appointment app, String ExpertId);
	public void getMostAndLeastVisitedExpert();
	public void banReportedExpert();
	public void updateAppointmentReport(Appointment ap, Integer id);
	public void removeAppointmentReport(Appointment ap, Integer id);

}


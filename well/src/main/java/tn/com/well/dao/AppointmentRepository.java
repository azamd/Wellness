package tn.com.well.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.com.well.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {

	@Query(value ="SELECT appointment.user_id FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) ASC LIMIT 1",nativeQuery=true)
    public String getLeastVisitedExpertId();

	
	@Query(value ="SELECT appointment.user_id FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) DESC LIMIT 1",nativeQuery=true)
    public String getMostVisitedExpertId();
	
	@Query(value ="SELECT COUNT(appointment.app_id) FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) DESC LIMIT 1",nativeQuery=true)
    public int getMostVisitedExpertAppNbr();

	@Query(value ="SELECT COUNT(appointment.app_id) FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) ASC LIMIT 1",nativeQuery=true)
    public int getLeastVisitedExpertAppNbr();

	@Modifying
	@Transactional
	@Query(value="DELETE FROM appointment WHERE appointment.user_id = :usrId",nativeQuery=true)
	public void DeleteAppByUsrId(@Param("usrId") String usrId);
	
	
	@Query(value ="SELECT DISTINCT user_id FROM appointment LIMIT 1",nativeQuery=true)
    public String retrieveUserId();
	
	@Query(value ="SELECT COUNT(appointment_report_id) FROM appointment WHERE user_id = :usrId",nativeQuery=true)
	public int getAppReportCountbyUserId(@Param("usrId") String usrId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE user SET `ban`= 1 WHERE id = :usrId",nativeQuery=true)

	public void updateBan(@Param("usrId") String usrId);
	
}

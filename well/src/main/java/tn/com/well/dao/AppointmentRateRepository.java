package tn.com.well.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.com.well.entity.AppointmentRate;

@Repository
public interface AppointmentRateRepository extends CrudRepository<AppointmentRate, Long>{

}

package tn.com.well.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.com.well.entity.enums.Satisfaction;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRate {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private float score;
	    @Enumerated(EnumType.STRING)
	    private Satisfaction satisfaction;

	    /*@JsonIgnore
	    @OneToOne
	    @JoinColumn(name = "appointment_id")*/
	    @JsonIgnore
	    @OneToOne(mappedBy="appointmentRate",cascade = CascadeType.ALL)
	    private Appointment appointment;

}

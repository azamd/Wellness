package tn.com.well.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.com.well.entity.enums.AppointmentReport;
import tn.com.well.entity.enums.AppointmentStatus;
import tn.com.well.entity.AppointmentRate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AppId;

    private Integer AppHour;

    @Temporal(TemporalType.DATE)
    private Date AppDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus AppStatus;
    
    @Enumerated(EnumType.STRING)
    private AppointmentReport AppReport;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    private AppointmentRate appointmentRate;
    
   
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private User sender;

}



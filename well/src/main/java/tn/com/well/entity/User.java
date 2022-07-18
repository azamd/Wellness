package tn.com.well.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.com.well.entity.enums.Specialty;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	
	@Id
    private String userId;
	
	private String username;
	
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    
    @Column(nullable=false ,length=120,unique=true)
    private String email;
    
    @Column(nullable=false,length=9)
    private String phone;

    
    @Column(nullable=false)
    private Date date_birth;
    
   
    private String address;
    
    
    private String city;
    
    
    private String state;
    
   
    private int zipCode;
    private int start_hour; //?? time ? e.g: 19:50
    private int end_hour; //?? time ? e.g: 19:50
    
    private boolean ban;
    

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    
    )
    private Set<Role> role;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Appointment> appointments;


   /* @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Review> reviews;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") 
    private List<Report> reports;*/
    
    
    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}

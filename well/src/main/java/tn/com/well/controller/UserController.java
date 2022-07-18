package tn.com.well.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;
import tn.com.well.entity.Role;
import tn.com.well.entity.User;
import tn.com.well.service.implementations.UserService;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

   /* @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }*/

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
    
    
    
    @GetMapping("/full_list")
    public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
        
    }
    
    
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/role/save").toUriString());
    	return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    
    
    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/user/save").toUriString());
    	return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
    	
    	userService.addRoleToUser(form.getUsername(),form.getRoleName());
    	return ResponseEntity.ok().build();
    }
    
    
}

@Data
class RoleToUserForm{
	private String username;
	private String roleName;
}


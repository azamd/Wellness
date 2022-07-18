package tn.com.well.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tn.com.well.dao.RoleDao;
import tn.com.well.dao.UserDao;
import tn.com.well.entity.Role;
import tn.com.well.entity.User;
import tn.com.well.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

  /* public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("For users that will navigate into the app");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserId("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


    }*/

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	@Override
	public User saveUser(User user) {
		log.info("user saved.");
		return userDao.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("role saved.");
		return roleDao.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user= userDao.findByUsername(username);
		Role role = roleDao.findByRoleName(roleName);
		log.info("role: "+role.getRoleName()+" assigned to user: "+user.getUserFirstName()+" "+user.getUserLastName()+", with Id: "+user.getUserId());
		user.getRole().add(role);		
	}

	@Override
	public User getUser(String username) {
		User user= userDao.findByUsername(username);
		log.info("Fetching the following user: "+user);
		return user;
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching users' full list: ");
		return (List<User>) userDao.findAll();
	}
}

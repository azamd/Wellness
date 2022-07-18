package tn.com.well.service;

import java.util.List;

import tn.com.well.entity.Role;
import tn.com.well.entity.User;

public interface IUserService {
 User saveUser(User user);
 Role saveRole(Role role);
 void addRoleToUser(String username, String roleName);
 User getUser(String username);
 List<User> getUsers();
}

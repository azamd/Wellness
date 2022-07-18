package tn.com.well.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.com.well.entity.Role;
import tn.com.well.entity.User;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

	Role findByRoleName(String Rolename);

}

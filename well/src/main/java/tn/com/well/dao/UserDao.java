package tn.com.well.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.com.well.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	User findByUsername(String username);
}

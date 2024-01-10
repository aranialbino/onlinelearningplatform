package com.shikharani.onlinelearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shikharani.onlinelearningplatform.model.User;

/**
 * This method is used for fetch User Object.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = :email")
	User findByUsername(String email);

}

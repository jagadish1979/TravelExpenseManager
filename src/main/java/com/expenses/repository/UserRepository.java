package com.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenses.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, char[] password);

}

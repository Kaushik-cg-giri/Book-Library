package com.book_management.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book_management.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
	
	Optional<Users> findByUsersName(String name);

}

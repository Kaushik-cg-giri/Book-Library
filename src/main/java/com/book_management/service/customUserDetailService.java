package com.book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.book_management.entity.Users;
import com.book_management.repository.UserRepository;

  @Service 
  public class customUserDetailService implements UserDetailsService{
  
  @Autowired 
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	  Users user = userRepository.findByUsersName(username)
			  					.orElseThrow(()->  new UsernameNotFoundException("User not found in DB"));
	  return User.builder()
			  	.username(user.getusersName())
			  	.password(user.getusersPswrd())
			  	.roles(user.getusersRole())
			  	.build();
  }
  
  }
 

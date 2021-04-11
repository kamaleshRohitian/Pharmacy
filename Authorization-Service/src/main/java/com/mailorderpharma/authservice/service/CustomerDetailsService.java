package com.mailorderpharma.authservice.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mailorderpharma.authservice.dao.UserDAO;
import com.mailorderpharma.authservice.entity.UserData;

import lombok.extern.slf4j.Slf4j;



/**Service class*/
@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userdao;

	/**
	 * @param String
	 * @return User 
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String uid) {
		
		try
		{
			UserData custuser = userdao.findById(uid).orElse(null);
			if(custuser != null) {
				custuser.getUname();
			}
			return new User(custuser.getUserid(), custuser.getUpassword(), new ArrayList<>());
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
			
		
		
	}

	public UserData addUser(UserData userlogincredentials) throws  UserIdNotAvailableException{
		 Optional<UserData> udata=checkUser(userlogincredentials);
		 UserData user=new UserData();
		 if(udata.isEmpty())
		 {
			user=userdao.save(userlogincredentials);
			return user;
		 }
		 else
		 {
			 throw new UserIdNotAvailableException("User Id is already available");
		 }

	}
	//for check userid 
	public Optional<UserData> checkUser(UserData userlogincredentials)
	{
		try {
			Optional<UserData> u=userdao.findById(userlogincredentials.getUserid());
			return u;
		}
		catch(Exception e)
		{
			throw new UserIdNotAvailableException("User Id is not available");
		}
	}

}

package com.naga.spring.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naga.spring.rest.exception.ResourceNotFoundException;
import com.naga.spring.rest.model.User;

/**
 * @author Vedasri
 *
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/users")
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	public UserController() {
		this.users = buildUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET,
				produces= {MediaType.APPLICATION_JSON_VALUE})	
	public List<User> getUsers(){
		return this.users;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET,
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public User getUser(@PathVariable("id") Long id) {
		User getUser = this.users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
		if( getUser == null ) {
			throw new ResourceNotFoundException(id, "User Not found");
		}
		return getUser;
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes= {MediaType.APPLICATION_JSON_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE})
	public User saveUser(@Valid @RequestBody User user) {
		Long nextId = 0L;
		if(this.users.size() != 0 ) {
			User lastUser = this.users.stream().skip(this.users.size()-1).findFirst().orElse(null);
			nextId = lastUser.getId()+1;
		}
		user.setId(nextId);
		this.users.add(user);
		return user;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		User modifiedUser = this.users.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(modifiedUser.getEmail());
		return modifiedUser;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id) {
		User deleteUser = this.users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
		if(deleteUser != null) {
			this.users.remove(deleteUser);
			return true;
		} else {
			return false;
		}
	}
	
	List<User> buildUsers(){
		List<User> users = new ArrayList<User>();
		
		User user = new User(1L,"Naga","Rama","NRama@email.com");
		users.add(user);
		user = new User(2L,"Paru","Rama","PRama@email.com");
		users.add(user);
		user = new User(3L,"Veda","Rama","VRama@email.com");
		users.add(user);
		user = new User(4L,"Yaj","Rama","YRama@email.com");
		users.add(user);
		
		return users;
	}
	
	User buildUser(Long id, String fname,String lname,String email){
		return new User(id,fname,lname,email);		
	}
}


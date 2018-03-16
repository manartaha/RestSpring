package com.mtaha;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	private List<User> users=new ArrayList<User>();
	
	// Constructeur de la classe
	public UserController() {
		this.users=buildUsers();
	}
	
	// Constructeur d'une liste de USERS
	List<User> buildUsers(){
		
		List<User> users=new ArrayList<User>();
		User first=buildUser(1,"Manar","Taha","taha@gmail.com");
		User second=buildUser(2,"Manar","Zakaria","zakaria@gmail.com");
		User third=buildUser(3,"Laaounate","Soufiane","Soufiane@gmail.com");
		User fourth=buildUser(4,"Najmi","Chaymaa","Chaymaa@gmail.com");
		User fifth=buildUser(5,"Ajjane","Safae","Safae@gmail.com");
		User sixth=buildUser(6,"Messi","Lionel","Lionel@gmail.com");
		
		users.add(first);
		users.add(second);
		users.add(third);
		users.add(fourth);
		users.add(fifth);
		users.add(sixth);
		
		return users;
	}
	// Constructeur d'un USER
	User buildUser(long id,String last_name,String first_name,String email) {
		
		return new User(id, last_name, first_name, email);
		
	}
	//*********************************************** Partie REST ****************************************************
	// Recupération de la liste des users
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getUsers() {
		return this.users;
	}
	
	// Recupération d'un seul User
	@RequestMapping(method=RequestMethod.GET, value= "/{id}")
	public User getUser(@PathVariable long id) {
				
		return this.users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
		
	}
	
	// Suppression d'un user
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public boolean deleteUser(@PathVariable long id) {
		
		User deletedUser=this.users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
		if (deletedUser!=null) {
			this.users.remove(deletedUser);
			return true;
		}
		return false;
	}

	//Ajouter un nouvel utilisateur
	@RequestMapping(method=RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		long new_id=0;
		if (this.users.size()!=0) {
			User last_user=this.users.stream().skip(this.users.size()-1).findFirst().orElse(null);
			new_id=last_user.getId()+1;
		}
		user.setId(new_id);
		this.users.add(user);
		return user;
	}
	
	// Modifier un utilisateur
	@RequestMapping(method=RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		
		User updatedUser = this.users.stream().filter(u->u.getId()==user.getId()).findFirst().orElse(null);
		
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setEmail(user.getEmail());
		
		return updatedUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

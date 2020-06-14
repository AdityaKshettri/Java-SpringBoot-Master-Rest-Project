package JavaRestMaster.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JavaRestMaster.entity.User;
import JavaRestMaster.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController 
{	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<?> authDetails()
	{
		User currentUser = userService.getCurrentUser();
		Map<Object, Object> model = new HashMap<>();
		model.put("username", currentUser.getUsername());
		model.put("roles", currentUser.getRoles());
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(model);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody User reqBody)
	{
		User user = userService.signin(reqBody.getUsername(), reqBody.getPassword());
		if(user == null) 
		{
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("The given user does not exist.");
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Successfully logged in... Welcome " + user.getUsername());
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User reqBody)
	{
		User newUser = userService.signup(reqBody.getUsername(), reqBody.getPassword());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("User " + newUser.getUsername() + " Successfully created." );
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout()
	{
		userService.logout();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("User successfully logged out");
	}
}

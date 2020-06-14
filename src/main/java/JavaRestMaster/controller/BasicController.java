package JavaRestMaster.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JavaRestMaster.entity.Basic;
import JavaRestMaster.entity.User;
import JavaRestMaster.service.BasicService;
import JavaRestMaster.service.UserService;

@RestController
@RequestMapping("/basics")
public class BasicController 
{
	@Autowired
	private BasicService basicService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<?> all() {
		List<Basic> basics = basicService.findAll();
		if(basics.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No basic found");
		}
		return ResponseEntity.ok(basics);
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Basic basicData, HttpServletRequest request)
	{
		basicData.setUser(userService.getCurrentUser());
		Basic theBasic = basicService.save(basicData);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(theBasic);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") int id) 
	{
		Optional<Basic> theBasic = basicService.findById(id);
		if(theBasic.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(theBasic);
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Basic form) 
	{
        Optional<Basic> theBasic = basicService.findById(id);
        if(theBasic.isPresent()) {
        	form.setId(id);
        	Basic updatedBasic = basicService.save(form);
        	return ResponseEntity
    				.status(HttpStatus.OK)
    				.body(updatedBasic);
		}
        return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id)
	{
		Optional<Basic> theBasic = basicService.findById(id);
        if(theBasic.isPresent()) {
        	basicService.deleteById(id);
            return ResponseEntity
            		.status(HttpStatus.OK)
            		.body("Successfully Deleted");
        }
        return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Basic not found at given id");
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> findBasicByCurrentUser()
	{
		User currentUser = userService.getCurrentUser();
		List<Basic> basics = basicService.findByUser(currentUser);
		if(basics.isEmpty()) 
		{
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No Basic present for current user.");
		}
		return ResponseEntity.ok(basics);
	}
}

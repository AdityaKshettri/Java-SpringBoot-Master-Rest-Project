package JavaRestMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import JavaRestMaster.entity.Basic;
import JavaRestMaster.entity.User;
import JavaRestMaster.service.BasicService;
import JavaRestMaster.service.UserService;

@SpringBootApplication
public class JavaRestMasterApplication implements CommandLineRunner
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private BasicService basicService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaRestMasterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{		
		//defining admin login details
		User admin = new User("admin", passwordEncoder.encode("password"), "USER", "ADMIN"); 
		userService.save(admin);
		
		//defining admin basic details
		Basic basic = new Basic("basic", admin);
		basicService.save(basic);
	}
}

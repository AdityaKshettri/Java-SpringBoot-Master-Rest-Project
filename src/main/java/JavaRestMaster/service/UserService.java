package JavaRestMaster.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import JavaRestMaster.entity.User;
import JavaRestMaster.repository.UserRepository;

@Service
public class UserService implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Stream.of(user.getRoles())
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()));
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User getCurrentUser()
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUsername(username);
	}
	
	@Transactional
	public User signup(String username, String password)
	{
		User user = new User(username, passwordEncoder.encode(password), "USER");
		return save(user);
	}
	
	@Transactional
	public void logout() {
		SecurityContextHolder.clearContext();
	}
	
	@Transactional
	public User signin(String username, String password)
	{
		User user = findByUsername(username);
		if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
			return null;
		}
		UsernamePasswordAuthenticationToken authReq = new 
				UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authReq);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return user;
	}
}

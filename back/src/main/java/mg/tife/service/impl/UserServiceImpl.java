package mg.tife.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mg.tife.entity.User;
import mg.tife.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> res =  userRepository.findByUsername(username);
		if(res.isEmpty()) throw new UsernameNotFoundException(username);
		return res.get();
	}
}
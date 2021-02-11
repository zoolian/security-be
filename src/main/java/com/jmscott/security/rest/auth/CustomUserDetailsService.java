package com.jmscott.security.rest.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmscott.security.rest.model.Password;
import com.jmscott.security.rest.model.Role;
import com.jmscott.security.rest.model.User;
import com.jmscott.security.rest.repository.PasswordRepository;
import com.jmscott.security.rest.repository.RoleRepository;
import com.jmscott.security.rest.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
  
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordRepository passwordRepository;
  
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = userRepository.findByUsername(username);
		if(user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			if(!user.isEnabled()) {
				return null;
			}
			return buildUserForAuthentication(user, authorities);
		} else { throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username)); }
	}

	public void saveUser(User user, Password password) {
		password.setPassword(bCryptPasswordEncoder.encode(password.getPassword()));
		user.setEnabled(true);
		if(user.getRoles().isEmpty()) {
			Role userRole = roleRepository.findByName("VIEWER");
			user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		}
		userRepository.save(user);
		passwordRepository.save(password);
	}
	
	public void savePassword(Password password) {
		password.setPassword(bCryptPasswordEncoder.encode(password.getPassword()));
		passwordRepository.save(password);
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	private JwtUserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		
		return new JwtUserDetails(user.getId(), user.getUsername(), passwordRepository.findByPersonId(user.getId()).getPassword(), authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Collection<Role> collection) {
		Set<GrantedAuthority> roles = new HashSet<>();
		collection.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		List<GrantedAuthority> ga = new ArrayList<>(roles);
		return ga;
	}
}

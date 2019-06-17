package com.project.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.webapp.model.AppUser;
import com.project.webapp.model.Role;
import com.project.webapp.repository.AppUserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for (Role r : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(),
					grantedAuthorities);
		}
	}
}
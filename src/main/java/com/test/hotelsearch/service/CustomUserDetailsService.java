package com.test.hotelsearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.test.hotelsearch.dto.UserDTO;
import com.test.hotelsearch.entity.Permission;
import com.test.hotelsearch.entity.Role;
import com.test.hotelsearch.entity.User;
import com.test.hotelsearch.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userBean = userRepo.findByUsername(username);
		
		if (userBean == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
//		return new org.springframework.security.core.userdetails.User(userBean.getUsername(), userBean.getPassword(),
//				new ArrayList<>());
		
		return new org.springframework.security.core.userdetails.User(userBean.getUsername(), userBean.getPassword(),
				getAuthorities(userBean));
		
	}
	
	
	public List<GrantedAuthority> getAuthorities(User user)
	{
		
		Set<Role> roleList = user.getRoles();
		List<GrantedAuthority> authorities=new ArrayList<>();
		for(Role role : roleList)
		{
			for(Permission permission :role.getPermissions())
			{
				authorities.add(new SimpleGrantedAuthority(permission.getName()));
			}
			
		}
		
		return authorities;
		
	}
	
	public UserDTO saveNewUser(User userBean)
	{
		User savedBean = userRepo.save(userBean);
		return savedBean.convertToDTO();
	}
	
	public boolean saveListOfUsers(List<User> userBeanList)
	{
		List<User> savedBean = userRepo.saveAll(userBeanList);
		return !savedBean.isEmpty();
	}
	
//	
//	authorities = role.getPermissions().stream()
//			.map(userRole -> new SimpleGrantedAuthority(userRole.getName()))
//			.collect(Collectors.toList());

}

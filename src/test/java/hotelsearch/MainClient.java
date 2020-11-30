package hotelsearch;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.test.hotelsearch.entity.Permission;
import com.test.hotelsearch.entity.Role;
import com.test.hotelsearch.entity.User;
import com.test.hotelsearch.repository.UserRepository;

@Component
public class MainClient implements ApplicationContextAware{

	private static ApplicationContext appContext;
	
	@Autowired
	UserRepository userRepository;
	
	static UserRepository staticUserRepo;
	
	@Autowired
	public void setStaticRole(UserRepository userRepository)
	{
		MainClient.staticUserRepo = userRepository;
	}
	
	public static void main(String[] args) {
		
		
		
		Permission permission = new Permission();
		permission.setName("CREATE");
				
		Set<Permission> permissionSet = new HashSet<>();
		permissionSet.add(permission);
		
		
		Role role = new Role();
		role.setName("ADMIN");
		role.setPermissions(permissionSet);
		
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(role);
		
		User user1 = new User();
		user1.setEmail("akar122@gmail.com");
		user1.setUsername("Ajinkya");
		user1.setPassword(new BCryptPasswordEncoder().encode("ajinkya"));
		user1.setRoles(roleSet);
		
		UserRepository userRepo1 = appContext.getBean(UserRepository.class);
		userRepo1.save(user1);
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
		MainClient.appContext = applicationContext;
		
	}
}

package hotelsearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.test.hotelsearch.entity.Permission;
import com.test.hotelsearch.entity.Role;
import com.test.hotelsearch.entity.User;
import com.test.hotelsearch.repository.PermissionRepository;
import com.test.hotelsearch.repository.RoleRepository;
import com.test.hotelsearch.repository.UserRepository;

@Component
public class SetUpDataLoader implements
ApplicationListener<ContextRefreshedEvent>  {

	boolean alreadySetup = false;
	
	
	@Autowired
    private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PermissionRepository permisionRepo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		
		if (alreadySetup)
            return;
		
		Permission permission = createPermissionIfNotFound("CREATE");
		Set<Permission> permissionSet = new HashSet<>(Arrays.asList(permission));
		
		createRoleIfNotFound("ADMIN",permissionSet);
		
		Role adminRole = roleRepo.findByName("ADMIN");
		User user1 = new User();
		user1.setEmail("akar122@gmail.com");
		user1.setUsername("Ajinkya");
		user1.setPassword(new BCryptPasswordEncoder().encode("ajinkya"));
		user1.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
		
		userRepository.save(user1);
		
		alreadySetup = false;
		
	}
	
	
	@Transactional
    Permission createPermissionIfNotFound(String name) {
 
		Permission permission = permisionRepo.findByName(name);
        if (permission == null) {
        	permission = new Permission();
        	permission.setName(name);
        	permisionRepo.save(permission);
        }
        return permission;
    }
	
	@Transactional
    Role createRoleIfNotFound(String name,Set<Permission> permissionSet) {
 
		Role role = roleRepo.findByName(name);
        if (role == null) {
        	role = new Role();
        	role.setName(name);
        	role.setPermissions(permissionSet);
        	roleRepo.save(role);
        }
        return role;
    }

}

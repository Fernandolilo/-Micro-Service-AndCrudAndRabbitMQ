package com.systempro.user;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.systempro.user.entities.Permission;
import com.systempro.user.entities.User;
import com.systempro.user.repositories.PermissionRepository;
import com.systempro.user.repositories.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUser(userRepository, permissionRepository, passwordEncoder);
		};
	}
	

	private void initUser(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passEncode) {
		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		if(findPermission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		}else {
			permission = findPermission;
		}
		
		User admin = new User();
		admin.setUserName("Fernando");
		admin.setAccontNonExpired(true);
		admin.setAccontNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(passEncode.encode("1234"));
		admin.setPermissions(Arrays.asList(permission));
		
		User find = userRepository.findByUserName("Fernando");
		if(find == null) {
			userRepository.save(admin);
		}	
	}
}

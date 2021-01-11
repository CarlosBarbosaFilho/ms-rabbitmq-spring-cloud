package br.com.cbgomes.ws.ws_auth;

import br.com.cbgomes.ws.ws_auth.permission.entity.Permission;
import br.com.cbgomes.ws.ws_auth.permission.repository.PermissionRepository;
import br.com.cbgomes.ws.ws_auth.user.entity.User;
import br.com.cbgomes.ws.ws_auth.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class WsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsAuthApplication.class, args);
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        return args -> {
            this.initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
        };
    }

    private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        Permission permission = null;
        Permission findPermission = permissionRepository.permissionByDescription("Admin");
        if(findPermission == null){
            permission = new Permission();
            permission.setDescription("Admin");
            permission = permissionRepository.save(permission);
        }else {
            permission = findPermission;
        }

        User admin = new User();
        admin.setUserName("cbgomes");
        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.setEnabled(true);
        admin.setPassword(bCryptPasswordEncoder.encode("12345"));
        admin.setPermissions(Arrays.asList(permission));

        User findUser = userRepository.findByUserName("cbgomes");
        if(findUser == null){
            userRepository.save(admin);
        }
    }
}

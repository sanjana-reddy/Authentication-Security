package com.unitedtekinfo.authsecurity.config;

import com.unitedtekinfo.authsecurity.entity.Role;
import com.unitedtekinfo.authsecurity.enums.RoleType;
import com.unitedtekinfo.authsecurity.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        createRoleIfNotExists(RoleType.ROLE_ADMIN.name());
        createRoleIfNotExists(RoleType.ROLE_CUSTOMER.name());
        createRoleIfNotExists(RoleType.ROLE_MANAGER.name());

    }

    private void createRoleIfNotExists(String roleName) {

        if (roleRepository.findByName(roleName).isEmpty()) {

            Role role = new Role();
            role.setName(roleName);

            roleRepository.save(role);

            System.out.println(roleName + " created.");
        }

    }
}
package movie.tickets.controller;

import java.util.Set;
import javax.annotation.PostConstruct;
import movie.tickets.model.Role;
import movie.tickets.model.User;
import movie.tickets.service.RoleService;
import movie.tickets.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
    private final RoleService roleService;
    private final UserService userService;

    public DataInjector(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void injectData() {
        roleService.add(Role.of(USER));
        roleService.add(Role.of(ADMIN));
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("admin");
        admin.setRoles(Set.of(roleService.getRoleByName(ADMIN)));
        User user = new User();
        user.setEmail("user");
        user.setPassword("user");
        user.setRoles(Set.of(roleService.getRoleByName(USER)));
        userService.add(admin);
        userService.add(user);
    }
}

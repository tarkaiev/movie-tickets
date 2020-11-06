package movie.tickets.service;

import movie.tickets.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}

package movie.tickets.dao;

import movie.tickets.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}

package by.gsu.yournet.dao;

import by.gsu.yournet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}

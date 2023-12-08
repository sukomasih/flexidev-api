package au.com.geekseat.theta.repository;

import au.com.geekseat.theta.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

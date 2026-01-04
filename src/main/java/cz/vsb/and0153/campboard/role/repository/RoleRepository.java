package cz.vsb.and0153.campboard.role.repository;

import cz.vsb.and0153.campboard.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

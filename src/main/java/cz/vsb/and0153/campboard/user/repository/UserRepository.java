package cz.vsb.and0153.campboard.user.repository;

import cz.vsb.and0153.campboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

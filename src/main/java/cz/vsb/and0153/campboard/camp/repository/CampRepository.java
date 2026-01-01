package cz.vsb.and0153.campboard.camp.repository;

import cz.vsb.and0153.campboard.camp.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampRepository extends JpaRepository<Camp, Long> {
}

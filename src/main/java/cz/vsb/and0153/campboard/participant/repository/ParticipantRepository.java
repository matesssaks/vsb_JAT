package cz.vsb.and0153.campboard.participant.repository;

import cz.vsb.and0153.campboard.participant.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	List<Participant> findAllByPersonUserIdOrderByCampStartDate(long userId);
}

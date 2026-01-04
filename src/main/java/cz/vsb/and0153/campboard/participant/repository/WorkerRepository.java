package cz.vsb.and0153.campboard.participant.repository;

import cz.vsb.and0153.campboard.participant.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
	List<Worker> findAllByCampId(long userId);
}

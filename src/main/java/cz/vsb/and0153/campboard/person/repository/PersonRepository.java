package cz.vsb.and0153.campboard.person.repository;

import cz.vsb.and0153.campboard.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> findAllByUserIdOrderByName(long userId);
	List<Person> findAllByNameContainingIgnoreCaseOrderByName(String name);
}

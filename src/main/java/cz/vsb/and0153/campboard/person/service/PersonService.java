package cz.vsb.and0153.campboard.person.service;

import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.dto.PersonForm;
import cz.vsb.and0153.campboard.person.mapper.PersonMapper;
import cz.vsb.and0153.campboard.person.repository.PersonRepository;
import cz.vsb.and0153.campboard.user.entity.User;
import cz.vsb.and0153.campboard.user.mapper.UserSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;
	private final UserSummaryMapper userMapper;

	public List<PersonDto> getAllPersonsByUser(User user) {
		return personMapper.toDto(personRepository.findAllByUserIdOrderByName(user.getId()));
	}

	public Optional<PersonDto> getPerson(Long id) {
		return personRepository.findById(id).map(personMapper::toDto);
	}

	public PersonDto savePerson(PersonForm person, User user) {
		return personMapper.toDto(personRepository.save(personMapper.toEntity(person, userMapper.toSummary(user))));
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}

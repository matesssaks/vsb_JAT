package cz.vsb.and0153.campboard.person.service;

import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.dto.PersonForm;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.mapper.PersonMapper;
import cz.vsb.and0153.campboard.person.repository.PersonRepository;
import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.entity.User;
import cz.vsb.and0153.campboard.user.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;
	private final UserRepository userRepository;

	public List<PersonDto> getAllPersonsByUser(UserDto user) {
		return personMapper.toDto(personRepository.findAllByUserIdOrderByName(user.getId()));
	}

	public Optional<PersonDto> getPerson(Long id) {
		return personRepository.findById(id).map(personMapper::toDto);
	}

	public void savePerson(PersonForm form, UserDto userDto) {
		User user = userRepository.findById(userDto.getId()).orElseThrow();

		Person person = Optional.ofNullable(form.getId())
			.flatMap(personRepository::findById)
			.map(existing -> personMapper.update(form, existing))
			.orElseGet(() -> personMapper.save(form, user));

		personRepository.save(person);
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}

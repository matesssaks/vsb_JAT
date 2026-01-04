package cz.vsb.and0153.campboard.participant.service;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.camp.repository.CampRepository;
import cz.vsb.and0153.campboard.participant.dto.ParticipantDto;
import cz.vsb.and0153.campboard.participant.entity.Participant;
import cz.vsb.and0153.campboard.participant.mapper.ParticipantMapper;
import cz.vsb.and0153.campboard.participant.repository.ParticipantRepository;
import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.repository.PersonRepository;
import cz.vsb.and0153.campboard.person.service.PersonService;
import cz.vsb.and0153.campboard.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

	private final PersonRepository personRepository;
	private final CampRepository campRepository;
	private final ParticipantRepository participantRepository;

	private final ParticipantMapper participantMapper;

	public List<ParticipantDto> getAllParticipantsByUser(UserDto user) {
		return participantMapper.toDto(participantRepository.findAllByPersonUserIdOrderByCampStartDate(user.getId()));
	}

	public void register(PersonDto personDto, CampDto campDto) {
		Person person = personRepository.findById(personDto.getId()).orElseThrow();
		Camp camp = campRepository.findById(campDto.getId()).orElseThrow();

		Participant participant = new Participant();
		participant.setPerson(person);
		participant.setCamp(camp);
		participant.setRegisteredDate(OffsetDateTime.now());

		participantRepository.save(participant);
	}
}

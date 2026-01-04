package cz.vsb.and0153.campboard.participant.service;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.camp.repository.CampRepository;
import cz.vsb.and0153.campboard.participant.dto.WorkerDto;
import cz.vsb.and0153.campboard.participant.dto.WorkerSearchDto;
import cz.vsb.and0153.campboard.participant.entity.Worker;
import cz.vsb.and0153.campboard.participant.mapper.WorkerMapper;
import cz.vsb.and0153.campboard.participant.repository.WorkerRepository;
import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.mapper.PersonMapper;
import cz.vsb.and0153.campboard.person.repository.PersonRepository;
import cz.vsb.and0153.campboard.role.dto.RoleDto;
import cz.vsb.and0153.campboard.role.entity.Role;
import cz.vsb.and0153.campboard.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
	private final PersonRepository personRepository;
	private final WorkerRepository workerRepository;
	private final CampRepository campRepository;
	private final RoleRepository roleRepository;
	private final PersonMapper personMapper;
	private final WorkerMapper workerMapper;

	public Optional<WorkerDto> getWorker(Long workerId) {
		return workerRepository.findById(workerId).map(workerMapper::toDto);
	}

	public List<WorkerSearchDto> searchWorkers(String name, boolean includeAll, CampDto camp) {
		List<Worker> campWorkers = workerRepository.findAllByCampId(camp.getId());

		if (includeAll) {
			List<Person> persons = personRepository.findAllByNameContainingIgnoreCaseOrderByName(name);
			return persons.stream()
				.map(person -> {
					WorkerSearchDto dto = new WorkerSearchDto();
					dto.setPerson(personMapper.toDto(person));
					campWorkers.stream()
						.filter(w -> w.getPerson().getId().equals(person.getId()))
						.findFirst()
						.ifPresent(w -> dto.setWorker(workerMapper.toDto(w)));
					return dto;
				})
				.toList();
		}

		return campWorkers.stream()
			.filter(w -> w.getPerson().getName().toLowerCase().contains(name.toLowerCase()))
			.map(w -> {
				WorkerSearchDto dto = new WorkerSearchDto();
				dto.setPerson(personMapper.toDto(w.getPerson()));
				dto.setWorker(workerMapper.toDto(w));
				return dto;
			})
			.toList();
	}

	public void createWorker(PersonDto personDto, CampDto campDto, RoleDto roleDto) {
		Person person = personRepository.findById(personDto.getId()).orElseThrow();
		Camp camp = campRepository.findById(campDto.getId()).orElseThrow();
		Role role = roleRepository.findById(roleDto.getId()).orElseThrow();

		Worker worker = new Worker();
		worker.setPerson(person);
		worker.setCamp(camp);
		worker.setRole(role);
		worker.setRegisteredDate(OffsetDateTime.now());

		workerRepository.save(worker);
	}

	public void updateWorker(WorkerDto workerDto, RoleDto roleDto) {
		Worker worker = workerRepository.findById(workerDto.getId()).orElseThrow();
		Role role = roleRepository.findById(roleDto.getId()).orElseThrow();

		worker.setRole(role);

		workerRepository.save(worker);
	}

	public void deleteWorker(Long workerId) {
		workerRepository.deleteById(workerId);
	}
}

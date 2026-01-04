package cz.vsb.and0153.campboard.camp.service;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.dto.CampForm;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.camp.mapper.CampMapper;
import cz.vsb.and0153.campboard.camp.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampService {

	private final CampRepository campRepository;
	private final CampMapper campMapper;

	public List<CampDto> getAllCamps() {
		return campMapper.toDto(campRepository.findAll());
	}

	public Optional<CampDto> getCamp(Long id) {
		return campRepository.findById(id).map(campMapper::toDto);
	}

	public CampDto saveCamp(CampForm form) {
		Camp camp = Optional.ofNullable(form.getId())
			.flatMap(campRepository::findById)
			.map(existing -> campMapper.update(form, existing))
			.orElseGet(() -> campMapper.save(form));

		return campMapper.toDto(campRepository.save(camp));
	}

	public void deleteCamp(Long id) {
		campRepository.deleteById(id);
	}
}

package cz.vsb.and0153.campboard.camp.service;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.mapper.CampMapper;
import cz.vsb.and0153.campboard.camp.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampService {

	private final CampRepository campRepository;
	private final CampMapper campMapper;

	public List<CampDto> getAllCamps() {
		return campMapper.toDto(campRepository.findAll());
	}
}

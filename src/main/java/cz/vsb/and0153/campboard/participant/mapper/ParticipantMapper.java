package cz.vsb.and0153.campboard.participant.mapper;

import cz.vsb.and0153.campboard.camp.mapper.CampMapper;
import cz.vsb.and0153.campboard.participant.dto.ParticipantDto;
import cz.vsb.and0153.campboard.participant.entity.Participant;
import cz.vsb.and0153.campboard.person.mapper.PersonMapper;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {DateTimeMapper.class, PersonMapper.class, CampMapper.class})
public interface ParticipantMapper {
	ParticipantDto toDto(Participant entity);
	List<ParticipantDto> toDto(List<Participant> entities);
}

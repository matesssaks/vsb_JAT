package cz.vsb.and0153.campboard.participant.mapper;

import cz.vsb.and0153.campboard.camp.mapper.CampMapper;
import cz.vsb.and0153.campboard.participant.dto.WorkerDto;
import cz.vsb.and0153.campboard.participant.entity.Worker;
import cz.vsb.and0153.campboard.person.mapper.PersonMapper;
import cz.vsb.and0153.campboard.role.mapper.RoleMapper;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {DateTimeMapper.class, PersonMapper.class, CampMapper.class, RoleMapper.class})
public interface WorkerMapper {
	WorkerDto toDto(Worker entity);
	List<WorkerDto> toDto(List<Worker> entities);
}

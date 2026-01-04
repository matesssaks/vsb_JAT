package cz.vsb.and0153.campboard.person.mapper;

import cz.vsb.and0153.campboard.person.dto.PersonSummaryDto;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateTimeMapper.class)
public interface PersonSummaryMapper {
	PersonSummaryDto toSummary(Person entity);

	@Mapping(target = "user", ignore = true)
	Person toEntity(PersonSummaryDto summary);
}

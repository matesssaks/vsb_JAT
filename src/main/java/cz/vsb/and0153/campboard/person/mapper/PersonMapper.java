package cz.vsb.and0153.campboard.person.mapper;

import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.dto.PersonForm;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import cz.vsb.and0153.campboard.user.dto.UserSummaryDto;
import cz.vsb.and0153.campboard.user.mapper.UserSummaryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {DateTimeMapper.class, UserSummaryMapper.class})
public interface PersonMapper {
	PersonDto toDto(Person entity);
	List<PersonDto> toDto(List<Person> entities);

	Person toEntity(PersonDto dto);
	List<Person> toEntity(List<PersonDto> dtos);

	@Mapping(target = "id", source = "form.id")
	@Mapping(target = "user", source = "user")
	Person toEntity(PersonForm form, UserSummaryDto user);
}

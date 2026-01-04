package cz.vsb.and0153.campboard.person.mapper;

import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.dto.PersonForm;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import cz.vsb.and0153.campboard.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = DateTimeMapper.class)
public interface PersonMapper {
	PersonDto toDto(Person entity);
	List<PersonDto> toDto(List<Person> entities);

	@Mapping(target = "id", source = "form.id")
	@Mapping(target = "user", source = "user")
	@Mapping(target = "participants", ignore = true)
	Person save(PersonForm form, User user);

	@Mapping(target = "user", ignore = true)
	@Mapping(target = "participants", ignore = true)
	Person update(PersonForm form, @MappingTarget Person entity);
}

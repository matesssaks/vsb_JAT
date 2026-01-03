package cz.vsb.and0153.campboard.user.mapper;

import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
	UserDto toDto(User entity);
	List<UserDto> toDto(List<User> entities);

	@Mapping(target = "password", ignore = true)
	@Mapping(target = "admin", expression = "java(false)")
	User toEntity(UserDto dto);
	List<User> toEntity(List<UserDto> dtos);
}

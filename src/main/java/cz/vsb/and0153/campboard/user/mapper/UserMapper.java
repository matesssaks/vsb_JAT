package cz.vsb.and0153.campboard.user.mapper;

import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
	@Mapping(target = "authorities", ignore = true)
	UserDto toDto(User entity);
	List<UserDto> toDto(List<User> entities);

	User toEntity(UserDto dto);
	List<User> toEntity(List<UserDto> dtos);
}

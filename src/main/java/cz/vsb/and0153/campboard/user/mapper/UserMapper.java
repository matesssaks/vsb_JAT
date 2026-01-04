package cz.vsb.and0153.campboard.user.mapper;

import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
	UserDto toDto(User entity);
	List<UserDto> toDto(List<User> entities);
}

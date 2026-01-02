package cz.vsb.and0153.campboard.auth.mapper;

import cz.vsb.and0153.campboard.auth.dto.RegisterForm;
import cz.vsb.and0153.campboard.user.dto.UserDto;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public abstract class AuthMapper {

	@Setter(onMethod_ = @Autowired)
	protected PasswordEncoder encoder;

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "password", expression = "java(encoder.encode(form.getPassword()))")
	public abstract UserDto toUser(RegisterForm form);
}

package cz.vsb.and0153.campboard.auth.mapper;

import cz.vsb.and0153.campboard.auth.dto.SignupForm;
import cz.vsb.and0153.campboard.user.entity.User;
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
	@Mapping(target = "password", expression = "java(encoder.encode(form.getPassword()))")
	@Mapping(target = "admin", expression = "java(false)")
	@Mapping(target = "persons", ignore = true)
	public abstract User save(SignupForm form);
}

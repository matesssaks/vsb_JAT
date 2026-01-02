package cz.vsb.and0153.campboard.auth.dto;

import cz.vsb.and0153.campboard.auth.validator.PasswordMatches;
import cz.vsb.and0153.campboard.auth.validator.UniqueEmail;
import cz.vsb.and0153.campboard.auth.validator.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordMatches(message = "{validation.password.match}")
public class RegisterForm {
	@NotEmpty(message = "{validation.username.required}")
	@Size(min = 3, message = "{validation.username.min}")
	@Size(max = 50, message = "{validation.username.max}")
	@UniqueUsername(message = "{validation.username.unique}")
	private String username;

	@NotEmpty(message = "{validation.email.required}")
	@Email(message = "{validation.email.invalid}")
	@UniqueEmail(message = "{validation.email.unique}")
	private String email;

	@NotEmpty(message = "{validation.password.required}")
	@Size(min = 6, message = "{validation.password.min}")
	private String password;

	@NotEmpty(message = "{validation.password.confirm}")
	private String password2;
}

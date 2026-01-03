package cz.vsb.and0153.campboard.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String username;
	private String email;
}

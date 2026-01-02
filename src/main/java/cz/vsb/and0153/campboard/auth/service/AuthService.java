package cz.vsb.and0153.campboard.auth.service;

import cz.vsb.and0153.campboard.auth.dto.RegisterForm;
import cz.vsb.and0153.campboard.auth.mapper.AuthMapper;
import cz.vsb.and0153.campboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserService userService;
	private final AuthMapper authMapper;

	public void registerUser(RegisterForm form) {
		userService.createUser(authMapper.toUser(form));
	}
}

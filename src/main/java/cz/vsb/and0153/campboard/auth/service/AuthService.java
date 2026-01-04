package cz.vsb.and0153.campboard.auth.service;

import cz.vsb.and0153.campboard.auth.dto.SignupForm;
import cz.vsb.and0153.campboard.auth.mapper.AuthMapper;
import cz.vsb.and0153.campboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final AuthMapper authMapper;

	public void registerUser(SignupForm form) {
		userRepository.save(authMapper.save(form));
	}
}

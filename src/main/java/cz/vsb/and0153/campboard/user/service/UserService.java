package cz.vsb.and0153.campboard.user.service;

import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.mapper.UserMapper;
import cz.vsb.and0153.campboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public Optional<UserDto> getUserByUsername(String username) {
		return userRepository.findByUsername(username).map(userMapper::toDto);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public UserDto createUser(UserDto userDto) {
		return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
	}
}

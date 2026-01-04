package cz.vsb.and0153.campboard.auth.service;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import cz.vsb.and0153.campboard.user.mapper.UserMapper;
import cz.vsb.and0153.campboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	@NonNull
	public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).map(
			user -> new AppUserDetails(userMapper.toDto(user), user.getPassword())
		).orElseThrow(() ->
			new UsernameNotFoundException("User not found")
		);
	}
}

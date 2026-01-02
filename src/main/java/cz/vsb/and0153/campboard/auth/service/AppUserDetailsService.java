package cz.vsb.and0153.campboard.auth.service;

import cz.vsb.and0153.campboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	@NonNull
	public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
		return userService.getUserByUsername(username).orElseThrow(() ->
			new UsernameNotFoundException("User not found")
		);
	}
}

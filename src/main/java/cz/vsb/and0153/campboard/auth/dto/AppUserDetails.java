package cz.vsb.and0153.campboard.auth.dto;

import cz.vsb.and0153.campboard.user.dto.UserDto;
import cz.vsb.and0153.campboard.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AppUserDetails implements UserDetails {

	@Getter
	private final transient UserDto user;
	private final transient String password;

	@Override
	@NonNull
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (user.isAdmin()) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return List.of();
	}

	@Override
	@NonNull
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	@NonNull
	public String getPassword() {
		return password;
	}
}

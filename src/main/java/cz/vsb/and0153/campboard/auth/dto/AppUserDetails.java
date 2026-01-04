package cz.vsb.and0153.campboard.auth.dto;

import cz.vsb.and0153.campboard.user.entity.User;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record AppUserDetails(
	User user
) implements UserDetails {

	@Override
	@NonNull
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (user.isAdmin()) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return List.of();
	}

	@Override
	@NonNull
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	@NonNull
	public String getUsername() {
		return user.getUsername();
	}
}

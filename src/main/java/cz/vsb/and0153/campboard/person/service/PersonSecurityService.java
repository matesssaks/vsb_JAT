package cz.vsb.and0153.campboard.person.service;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import cz.vsb.and0153.campboard.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("personSecurity")
@RequiredArgsConstructor
public class PersonSecurityService {

	private final PersonRepository personRepository;

	public boolean isPersonManager(AppUserDetails details, Long personId) {
		if (details == null || details.getUser() == null) return false;

		if (details.getUser().isAdmin() || personId == null) return true; // Allow admins and new person creation

		return personRepository.findById(personId).map(
			person -> person.getUser().getId().equals(details.getUser().getId())
		).orElse(false);
	}
}

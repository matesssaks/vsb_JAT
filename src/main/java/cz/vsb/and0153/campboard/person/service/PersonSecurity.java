package cz.vsb.and0153.campboard.person.service;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("personSecurity")
@RequiredArgsConstructor
public class PersonSecurity {

	private final PersonService personService;

	public boolean isAllowed(AppUserDetails details, Long personId) {
		if (details == null || details.user() == null) return false;

		if (details.user().isAdmin() || personId == null) return true; // Allow admins and new person creation

		return personService.getPerson(personId).map(
			person -> person.getUser().getId().equals(details.user().getId())
		).orElse(false);
	}
}

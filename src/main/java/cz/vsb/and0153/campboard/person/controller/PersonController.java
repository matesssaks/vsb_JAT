package cz.vsb.and0153.campboard.person.controller;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.dto.PersonForm;
import cz.vsb.and0153.campboard.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

	private static final String PERSON_LIST_TEMPLATE = "user/persons";
	private static final String PERSON_EDIT_MODAL = "user/modal/editPerson";
	private static final String PERSON_DELETE_MODAL = "user/modal/deletePerson";
	private static final String PERSON_LIST_VAR = "persons";
	private static final String PERSON_VAR = "person";

	private final PersonService personService;

	@GetMapping({"", "/"})
	@PreAuthorize("isAuthenticated()")
	public String getAllPersons(Model model, @AuthenticationPrincipal AppUserDetails userDetails) {
		model.addAttribute(PERSON_LIST_VAR, personService.getAllPersonsByUser(userDetails.user()));
		return PERSON_LIST_TEMPLATE;
	}

	@GetMapping("/new")
	@PreAuthorize("isAuthenticated()")
	public String newPerson(Model model) {
		model.addAttribute(PERSON_VAR, new PersonDto());
		return PERSON_EDIT_MODAL;
	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("@personSecurity.isAllowed(principal, #id)")
	public String editPerson(@PathVariable Long id, Model model) {
		PersonDto person = personService.getPerson(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute(PERSON_VAR, person);
		return PERSON_EDIT_MODAL;
	}

	@PostMapping("/save")
	@PreAuthorize("@personSecurity.isAllowed(principal, #form.id)")
	public String savePerson(
		@ModelAttribute(PERSON_VAR) @Valid PersonForm form,
		BindingResult result,
		@AuthenticationPrincipal AppUserDetails userDetails
	) {
		if (result.hasErrors()) {
			return PERSON_EDIT_MODAL;
		}

		personService.savePerson(form, userDetails.user());
		return "redirect:/person";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("@personSecurity.isAllowed(principal, #id)")
	public String deletePerson(@PathVariable Long id, Model model) {
		PersonDto person = personService.getPerson(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute(PERSON_VAR, person);
		return PERSON_DELETE_MODAL;
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("@personSecurity.isAllowed(principal, #id)")
	public String confirmDeletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
		return "redirect:/person";
	}
}

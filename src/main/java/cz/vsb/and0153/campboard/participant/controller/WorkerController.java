package cz.vsb.and0153.campboard.participant.controller;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.service.CampService;
import cz.vsb.and0153.campboard.participant.dto.WorkerDto;
import cz.vsb.and0153.campboard.participant.dto.WorkerForm;
import cz.vsb.and0153.campboard.participant.dto.WorkerSearchForm;
import cz.vsb.and0153.campboard.participant.service.WorkerService;
import cz.vsb.and0153.campboard.person.dto.PersonDto;
import cz.vsb.and0153.campboard.person.service.PersonService;
import cz.vsb.and0153.campboard.role.dto.RoleDto;
import cz.vsb.and0153.campboard.role.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/camp/{camp}/worker")
@RequiredArgsConstructor
public class WorkerController {

	private final CampService campService;
	private final WorkerService workerService;
	private final RoleService roleService;
	private final PersonService personService;

	@GetMapping({"", "/"})
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String dashboardPerson(
		@PathVariable Long camp,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("workers", workerService.searchWorkers("", false, campDto));

		model.addAttribute("camp", campDto);
		model.addAttribute("search", new WorkerSearchForm());
		return "dashboard/workers";
	}

	@PostMapping({"", "/"})
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String dashboardPerson(
		@PathVariable Long camp,
		Model model,
		@ModelAttribute("search") WorkerSearchForm form
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		String name = Optional.ofNullable(form.getName()).orElse("");
		boolean includeAll = Optional.ofNullable(form.getIncludeAll()).orElse(false);
		model.addAttribute("workers", workerService.searchWorkers(name, includeAll, campDto));

		model.addAttribute("camp", campDto);
		return "dashboard/workers";
	}

	@GetMapping("/new/{person}")
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String newWorker(
		@PathVariable Long camp,
		@PathVariable Long person,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
		PersonDto personDto = personService.getPerson(person).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		WorkerForm form = new WorkerForm();
		form.setPerson(person);

		model.addAttribute("camp", campDto);
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("worker", form);
		model.addAttribute("person", personDto);
		return "dashboard/modal/editWorker";
	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String editWorker(
		@PathVariable Long camp,
		@PathVariable Long id,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
		WorkerDto workerDto = workerService.getWorker(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		WorkerForm form = new WorkerForm();
		form.setId(workerDto.getId());
		form.setPerson(workerDto.getPerson().getId());
		form.setRole(workerDto.getRole().getId());

		model.addAttribute("camp", campDto);
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("worker", form);
		model.addAttribute("person", workerDto.getPerson());
		return "dashboard/modal/editWorker";
	}

	@PostMapping("/save")
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String saveWorker(
		@ModelAttribute("worker") @Valid WorkerForm form,
		BindingResult result,
		@PathVariable Long camp,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
		PersonDto personDto = personService.getPerson(form.getPerson()).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		if (result.hasErrors()) {
			model.addAttribute("camp", campDto);
			model.addAttribute("roles", roleService.getAllRoles());
			model.addAttribute("person", personDto);
			return "dashboard/modal/editWorker";
		}

		RoleDto roleDto = roleService.getRole(form.getRole()).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		if (form.getId() == null) {
			workerService.createWorker(personDto, campDto, roleDto);
		} else {
			WorkerDto workerDto = workerService.getWorker(form.getId()).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND)
			);

			workerService.updateWorker(workerDto, roleDto);
		}

		return String.format("redirect:/camp/%s/worker", camp);
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String deleteWorker(
		@PathVariable Long camp,
		@PathVariable Long id,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
		WorkerDto workerDto = workerService.getWorker(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
		model.addAttribute("worker", workerDto);
		model.addAttribute("camp", campDto);

		return "dashboard/modal/deleteWorker";
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("@workerSecurity.canEditWorkers(principal, #camp)")
	public String confirmDeleteWorker(
		@PathVariable Long camp,
		@PathVariable Long id
	) {
		log.info("Deleting worker with id: {}", id);

		workerService.deleteWorker(id);
		return String.format("redirect:/camp/%s/worker", camp);
	}
}

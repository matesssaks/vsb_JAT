package cz.vsb.and0153.campboard.camp.controller;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.service.CampService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/camp")
@RequiredArgsConstructor
public class CampController {

	private static final String CAMP_LIST_TEMPLATE = "camp/camps";
	private static final String CAMP_TEMPLATE = "camp/camp";
	private static final String CAMP_REGISTER_TEMPLATE = "camp/register";
	private static final String CAMP_EDIT_MODAL = "camp/modal/editCamp";
	private static final String CAMP_DELETE_MODAL = "camp/modal/deleteCamp";

	private final CampService campService;

	@GetMapping("")
	@PreAuthorize("permitAll()")
	public String getCamps(Model model) {
		model.addAttribute("camps", campService.getAllCamps());
		return CAMP_LIST_TEMPLATE;
	}

	@GetMapping("/{id}")
	@PreAuthorize("permitAll()")
	public String getCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("camp", camp);
		return CAMP_TEMPLATE;
	}

	@GetMapping("/register/{id}")
	@PreAuthorize("isAuthenticated()")
	public String registerCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("camp", camp);
		return CAMP_REGISTER_TEMPLATE;
	}

	@GetMapping("/new")
	@PreAuthorize("hasRole('ADMIN')")
	public String editCamp(Model model) {
		model.addAttribute("camp", new CampDto());
		return CAMP_EDIT_MODAL;
	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("camp", camp);
		return CAMP_EDIT_MODAL;
	}

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public String saveCamp(
		@ModelAttribute("camp") @Valid CampDto camp,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return CAMP_EDIT_MODAL;
		}

		Long id = campService.saveCamp(camp).getId();
		return "redirect:/camp/" + id;
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("camp", camp);
		return CAMP_DELETE_MODAL;
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String confirmDeleteCamp(@PathVariable Long id) {
		campService.deleteCamp(id);
		return "redirect:/camp";
	}
}

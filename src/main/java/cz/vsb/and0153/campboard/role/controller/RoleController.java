package cz.vsb.and0153.campboard.role.controller;

import cz.vsb.and0153.campboard.role.dto.RoleDto;
import cz.vsb.and0153.campboard.role.dto.RoleForm;
import cz.vsb.and0153.campboard.role.service.RoleService;
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
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

	private static final String ROLE_LIST_TEMPLATE = "settings/roles";
	private static final String ROLE_EDIT_MODAL = "settings/modal/editRole";
	private static final String ROLE_DELETE_MODAL = "settings/modal/deleteRole";
	private static final String ROLE_LIST_VAR = "roles";
	private static final String ROLE_VAR = "role";

	private final RoleService roleService;

	@GetMapping({"", "/"})
	@PreAuthorize("hasRole('ADMIN')")
	public String getAllRoles(Model model) {
		model.addAttribute(ROLE_LIST_VAR, roleService.getAllRoles());
		return ROLE_LIST_TEMPLATE;
	}

	@GetMapping("/new")
	@PreAuthorize("hasRole('ADMIN')")
	public String newRole(Model model) {
		model.addAttribute(ROLE_VAR, new RoleDto());
		return ROLE_EDIT_MODAL;
	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editRole(@PathVariable Long id, Model model) {
		RoleDto role = roleService.getRole(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute(ROLE_VAR, role);
		return ROLE_EDIT_MODAL;
	}

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public String saveRole(
		@ModelAttribute(ROLE_VAR) @Valid RoleForm form,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return ROLE_EDIT_MODAL;
		}

		roleService.saveRole(form);

		return "redirect:/role";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteRole(@PathVariable Long id, Model model) {
		RoleDto role = roleService.getRole(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute(ROLE_VAR, role);
		return ROLE_DELETE_MODAL;
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String confirmDeleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return "redirect:/role";
	}
}

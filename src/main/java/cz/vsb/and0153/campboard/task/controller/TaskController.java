package cz.vsb.and0153.campboard.task.controller;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/camp/{camp}/task")
@RequiredArgsConstructor
public class TaskController {

	private final CampService campService;

	@GetMapping({"", "/"})
	@PreAuthorize("@workerSecurity.canViewTasks(principal, #camp)")
	public String dashboardPerson(
		@PathVariable Long camp,
		Model model
	) {
		CampDto campDto = campService.getCamp(camp).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND)
		);

		model.addAttribute("camp", campDto);
		return "dashboard/tasks";
	}
}

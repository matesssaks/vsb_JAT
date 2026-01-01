package cz.vsb.and0153.campboard.camp.controller;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/camp")
@RequiredArgsConstructor
public class CampController {

	private final CampService campService;

	@GetMapping({"", "/"})
	public String getCamps(Model model) {
		model.addAttribute("camps", campService.getAllCamps());
		return "camp/camps";
	}

	@GetMapping("/{id}")
	public String getCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Camp not found")
		);

		model.addAttribute("camp", camp);
		return "camp/camp";
	}

	@GetMapping("/register/{id}")
	public String registerCamp(@PathVariable Long id, Model model) {
		CampDto camp = campService.getCamp(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Camp not found")
		);

		model.addAttribute("camp", camp);
		return "camp/register";
	}
}

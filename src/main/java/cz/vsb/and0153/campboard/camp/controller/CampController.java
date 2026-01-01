package cz.vsb.and0153.campboard.camp.controller;

import cz.vsb.and0153.campboard.camp.service.CampService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camp")
public class CampController {

	private final CampService campService;

	@GetMapping("")
	public String getCamps(Model model) {
		model.addAttribute("camps", campService.getAllCamps());
		return "camps";
	}
}

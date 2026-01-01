package cz.vsb.and0153.campboard.camp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class IndexController {

	private final CampController campController;

	@GetMapping({"", "/"})
	public String getIndex(Model model) {
		return campController.getCamps(model);
	}
}

package cz.vsb.and0153.campboard;

import cz.vsb.and0153.campboard.camp.controller.CampController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class IndexController implements ErrorController {

	private final CampController campController;

	@GetMapping({"", "/"})
	@PreAuthorize("permitAll()")
	public String indexPage(Model model) {
		return campController.getCamps(model);
	}

	@GetMapping("/error")
	@PreAuthorize("permitAll()")
	public String errorPage() {
		return "redirect:/";
	}
}

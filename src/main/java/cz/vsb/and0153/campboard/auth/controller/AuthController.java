package cz.vsb.and0153.campboard.auth.controller;

import cz.vsb.and0153.campboard.auth.dto.RegisterForm;
import cz.vsb.and0153.campboard.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@GetMapping("/login")
	public String loginPage() {
		return "auth/login";
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("form", new RegisterForm());
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String signup(
		@ModelAttribute("form") @Valid RegisterForm form,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return "auth/signup";
		}

		authService.registerUser(form);

		return "redirect:/login";
	}
}

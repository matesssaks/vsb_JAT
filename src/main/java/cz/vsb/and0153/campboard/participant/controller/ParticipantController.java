package cz.vsb.and0153.campboard.participant.controller;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import cz.vsb.and0153.campboard.participant.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/participant")
public class ParticipantController {

	private static final String PARTICIPANT_LIST_TEMPLATE = "user/participants";
	private static final String PARTICIPANT_LIST_VAR = "participants";

	private final ParticipantService participantService;

	@GetMapping({"", "/"})
	@PreAuthorize("isAuthenticated()")
	public String getAllPersons(Model model, @AuthenticationPrincipal AppUserDetails userDetails) {
		model.addAttribute(PARTICIPANT_LIST_VAR, participantService.getAllParticipantsByUser(userDetails.getUser()));
		return PARTICIPANT_LIST_TEMPLATE;
	}
}

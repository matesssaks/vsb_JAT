package cz.vsb.and0153.campboard.user.controller;

import cz.vsb.and0153.campboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
}

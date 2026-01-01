package cz.vsb.and0153.campboard.user.service;

import cz.vsb.and0153.campboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
}

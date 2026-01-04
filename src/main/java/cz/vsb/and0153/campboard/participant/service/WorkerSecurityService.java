package cz.vsb.and0153.campboard.participant.service;

import cz.vsb.and0153.campboard.auth.dto.AppUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("workerSecurity")
public class WorkerSecurityService {

	private boolean denyAccessDashboard(AppUserDetails details, Long campId) {
		if (details == null || details.getUser() == null) return true;

		if (details.getUser().isAdmin() || campId == null) return false;

		return true;
	}

	public boolean canEditWorkers(AppUserDetails details, Long campId) {
		if (denyAccessDashboard(details, campId)) return false;

		return true;
	}

	public boolean canViewTasks(AppUserDetails details, Long campId) {
		if (denyAccessDashboard(details, campId)) return false;

		return true;
	}
}

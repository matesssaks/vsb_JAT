package cz.vsb.and0153.campboard.role.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleForm {
	private Long id;
	private String name;
	private Boolean canEditWorker;
	private Boolean canCreateTask;
	private Boolean canToggleTask;
}

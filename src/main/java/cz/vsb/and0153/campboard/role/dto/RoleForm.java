package cz.vsb.and0153.campboard.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleForm {
	private Long id;

	@NotBlank(message = "{validation.name.required}")
	@Size(min = 3, message = "{validation.name.min}")
	@Size(max = 20, message = "{validation.name.max}")
	private String name;

	@NotNull(message = "{validation.permission.required}")
	private Boolean canEditWorker;

	@NotNull(message = "{validation.permission.required}")
	private Boolean canCreateTask;

	@NotNull(message = "{validation.permission.required}")
	private Boolean canToggleTask;
}

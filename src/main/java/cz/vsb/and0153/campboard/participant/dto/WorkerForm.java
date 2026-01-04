package cz.vsb.and0153.campboard.participant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WorkerForm {
	private Long id;

	@NotNull
	private Long person;

	@NotNull(message = "{validation.role.required}")
	private Long role;
}

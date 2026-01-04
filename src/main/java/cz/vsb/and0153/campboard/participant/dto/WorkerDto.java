package cz.vsb.and0153.campboard.participant.dto;

import cz.vsb.and0153.campboard.role.dto.RoleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WorkerDto extends ParticipantDto {
	private RoleDto role;
}

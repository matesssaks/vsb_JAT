package cz.vsb.and0153.campboard.participant.dto;

import cz.vsb.and0153.campboard.person.dto.PersonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkerSearchDto {
	private PersonDto person;
	private WorkerDto worker;
}

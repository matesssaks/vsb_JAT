package cz.vsb.and0153.campboard.participant.dto;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.person.dto.PersonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ParticipantDto {
	private Long id;
	private CampDto camp;
	private PersonDto person;
	private LocalDateTime registeredDate;
}

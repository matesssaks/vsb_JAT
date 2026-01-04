package cz.vsb.and0153.campboard.person.dto;

import cz.vsb.and0153.campboard.user.dto.UserSummaryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class PersonDto extends PersonSummaryDto {
	private UserSummaryDto user;
}

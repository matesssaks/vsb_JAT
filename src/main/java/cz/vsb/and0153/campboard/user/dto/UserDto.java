package cz.vsb.and0153.campboard.user.dto;

import cz.vsb.and0153.campboard.person.dto.PersonSummaryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDto extends UserSummaryDto {
	private List<PersonSummaryDto> persons;
}

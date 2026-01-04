package cz.vsb.and0153.campboard.person.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PersonDto {
	private Long id;

	private String personalNumber;

	private String name;

	private String town;

	private String street;

	private String postalCode;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate bornDate;
}

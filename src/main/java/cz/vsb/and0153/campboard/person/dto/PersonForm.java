package cz.vsb.and0153.campboard.person.dto;

import cz.vsb.and0153.campboard.tools.validator.BeforeToday;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PersonForm {
	private Long id;

	@NotBlank(message = "{validation.personalNumber.required}")
	@Size(min = 10, max = 10, message = "{validation.personalNumber.invalid}")
	private String personalNumber;

	@NotBlank(message = "{validation.name.required}")
	@Size(min = 3, message = "{validation.name.min}")
	@Size(max = 50, message = "{validation.name.max}")
	private String name;

	@NotBlank(message = "{validation.town.required}")
	private String town;

	@NotBlank(message = "{validation.street.required}")
	private String street;

	@NotBlank(message = "{validation.postalCode.required}")
	private String postalCode;

	@NotNull(message = "{validation.bornDate.required}")
	@BeforeToday(message = "{validation.bornDate.beforeToday}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate bornDate;
}

package cz.vsb.and0153.campboard.camp.dto;

import cz.vsb.and0153.campboard.camp.validator.CampEndDateAfterStart;
import cz.vsb.and0153.campboard.tools.validator.AfterToday;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@CampEndDateAfterStart(message = "{validation.endDate.after_start}")
public class CampDto {
	private Long id;

	@NotBlank(message = "{validation.name.required}")
	@Size(min = 3, message = "{validation.name.min}")
	@Size(max = 50, message = "{validation.name.max}")
	private String name;

	@NotBlank(message = "{validation.description.required}")
	private String description;

	@NotBlank(message = "{validation.imgUrl.required}")
	private String imgUrl;

	@NotNull(message = "{validation.startDate.required}")
	@AfterToday(message = "{validation.startDate.after_today}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime startDate;

	@NotNull(message = "{validation.endDate.required}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endDate;

	@NotNull(message = "{validation.price.required}")
	private Double price;

	@NotNull(message = "{validation.capacity.required}")
	@Min(value = 0, message = "{validation.capacity.min}")
	private Integer capacity;
}

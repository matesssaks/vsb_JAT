package cz.vsb.and0153.campboard.camp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class CampDto {
	private Long id;
	private String name;
	private String description;
	private String imgUrl;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private Integer price;
	private Integer capacity;
	private Integer registered;
}

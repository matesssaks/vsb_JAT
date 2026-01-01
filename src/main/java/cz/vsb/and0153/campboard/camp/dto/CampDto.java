package cz.vsb.and0153.campboard.camp.dto;

import java.time.OffsetDateTime;

public record CampDto(
	Long id,
	String name,
	String description,
	String imgUrl,
	OffsetDateTime startDate,
	OffsetDateTime endDate,
	Integer price,
	Integer capacity,
	Integer registered
) {}

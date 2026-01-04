package cz.vsb.and0153.campboard.tools.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper
public abstract class DateTimeMapper {
	public LocalDateTime mapDateTime(OffsetDateTime offsetDateTime) {
		if (offsetDateTime == null) return null;
		ZoneId userZone = LocaleContextHolder.getTimeZone().toZoneId();
		return offsetDateTime.atZoneSameInstant(userZone).toLocalDateTime();
	}

	public OffsetDateTime mapDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) return null;
		ZoneId userZone = LocaleContextHolder.getTimeZone().toZoneId();
		return localDateTime.atZone(userZone).toOffsetDateTime();
	}

	public LocalDate mapDate(OffsetDateTime offsetDateTime) {
		return mapDateTime(offsetDateTime).toLocalDate();
	}

	public OffsetDateTime mapDate(LocalDate localDate) {
		return mapDateTime(localDate.atStartOfDay());
	}
}

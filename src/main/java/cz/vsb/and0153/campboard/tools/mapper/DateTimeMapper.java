package cz.vsb.and0153.campboard.tools.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper
public abstract class DateTimeMapper {
	public LocalDateTime map(OffsetDateTime offsetDateTime) {
		if (offsetDateTime == null) return null;
		ZoneId userZone = LocaleContextHolder.getTimeZone().toZoneId();
		return offsetDateTime.atZoneSameInstant(userZone).toLocalDateTime();
	}

	public OffsetDateTime map(LocalDateTime localDateTime) {
		if (localDateTime == null) return null;
		ZoneId userZone = LocaleContextHolder.getTimeZone().toZoneId();
		return localDateTime.atZone(userZone).toOffsetDateTime();
	}
}

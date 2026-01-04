package cz.vsb.and0153.campboard.user.mapper;

import cz.vsb.and0153.campboard.user.dto.UserSummaryDto;
import cz.vsb.and0153.campboard.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserSummaryMapper {
	UserSummaryDto toSummary(User entity);

	@Mapping(target = "password", ignore = true)
	@Mapping(target = "admin", ignore = true)
	@Mapping(target = "persons", ignore = true)
	User toEntity(UserSummaryDto summary);
}

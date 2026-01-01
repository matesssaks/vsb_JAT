package cz.vsb.and0153.campboard.camp.mapper;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CampMapper {
	CampDto toDto(Camp entity);
	List<CampDto> toDto(List<Camp> entities);

	Camp toEntity(CampDto dto);
	List<Camp> toEntity(List<CampDto> dtos);
}

package cz.vsb.and0153.campboard.camp.mapper;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.dto.CampForm;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = DateTimeMapper.class)
public interface CampMapper {
	@Mapping(target = "price", expression = "java(entity.getPrice() / 100d)")
	CampDto toDto(Camp entity);
	List<CampDto> toDto(List<Camp> entities);

	CampDto toDto(CampForm form);

	@Mapping(target = "price", expression = "java((int) (dto.getPrice() * 100))")
	Camp toEntity(CampDto dto);
	List<Camp> toEntity(List<CampDto> dtos);

	Camp toEntity(CampForm form);
}

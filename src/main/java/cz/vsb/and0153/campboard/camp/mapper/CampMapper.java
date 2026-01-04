package cz.vsb.and0153.campboard.camp.mapper;

import cz.vsb.and0153.campboard.camp.dto.CampDto;
import cz.vsb.and0153.campboard.camp.dto.CampForm;
import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.tools.mapper.DateTimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = DateTimeMapper.class)
public interface CampMapper {
	@Mapping(target = "price", expression = "java(entity.getPrice() / 100d)")
	CampDto toDto(Camp entity);
	List<CampDto> toDto(List<Camp> entities);

	@Mapping(target = "participants", ignore = true)
	@Mapping(target = "price", expression = "java((int) (form.getPrice() * 100))")
	Camp save(CampForm form);

	@Mapping(target = "participants", ignore = true)
	@Mapping(target = "price", expression = "java((int) (form.getPrice() * 100))")
	Camp update(CampForm form, @MappingTarget Camp entity);
}

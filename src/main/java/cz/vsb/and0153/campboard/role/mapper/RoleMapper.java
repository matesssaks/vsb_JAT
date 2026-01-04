package cz.vsb.and0153.campboard.role.mapper;

import cz.vsb.and0153.campboard.role.dto.RoleDto;
import cz.vsb.and0153.campboard.role.dto.RoleForm;
import cz.vsb.and0153.campboard.role.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface RoleMapper {
	RoleDto toDto(Role entity);
	List<RoleDto> toDto(List<Role> entities);

	Role save(RoleForm form);
	Role update(RoleForm form, @MappingTarget Role entity);
}

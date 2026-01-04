package cz.vsb.and0153.campboard.role.service;

import cz.vsb.and0153.campboard.role.dto.RoleDto;
import cz.vsb.and0153.campboard.role.dto.RoleForm;
import cz.vsb.and0153.campboard.role.entity.Role;
import cz.vsb.and0153.campboard.role.mapper.RoleMapper;
import cz.vsb.and0153.campboard.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;

	public List<RoleDto> getAllRoles() {
		return roleMapper.toDto(roleRepository.findAll());
	}

	public Optional<RoleDto> getRole(Long id) {
		return roleRepository.findById(id).map(roleMapper::toDto);
	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}

	public void saveRole(RoleForm form) {
		Role role = Optional.ofNullable(form.getId())
			.flatMap(roleRepository::findById)
			.map(existing -> roleMapper.update(form, existing))
			.orElseGet(() -> roleMapper.save(form));

		roleRepository.save(role);
	}
}

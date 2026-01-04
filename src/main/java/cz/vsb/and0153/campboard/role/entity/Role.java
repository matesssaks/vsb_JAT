package cz.vsb.and0153.campboard.role.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "can_edit_worker", nullable = false)
	private Boolean canEditWorker;

	@Column(name = "can_create_task", nullable = false)
	private Boolean canCreateTask;

	@Column(name = "can_toggle_task", nullable = false)
	private Boolean canToggleTask;
}

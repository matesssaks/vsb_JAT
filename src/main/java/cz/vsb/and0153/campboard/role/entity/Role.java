package cz.vsb.and0153.campboard.role.entity;

import cz.vsb.and0153.campboard.participant.entity.Worker;
import cz.vsb.and0153.campboard.participant.entity.Worker_;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.entity.Person_;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

	@OneToMany(mappedBy = Worker_.ROLE, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Worker> workers;
}

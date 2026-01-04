package cz.vsb.and0153.campboard.participant.entity;

import cz.vsb.and0153.campboard.role.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "worker")
@PrimaryKeyJoinColumn(name = "participant_id")
public class Worker extends Participant {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
}

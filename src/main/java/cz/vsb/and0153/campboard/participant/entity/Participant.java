package cz.vsb.and0153.campboard.participant.entity;

import cz.vsb.and0153.campboard.camp.entity.Camp;
import cz.vsb.and0153.campboard.person.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "participant")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "camp_id", nullable = false)
	private Camp camp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@Column(name = "registered_date", nullable = false)
	private OffsetDateTime registeredDate;
}

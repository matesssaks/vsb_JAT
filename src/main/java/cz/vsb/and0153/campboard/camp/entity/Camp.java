package cz.vsb.and0153.campboard.camp.entity;

import cz.vsb.and0153.campboard.participant.entity.Participant;
import cz.vsb.and0153.campboard.participant.entity.Participant_;
import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.entity.Person_;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "camp")
public class Camp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "camp_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@Column(name = "start_date", nullable = false)
	private OffsetDateTime startDate;

	@Column(name = "end_date", nullable = false)
	private OffsetDateTime endDate;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "capacity", nullable = false)
	private Integer capacity;

	@OneToMany(mappedBy = Participant_.CAMP, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participant> participants;
}

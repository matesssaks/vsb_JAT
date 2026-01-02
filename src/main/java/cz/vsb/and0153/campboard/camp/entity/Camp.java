package cz.vsb.and0153.campboard.camp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

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

	@Column(name = "registered", nullable = false)
	private Integer registered;
}

package cz.vsb.and0153.campboard.person.entity;

import cz.vsb.and0153.campboard.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "personal_number", nullable = false)
	private String personalNumber;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "town", nullable = false)
	private String town;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "postal_code", nullable = false)
	private String postalCode;

	@Column(name = "born_date", nullable = false)
	private OffsetDateTime bornDate;
}

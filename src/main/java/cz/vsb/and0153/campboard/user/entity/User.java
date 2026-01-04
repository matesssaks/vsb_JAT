package cz.vsb.and0153.campboard.user.entity;

import cz.vsb.and0153.campboard.person.entity.Person;
import cz.vsb.and0153.campboard.person.entity.Person_;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "admin", nullable = false)
	private boolean admin = false;

	@OneToMany(mappedBy = Person_.USER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Person> persons;
}

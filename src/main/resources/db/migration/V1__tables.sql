CREATE TABLE users (
	user_id identity PRIMARY KEY,
	name varchar(50) UNIQUE NOT NULL,
	email varchar(50) UNIQUE NOT NULL,
	password varchar(255) NOT NULL,
	admin boolean NOT NULL DEFAULT false,
	CHECK (email LIKE '%_@_%._%')
);

CREATE TABLE person (
	person_id identity PRIMARY KEY,
	user_id int NOT NULL REFERENCES users (user_id),
	personal_number varchar(20) NOT NULL,
	name varchar(50) NOT NULL,
	town varchar(50) NOT NULL,
	street varchar(50) NOT NULL,
	postal_code varchar(10) NOT NULL,
	born_date timestamp with time zone NOT NULL
);

CREATE TABLE camp (
	camp_id identity PRIMARY KEY,
	name varchar(50) NOT NULL,
	description varchar(1024) NOT NULL,
	img_url varchar(255) NOT NULL,
	start_date timestamp with time zone NOT NULL,
	end_date timestamp with time zone NOT NULL,
	price int NOT NULL,
	capacity int NOT NULL
	CHECK (price >= 0)
	CHECK (capacity >= 0)
	CHECK (start_date < end_date)
);

CREATE TABLE participant (
	participant_id identity PRIMARY KEY,
	camp_id int NOT NULL REFERENCES camp (camp_id),
	person_id int NOT NULL REFERENCES person (person_id),
	registered_date timestamp with time zone NOT NULL,
	UNIQUE (camp_id, person_id)
);

CREATE TABLE role (
	role_id identity PRIMARY KEY,
	name varchar(20) NOT NULL,
	can_edit_worker boolean NOT NULL,
	can_create_task boolean NOT NULL,
	can_toggle_task boolean NOT NULL
);

CREATE TABLE worker (
	participant_id int PRIMARY KEY REFERENCES participant (participant_id),
	role_id int NOT NULL REFERENCES role (role_id)
);

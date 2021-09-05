create table if not exists password_blacklist
(
	id serial not null,
	password varchar(64)
);

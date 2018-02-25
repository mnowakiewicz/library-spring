insert into user_role(role, description) VALUES ("ROLE_USER", "default role for user");

insert INTO user(user_id, username, email, password, date) VALUES (1, "user111", "user111@gmail.com", "pass111", current_timestamp());
insert into user_user_roles(user_user_id, user_roles_user_role_id) values (1, 1);

insert into library.book(author, title, isbn, image_url, id_user, timestamp) values
("Derf Backderf, Annika", "Śmieci", "9788380970519", "https://ksiegarnia.proszynski.pl/grafika/okladki/duze/Smieci.jpg", 1, current_timestamp()),
("Enfield Lizzie", "Dawka życia ", "9788380972155", "https://ksiegarnia.proszynski.pl/grafika/okladki/duze/dawka.front.jpg", 1, current_timestamp()),
("Julie Lawson Timmer", "Rodzinne więzy", "9788380972216", "https://ksiegarnia.proszynski.pl/grafika/okladki/duze/Rodzinne.wiezy1.jpg", 1, current_timestamp()),
("Katarzyna Kołczewska", "Czarne narcyzy", "9788380971646", "https://ksiegarnia.proszynski.pl/grafika/okladki/duze/czarne_narcyzy.jpg", 1, current_timestamp()),
("Maria Czubaszek", "Nienachalna z urody", "9788380693500", "https://ksiegarnia.proszynski.pl/grafika/okladki/duze/Nienachalna.z.urody.jpg", 1, current_timestamp());


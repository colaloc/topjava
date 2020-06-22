DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, user_id, datetime, description, calories)
VALUES (100004, 100000, '2020-01-30 10:00:00', 'новая еда', 500),
       (2, 100000, '2020-01-30 13:00:00', 'еда для удаления', 1000),
       (3, 100000, '2020-01-30 20:00:00', 'еда до обновления', 500),
       (4, 100000, '2020-01-30 15:15:00', 'еда для получения', 666);
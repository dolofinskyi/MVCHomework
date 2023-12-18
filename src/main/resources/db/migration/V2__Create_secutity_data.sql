CREATE TABLE users (
  id IDENTITY PRIMARY KEY,
  username varchar NOT NULL,
  password varchar NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE roles (
  id IDENTITY PRIMARY KEY,
  name varchar NOT NULL
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL,
  role_id varchar NOT NULL,
  PRIMARY KEY(user_id, role_id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(role_id) REFERENCES roles(id)
);


INSERT INTO users (username, password)
VALUES ('user', 'user');

INSERT INTO roles (name)
VALUES ('USER');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
DROP TABLE IF EXISTS items CASCADE;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id    BIGINT       PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(512) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS items (
    id          BIGINT       PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    owner_id    BIGINT       NOT NULL REFERENCES users (id),
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(512) NOT NULL,
    available   BOOLEAN      NOT NULL
);
CREATE TABLE customertable(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    age INT NOT NULL,
    country TEXT NOT NULL,
    gender TEXT NOT NULL
);
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO users (username, password, role, enabled)
VALUES 
('admin', '$2a$10$EkilMzosj1c7p5kBlFA.ce8E1BbVDY5kF9XyYp41jRBXJEWQzneN2', 'ROLE_ADMIN', true)
ON CONFLICT (username) DO NOTHING;

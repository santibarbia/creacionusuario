drop table if exists users cascade;
CREATE TABLE IF NOT EXISTS USERS (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    token VARCHAR(255),
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    is_active BOOLEAN
);
drop table if exists phone cascade;
-- Crear la tabla Phone
CREATE TABLE IF NOT EXISTS phone (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID,
    number VARCHAR(20),
    city_code VARCHAR(10),
    country_code VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Insertar usuarios
INSERT INTO users (id, name, email, password, token,created, modified, last_login, is_active)
VALUES
  ('c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032', 'Juan Pérez', 'juan@example.com', 'password123', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTcwODk4MDY1NCwiZXhwIjoxNzA4OTg0MjU0fQ.QTOJS2XlCOToRCPm5gvowwTMltrjm0M7niwSUcAMInE', '2022-01-01', '2022-01-01', '2022-01-01', true),
  ('f2c12b16-cb4d-45f7-963b-02ea1ec5a3d1', 'María Rodríguez', 'maria@example.com', 'securepass', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW50aWFnbyIsImlhdCI6MTcwODk4MDY1NCwiZXhwIjoxNzA4OTg0MjU0fQ.QTOJS2XlCOToRCPm5gvowwTMltrjm0M7niwSUcAMInE','2022-01-02', '2022-01-02', '2022-01-02', true);

-- Insertar teléfonos asociados a usuarios
INSERT INTO phone (id, user_id, number, city_code, country_code)
VALUES
  (1, 'c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032', '123456789', '1', '57'),
  (2, 'c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032', '987654321', '2', '57'),
  (3, 'f2c12b16-cb4d-45f7-963b-02ea1ec5a3d1', '555555555', '3', '57');
-- Crear la tabla odontologos
CREATE TABLE IF NOT EXISTS odontologos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numeroMatricula INT UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
);
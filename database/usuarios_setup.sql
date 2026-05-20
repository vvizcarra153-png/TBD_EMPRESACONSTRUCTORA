-- Crear tabla de Usuarios
CREATE TABLE Usuarios (
    id INT PRIMARY KEY IDENTITY(1,1),
    usuario VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(50) DEFAULT 'Usuario',
    estado VARCHAR(20) DEFAULT 'Activo'
);

-- Insertar usuarios de prueba
INSERT INTO Usuarios VALUES ('admin', 'Administrador', 'admin@constructora.com', 'admin123', 'Administrador', 'Activo');
INSERT INTO Usuarios VALUES ('jperez', 'Juan Perez', 'jperez@constructora.com', '1234', 'Jefe de Proyectos', 'Activo');
INSERT INTO Usuarios VALUES ('aflores', 'Ana Flores', 'aflores@constructora.com', '1234', 'Contador', 'Activo');
INSERT INTO Usuarios VALUES ('lmamani', 'Luis Mamani', 'lmamani@constructora.com', '1234', 'Técnico', 'Inactivo');

-- Verificar datos
SELECT * FROM Usuarios;

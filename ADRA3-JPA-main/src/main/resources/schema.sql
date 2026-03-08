CREATE TABLE IF NOT EXISTS activitats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_ruta VARCHAR(255) NOT NULL,
    descripcio VARCHAR(255) NOT NULL,
    dias INT NOT NULL,
    horas INT NOT NULL,
    minuts INT NOT NULL,
    distancia INT NOT NULL,
    data_created DATETIME(6),
    date_update DATETIME(6)
);
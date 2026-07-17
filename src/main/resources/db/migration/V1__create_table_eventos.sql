CREATE TABLE Eventos (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    local VARCHAR(255),
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    capacity INTEGER,
    type VARCHAR(50)
);

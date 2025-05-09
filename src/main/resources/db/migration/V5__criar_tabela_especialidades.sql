CREATE TABLE IF NOT EXISTS especialidades (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    nome VARCHAR(150) UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    categoria CHAR(2) NOT NULL,
    tipo CHAR(1) NOT NULL DEFAULT 'M'
);
CREATE TABLE IF NOT EXISTS clientes (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    razao VARCHAR(150) NOT NULL,
    nm_completo VARCHAR(150) NOT NULL,
    tipo CHAR(2) NOT NULL DEFAULT 'PF'
);
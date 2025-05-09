CREATE TABLE IF NOT EXISTS usuarios (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    avatar VARCHAR(150) default 'default.png',
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nm_completo VARCHAR(150) NOT NULL,
    perfil VARCHAR(4) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    ativo BOOLEAN default 'false',
    principal BOOLEAN default 'false',
    dt_criado_em DATE NOT NULL DEFAULT 'now()',
    dt_atualizado_em DATE
);
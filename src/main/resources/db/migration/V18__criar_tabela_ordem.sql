CREATE TABLE IF NOT EXISTS ordens (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    cliente_id VARCHAR(150) NOT NULL,
    veiculo_id VARCHAR(150),
    estabelecimento_id VARCHAR(150) NOT NULL,
    observacoes TEXT,
    desconto INTEGER NOT NULL DEFAULT '0',
    parcelas INTEGER NOT NULL DEFAULT '0',
    dias_proposta INTEGER NOT NULL DEFAULT '0',
    valor_entrada DECIMAL(10,2),
    forma_pagamento CHAR(2) NOT NULL DEFAULT 'DI',
    tp_proposta CHAR(1) NOT NULL DEFAULT 'O',
    status CHAR(2) NOT NULL DEFAULT 'AA',
    usuario_id VARCHAR(150) NOT NULL,
    status_oficina CHAR(2),
    dt_criado_em DATE NOT NULL DEFAULT 'now()',
    dt_atualizado_em DATE,
    CONSTRAINT fk_ordem_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_ordem_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_ordem_estabelecimento FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_ordem_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
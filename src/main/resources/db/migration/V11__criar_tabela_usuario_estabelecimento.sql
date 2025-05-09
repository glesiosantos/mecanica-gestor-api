CREATE TABLE IF NOT EXISTS usuario_estabelecimento (
    usuario_id VARCHAR(150) NOT NULL,
    estabelecimento_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_usuario_estabelecimento FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_estabelecimento_usuario FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE CASCADE ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS veiculos (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    placa VARCHAR(20) NOT NULL UNIQUE,
    modelo VARCHAR(150) NOT NULL,
    ano INT NOT NULL DEFAULT '0',
    marca_id VARCHAR(150) NOT NULL,
    CONSTRAINT fk_marca_veiculo FOREIGN KEY (marca_id) REFERENCES marcas(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
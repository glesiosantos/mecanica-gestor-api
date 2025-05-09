CREATE TABLE IF NOT EXISTS ordem_pendencias (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    ordem_id VARCHAR(150) NOT NULL,
    pendencias TEXT NOT NULL,
    CONSTRAINT fk_ordem_item FOREIGN KEY (ordem_id) REFERENCES ordens(id) ON DELETE CASCADE ON UPDATE NO ACTION
);
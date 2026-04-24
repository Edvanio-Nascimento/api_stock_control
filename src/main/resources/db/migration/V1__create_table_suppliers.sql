CREATE TABLE tb_suppliers (
                              id BINARY(16) NOT NULL,
                              company_name VARCHAR(80) NOT NULL,
                              cnpj VARCHAR(18) NOT NULL,
                              contact_email VARCHAR(50) NOT NULL,
                              PRIMARY KEY (id),
                              CONSTRAINT uk_supplier_cnpj UNIQUE (cnpj)
) ENGINE=InnoDB;

-- Índice para acelerar a busca por CNPJ (findByCnpj e existsByCnpj)
-- Embora o UNIQUE já crie um índice, deixamos explícito o nome para clareza
CREATE INDEX idx_supplier_cnpj ON tb_suppliers (cnpj);

-- Índice para busca parcial por nome (findByCompanyNameContainingIgnoreCase)
-- O prefixo ajuda se a busca fosse "começa com", mas para "contém" o índice
-- melhora a performance em datasets maiores se comparado a um scan de tabela.
CREATE INDEX idx_supplier_company_name ON tb_suppliers (company_name);

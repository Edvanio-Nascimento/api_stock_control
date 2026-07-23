
CREATE TABLE tb_products (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID(), 1)),
    name VARCHAR(100) NOT NULL,
    sku VARCHAR(30) NOT NULL UNIQUE,
    price DECIMAL(18, 2) NOT NULL CHECK ( price > 0 ),
    current_stock INTEGER NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    id_category BINARY(16) NOT NULL,
    id_supplier BINARY(16) NOT NULL,

    CONSTRAINT fk_products_categories FOREIGN KEY (id_category) REFERENCES tb_categories(id),
    CONSTRAINT fk_products_suppliers FOREIGN KEY (id_supplier) REFERENCES tb_suppliers(id)
);

CREATE INDEX idx_product_name ON tb_products(name);
CREATE INDEX idx_product_active ON tb_products(active);
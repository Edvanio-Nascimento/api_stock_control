
CREATE TABLE tb_stock_movement (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID(), 1)),
    enum_type VARCHAR(20) NOT NULL,
    quantity INTEGER NOT NULL CHECK ( quantity > 0 ),
    sale_date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    justify VARCHAR(300) NOT NULL,

    id_product BINARY(16) NOT NULL,
    CHECK ( enum_type IN ('ENTRY', 'EXIT')),

    CONSTRAINT fk_stock_movement_product FOREIGN KEY (id_product) REFERENCES tb_products(id)
);

CREATE INDEX idx_stock_movement_enum_type ON tb_stock_movement(enum_type);
CREATE INDEX idx_stock_movement_sale_date_time ON tb_stock_movement(sale_date_time);
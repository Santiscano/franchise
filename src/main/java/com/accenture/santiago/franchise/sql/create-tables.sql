-- Tabla de franquicias
CREATE TABLE franchise (
   id_franchise INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   name VARCHAR(45) NOT NULL UNIQUE
);

-- Tabla de sucursales
CREATE TABLE branch (
    id_branch INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    franchise_id INTEGER NOT NULL,
    name VARCHAR(45) NOT NULL UNIQUE,
    CONSTRAINT fk_branch_franchise FOREIGN KEY (franchise_id) REFERENCES franchise(id_franchise)
);

-- Tabla de productos
CREATE TABLE product (
    id_product INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    branch_id INTEGER NOT NULL,
    name VARCHAR(45) NOT NULL,
    stock BIGINT,
    CONSTRAINT fk_product_branch FOREIGN KEY (branch_id) REFERENCES branch(id_branch),
    CONSTRAINT unique_product_name_per_branch UNIQUE (branch_id, name)
);

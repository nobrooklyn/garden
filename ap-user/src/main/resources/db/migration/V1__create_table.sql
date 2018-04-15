CREATE TABLE IF NOT EXISTS USER (
    id varchar(64),
    firstName varchar(50),
    lastName varchar(50),
    birthday date,
    height float,
    weight float,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;

INSERT INTO USER VALUES(uuid(), 'admin', 'app', cast('1980-01-01' as date), 172.0, 64.0);
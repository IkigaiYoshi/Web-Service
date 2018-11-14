CREATE TABLE IF NOT EXISTS service
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    temperature varchar(50) NOT NULL,
    coordinate varchar(255) NOT NULL
);
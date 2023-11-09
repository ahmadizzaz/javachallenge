CREATE TABLE IF NOT EXISTS postcodelatlng (
    id int NOT NULL AUTO_INCREMENT,
    postcode varchar(8) NOT NULL,
    latitude decimal(10,7) NOT NULL,
    longitude decimal(10,7) NOT NULL,
    PRIMARY KEY (id)
    );

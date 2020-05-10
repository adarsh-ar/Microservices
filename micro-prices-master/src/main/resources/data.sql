DROP TABLE IF EXISTS price;
 
CREATE TABLE price (
  id BIGINT PRIMARY KEY,
  price DOUBLE(250) NOT NULL,
  base_price DOUBLE(250) NOT NULL,
  selling_price DOUBLE(250) DEFAULT NULL
);
 
INSERT INTO price (id, price, base_price, selling_price) VALUES
  (1001, 22.0, 24.0, 28.0),
  (1002, 22.0, 24.0, 28.0),
  (1003, 22.0, 24.0, 28.0);
CREATE TABLE User (
  username VARCHAR(1024),
  firstname VARCHAR(1024) NOT NULL,
  password VARCHAR(1024) NOT NULL,
  salt VARCHAR(1024) NOT NULL,
  PRIMARY KEY (username)
);
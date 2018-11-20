create database wallethub;
use wallethub;

CREATE TABLE log (
  id INT NOT NULL AUTO_INCREMENT,
  date DATETIME NOT NULL,
  ip VARCHAR(20) NOT NULL,
  status INT(5) NOT NULL,
  userAgent TEXT NOT NULL,
  PRIMARY KEY (id)
);